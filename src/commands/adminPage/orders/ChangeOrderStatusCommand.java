package commands.adminPage.orders;

import commands.Command;
import dao.CarOrderDAO;
import dao.DAOFactory;
import model.CarOrder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This command changer status of order
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class ChangeOrderStatusCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeOrderStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        int orderId = ((CarOrder) session.getAttribute("order")).getId();
        int status = Integer.valueOf(request.getParameter("statusId"));
        String comment = request.getParameter("comment");

        DAOFactory factory = DAOFactory.getInstance();
        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            carOrderDao.updateOrderStatus(orderId, status, comment);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        CarOrder order = null;

        try {
            order = carOrderDao.getOrderById(orderId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("order", order);

        return (String) session.getAttribute("path");
    }
}