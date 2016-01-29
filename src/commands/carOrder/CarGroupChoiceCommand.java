package commands.carOrder;

import commands.Command;
import dao.CarGroupDAO;
import dao.DAOFactory;
import model.CarGroup;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * This command saves chosen car group
 * Created by Nataliia Kozoriz on 16.01.2016.
 */
public class CarGroupChoiceCommand implements Command {

    private static final Logger logger = Logger.getLogger(CarGroupChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String selected = request.getParameter("carGroup");

        CarGroup selectedCarGroup = new CarGroup();
        DAOFactory factory = DAOFactory.getInstance();
        CarGroupDAO carGroupDao = factory.getCarGroupDAO();
        try {
            selectedCarGroup = carGroupDao.getCarGroupById(selected);
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }

        session.setAttribute("selectedCarGroup", selectedCarGroup);

        return "/jsp/makeOrder/clientDataInput.jsp";
    }
}