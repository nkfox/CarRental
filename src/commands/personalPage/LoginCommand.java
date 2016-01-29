package commands.personalPage;

import commands.Command;
import dao.ClientDAO;
import dao.DAOFactory;
import model.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * This command log guest in. Redirect to personal page.
 * Created by Nataliia Kozoriz on 23.01.2016.
 */
public class LoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();

        Client client = null;
        try {
            client = clientDao.clientByIdPass(id, password);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        String path;
        if (client == null) {
            path = "/jsp/login.jsp";
            session.setAttribute("info", "wrong");
        } else {
            path = "/jsp/clientPages/personalPage.jsp";
            session.setAttribute("client", client);
            session.setAttribute("path", path);
        }
        return path;
    }
}