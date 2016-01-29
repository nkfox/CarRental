package commands.adminPage.clients;

import commands.Command;
import dao.ClientDAO;
import dao.DAOFactory;
import model.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * This command make chosen client admin if he is not or delete him from admin table if he is admin
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class MakeAdminCommand implements Command {

    private static final Logger logger = Logger.getLogger(MakeAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String id = request.getParameter("clientId");
        String adminString = request.getParameter("admin");
        boolean admin = "on".equals(adminString);

        List<Client> clients = (List<Client>) session.getAttribute("clients");

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();
        try {
            clientDao.makeAdmin(id, admin);
            updateClient(clients, id, admin);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("clients", clients);

        return (String) session.getAttribute("path");
    }

    private void updateClient(List<Client> clients, String id, boolean admin) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                client.setAdmin(admin);
            }
        }
    }
}