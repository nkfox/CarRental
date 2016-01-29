package commands.language;

import commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command changes language and return path to the same page
 * Created by Nataliia Kozoriz on 22.01.2016.
 */
public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String language = request.getParameter("newLanguage");
        session.setAttribute("locale",language);
        return (String)session.getAttribute("path");
    }
}
