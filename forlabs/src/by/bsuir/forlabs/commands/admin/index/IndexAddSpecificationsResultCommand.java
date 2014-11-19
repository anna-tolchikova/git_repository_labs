package by.bsuir.forlabs.commands.admin.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexAddSpecificationsResultCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAddSpecificationsResultCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        page = ConfigurationManager.getProperty("path.page.admin.category.specifications.add.result");
        return page;
    }
}
