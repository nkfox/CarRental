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
import java.util.ArrayList;
import java.util.List;

/**
 * This command shows all clients
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class ClientsCommand implements Command {

    private static final Logger logger = Logger.getLogger(ClientsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String path = "/jsp/adminPages/clients.jsp";
        session.setAttribute("path", path);

        List<Client> clients = new ArrayList<>();

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();
        try {
            clients = clientDao.getAllClients();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("clients", clients);

        return path;
    }

}
