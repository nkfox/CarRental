package dao;

import model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to CarOrder table in CarRental database.
 * Created by Nataliia Kozoriz on 18.01.2016.
 *
 * @see CarOrder
 * @see OrderStatus
 */
public class CarOrderDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    CarOrderDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Add order to database. Set id of inserted order
     *
     * @param carOrder car order to add
     */
    public void addOrder(CarOrder carOrder) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("newOrder"), Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, carOrder.getClient().getId());
        ps.setString(2, carOrder.getCarGroup().getId());
        ps.setString(3, carOrder.getStartDate().toString());
        ps.setString(4, carOrder.getEndDate().toString());
        ps.setInt(5, carOrder.getStartPlace().getId());
        ps.setInt(6, carOrder.getEndPlace().getId());

        OrderStatus status = getInitialStatus(con);
        ps.setInt(7, status.getId());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            con.close();
            throw new SQLException("Creation failed, no rows affected.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                carOrder.setId((int) generatedKeys.getLong(1));
            } else {
                con.close();
                throw new SQLException("Creation failed, no ID obtained.");
            }
        }
        con.close();
    }

    /**
     * Change order status to 'canceled'
     *
     * @param id car order number
     */
    public void cancelOrder(int id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("cancelOrder"));

        OrderStatus status = getCanceledStatus(con);
        ps.setInt(1, status.getId());
        ps.setInt(2, id);
        ps.executeUpdate();

        con.close();
    }

    /**
     * Change order status
     *
     * @param id      order number
     * @param status  new status number
     * @param comment reason of changing
     */
    public void updateOrderStatus(int id, int status, String comment) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("updateOrderStatus"));

        ps.setInt(1, status);
        ps.setString(2, comment);
        ps.setInt(3, id);
        ps.executeUpdate();

        con.close();
    }

    private OrderStatus getInitialStatus(Connection con) throws SQLException {
        return getStatusByName(con, "Registered");
    }

    private OrderStatus getCanceledStatus(Connection con) throws SQLException {
        return getStatusByName(con, "Canceled");
    }

    private OrderStatus getStatusByName(Connection con, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("initialStatus"));
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int id = rs.getInt("id");
        String statusEN = rs.getString("statusEN");
        String statusUK = rs.getString("statusUK");

        return new OrderStatus(id, statusEN, statusUK);
    }

    private OrderStatus getStatusById(int id, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("statusById"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();
        String statusEN = rs.getString("statusEN");
        String statusUK = rs.getString("statusUK");

        return new OrderStatus(id, statusEN, statusUK);
    }

    /**
     * Returns list of all order statuses
     *
     * @return list of order statuses
     * @see OrderStatus
     */
    public List<OrderStatus> getAllStatuses() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allStatuses"));
        ResultSet rs = ps.executeQuery();

        List<OrderStatus> statuses = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String statusEN = rs.getString("statusEN");
            String statusUK = rs.getString("statusUK");

            OrderStatus status = new OrderStatus(id, statusEN, statusUK);
            statuses.add(status);
        }
        return statuses;
    }

    /**
     * Returns amount of rent days of giver order
     *
     * @param id order number
     * @return days amount
     */
    public int getPeriod(int id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("periodByOrderId"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int period = rs.getInt("period");
            con.close();
            return period;
        }
        con.close();
        return 0;
    }

    /**
     * Returns all orders of client
     *
     * @param client client for searching his orders
     * @return list of orders
     * @see CarOrder
     */
    public List<CarOrder> getOrdersByClient(Client client) throws SQLException {
        List<CarOrder> orders = new ArrayList<>();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("ordersByClientId"));
        ps.setString(1, client.getId());
        ResultSet rs = ps.executeQuery();

        DAOFactory factory = DAOFactory.getInstance();
        CarGroupDAO carGroupDao = factory.getCarGroupDAO();
        PlaceDAO placeDao = factory.getPlaceDAO();


        while (rs.next()) {
            int id = rs.getInt("id");
            String carGroupId = rs.getString("carGroup");
            Date startDate = rs.getDate("startDate");
            Date endDate = rs.getDate("endDate");
            int startPlaceId = rs.getInt("startPlace");
            int endPlaceId = rs.getInt("endPlace");
            int statusId = rs.getInt("status");
            String comment = rs.getString("comment");

            CarGroup carGroup = carGroupDao.getCarGroupById(carGroupId);
            Place startPlace = placeDao.getPlaceById(startPlaceId);
            Place endPlace = placeDao.getPlaceById(endPlaceId);
            OrderStatus status = getStatusById(statusId, con);

            CarOrder order = new CarOrder(id, client, carGroup, startDate, endDate, startPlace, endPlace, status, comment);
            orders.add(order);
        }
        con.close();
        return orders;
    }

    /**
     * Returns order by its id
     *
     * @param id order id
     * @return order
     * @see CarOrder
     */
    public CarOrder getOrderById(int id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("ordersById"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();
        CarGroupDAO carGroupDao = factory.getCarGroupDAO();
        PlaceDAO placeDao = factory.getPlaceDAO();

        CarOrder order = null;
        if (rs.next()) {
            String clientId = rs.getString("client");
            String carGroupId = rs.getString("carGroup");
            Date startDate = rs.getDate("startDate");
            Date endDate = rs.getDate("endDate");
            int startPlaceId = rs.getInt("startPlace");
            int endPlaceId = rs.getInt("endPlace");
            int statusId = rs.getInt("status");
            String comment = rs.getString("comment");

            Client client = clientDao.clientById(clientId);
            CarGroup carGroup = carGroupDao.getCarGroupById(carGroupId);
            Place startPlace = placeDao.getPlaceById(startPlaceId);
            Place endPlace = placeDao.getPlaceById(endPlaceId);
            OrderStatus status = getStatusById(statusId, con);

            order = new CarOrder(id, client, carGroup, startDate, endDate, startPlace, endPlace, status, comment);
        }
        con.close();
        return order;
    }

    /**
     * Returns list of all orders
     *
     * @return list of orders
     * @see CarOrder
     */
    public List<CarOrder> getAllOrders() throws SQLException {
        List<CarOrder> orders = new ArrayList<>();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allOrders"));
        ResultSet rs = ps.executeQuery();

        DAOFactory factory = DAOFactory.getInstance();
        CarGroupDAO carGroupDao = factory.getCarGroupDAO();
        ClientDAO clientDao = factory.getClientDAO();
        PlaceDAO placeDao = factory.getPlaceDAO();


        while (rs.next()) {
            int id = rs.getInt("id");
            String clientId = rs.getString("client");
            String carGroupId = rs.getString("carGroup");
            Date startDate = rs.getDate("startDate");
            Date endDate = rs.getDate("endDate");
            int startPlaceId = rs.getInt("startPlace");
            int endPlaceId = rs.getInt("endPlace");
            int statusId = rs.getInt("status");
            String comment = rs.getString("comment");

            Client client = clientDao.clientById(clientId);
            CarGroup carGroup = carGroupDao.getCarGroupById(carGroupId);
            Place startPlace = placeDao.getPlaceById(startPlaceId);
            Place endPlace = placeDao.getPlaceById(endPlaceId);
            OrderStatus status = getStatusById(statusId, con);

            CarOrder order = new CarOrder(id, client, carGroup, startDate, endDate, startPlace, endPlace, status, comment);
            orders.add(order);
        }
        con.close();
        return orders;
    }
}

