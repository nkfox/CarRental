package commands.adminPage.orders;

import commands.Command;
import dao.DAOFactory;
import dao.PaymentDAO;
import model.CarOrder;
import model.Payment;
import model.PaymentName;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command is triggered when car has returned broken.
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class PayForRepairCommand implements Command {

    private static final Logger logger = Logger.getLogger(PayForRepairCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        int orderId = ((CarOrder) session.getAttribute("order")).getId();
        double price = Double.valueOf(request.getParameter("price"));
        int nameId = Integer.valueOf(request.getParameter("paymentNameId"));

        DAOFactory factory = DAOFactory.getInstance();
        PaymentDAO paymentDao = factory.getPaymentDAO();
        PaymentName paymentName = null;
        try {
            paymentName = paymentDao.getNameById(nameId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        Payment payment = new Payment(orderId,price,paymentName);

        List<Payment> payments = new ArrayList();
        try {
            paymentDao.addPayment(payment);
            payments = paymentDao.getPaymentsByOrderId(orderId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("payments",payments);

        String path = "/jsp/adminPages/changeOrder.jsp";
        session.setAttribute("path", path);

        return path;
    }
}