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
 * This command shows cars searched by id, group or model
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class SearchCarsCommand implements Command {

    private static final Logger logger = Logger.getLogger(SearchCarsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        String search = request.getParameter("search");

        List<Car> cars = new ArrayList<>();
        List<CarState> states = new ArrayList<>();
        DAOFactory factory = DAOFactory.getInstance();
        CarDAO carDao = factory.getCarDAO();
        try {
            cars = carDao.getCarsByIdOrGroupOrModel(search);
            states = carDao.getAllCarStates();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("cars", cars);
        session.setAttribute("states", states);
        session.setAttribute("search", search);

        String path = "/jsp/adminPages/cars.jsp";
        session.setAttribute("path", path);
        return path;
    }
}