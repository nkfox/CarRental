package dao;

import model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to Rent table in CarRental database.
 * Created by Nataliia Kozoriz on 24.01.2016.
 *
 * @see Rent
 * @see RentStatus
 */
public class RentDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    RentDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Add rent to database.
     *
     * @param carOrder car order number
     * @param car      car number
     * @see CarOrder
     * @see Car
     * @see RentStatus
     */
    public void addRent(int carOrder, String car) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("newRent"));

        ps.setInt(1, carOrder);
        ps.setString(2, car);

        OrderStatus status = getInitialStatus(con);
        ps.setInt(3, status.getId());
        ps.executeUpdate();

        con.close();
    }

    private RentStatus getRentStatusById(Connection con, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("rentStatusById"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();
        String statusEN = rs.getString("statusEN");
        String statusUK = rs.getString("statusUK");

        RentStatus status = new RentStatus(id, statusEN, statusUK);

        return status;
    }

    /**
     * Return list of all rent statuses
     *
     * @return list of rent statuses
     * @see RentStatus
     */
    public List<RentStatus> getAllStatuses() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allRentStatuses"));
        ResultSet rs = ps.executeQuery();

        List<RentStatus> statuses = new ArrayList();
        while (rs.next()) {
            int id = rs.getInt("id");
            String statusEN = rs.getString("statusEN");
            String statusUK = rs.getString("statusUK");

            RentStatus status = new RentStatus(id, statusEN, statusUK);
            statuses.add(status);
        }
        return statuses;
    }

    /**
     * Return list of client rents
     *
     * @param client client which rents is needed to get
     * @return list of rents
     * @see Rent
     * @see Client
     */
    public List<Rent> getRentsByClient(Client client) throws SQLException {
        List<Rent> rents = new ArrayList();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("rentsByClientId"));
        ps.setString(1, client.getId());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int carOrder = rs.getInt("carOrder");
            String carId = rs.getString("car");
            int statusId = rs.getInt("status");
            RentStatus status = getRentStatusById(con, statusId);

            DAOFactory factory = DAOFactory.getInstance();
            CarDAO carDao = factory.getCarDAO();
            Car car = carDao.getCarById(carId);

            Rent rent = new Rent(id, carOrder, car, status);
            rents.add(rent);
        }
        con.close();
        return rents;
    }

    /**
     * Return rent by order id
     *
     * @param order car order number
     * @return rent
     * @see Rent
     * @see CarOrder
     */
    public Rent getRentByOrderId(int order) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("rentByOrderId"));
        ps.setInt(1, order);
        ResultSet rs = ps.executeQuery();

        Rent rent = null;
        if (rs.next()) {
            int id = rs.getInt("id");
            String carId = rs.getString("car");
            int statusId = rs.getInt("status");
            RentStatus status = getRentStatusById(con, statusId);

            DAOFactory factory = DAOFactory.getInstance();
            CarDAO carDao = factory.getCarDAO();
            Car car = carDao.getCarById(carId);

            rent = new Rent(id, order, car, status);
        }
        con.close();
        return rent;
    }

    private OrderStatus getInitialStatus(Connection con) throws SQLException {
        return getStatusByName(con, "In rent");
    }

    private OrderStatus getStatusByName(Connection con, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("statusByName"));
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int id = rs.getInt("id");
        String statusEN = rs.getString("statusEN");
        String statusUK = rs.getString("statusUK");

        OrderStatus status = new OrderStatus(id, statusEN, statusUK);

        return status;
    }

    /**
     * Update status of rent
     *
     * @param id     rent number
     * @param status new status number
     * @return true if rent status has been changed
     * @see Rent
     */
    public boolean updateRentStatus(int id, int status) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("updateRentStatus"));

        ps.setInt(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();

        OrderStatus statusBroken = getStatusByName(con, "Returned broken");

        con.close();

        return statusBroken.getId() == status;
    }

}
