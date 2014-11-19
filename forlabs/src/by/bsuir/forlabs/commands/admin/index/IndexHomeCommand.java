package by.bsuir.forlabs.commands.admin.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.AdminRequestsProcessingLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.Composed;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexHomeCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        try {

            ArrayList<Composed> newRequests = AdminRequestsProcessingLogic.findNewRequests();
            ArrayList<Composed> expiredRequests = AdminRequestsProcessingLogic.findExpiredRequests();
            ArrayList<Composed> notReturnedRequests = AdminRequestsProcessingLogic.findNotReturnedRequests();
            ArrayList<Composed> damagedRequests = AdminRequestsProcessingLogic.findDamagedRequests();

            request.setAttribute("newRequests", newRequests);
            request.setAttribute("expiredRequests", expiredRequests);
            request.setAttribute("notReturnedRequests", notReturnedRequests);
            request.setAttribute("damagedRequests", damagedRequests);

            page = ConfigurationManager.getProperty("path.page.admin.home");

        } catch (LogicalException e) {
            printException(e);
        }


        return page;
    }
}
