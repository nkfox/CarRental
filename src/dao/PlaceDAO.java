package dao;

import model.Place;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class process with queries to Place table in CarRental database.
 * Created by Nataliia Kozoriz on 23.01.2016.
 */
public class PlaceDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    PlaceDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Returns list of all places
     *
     * @return list of places
     * @see Place
     */
    public List<Place> getAll() throws SQLException {
        List<Place> places = new ArrayList<>();
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allPlaces"));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String cityEN = rs.getString("cityEN");
            String cityUK = rs.getString("cityUK");
            String addressEN = rs.getString("addressEN");
            String addressUK = rs.getString("addressUK");
            Place place = new Place(id, cityEN, cityUK, addressEN, addressUK);
            places.add(place);
        }
        con.close();
        return places;
    }

    /**
     * Returns place by its number
     *
     * @param id place number
     * @return place
     * @see Place
     */
    public Place getPlaceById(int id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("placeById"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String cityEN = rs.getString("cityEN");
            String cityUK = rs.getString("cityUK");
            String addressEN = rs.getString("addressEN");
            String addressUK = rs.getString("addressUK");
            con.close();
            return new Place(id, cityEN, cityUK, addressEN, addressUK);
        }

        con.close();
        return null;
    }
}
