package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexExceptionCommand implements Command{

    private static Logger log = Logger.getLogger(IndexExceptionCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.info("EXCEPTION! redirect to error page = " + request.getRequestURI());

        String page = ConfigurationManager.getProperty("path.page.exception");
        return page;
    }
}
