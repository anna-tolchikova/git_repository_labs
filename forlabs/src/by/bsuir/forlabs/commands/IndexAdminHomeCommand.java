package by.bsuir.forlabs.commands;

import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.ClientRequestsLogic;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import by.bsuir.forlabs.subjects.ClientRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

public class IndexAdminHomeCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            // выбрать 1. новые заявки 2.ожидают ремонта 3.всего используется 4.свободно


            // select new requests
            ArrayList<ClientRequest> newRequests = ClientRequestsLogic.findShortNewRequests();

//            // select car, need to be repaired
//            ArrayList<ClientRequest> carsForRepair = ClientRequestsLogic.findShortCarsForRepair();


            request.setAttribute("newRequests", newRequests);

            page = ConfigurationManager.getProperty("path.page.admin.home");

        } catch (LogicalException e) {
            printEx(e);
        }


        return page;
    }
}
