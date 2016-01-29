package commands.adminPage.orders;

import commands.Command;
import dao.CarOrderDAO;
import dao.DAOFactory;
import dao.PaymentDAO;
import dao.RentDAO;
import model.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command change order. Shows all info about order, its rent and payments
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class ChangeOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String orderId = request.getParameter("orderId");

        List<OrderStatus> statuses = new ArrayList<>();

        DAOFactory factory = DAOFactory.getInstance();
        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            statuses = carOrderDao.getAllStatuses();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        CarOrder order = null;
        try {
            order = carOrderDao.getOrderById(Integer.valueOf(orderId));
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        List<Payment> payments = new ArrayList();
        List<PaymentName> paymentNames = new ArrayList();
        PaymentDAO paymentDao = factory.getPaymentDAO();
        try {
            payments = paymentDao.getPaymentsByOrderId(order.getId());
            paymentNames = paymentDao.getAllNames();

        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        Rent rent = null;
        List<RentStatus> rentStatuses = new ArrayList();
        RentDAO rentDao = factory.getRentDAO();
        try {
            rent = rentDao.getRentByOrderId(order.getId());
            rentStatuses = rentDao.getAllStatuses();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("statuses", statuses);
        session.setAttribute("orderId", orderId);
        session.setAttribute("order", order);
        session.setAttribute("payments", payments);
        session.setAttribute("paymentNames", paymentNames);
        session.setAttribute("rent", rent);
        session.setAttribute("rentStatuses", rentStatuses);

        String path = "/jsp/adminPages/changeOrder.jsp";
        session.setAttribute("path", path);
        return path;
    }
}