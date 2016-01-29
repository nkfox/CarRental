package commands.personalPage;

import commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command shows all personal data of client
 * Created by Nataliia Kozoriz on 24.01.2016.
 */
public class PersonalDataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String path = "/jsp/clientPages/personalData.jsp";
        session.setAttribute("path", path);
        return path;
    }
}
