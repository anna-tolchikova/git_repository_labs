package by.bsuir.forlabs;

import by.bsuir.forlabs.commandfactory.CommandFactory;
import by.bsuir.forlabs.commands.Command;
import by.bsuir.forlabs.resourcesmanagers.ConfigurationManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        CommandFactory factory = new CommandFactory();
        Command command = factory.defineCommand(request);

        log.info("\t POST");
        log.info("\t request uri (from request): " + request.getRequestURI()); // a String containing the part of the URL from the protocol name up to the query string
        log.info("\t request path info (from request): " + request.getPathInfo()); // a String, decoded by the web container, specifying extra path information that comes after the servlet path but before the query string in the request URL; or null if the URL does not have any extra path information
        log.info("\t context path (from servlet): " + getServletContext().getContextPath());
        log.info("\t context path (from request): " + request.getContextPath());
        log.info("\t servlet path (from request): " + request.getServletPath());
        log.info("\t servlet info (from servlet): " + this.getServletInfo());
        log.info("\t session id (from request): " + request.getSession().getId());
        log.info("command = " + command);

        page = command.execute(request);   // пути как в роутинге

        log.info("page after execute command = " + page);

        if (page != null) {
            log.info("redirect to page = " + page);
            response.sendRedirect(page);
        }
        else {
//          execute возвращает null в случае пустой команды
            request.getSession().removeAttribute("user");  // не. надо. наказание за неверный постинг | наверное не стоит... пока можно так. потом - отправлять на 404 страницу? или на логин..
            page = ConfigurationManager.getProperty("path.page.login");
//            response.sendRedirect(getServletContext().getContextPath() + page); ?? в чем фишка
            response.sendRedirect(page);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("\t GET");
        log.info(" request = " + request.getRequestURI() + "?" + request.getQueryString());


        String page = null;
        CommandFactory factory = new CommandFactory();

        String[] pathParts = request.getPathInfo().split("/");
        String commandStr = "index";
        for (int i = 1 ; i < pathParts.length ; ++i) {
            commandStr += "_" + pathParts[i];
        }
        request.setAttribute("command", commandStr);

        log.info("commandStr (from request)= " + request.getAttribute("command"));

        Command command = factory.defineCommand(request);
        page = command.execute(request);  // получаем страницу с WEB-INF

        log.info("page after execute command = " + page);

        if (page != null) {
            log.info("forward to page = " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
        else {
//            execute возвращает null в случае пустой команды
//            request.getSession().removeAttribute("user");
            page = ConfigurationManager.getProperty("path.page.error404");
            request.getRequestDispatcher(page).forward(request, response);

//            response.sendRedirect(getServletContext().getContextPath() + page); ???? в чем фишка
//            response.sendRedirect(page);
        }
    }

}
