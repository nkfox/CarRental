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
 * This command shows payments searched by id or order id
 * Created by Nataliia Kozoriz on 27.01.2016.
 */
public class SearchPaymentsCommand implements Command {

    private static final Logger logger = Logger.getLogger(SearchPaymentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);


        int id = Integer.valueOf(request.getParameter("search"));

        DAOFactory factory = DAOFactory.getInstance();
        PaymentDAO paymentDao = factory.getPaymentDAO();

        List<Payment> payments = new ArrayList<>();
        try {
            payments = paymentDao.getPaymentsByIdOrOrder(id);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("payments", payments);

        return (String) session.getAttribute("path");
    }

}
