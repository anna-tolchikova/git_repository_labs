package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.resourcesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class EmptyCommand implements Command {

    /**
     * Реализация исполнения команды со страницы jsp в случае пустой команды
     *
     * @param request объект запроса
     * @return null всегда
     */

    @Override
    public String execute(HttpServletRequest request) {
        /* в случае ошибки или прямого обращения к контроллеру - возврат null. (переадресация на страницу ошибки) */
        request.getSession().setAttribute("nullPage",
                new MessageManager((Locale) request.getSession().getAttribute("localeObj")).getProperty("message.nullpage"));
        return null;
    }
}
