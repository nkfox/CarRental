package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Empty command. Return path to home page
 * Created by Nataliia Kozoriz on 28.01.2016.
 */
public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/index.jsp";
    }
}
