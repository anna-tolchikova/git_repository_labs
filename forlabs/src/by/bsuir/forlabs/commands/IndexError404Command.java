package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexError404Command implements Command{

    private static Logger log = Logger.getLogger(IndexError404Command.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.info("ERROR! redirect to error page = " + request.getRequestURI());

        String page = ConfigurationManager.getProperty("path.page.error404");
        return page;
    }
}
