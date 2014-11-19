package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ChangeStatusLogic;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Locale;

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
        HttpSession session = request.getSession();
        String id = request.getParameter("id");

        if ( id != null && !id.isEmpty()) {
            log.info("request has parameter id = " + id);
            int idRequest = Integer.parseInt(id);

            try {

                ChangeStatusLogic.rejectRequest(idRequest, request.getParameter("rejectCommentArea").trim());

            } catch (LogicalException e) {
                session.setAttribute("commandError",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                printException(e);
            }

            page = RoutingManager.getProperty("path.page.admin.application");
            page += "?id=" + idRequest;
        }

        return page;
    }
}
