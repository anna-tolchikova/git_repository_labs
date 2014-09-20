package by.bsuir.forlabs.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener, HttpSessionActivationListener {

    private static final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String localeStr =session.getServletContext().getInitParameter("localeStr");
        String[] localeParts = localeStr.split("_");
        if (localeParts.length != 2) {
            throw new IllegalArgumentException("Context init parameter \"localeStr\" has mask language_country");
        }
        session.setAttribute("localeStr", localeStr);
        Locale localeObj = new Locale(localeParts[0], localeParts[1]);
        session.setAttribute("localeObj", localeObj);
        log.info("Listener works on session creation IDSession = " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        log.info("Listener works on session destroy IDSession = " + session.getId());
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        log.info("Listener works on session passivation IDSession =  " + session.getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        log.info("Listener works on session activation IDSession = " + session.getId());
    }
}
