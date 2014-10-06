package by.bsuir.forlabs.commands.client;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ChangeStatusLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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

        String id = null;
        if ((id = request.getParameter("id")) != null && !("null").equals(id)) {
            log.info("request has parameter id = " + id);

            try {
                ChangeStatusLogic.setPayedRequestsStatus(Integer.parseInt(id));
            } catch (LogicalException e) {
                request.getSession().setAttribute("commandError", 1);
                printException(e);
            }

            page = ConfigurationManager.getProperty("path.page.admin.application");
            page += "?id=" + id;
        }

        return page;
    }
}
