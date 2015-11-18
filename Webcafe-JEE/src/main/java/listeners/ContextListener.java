package listeners;

import dao.EventDao;
import javax.servlet.*;
import model.Event;

/* @author BertGoens */
public class ContextListener implements ServletContextListener {

    int usersLoggedIn;
    Event nextEvent;
    ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();

        usersLoggedIn = 0;
        context.setAttribute("usersLoggedIn", usersLoggedIn);

        //get upcoming webcafe
        EventDao daoEvent = new EventDao();
        nextEvent = daoEvent.getNextEvent();
        context.setAttribute("nextEvent", nextEvent);

        context.setAttribute("teller", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Nothing to do here?
    }
}
