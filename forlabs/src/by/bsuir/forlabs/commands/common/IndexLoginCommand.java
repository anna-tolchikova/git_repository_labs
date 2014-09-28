package by.bsuir.forlabs.commands.common;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexLoginCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = ConfigurationManager.getProperty("path.page.login");

        return page;
    }
}
