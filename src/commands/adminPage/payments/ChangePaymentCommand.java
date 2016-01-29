package commands.adminPage.payments;

import commands.Command;
import dao.DAOFactory;
import dao.PaymentDAO;
import model.Payment;
import model.PaymentName;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * This command changes payment status and state (paid or not)
 * Created by Nataliia Kozoriz on 27.01.2016.
 */
public class ChangePaymentCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangePaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        int paymentId = Integer.valueOf(request.getParameter("paymentId"));
        String paidString = request.getParameter("paidStatus");
        boolean paid = "on".equals(paidString);
        int paymentNameId = Integer.valueOf(request.getParameter("paymentNameId"));

        DAOFactory factory = DAOFactory.getInstance();
        PaymentDAO paymentDao = factory.getPaymentDAO();

        PaymentName paymentName = null;
        List<Payment> payments = (List<Payment>) session.getAttribute("payments");
        try {
            paymentName = paymentDao.getNameById(paymentNameId);
            paymentDao.updatePayment(paymentId, paid, paymentNameId);
            updatePayment(payments, paymentId, paid, paymentName);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        return (String) session.getAttribute("path");
    }

    private void updatePayment(List<Payment> payments, int paymentId, boolean paid, PaymentName paymentName) {
        for (Payment payment : payments) {
            if (payment.getId() == paymentId) {
                payment.setPaid(paid);
                payment.setPaymentName(paymentName);
            }
        }
    }
}