package by.bsuir.forlabs.commands.client;

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

public class PayForRentalCommand implements Command {

    private final static Logger log = Logger.getLogger(PayForRentalCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();
        String id = request.getParameter("id");

        if ( id != null && !id.isEmpty()) {
            int idRequest = Integer.parseInt(id);

            try {
                ChangeStatusLogic.setPayedRequestsStatus(idRequest);
                session.setAttribute("successEdit",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.successEdit"));

            } catch (LogicalException e) {
                session.setAttribute("commandError",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                printException(e);
            }

            page = RoutingManager.getProperty("path.page.client.home");
        }

        return page;
    }
}
