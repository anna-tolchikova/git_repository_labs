package by.bsuir.forlabs.commands.admin.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.AdminRequestsProcessingLogic;
import by.bsuir.forlabs.logic.admin.AvailableCarsLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexApplicationCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexApplicationCommand.class);

    private final static int NEW_REQUESTS_STATUS = 1;

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        String id = request.getParameter("id");
        if ( id != null && !id.isEmpty()) {
            try {

                int idApplication = Integer.parseInt(id);
                ComposedRequestSpecificationStatus composedInfo = AdminRequestsProcessingLogic.composeFullInfoById(idApplication);

                int freeCarsCount = 0;
                if (composedInfo.getStatus().getId() == NEW_REQUESTS_STATUS) {
                    // выбрать, сколько будет свободно машин на ту дату
                    freeCarsCount = AvailableCarsLogic.countAvailableCarsForRequest(idApplication);
                    request.setAttribute("freeCarsCount", freeCarsCount);
                }

                request.setAttribute("composedInfo", composedInfo);
                page = ConfigurationManager.getProperty("path.page.admin.application");

            } catch (LogicalException e) {
                printException(e);
            }
        }

        return page;
    }
}
