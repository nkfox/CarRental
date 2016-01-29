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
 * This command changes personal data of client.
 * Created by Nataliia Kozoriz on 26.01.2016.
 */
public class ChangePersonalDataCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangePersonalDataCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String path = (String) session.getAttribute("path");

        String nameSurnameEN = request.getParameter("nameSurnameEN");
        String nameSurnameUK = request.getParameter("nameSurnameUK");
        String mail = request.getParameter("mail");
        String driverLicense = request.getParameter("driverLicense");
        String telephone = request.getParameter("telephone");


        Client client = (Client) session.getAttribute("client");
        client.setNameSurnameEN(nameSurnameEN);
        client.setNameSurnameUK(nameSurnameUK);
        client.setMail(mail);
        client.setDriverLicense(driverLicense);
        client.setTelephone(telephone);

        String personalDataChange = "fails";

        DAOFactory factory = DAOFactory.getInstance();
        ClientDAO clientDao = factory.getClientDAO();
        try {
            clientDao.updateClientInfo(client);
            session.setAttribute("client", client);
            personalDataChange = "success";
        } catch (SQLException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("personalDataChange", personalDataChange);

        return path;
    }

}
