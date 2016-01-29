package commands.adminPage.cars;

import commands.Command;
import dao.CarDAO;
import dao.DAOFactory;
import model.Car;
import model.CarState;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows all cars
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class CarsCommand implements Command {

    private static final Logger logger = Logger.getLogger(CarsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        List<Car> cars = new ArrayList<>();
        List<CarState> states = new ArrayList<>();
        DAOFactory factory = DAOFactory.getInstance();
        CarDAO carDao = factory.getCarDAO();
        try {
            cars = carDao.getAllCars();
            states = carDao.getAllCarStates();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("cars", cars);
        session.setAttribute("states", states);

        return "/jsp/adminPages/cars.jsp";
    }
}