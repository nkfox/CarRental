package commands.adminPage.orders;

import commands.Command;
import dao.CarOrderDAO;
import dao.DAOFactory;
import model.CarOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This command shows all orders
 * Created by Nataliia Kozoriz on 25.01.2016.
 */
public class OrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(OrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String path = "/jsp/adminPages/orders.jsp";

        session.setAttribute("path", path);

        List<CarOrder> orders = new ArrayList<>();

        DAOFactory factory = DAOFactory.getInstance();
        CarOrderDAO carOrderDao = factory.getCarOrderDAO();
        try {
            orders = carOrderDao.getAllOrders();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("orders", orders);

        return path;
    }

}
