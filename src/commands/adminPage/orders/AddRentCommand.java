package commands.adminPage.orders;

import commands.Command;
import dao.CarDAO;
import dao.DAOFactory;
import dao.RentDAO;
import model.CarOrder;
import model.Rent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This command add rent to order
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class AddRentCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddRentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        CarOrder order = (CarOrder) session.getAttribute("order");
        String carId = request.getParameter("carId");

        DAOFactory factory = DAOFactory.getInstance();
        RentDAO rentDao = factory.getRentDAO();
        Rent rent = null;
        try {
            rentDao.addRent(order.getId(), carId);
            rent = rentDao.getRentByOrderId(order.getId());
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        CarDAO carDao = factory.getCarDAO();
        try {
            carDao.rentCar(carId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("rent", rent);

        String path = "/jsp/adminPages/changeOrder.jsp";
        session.setAttribute("path", path);
        return path;
    }
}