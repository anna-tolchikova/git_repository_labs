package by.bsuir.forlabs.commands.client.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ShowSpecificationsIndexLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.Category;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexSpecificationsCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexSpecificationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        String id = request.getParameter("id");

        // getting specifications id
        if ( id != null && !id.isEmpty()) {
            try {

                int idSpecifications = Integer.parseInt(id);
                Specification specifications = ShowSpecificationsIndexLogic.findSpecification(idSpecifications);
                if (specifications != null) {
                    Category  category = ShowSpecificationsIndexLogic.findCategory(specifications.getIdCategory());

                    request.setAttribute("category", category);
                    request.setAttribute("specifications", specifications);
                    page = ConfigurationManager.getProperty("path.page.client.specifications");
                }
                else {
                    log.error("Specifications with id = " + idSpecifications + " don't exist");
                }

            } catch (LogicalException e) {
                printException(e);
            }
        }

        return page;
    }
}
