package commands.personalPage;

import commands.Command;
import dao.CarOrderDAO;
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
 * This command shows all orders of client
 * Created by Nataliia Kozoriz on 24.01.2016.
 */
public class PersonalOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(PersonalOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("path", "/jsp/clientPages/personalOrders.jsp");

        List<CarOrder> orders = new ArrayList();
        Client client = (Client) session.getAttribute("client");

        DAOFactory factory = DAOFactory.getInstance();
        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            orders = carOrderDao.getOrdersByClient(client);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("orders", orders);

        return "/jsp/clientPages/personalOrders.jsp";
    }

}
