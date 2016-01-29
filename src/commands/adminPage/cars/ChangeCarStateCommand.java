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
import java.util.List;

/**
 * This command change state of chosen car
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class ChangeCarStateCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeCarStateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String carId = request.getParameter("carId");
        int carStateId = Integer.valueOf(request.getParameter("carStateId"));
        List<Car> cars = (List<Car>) session.getAttribute("cars");
        List<CarState> states = (List<CarState>) session.getAttribute("states");

        DAOFactory factory = DAOFactory.getInstance();
        CarDAO carDao = factory.getCarDAO();
        try {
            carDao.updateCarState(carId, carStateId);
            updateCarState(cars, states, carId, carStateId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("cars", cars);

        String path = "/jsp/adminPages/cars.jsp";
        session.setAttribute("path", path);
        return path;
    }

    private void updateCarState(List<Car> cars, List<CarState> states, String id, int state) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                car.setState(getStateById(states, state));
            }
        }
    }

    private CarState getStateById(List<CarState> states, int state) {
        for (CarState carState : states) {
            if (carState.getId() == (state)) {
                return carState;
            }
        }
        return null;
    }
}