package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * command for processing changing locale
 */

public class ApproveRequestCommand implements Command {

    private final static Logger log = Logger.getLogger(ApproveRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;

        String id = null;
        if ((id = request.getParameter("id")) != null && !("null").equals(id)) {
            log.info("request has parameter id = " + id);








            page += "?id=" + id;
        }

        return page;
    }
}
