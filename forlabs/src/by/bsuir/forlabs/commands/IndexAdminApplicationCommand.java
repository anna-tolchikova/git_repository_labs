package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexAdminApplicationCommand implements Command{

    private final static Logger log = Logger.getLogger(IndexAdminApplicationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        if (request.getParameter("id") == null) {
            page = ConfigurationManager.getProperty("path.page.error404");
        }
        else {
//        try {

            page = ConfigurationManager.getProperty("path.page.admin.application");

//        } catch (LogicalException e) {
//            printEx(e);
//        }
        }

        return page;
    }
}
