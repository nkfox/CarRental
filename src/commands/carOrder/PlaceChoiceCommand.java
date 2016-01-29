package commands.carOrder;

import commands.Command;
import dao.CarGroupDAO;
import dao.DAOFactory;
import model.CarGroup;
import model.Place;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This command saves chosen start location and end location
 * Created by Nataliia Kozoriz on 23.01.2016.
 */
public class PlaceChoiceCommand implements Command {

    private static final Logger logger = Logger.getLogger(PlaceChoiceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        Integer startPlaceId = Integer.valueOf(request.getParameter("selectStartPlace"));
        Integer endPlaceId = Integer.valueOf(request.getParameter("selectEndPlace"));
        List<Place> allPlaces = (List<Place>) request.getSession().getAttribute("allPlaces");
        Place startPlace = getPlaceById(startPlaceId, allPlaces);
        Place endPlace = getPlaceById(endPlaceId, allPlaces);
        session.setAttribute("startPlace", startPlace);
        session.setAttribute("endPlace", endPlace);

        DAOFactory factory = DAOFactory.getInstance();
        List<CarGroup> allCarGroups = new ArrayList<CarGroup>();

        CarGroupDAO carGroupDao = factory.getCarGroupDAO();
        try {
            allCarGroups = carGroupDao.getAll();
        } catch (SQLException | NullPointerException ex) {
            logger.error("DBError", ex);
        }
        session.setAttribute("allCarGroups", allCarGroups);

        return "/jsp/makeOrder/carGroupChoice.jsp";
    }

    private Place getPlaceById(int id, List<Place> allPlaces) {
        for (Place place : allPlaces) {
            if (id == place.getId())
                return place;
        }
        return null;
    }
}
