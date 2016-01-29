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
 * This command changes password of client
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class ChangePasswordCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String path = (String) session.getAttribute("path");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatedPassword = request.getParameter("repeatedPassword");

        Client client = (Client) session.getAttribute("client");
        String password = client.getPassword();

        String passChanged = "";

        if (password.equals(oldPassword) && newPassword.equals(repeatedPassword)) {
            DAOFactory factory = DAOFactory.getInstance();
            ClientDAO clientDao = factory.getClientDAO();
            passChanged = "fail";
            try {
                client.setPassword(newPassword);
                clientDao.updateClientInfo(client);
                session.setAttribute("client", client);
                passChanged = "success";
            } catch (SQLException ex) {
                logger.error("DBError", ex);
            }
        } else {
            passChanged = "fail";
        }
        session.setAttribute("passChanged", passChanged);

        return path;
    }

}
