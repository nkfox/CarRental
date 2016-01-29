package commands.adminPage.orders;

import commands.Command;
import dao.CarOrderDAO;
import dao.ClientDAO;
import dao.DAOFactory;
import model.CarOrder;
import model.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows orders searched by client
 * Created by Nataliia Kozoriz on 25.01.2016.
 */
public class SearchOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(SearchOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String clientId = request.getParameter("searchClient");
        Client client = null;

        DAOFactory factory = DAOFactory.getInstance();

        ClientDAO clientDao = factory.getClientDAO();
        try {
            client = clientDao.clientById(clientId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        List<CarOrder> orders = new ArrayList<>();


        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            orders = carOrderDao.getOrdersByClient(client);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("orders", orders);

        return (String) session.getAttribute("path");
    }

}
