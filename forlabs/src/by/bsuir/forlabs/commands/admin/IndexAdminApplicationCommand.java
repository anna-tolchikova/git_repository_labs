package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.CarLogic;
import by.bsuir.forlabs.logic.ClientRequestsLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

public class IndexAdminApplicationCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAdminApplicationCommand.class);

    private final static int NEW_REQUESTS_STATUS = 1;

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        int id;

        if (request.getParameter("id") != null || !("null".equals(request.getParameter("id")))) {
            try {

                id = Integer.parseInt(request.getParameter("id"));
                ComposedRequestSpecificationStatus composedInfo = ClientRequestsLogic.composeFullInfoById(id);

                int freeCarsCount = 0;
                if (composedInfo.getStatus().getId() == NEW_REQUESTS_STATUS) {
                    // выбрать, сколько будет свободно машин на ту дату
                    freeCarsCount = CarLogic.countFreeCarsBySpecificationsOnDate(
                            composedInfo.getSpecification().getId(), composedInfo.getClientRequest().getRentalDate()
                    );
                    request.setAttribute("freeCarsCount", freeCarsCount);
                }

                request.setAttribute("composedInfo", composedInfo);
                page = ConfigurationManager.getProperty("path.page.admin.application");

            } catch (LogicalException e) {
                printEx(e);
            }
        }

        return page;
    }
}
