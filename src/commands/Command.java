package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for all commands
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
