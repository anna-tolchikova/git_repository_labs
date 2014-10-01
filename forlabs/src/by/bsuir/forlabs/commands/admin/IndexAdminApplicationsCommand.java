package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.ClientRequestsLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

public class IndexAdminApplicationsCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAdminApplicationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;

        try {

            ArrayList<ComposedRequestSpecificationStatus> composedInfo = ClientRequestsLogic.composeFullInfo();

            request.setAttribute("composedInfo", composedInfo);
            page = ConfigurationManager.getProperty("path.page.admin.applications");

        } catch (LogicalException e) {
            printEx(e);
        }

        return page;
    }
}
