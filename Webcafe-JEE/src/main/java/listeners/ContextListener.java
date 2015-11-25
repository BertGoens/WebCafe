package listeners;

import dao.EventDao;
import java.util.*;
import javax.servlet.*;
import model.Event;
import model.User;

/* @author BertGoens */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context;
        context = sce.getServletContext();

        int usersLoggedIn = 0;
        context.setAttribute("usersLoggedIn", usersLoggedIn);

        //get upcoming webcafe
        EventDao daoEvent = new EventDao();
        Event nextEvent = daoEvent.getNextEvent();
        context.setAttribute("nextEvent", nextEvent);

        //get upcoming webcafe visitors
        List<User> nextEventVisitors = nextEvent.getVisitorsList();
        context.setAttribute("nextEventVisitors", nextEventVisitors);

        context.setAttribute("teller", 0);

        Map<User, Integer> userMap = new HashMap<>();
        context.setAttribute("userMap", userMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Nothing to do here?
    }
}
