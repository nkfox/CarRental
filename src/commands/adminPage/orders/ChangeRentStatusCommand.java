package commands.adminPage.orders;

import commands.Command;
import dao.DAOFactory;
import dao.RentDAO;
import model.CarOrder;
import model.Rent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This command changer status of rent
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class ChangeRentStatusCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeRentStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        int orderId = ((CarOrder) session.getAttribute("order")).getId();
        int rentId = ((Rent) session.getAttribute("rent")).getId();
        int status = Integer.valueOf(request.getParameter("rentStatusId"));

        DAOFactory factory = DAOFactory.getInstance();
        RentDAO rentDao = factory.getRentDAO();
        Rent rent = null;
        boolean needToPay = false;
        try {
            needToPay = rentDao.updateRentStatus(rentId, status);
            rent = rentDao.getRentByOrderId(orderId);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("rent", rent);

        if (needToPay)
            return "/jsp/adminPages/payForRepair.jsp";
        else
            return (String) session.getAttribute("path");
    }
}