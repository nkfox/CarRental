package commands.adminPage.orders;

import commands.Command;
import dao.CarDAO;
import dao.DAOFactory;
import model.Car;
import model.CarOrder;
import model.CarState;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command chooses car for rent
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class ChooseCarCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChooseCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        CarOrder order = (CarOrder) session.getAttribute("order");

        List<Car> cars = new ArrayList<>();
        List<CarState> states = new ArrayList<>();
        DAOFactory factory = DAOFactory.getInstance();
        CarDAO carDao = factory.getCarDAO();
        try {
            cars = carDao.getVacantCarsByGroupPlace(order.getCarGroup().getId(), order.getStartPlace().getId());
            states = carDao.getAllCarStates();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("cars", cars);
        session.setAttribute("states", states);

        String path = "/jsp/adminPages/chooseCar.jsp";
        session.setAttribute("path", path);
        return path;
    }
}