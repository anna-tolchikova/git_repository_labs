package by.bsuir.forlabs;

import by.bsuir.forlabs.commandfactory.CommandFactory;
import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControlServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(ControlServlet.class);


    /**
     *
     *
     */

    @Override
    public void init(){
        try {
            super.init();
            String logConfigFilename = getInitParameter("logConfigFile");
            String pref ="";
            pref = getServletContext().getRealPath("/");
            DOMConfigurator.configure(pref + logConfigFilename);


        } catch (ServletException e) {
            throw new RuntimeException("Error servlet initialization. ", e);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {

        String page = null;
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request); // request must contain param "command"
        log.info("\t request uri (from request): " + request.getRequestURI()); // a String containing the part of the URL from the protocol name up to the query string
        log.info("\t request path info (from request): " + request.getPathInfo()); // a String, decoded by the web container, specifying extra path information that comes after the servlet path but before the query string in the request URL; or null if the URL does not have any extra path information
        log.info("\t context path (from servlet): " + getServletContext().getContextPath());
        log.info("\t context path (from request): " + request.getContextPath());
        log.info("\t servlet path (from request): " + request.getServletPath());
        log.info("\t servlet info (from servlet): " + this.getServletInfo());
        log.info("\t session id (from request): " + request.getSession().getId());

        log.info("command = " + command);

        page = command.execute(request);

        log.info("page after execute command = " + page);
        if (page != null) {
            if (page.equals(request.getPathInfo())) {
                log.info("forward to page = " + page + " requestURI = " + request.getRequestURI());
                request.getRequestDispatcher(page).forward(request, response);
            } else {

                log.info("redirect to page = " + page);
                response.sendRedirect(page); // We'd like to fire redirect in case of a view change as result of the action (PRG pattern).
            }
        }
        else {
            // execute возвращает null в случае пустой команды

            request.getSession().removeAttribute("user");
            page = ConfigurationManager.getProperty("path.page.login");
//            response.sendRedirect(getServletContext().getContextPath() + page); ???? в чем фишка
            response.sendRedirect(page);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException e) {
            log.error(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException e) {
            log.error(e.getMessage());
        }
    }

}
