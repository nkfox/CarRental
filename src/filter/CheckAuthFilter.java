package filter;

import model.*;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * This class checks if guest is allowed to see page he wants.
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class CheckAuthFilter implements Filter {

    /**
     * Logger for saving exceptions.
     */
    public static final Logger logger = getLogger(CheckAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("client");

        String path = request.getServletPath();

        if (isClientPage(path) && client == null ||
                isAdminPage(path) && (client == null || !client.isAdmin())) {
            logger.info("Guest has no access to page " + path);
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, servletResponse);

        } else {
            path = redirectPublicPages(path, request);
            path = redirectClientPages(path, request);
            path = redirectAdminPages(path, request);
            if (!request.getServletPath().equals(path)) {
                logger.info("You have no access to page " + path + ". You have not entered previous data");
            }
            request.getRequestDispatcher(path).forward(request, servletResponse);
        }
    }

    private String redirectPublicPages(String path, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        CarGroup selectedCarGroup = (CarGroup) session.getAttribute("selectedCarGroup");
        if ("/jsp/makeOrder/clientDataInput.jsp".equals(path) && selectedCarGroup == null) {
            path = "/jsp/makeOrder/carGroupChoice.jsp";
        }
        Place startPlace = (Place) session.getAttribute("startPlace");
        Place endPlace = (Place) session.getAttribute("endPlace");
        if ("/jsp/makeOrder/carGroupChoice.jsp".equals(path) && (startPlace == null || endPlace == null)) {
            path = "/jsp/makeOrder/placeChoice.jsp";
        }
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");
        if ("/jsp/makeOrder/placeChoice.jsp".equals(path) && (startDate == null || endDate == null)) {
            path = "/jsp/makeOrder/dateChoice.jsp";
        }
        return path;
    }

    private String redirectClientPages(String path, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<CarOrder> orders = (List<CarOrder>) session.getAttribute("orders");
        List<Payment> payments = (List<Payment>) session.getAttribute("payments");
        List<Rent> rents = (List<Rent>) session.getAttribute("rents");
        if ("/jsp/clientPages/personalOrders.jsp".equals(path) && orders == null ||
                "/jsp/clientPages/personalPayments.jsp".equals(path) && payments == null ||
                "/jsp/clientPages/personalRents.jsp".equals(path) && rents == null) {
            path = "/jsp/adminPages/adminPage.jsp";
        }
        return path;
    }

    private String redirectAdminPages(String path, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        CarOrder order = (CarOrder) session.getAttribute("order");
        Rent rent = (Rent) session.getAttribute("rent");
        List<Car> cars = (List<Car>) session.getAttribute("cars");
        List<CarOrder> orders = (List<CarOrder>) session.getAttribute("orders");
        List<Payment> payments = (List<Payment>) session.getAttribute("payments");
        List<Client> clients = (List<Client>) session.getAttribute("clients");
        if ("/jsp/adminPages/payForRepair.jsp".equals(path) && rent == null) {
            path = "/jsp/adminPages/changeOrder.jsp";
        }
        if ("/jsp/adminPages/chooseCar.jsp".equals(path) && cars == null) {
            path = "/jsp/adminPages/changeOrder.jsp";
        }
        if ("/jsp/adminPages/changeOrder.jsp".equals(path) && order == null) {
            path = "/jsp/adminPages/orders.jsp";
        }
        if ("/jsp/adminPages/orders.jsp".equals(path) && orders == null ||
                "/jsp/adminPages/payments.jsp".equals(path) && payments == null ||
                "/jsp/adminPages/clients.jsp".equals(path) && clients == null ||
                "/jsp/adminPages/cars.jsp".equals(path) && cars == null) {
            path = "/jsp/adminPages/adminPage.jsp";
        }
        return path;
    }

    private boolean isPublicPage(String page) {
        String[] paths = {"/jsp/index.jsp",
                "/jsp/login.jsp",
                "/jsp/makeOrder/dateChoice.jsp",
                "/jsp/makeOrder/placeChoice.jsp",
                "/jsp/makeOrder/carGroupChoice.jsp",
                "/jsp/makeOrder/clientDataInput.jsp"};
        for (String path : paths) {
            if (path.equals(page)) {
                return true;
            }
        }
        return false;
    }

    private boolean isClientPage(String page) {
        String[] paths = {"/jsp/clientPages/personalPage.jsp",
                "/jsp/clientPages/personalData.jsp",
                "/jsp/clientPages/personalOrders.jsp",
                "/jsp/clientPages/personalPayments.jsp",
                "/jsp/clientPages/personalRents.jsp"};
        for (String path : paths) {
            if (path.equals(page)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdminPage(String page) {
        String[] paths = {"/jsp/adminPages/adminPage.jsp",
                "/jsp/adminPages/cars.jsp",
                "/jsp/adminPages/clients.jsp",
                "/jsp/adminPages/payments.jsp",
                "/jsp/adminPages/orders.jsp",
                "/jsp/adminPages/changeOrder.jsp",
                "/jsp/adminPages/payForRepair.jsp",
                "/jsp/adminPages/chooseCar.jsp",};
        for (String path : paths) {
            if (path.equals(page)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
