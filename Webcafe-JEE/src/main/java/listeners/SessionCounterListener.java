package listeners;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.User;

public class SessionCounterListener implements HttpSessionListener {

    int teller = 0;
    User loggedInUser = null;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();

        teller = (Integer) context.getAttribute("teller");
        if (teller == -1) {
            teller++;
        }
        teller++;

        context.setAttribute("teller", teller);

        context.setAttribute("loggedInUser", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();

        destroySessionTeller(context);
        destroyAdjustContextUserMapAndCounter(context);
        destroyLoggedInUser(context);
    }

    private void destroySessionTeller(ServletContext context) {
        Integer teller = (Integer) context.getAttribute("teller");

        if (teller > 0) {
            teller--;
        }

        context.setAttribute("teller", teller);
    }

    private void destroyLoggedInUser(ServletContext context) {
        context.setAttribute("loggedInUser", null);
    }

    private void destroyAdjustContextUserMapAndCounter(ServletContext context) {
        HashMap<User, Integer> userMap = (HashMap< User, Integer>) context.getAttribute("userMap");
        User loggedInUser = (User) context.getAttribute("loggedInUser");;
        Integer usersLoggedIn = (Integer) context.getAttribute("usersLoggedIn");

        if (userMap.containsKey(loggedInUser)) {
            Integer count = userMap.get(loggedInUser);
            userMap.put(loggedInUser, count - 1);

            //meerdere sessies open
        } else {
            userMap.remove(loggedInUser);
            usersLoggedIn--;
            context.setAttribute("usersLoggedIn", usersLoggedIn);
        }

        context.setAttribute("userMap", userMap);
    }
}
