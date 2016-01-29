package commands.personalPage;

import commands.Command;
import dao.DAOFactory;
import dao.RentDAO;
import model.Client;
import model.Rent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows all rents of client
 * Created by Admin on 24.01.2016.
 */
public class PersonalRentsCommand implements Command {

    private static final Logger logger = Logger.getLogger(PersonalRentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("path", "/jsp/clientPages/personalRents.jsp");

        List<Rent> rents = new ArrayList<>();
        Client client = (Client) session.getAttribute("client");

        DAOFactory factory = DAOFactory.getInstance();
        RentDAO rentDao = factory.getRentDAO();
        try {
            rents = rentDao.getRentsByClient(client);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("rents", rents);

        return "/jsp/clientPages/personalRents.jsp";
    }

}
