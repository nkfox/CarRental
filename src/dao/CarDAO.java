package dao;

import model.Car;
import model.CarState;
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
 * This class process with queries to Car table in CarRental database.
 * Created by Nataliia Kozoriz on 24.01.2016.
 *
 * @see Car
 * @see CarState
 */
public class CarDAO {

    /**
     * Path to database
     */
    DataSource ds;

    /**
     * Resource where all sql statements are
     */
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLStatements");

    CarDAO(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Returns car by its id number
     *
     * @param id car number.
     * @return car
     */
    public Car getCarById(String id) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("carById"));
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        Car car = null;
        List<Car> cars = getResultListOfCars(rs, con);
        if (!cars.isEmpty())
            car = cars.get(0);

        con.close();
        return car;
    }

    /**
     * Change car status to 'in rent'
     *
     * @param id car number.
     */
    public void rentCar(String id) throws SQLException {
        Connection con = ds.getConnection();
        CarState state = getStateByName("In rent", con);
        con.close();
        updateCarState(id, state.getId());
    }

    /**
     * Change car state
     *
     * @param id    car number.
     * @param state new state id
     */
    public void updateCarState(String id, int state) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("updateCarState"));
        ps.setInt(1, state);
        ps.setString(2, id);
        ps.executeUpdate();
        con.close();
    }

    private List<Car> getResultListOfCars(ResultSet rs, Connection con) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        PlaceDAO placeDao = factory.getPlaceDAO();

        List<Car> cars = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String modelEN = rs.getString("modelEN");
            String modelUK = rs.getString("modelUK");
            String carGroup = rs.getString("carGroup");
            int placeId = rs.getInt("place");
            int stateId = rs.getInt("carState");

            Place place = placeDao.getPlaceById(placeId);
            CarState state = getStateById(stateId, con);

            Car car = new Car(id, modelEN, modelUK, carGroup, place, state);
            cars.add(car);
        }
        return cars;
    }

    /**
     * Returns list of all cars
     *
     * @return list of all cars
     */
    public List<Car> getAllCars() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allCars"));
        ResultSet rs = ps.executeQuery();
        List<Car> cars = getResultListOfCars(rs, con);
        con.close();
        return cars;
    }

    /**
     * Returns list of vacant cars that is of given car group and is in giver location
     *
     * @param group car group id
     * @param place location
     * @return list of cars
     * @see Car
     */
    public List<Car> getVacantCarsByGroupPlace(String group, int place) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("carsByGroupPlaceState"));
        CarState state = getStateByName("Vacant", con);
        ps.setString(1, group);
        ps.setInt(2, place);
        ps.setInt(3, state.getId());
        ResultSet rs = ps.executeQuery();
        List<Car> cars = getResultListOfCars(rs, con);
        con.close();
        return cars;
    }

    /**
     * Returns list of cars that has given string as id,group id or model in English or Ukrainian
     *
     * @param search string that is compared with car id, group id or model
     * @return list of cars
     * @see Car
     */
    public List<Car> getCarsByIdOrGroupOrModel(String search) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("carsByIdOrGroupOrModel"));
        ps.setString(1, search);
        ps.setString(2, search);
        ps.setString(3, search);
        ps.setString(4, search);
        ResultSet rs = ps.executeQuery();
        List<Car> cars = getResultListOfCars(rs, con);
        con.close();
        return cars;
    }

    private CarState getStateById(int id, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("stateById"));
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        CarState state = null;
        if (rs.next()) {
            String statusEN = rs.getString("stateEN");
            String statusUK = rs.getString("stateUK");

            state = new CarState(id, statusEN, statusUK);
        }
        return state;
    }

    /**
     * Returns list of all car statuses
     *
     * @return list of car statuses
     * @see CarState
     */
    public List<CarState> getAllCarStates() throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("allStates"));
        ResultSet rs = ps.executeQuery();

        List<CarState> states = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String statusEN = rs.getString("stateEN");
            String statusUK = rs.getString("stateUK");

            CarState state = new CarState(id, statusEN, statusUK);
            states.add(state);
        }
        return states;
    }

    private CarState getStateByName(String name, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(resourceBundle.getString("stateByName"));
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        CarState state = null;
        if (rs.next()) {
            int id = rs.getInt("id");
            String statusEN = rs.getString("stateEN");
            String statusUK = rs.getString("stateUK");
            state = new CarState(id, statusEN, statusUK);
        }
        return state;
    }
}
