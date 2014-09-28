package by.bsuir.forlabs.commands.common;

import by.bsuir.forlabs.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private final static Logger log = Logger.getLogger(EmptyCommand.class);

    /**
     * Реализация исполнения команды со страницы jsp в случае пустой
     * (прямого обращения к контроллеру)
     * или несуществующей команды
     *
     * @param request объект запроса
     * @return null всегда
     */

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");
        return null;
    }
}
