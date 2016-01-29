package commands.carOrder;

import commands.Command;
import dao.DAOFactory;
import dao.PlaceDAO;
import model.Place;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command saves chosen rent period
 * Created by Nataliia Kozoriz on 17.01.2016.
 */
public class DateChoiceCommand implements Command {

    private static final Logger logger = Logger.getLogger(DateChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String startDate = request.getParameter("startDate");
        session.setAttribute("startDate", startDate);
        String endDate = request.getParameter("endDate");
        session.setAttribute("endDate", endDate);

        DAOFactory factory = DAOFactory.getInstance();
        List<Place> allPlaces = new ArrayList<Place>();

        PlaceDAO placeDao = factory.getPlaceDAO();
        try {
            allPlaces = placeDao.getAll();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("allPlaces", allPlaces);

        return "/jsp/makeOrder/placeChoice.jsp";
    }
}
