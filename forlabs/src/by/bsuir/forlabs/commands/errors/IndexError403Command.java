package by.bsuir.forlabs.commands.errors;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexError403Command implements Command {

    private static Logger log = Logger.getLogger(IndexError403Command.class);

    @Override
    public String execute(HttpServletRequest request) {

        log.info("works");

        String page = ConfigurationManager.getProperty("path.page.error403");
        return page;
    }
}
