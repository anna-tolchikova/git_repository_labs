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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class EditCarCommand implements Command {

    private final static Logger log = Logger.getLogger(EditCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        if ( id != null && !id.isEmpty()) {
            log.info("request has parameter id car= " + id);
            int idCar = Integer.parseInt(id);

            String number = request.getParameter("number");
            if ( number != null && !number.isEmpty()) {
                if (checkWithRegExp(number)) {
                    try {

                        CarsProcessingLogic.editCar(idCar, number);

                        session.setAttribute("successEdit",
                                new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.successEdit"));

                    } catch (LogicalException e) {
                        session.setAttribute("commandError",
                                new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                        printException(e);
                    }
                }
                else {
                    session.setAttribute("patternError",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.patternerror"));
                }
            }
            else {
                session.setAttribute("requiredFieldsEmpty",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.requiredfieldsempty"));
            }

            page = RoutingManager.getProperty("path.page.admin.specifications.cars");
            page += "?id=" + request.getParameter("idSpecifications");
        }

        return page;
    }

    private static boolean checkWithRegExp(String carNumber){
        Pattern p = Pattern.compile("\\d{4}-\\d{2}\\s[A-Za-z]{2}");
        Matcher m = p.matcher(carNumber);
        return m.matches();
    }

}
