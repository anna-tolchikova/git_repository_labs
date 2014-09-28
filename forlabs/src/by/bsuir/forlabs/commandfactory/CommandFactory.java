package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.commands.common.EmptyCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private final static Logger log = Logger.getLogger(CommandFactory.class);

    public Command defineCommand(HttpServletRequest request) {

        Command current = new EmptyCommand();


        String action;
        if ((action = request.getParameter("command")) == null || action.isEmpty()) {  // если команда не задана в текущем запросе
            if (( action = (String)request.getAttribute("command")) == null || action.isEmpty()) {
                return current;
            }
        }

        try {                                               // получение объекта, соответствующего команде
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.error("unknown command :" + e);
        }
        return current;
    }
}