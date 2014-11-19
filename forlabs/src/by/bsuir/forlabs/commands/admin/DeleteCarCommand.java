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

public class DeleteCarCommand implements Command {

    private final static Logger log = Logger.getLogger(DeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            log.info("request has parameter id car= " + id);
            int idCar = Integer.parseInt(id);

            try {

                CarsProcessingLogic.deleteCar(idCar);

                session.setAttribute("successDelete",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.successDelete"));


            } catch (LogicalException e) {
                session.setAttribute("commandError",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                printException(e);
            }


            page = RoutingManager.getProperty("path.page.admin.specifications.cars");
            page += "?id=" + request.getParameter("idSpecifications");
        }

        return page;
    }
}
