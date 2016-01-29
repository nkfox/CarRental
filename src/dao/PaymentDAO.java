package dao;

import model.CarOrder;
import model.Client;
import model.Payment;
import model.PaymentName;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to Payment table in CarRental database.
 * Created by Nataliia Kozoriz on 18.01.2016.
 *
 * @see Payment
 * @see PaymentName
 */
public class PaymentDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    PaymentDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Add payment to database
     *
     * @param payment payment to add
     * @see Payment
     */
    public void addPayment(Payment payment) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("newPayment"));

        if (payment.getPaymentName() == null)
            payment.setPaymentName(getName(con, "Rent"));

        ps.setInt(1, payment.getCarOrder());
        ps.setDouble(2, payment.getPrice());
        ps.setInt(3, payment.getPaymentName().getId());
        ps.setInt(4, payment.isPaid() ? 1 : 0);

        ps.execute();
        con.close();
    }

    /**
     * Update payment to database
     *
     * @param id     payment number
     * @param paid   true if payment is paid
     * @param nameId payment type
     * @see Payment
     */
    public void updatePayment(int id, boolean paid, int nameId) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("updatePayment"));

        ps.setInt(1, nameId);
        ps.setInt(2, paid ? 1 : 0);
        ps.setInt(3, id);

        ps.execute();
        con.close();
    }

    private PaymentName getName(Connection con, String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("paymentByName"));
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int id = rs.getInt("id");
        String nameEN = rs.getString("nameEN");
        String nameUK = rs.getString("nameUK");

        PaymentName paymentName = new PaymentName(id, nameEN, nameUK);
        return paymentName;
    }

    /**
     * Returns payment name by its id
     *
     * @param id payment name number
     * @return payment name
     * @see PaymentName
     */
    public PaymentName getNameById(int id) throws SQLException {
        Connection con = ds.getConnection();
        PaymentName paymentName = getNameById(con, id);
        con.close();
        return paymentName;
    }

    private PaymentName getNameById(Connection con, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("paymentByNameId"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();
        String nameEN = rs.getString("nameEN");
        String nameUK = rs.getString("nameUK");

        PaymentName paymentName = new PaymentName(id, nameEN, nameUK);

        return paymentName;
    }

    /**
     * Returns list of all payment names
     *
     * @return list of payment names
     * @see PaymentName
     */
    public List<PaymentName> getAllNames() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allPaymentNames"));
        ResultSet rs = ps.executeQuery();

        List<PaymentName> paymentNames = new ArrayList();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameEN = rs.getString("nameEN");
            String nameUK = rs.getString("nameUK");

            PaymentName paymentName = new PaymentName(id, nameEN, nameUK);
            paymentNames.add(paymentName);
        }
        con.close();
        return paymentNames;
    }

    /**
     * Returns list of  payments of giver client
     *
     * @param client client which payments are needed
     * @return list of payments
     * @see Payment
     */
    public List<Payment> getPaymentsByClient(Client client) throws SQLException {
        List<Payment> payments = new ArrayList();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("paymentsByClientId"));
        ps.setString(1, client.getId());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int carOrder = rs.getInt("carOrder");
            double price = rs.getDouble("price");
            int nameId = rs.getInt("name");
            boolean paid = (rs.getInt("paid")) != 0 ? true : false;
            PaymentName name = getNameById(con, nameId);

            Payment payment = new Payment(id, carOrder, price, name, paid);
            payments.add(payment);
        }
        con.close();
        return payments;
    }

    /**
     * Returns list of payment which contains given integer as id or order number
     *
     * @param searchId integer to search
     * @return list of payments
     * @see Payment
     */
    public List<Payment> getPaymentsByIdOrOrder(int searchId) throws SQLException {
        List<Payment> payments = new ArrayList();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("paymentsByIdOrOrder"));
        ps.setInt(1, searchId);
        ps.setInt(2, searchId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int carOrder = rs.getInt("carOrder");
            double price = rs.getDouble("price");
            int nameId = rs.getInt("name");
            boolean paid = (rs.getInt("paid")) != 0 ? true : false;
            PaymentName name = getNameById(con, nameId);

            Payment payment = new Payment(id, carOrder, price, name, paid);
            payments.add(payment);
        }
        con.close();
        return payments;
    }

    /**
     * Returns order id by payment that is connected to it
     *
     * @param id payment number
     * @return order id
     * @see Payment
     * @see CarOrder
     */
    public int getOrderIdByPayment(int id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("orderIdByPayment"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        int order = 1;
        if (rs.next()) {
            order = rs.getInt("carOrder");
        }
        con.close();
        return order;
    }

    /**
     * Returns list of all payments
     *
     * @return list of payments
     * @see Payment
     */
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allPayments"));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int carOrder = rs.getInt("carOrder");
            double price = rs.getDouble("price");
            int nameId = rs.getInt("name");
            boolean paid = (rs.getInt("paid")) != 0 ? true : false;
            PaymentName name = getNameById(con, nameId);

            Payment payment = new Payment(id, carOrder, price, name, paid);
            payments.add(payment);
        }
        con.close();
        return payments;
    }

    /**
     * Returns list of payment which are connected with giver order
     *
     * @param order number of order
     * @return list of payments
     * @see Payment
     * @see CarOrder
     */
    public List<Payment> getPaymentsByOrderId(int order) throws SQLException {
        List<Payment> payments = new ArrayList();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("paymentsByOrderId"));
        ps.setInt(1, order);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            double price = rs.getDouble("price");
            int nameId = rs.getInt("name");
            PaymentName name = getNameById(con, nameId);
            boolean paid = (rs.getInt("paid")) != 0 ? true : false;

            Payment payment = new Payment(id, order, price, name, paid);
            payments.add(payment);
        }
        con.close();
        return payments;
    }
}
