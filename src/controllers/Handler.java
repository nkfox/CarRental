package controllers;

import commands.Command;
import commands.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller
 */
public class Handler extends HttpServlet {

    /**
     * Logger for saving exceptions.
     */
    private static final Logger logger = Logger.getLogger(Handler.class);

    /**
     * Finds appropriate command, executes it, gets new pass and redirects to it
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            Command handler = CommandFactory.getInstance().getCommand(request);
            String path = handler.execute(request, response);
            request.getSession(true).setAttribute("path", path);
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException ex) {
            logger.error("ServletException", ex);
        } catch (IOException ex) {
            logger.error("IOException", ex);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }


}
