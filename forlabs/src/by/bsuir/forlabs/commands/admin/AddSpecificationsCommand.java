package by.bsuir.forlabs.commands.admin;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.SpecificationsProcessingLogic;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.Car;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class AddSpecificationsCommand implements Command {

    private final static Logger log = Logger.getLogger(AddSpecificationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();

        Specification tmpSpecifications = new Specification();
        Car tmpCar = new Car();
        try {
            SpecificationsProcessingLogic.fillFromMultipartdata(request, tmpSpecifications, tmpCar);
        } catch (IOException | FileUploadException e) {
            printException(e);
            return page;
        }
        int idCategory = tmpSpecifications.getIdCategory();
        if (idCategory != 0) {
            if (!SpecificationsProcessingLogic.requiredFieldsEmpty(tmpSpecifications)) {
                try {
                    SpecificationsProcessingLogic.addSpecifications(tmpSpecifications, tmpCar);

                    session.setAttribute("successAdd",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.successAdd"));
                    page = RoutingManager.getProperty("path.page.admin.category.specifications.add.result");
                    return page;

                } catch (LogicalException e) {
                    session.setAttribute("commandError",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                    printException(e);

                }

            } else {
                session.setAttribute("requiredFieldsEmpty",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.requiredfieldsempty"));
            }
            session.setAttribute("specifications", tmpSpecifications);
            session.setAttribute("car", tmpCar);
            page = RoutingManager.getProperty("path.page.admin.category.specifications.add");
            page += "?id=" + idCategory;
        }
        return page;
    }




}
