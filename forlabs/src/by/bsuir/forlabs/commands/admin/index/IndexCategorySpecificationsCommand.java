package by.bsuir.forlabs.commands.admin.index;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.admin.ClientRequestsLogic;
import by.bsuir.forlabs.logic.admin.ShowCarsIndexLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.Category;
import by.bsuir.forlabs.subjects.Specification;
import by.bsuir.forlabs.subjects.composers.ComposedRequestSpecificationStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

public class IndexCategorySpecificationsCommand implements Command {

    private final static Logger log = Logger.getLogger(IndexCategorySpecificationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");
        String page = null;
        try {
            if (!request.getParameter("category").isEmpty()) {

                Category category = ShowCarsIndexLogic.findCategory(Integer.parseInt(request.getParameter("category")));
                if (category != null) {

                    ArrayList<Specification> specificationsInCategory = ShowCarsIndexLogic.findSpecificationsByCategory(category);
                    // для хэдера
                    request.setAttribute("category", category);
                    // прислать specificationsInCategory для таблицы
                    request.setAttribute("specificationsInCategory", specificationsInCategory);
                    page = ConfigurationManager.getProperty("path.page.admin.category.specifications");
                }

            }
        } catch (LogicalException e) {
            printException(e);
        }

        return page;
    }
}
