package by.bsuir.forlabs.commands.client;

import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.logic.client.ClientRequestsProcessingLogic;
import by.bsuir.forlabs.resourcesmanagers.MessageManager;
import by.bsuir.forlabs.resourcesmanagers.RoutingManager;
import by.bsuir.forlabs.subjects.ClientRequest;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Locale;

import static by.bsuir.forlabs.utilits.ExceptionsPrintWrapper.printException;

/**
 * command for processing changing locale
 */

public class AddRequestCommand implements Command {

    private final static Logger log = Logger.getLogger(AddRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("works");

        String page = null;
        HttpSession session = request.getSession();


        String id = request.getParameter("idSpecification");
        if (id != null && !id.isEmpty()) {
            int idSpecification = Integer.parseInt(id);
            ClientRequest clientRequest = new ClientRequest();
            clientRequest.setIdSpecification(idSpecification);
            try {
                if (ClientRequestsProcessingLogic.setRequiredFields(request, clientRequest)) {

                    ClientRequestsProcessingLogic.addClientRequest(clientRequest);

                    session.setAttribute("applicationMade",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.applicationMade"));


                } else {
                    session.setAttribute("requiredFieldsEmpty",
                            new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.requiredfieldsempty"));
                    session.setAttribute("clientRequest", clientRequest);
                }
            } catch (LogicalException  e) {
                session.setAttribute("commandError",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.commanderror"));
                session.setAttribute("clientRequest", clientRequest);
                printException(e);

            } catch (ParseException e){
                session.setAttribute("patternError",
                        new MessageManager((Locale) session.getAttribute("localeObj")).getProperty("message.patternerror"));
                printException(e);
            }
            page = RoutingManager.getProperty("path.page.client.specifications");
            page += "?id=" + idSpecification;
        }
        return page;
    }


}
