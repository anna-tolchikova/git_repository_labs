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

import static by.bsuir.forlabs.utilits.ExceptionPrinter.printEx;

public class ControlServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(ControlServlet.class);


    /**
     *
     *
     */

    @Override
    public void init() {
        try {
            super.init();
            String logConfigFilename = getInitParameter("logConfigFile");
            String pref = "";
            pref = getServletContext().getRealPath("/");
            DOMConfigurator.configure(pref + logConfigFilename);
        } catch (ServletException e) {
            throw new RuntimeException("Error servlet initialization. ", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        log.info("\t POST");
        log.info("\t request uri (from request): " + request.getRequestURI()); // a String containing the part of the URL from the protocol name up to the query string
        log.info("\t request path info (from request): " + request.getPathInfo()); // a String, decoded by the web container, specifying extra path information that comes after the servlet path but before the query string in the request URL; or null if the URL does not have any extra path information
        log.info("\t context path (from servlet): " + getServletContext().getContextPath());
        log.info("\t context path (from request): " + request.getContextPath());
        log.info("\t servlet path (from request): " + request.getServletPath());

        String referer = request.getHeader("Referer");
        log.info("referer = " + referer);

        try {
            CommandFactory factory = new CommandFactory();
            Command command = factory.defineCommand(request);

            log.info("command = " + command);

            page = command.execute(request);   // пути как в роутинге

            log.info("page after execute command = " + page);

            if (page != null ) {
                log.info("redirect to page = " + page);
                response.sendRedirect(page);
            } else {
//          execute возвращает null в случае отловленного исключения или неверных входных параметров во время выполнения команды
                log.error("some catched exception occured or bad input data");
                log.error("forward to 404");
                page = ConfigurationManager.getProperty("path.page.error404");
                request.setAttribute("previousPage", referer);
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (Throwable e) {
            log.error("THROWABLE! some uncatched exception occured");
            printEx(e);
            log.info("forward to 500");
            page = ConfigurationManager.getProperty("path.page.exception");
            request.setAttribute("previousPage", referer);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("\t GET");
        log.info(" request = " + request.getRequestURI() + "?" + request.getQueryString());

        String page = null;
        String referer = request.getHeader("Referer");
        log.info("referer = " + referer);
        try {
            CommandFactory factory = new CommandFactory();

            String[] pathParts = request.getPathInfo().split("/");
            String commandStr = "index";
            for (int i = 1; i < pathParts.length; ++i) {
                commandStr += "_" + pathParts[i];
            }
            request.setAttribute("command", commandStr);

            log.info("commandStr (from request)= " + request.getAttribute("command"));

            Command command = factory.defineCommand(request);
            page = command.execute(request);  // получаем страницу с WEB-INF

            log.info("page after execute command = " + page);

            if (page != null && !("null").equals(page)) {
                log.info("forward to page = " + page);
                request.getRequestDispatcher(page).forward(request, response);
            } else {
//          execute возвращает null в случае отловленного исключения или неверных входных параметров во время выполнения команды
                log.error("some catched exception occured or bad input data");
                log.error("forward to 404");
                page = ConfigurationManager.getProperty("path.page.error404");
                request.setAttribute("previousPage", referer);
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (Throwable e) {
            log.error("THROWABLE! some uncatched exception occured");
            printEx(e);
            log.info("forward to 500");
            page = ConfigurationManager.getProperty("path.page.exception");
            request.setAttribute("previousPage", referer);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
