package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.CarsProcessingLogic;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class AddCarCommand implements Command {

    private final static Logger log = Logger.getLogger(AddCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();
        String id = request.getParameter("idSpecifications");
        if (id != null && !id.isEmpty()) {
            int idSpecifications = Integer.parseInt(id);

            String number = request.getParameter("number");
            if (number != null && !number.isEmpty()) {
                if (CarsProcessingLogic.checkWithRegExp(number)) {
                    try {

                        CarsProcessingLogic.addCar(number, idSpecifications);

                        session.setAttribute("successAdd",
                                new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.successAdd"));

                    } catch (LogicalException e) {
                        session.setAttribute("commandError",
                                new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                        printException(e);
                    }
                } else {
                    session.setAttribute("patternError",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.patternerror"));
                }
            } else {
                session.setAttribute("requiredFieldsEmpty",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.requiredfieldsempty"));
            }

            page = RoutingManager.getProperty("path.page.admin.specifications.cars");
            page += "?id=" + idSpecifications;
        }

        return page;
    }

}
