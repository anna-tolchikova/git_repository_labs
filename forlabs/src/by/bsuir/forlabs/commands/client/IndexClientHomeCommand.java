package by.bsuir.forlabs.commands.client;

import by.bsuir.forlabs.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IndexClientHomeCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexClientHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");
        return null;
    }
}
