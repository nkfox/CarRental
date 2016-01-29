package commands.carOrder;

import commands.Command;
import dao.CarOrderDAO;
import dao.ClientDAO;
import dao.DAOFactory;
import dao.PaymentDAO;
import model.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * This command read personal data, register new client or update info of existed client, login, save order and payment.
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class ClientDataInputCommand implements Command {

    private static final Logger logger = Logger.getLogger(ClientDataInputCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String nameSurname = request.getParameter("nameSurname");
        String id = request.getParameter("id");
        String mail = request.getParameter("mail");
        String driverLicense = request.getParameter("driverLicense");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();

        Client client = null;
        try {
            client = clientDao.clientByIdPass(id, password);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        if (client == null) {
            boolean admin = false;
            try {
                admin = clientDao.isAdmin(id);
            } catch (SQLException | NullPointerException ex) {
                logger.error("DBError", ex);
            }

            password = getNewPassword();
            client = new Client(id, nameSurname, mail, driverLicense, telephone, admin, password);

            boolean isClientExist = false;
            try {
                isClientExist = clientDao.isClientExist(client.getId());
            } catch (SQLException ex) {
                logger.error("DBError", ex);
            }


            if (!isClientExist) {
                try {
                    clientDao.addClient(client);
                } catch (SQLException ex) {
                    logger.error("DBError", ex);
                }
            } else {
                try {
                    clientDao.updateClientInfo(client);
                } catch (SQLException ex) {
                    logger.error("DBError", ex);
                }
            }

        }

        session.setAttribute("client", client);

        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");
        Place startPlace = (Place) session.getAttribute("startPlace");
        Place endPlace = (Place) session.getAttribute("endPlace");
        CarGroup carGroup = (CarGroup) session.getAttribute("selectedCarGroup");

        CarOrder order = new CarOrder(client, carGroup, Date.valueOf(startDate), Date.valueOf(endDate),
                startPlace, endPlace);

        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            carOrderDao.addOrder(order);
        } catch (SQLException ex) {
            logger.error("DBError", ex);
        }

        double price = 0;
        try {
            price = carOrderDao.getPeriod(order.getId()) * carGroup.getPrice();
        } catch (SQLException e) {
            logger.error("DBError", e);
        }
        Payment payment = new Payment(order.getId(), price, null);

        PaymentDAO paymentDao = factory.getPaymentDAO();
        try {
            paymentDao.addPayment(payment);
        } catch (SQLException ex) {
            logger.error("DBError", ex);
        }

        return "/jsp/clientPages/personalPage.jsp";
    }

    private String getNewPassword() {
        StringBuilder password = new StringBuilder("");
        int passLength = 8;
        for (int i = 0; i < passLength; i++) {
            char c = (char) ((int) (Math.random() * 26) + 65);
            password.append(c);
        }
        return password.toString();
    }

}
