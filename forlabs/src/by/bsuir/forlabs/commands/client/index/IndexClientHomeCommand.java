package by.bsuir.forlabs.commands.client.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.client.ClientRequestsProcessingLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexClientHomeCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexClientHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");
        String page = null;
        try {

            ArrayList<ComposedRequestSpecificationStatus> composedInfo = ClientRequestsProcessingLogic.composeFullInfo();
            request.setAttribute("composedInfo", composedInfo);
            page = ConfigurationManager.getProperty("path.page.client.home");

        } catch (LogicalException e) {
            printException(e);
        }
        return page;
    }
}
