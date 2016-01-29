package dao;

import model.CarGroup;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to CarGroup table in CarRental database.
 * Created by Nataliia Kozoriz on 17.01.2016.
 *
 * @see CarGroup
 */
public class CarGroupDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    CarGroupDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Returns list of all car groups
     *
     * @return list of car groups
     * @see CarGroup
     */
    public List<CarGroup> getAll() throws SQLException {
        List<CarGroup> carGroups = new ArrayList<>();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allCarGroups"));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            int capacity = rs.getInt("capacity");
            int carBody = rs.getInt("carBody");
            String transmission = rs.getString("transmission");

            if (transmission.equals("m"))
                transmission = "manual";
            else
                transmission = "automatic";

            int price = rs.getInt("price");
            CarGroup carGroup = new CarGroup(id, capacity, carBody, transmission, price);
            carGroups.add(carGroup);
        }
        con.close();
        return carGroups;
    }

    /**
     * Returns car group by its id
     *
     * @param id car group id
     * @return car group
     * @see CarGroup
     */
    public CarGroup getCarGroupById(String id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("carGroupById"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        CarGroup carGroup = new CarGroup();
        if (rs.next()) {
            String newId = rs.getString("id");
            int capacity = rs.getInt("capacity");
            int carBody = rs.getInt("carBody");
            String transmission = rs.getString("transmission");

            if (transmission.equals("m"))
                transmission = "manual";
            else
                transmission = "automatic";

            int price = rs.getInt("price");
            carGroup = new CarGroup(newId, capacity, carBody, transmission, price);
        }

        con.close();
        return carGroup;
    }

}
