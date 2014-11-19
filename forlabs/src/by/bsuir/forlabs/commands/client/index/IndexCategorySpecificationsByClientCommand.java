package by.bsuir.forlabs.commands.client.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ShowCategoryIndexLogic;
import by.bsuir.forlabs.logic.client.ShowCategoryIndexByClientLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.Category;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexCategorySpecificationsByClientCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexCategorySpecificationsByClientCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");
        String page = null;
        try {
            String id = request.getParameter("category");
            if ( id != null && !id.isEmpty()) {

                int idCategory =  Integer.parseInt(id);
                Category category = ShowCategoryIndexLogic.findCategory(idCategory);
                if (category != null) {

                    ArrayList<Specification> specificationsInCategory =
                            ShowCategoryIndexByClientLogic.findSpecificationsByCategory(category);

                    request.setAttribute("category", category);
                    request.setAttribute("specificationsInCategory", specificationsInCategory);
                    page = ConfigurationManager.getProperty("path.page.client.category.specifications");
                }
                else {
                    log.error("Category with id = " + idCategory + " doesn't exist");
                }

            }
        } catch (LogicalException e) {
            printException(e);
        }

        return page;
    }
}
