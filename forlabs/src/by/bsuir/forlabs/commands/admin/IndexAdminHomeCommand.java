package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.ClientRequestsLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.Composed;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

public class IndexAdminHomeCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAdminHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        try {

            ArrayList<Composed> newRequests = ClientRequestsLogic.findNewRequests();
            ArrayList<Composed> expiredRequests = ClientRequestsLogic.findExpiredRequests();
            ArrayList<Composed> notReturnedRequests = ClientRequestsLogic.findNotReturnedRequests();
            ArrayList<Composed> damagedRequests = ClientRequestsLogic.findDamagedRequests();

            request.setAttribute("newRequests", newRequests);
            request.setAttribute("expiredRequests", expiredRequests);
            request.setAttribute("notReturnedRequests", notReturnedRequests);
            request.setAttribute("damagedRequests", damagedRequests);

            page = ConfigurationManager.getProperty("path.page.admin.home");

        } catch (LogicalException e) {
            printEx(e);
        }


        return page;
    }
}
