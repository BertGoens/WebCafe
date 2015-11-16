package listeners;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.Event;

/* @author BertGoens */
public class ContextListener implements ServletContextListener {

    int usersLoggedIn;
    List<Event> PremadeEventList;
    Event upcomingWebcafe;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        usersLoggedIn++;
        context.setAttribute("usersLoggedIn", usersLoggedIn);

        //get upcoming webcafe
        context.setAttribute("upcomingWc", upcomingWebcafe);

        PremadeEventList = new ArrayList<>();
        Event feest = new Event();
        feest.setName("Feestje @ Bertn");
        feest.setLocation("Koksijde");
        feest.setId(-1);
        feest.setImagePath("/Users/BertGoens/Pictures/Profiel/sfeer.jpg");

        PremadeEventList.add(feest);

        context.setAttribute("PremadeEventList", PremadeEventList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        usersLoggedIn--;
    }
}
