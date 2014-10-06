package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ChangeStatusLogic;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class RejectRequestCommand implements Command {

    private final static Logger log = Logger.getLogger(RejectRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;

        String id = null;
        if ((id = request.getParameter("id")) != null && !("null").equals(id)) {
            log.info("request has parameter id = " + id);

            try {
                log.info("request.getParameter(\"rejectCommentArea\") = "+request.getParameter("rejectCommentArea"));
                ChangeStatusLogic.rejectRequest(Integer.parseInt(id), request.getParameter("rejectCommentArea"));
            } catch (LogicalException e) {
                request.getSession().setAttribute("commandError", 1);
                printException(e);
            }

            page = RoutingManager.getProperty("path.page.admin.application");
            page += "?id=" + id;
        }

        return page;
    }
}
