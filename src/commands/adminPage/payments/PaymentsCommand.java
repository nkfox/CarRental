package commands.adminPage.payments;

import commands.Command;
import dao.DAOFactory;
import dao.PaymentDAO;
import model.Payment;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows all payments
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class PaymentsCommand implements Command {

    private static final Logger logger = Logger.getLogger(PaymentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String path = "/jsp/adminPages/payments.jsp";
        session.setAttribute("path", path);

        List<Payment> payments = new ArrayList<>();

        DAOFactory factory = DAOFactory.getInstance();
        PaymentDAO paymentDao = factory.getPaymentDAO();
        try {
            payments = paymentDao.getAllPayments();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("payments", payments);

        return path;
    }

}
