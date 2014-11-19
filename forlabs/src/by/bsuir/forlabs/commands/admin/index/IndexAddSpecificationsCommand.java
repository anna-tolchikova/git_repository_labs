package by.bsuir.forlabs.commands.admin.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ShowSpecificationsIndexLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.Category;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexAddSpecificationsCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexAddSpecificationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        String id = request.getParameter("id");

        // getting category id
        if ( id != null && !id.isEmpty()) {
            try {

                int idCategory = Integer.parseInt(id);
                Category category = ShowSpecificationsIndexLogic.findCategory(idCategory);
                if (category != null) {

                    request.setAttribute("category", category);
                    page = ConfigurationManager.getProperty("path.page.admin.category.specifications.add");
                }
                else {
                    log.error("Category with id = " + idCategory + " doesn't exist");
                }

            } catch (LogicalException e) {
                printException(e);
            }
        }

        return page;
    }
}
