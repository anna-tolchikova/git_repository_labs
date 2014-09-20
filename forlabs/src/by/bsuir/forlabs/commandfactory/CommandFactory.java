package by.bsuir.forlabs.commandfactory;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.commands.EmptyCommand;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class CommandFactory {

    public Command defineCommand(HttpServletRequest request) {

        Command current = new EmptyCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {           // если команда не задана в текущем запросе
            return current;
        }

        try {                                               // получение объекта, соответствующего команде
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("wrongAction", action
                    + new MessageManager((Locale)request.getSession().getAttribute("localeObj")).getProperty("message.wrongaction"));
        }
        return current;
    }
}