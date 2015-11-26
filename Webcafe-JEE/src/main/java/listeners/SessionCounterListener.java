package listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import util.UsersUtil;

public class SessionCounterListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();

        Integer teller = (Integer) context.getAttribute("teller");
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
        UsersUtil.logoutCurrentUser(context);
    }

    private void destroySessionTeller(ServletContext context) {
        Integer teller = (Integer) context.getAttribute("teller");

        if (teller > 0) {
            teller--;
        }

        context.setAttribute("teller", teller);
    }

}
