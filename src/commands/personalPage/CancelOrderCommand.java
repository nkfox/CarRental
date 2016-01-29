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
 * This command gets order by its id, changes its status to 'canceled' and update order list
 * Created by Nataliia Kozoriz on 24.01.2016.
 */
public class CancelOrderCommand implements Command {

    private static final Logger logger = Logger.getLogger(CancelOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        int id = Integer.valueOf(request.getParameter("orderId"));

        DAOFactory factory = DAOFactory.getInstance();
        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            carOrderDao.cancelOrder(id);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        List<CarOrder> orders = new ArrayList();
        Client client = (Client) session.getAttribute("client");

        try {
            orders = carOrderDao.getOrdersByClient(client);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("orders", orders);

        return (String) session.getAttribute("path");
    }
}
