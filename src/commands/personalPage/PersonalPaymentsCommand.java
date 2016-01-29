package commands.personalPage;

import commands.Command;
import dao.DAOFactory;
import dao.PaymentDAO;
import model.Client;
import model.Payment;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows all payments of client
 * Created by Nataliia Kozoriz on 24.01.2016.
 */
public class PersonalPaymentsCommand implements Command {

    private static final Logger logger = Logger.getLogger(PersonalPaymentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("path", "/jsp/clientPages/personalPayments.jsp");

        List<Payment> payments = new ArrayList<>();
        Client client = (Client) session.getAttribute("client");

        DAOFactory factory = DAOFactory.getInstance();
        PaymentDAO paymentDao = factory.getPaymentDAO();
        try {
            payments = paymentDao.getPaymentsByClient(client);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("payments", payments);

        return "/jsp/clientPages/personalPayments.jsp";
    }

}
