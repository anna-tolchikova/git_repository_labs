package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexAdminApplicationCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAdminApplicationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;

        if (request.getParameter("id") != null) {
//        try {

            page = ConfigurationManager.getProperty("path.page.admin.application");

//        } catch (LogicalException e) {
//            printEx(e);
//        }
        }

        return page;
    }
}
