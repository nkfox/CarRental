package dao;

import model.Client;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to Client table in CarRental database.
 * Created by Nataliia Kozoriz on 17.01.2016.
 *
 * @see Client
 */
public class ClientDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    ClientDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Checks if client is admin
     *
     * @param id client passport number
     * @return true if client is admin
     * @see Client
     */
    public boolean isAdmin(String id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("admin"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        boolean res = rs.next();
        con.close();
        return res;
    }

    /**
     * Checks if client is exist in database
     *
     * @param id client passport number
     * @return true if client is exist
     * @see Client
     */
    public boolean isClientExist(String id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("client"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        boolean res = rs.next();
        con.close();
        return res;
    }

    /**
     * Returns list of all clients
     *
     * @return list of clients
     * @see Client
     */
    public List<Client> getAllClients() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allClients"));
        ResultSet rs = ps.executeQuery();

        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String nameSurnameEN = rs.getString("nameSurnameEN");
            String nameSurnameUK = rs.getString("nameSurnameUK");
            String mail = rs.getString("mail");
            String driverLicense = rs.getString("driverLicense");
            String telephone = rs.getString("telephone");
            String password = rs.getString("password");
            boolean admin = isAdmin(id);
            Client client = new Client(id, nameSurnameEN, nameSurnameUK, mail, driverLicense, telephone, admin, password);
            clients.add(client);
        }
        con.close();
        return clients;
    }

    /**
     * Returns list of clients that have giver string as passport number or name
     *
     * @param search string to search in passport number or name
     * @return list of clients
     * @see Client
     */
    public List<Client> getClientsByPassportOrName(String search) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("clientsByPassportOrName"));
        ps.setString(1, search);
        ps.setString(2, search);
        ps.setString(3, search);
        ResultSet rs = ps.executeQuery();

        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String nameSurnameEN = rs.getString("nameSurnameEN");
            String nameSurnameUK = rs.getString("nameSurnameUK");
            String mail = rs.getString("mail");
            String driverLicense = rs.getString("driverLicense");
            String telephone = rs.getString("telephone");
            String password = rs.getString("password");
            boolean admin = isAdmin(id);
            Client client = new Client(id, nameSurnameEN, nameSurnameUK, mail, driverLicense, telephone, admin, password);
            clients.add(client);
        }
        con.close();
        return clients;
    }

    /**
     * Returns client by his passport and password
     *
     * @param id       client passport number
     * @param password client password
     * @return client
     * @see Client
     */
    public Client clientByIdPass(String id, String password) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("clientByIdPass"));
        ps.setString(1, id);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nameSurnameEN = rs.getString("nameSurnameEN");
            String nameSurnameUK = rs.getString("nameSurnameUK");
            String mail = rs.getString("mail");
            String driverLicense = rs.getString("driverLicense");
            String telephone = rs.getString("telephone");
            boolean admin = isAdmin(id);
            Client client = new Client(id, nameSurnameEN, nameSurnameUK, mail, driverLicense, telephone, admin, password);
            con.close();
            return client;
        }
        con.close();
        return null;
    }

    private boolean isAdmin(String id, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("admin"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        boolean res = rs.next();
        return res;
    }

    /**
     * Returns client by his passport
     *
     * @param id client passport number
     * @return client
     * @see Client
     */
    public Client clientById(String id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("clientById"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Client client = null;
        if (rs.next()) {
            String nameSurnameEN = rs.getString("nameSurnameEN");
            String nameSurnameUK = rs.getString("nameSurnameUK");
            String mail = rs.getString("mail");
            String driverLicense = rs.getString("driverLicense");
            String telephone = rs.getString("telephone");
            String password = rs.getString("password");
            boolean admin = isAdmin(id, con);
            client = new Client(id, nameSurnameEN, nameSurnameUK, mail, driverLicense, telephone, admin, password);
        }
        con.close();
        return client;
    }

    /**
     * Add client to database
     *
     * @param client client to add
     * @see Client
     */
    public void addClient(Client client) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("newClient"));
        ps.setString(1, client.getId());
        ps.setString(2, client.getNameSurnameEN());
        ps.setString(3, client.getNameSurnameUK());
        ps.setString(4, client.getMail());
        ps.setString(5, client.getDriverLicense());
        ps.setString(6, client.getTelephone());
        ps.setString(7, client.getPassword());
        ps.execute();
        con.close();
    }

    /**
     * Update client info
     *
     * @param client client which info is updating
     * @see Client
     */
    public void updateClientInfo(Client client) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("update"));
        ps.setString(1, client.getNameSurnameEN());
        ps.setString(2, client.getNameSurnameUK());
        ps.setString(3, client.getMail());
        ps.setString(4, client.getDriverLicense());
        ps.setString(5, client.getTelephone());
        ps.setString(6, client.getPassword());
        ps.setString(7, client.getId());
        ps.execute();
        con.close();
    }

    /**
     * Make client admin or delete him from admin table
     *
     * @param id    client passport number
     * @param admin if true make client admin, otherwise delete him from admin table
     * @see Client
     */
    public void makeAdmin(String id, boolean admin) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps;
        if (admin) ps = con.prepareStatement(resourceBundle.getString("makeAdmin"));
        else
            ps = con.prepareStatement(resourceBundle.getString("deleteAdmin"));
        ps.setString(1, id);
        ps.executeUpdate();
        con.close();
    }
}
