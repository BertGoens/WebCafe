package servlets.account.event;

import dao.EventDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;
import model.User;
import util.UsersUtil;

/* @author BertGoens */
public class UnregisterUserToEvent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int eventId = -1;
        try {
            eventId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            response.sendRedirect(getServletContext().getContextPath() + "/Event/Detail?id=" + eventId);
            return;
        }

        User loggedInUser = UsersUtil.getLoggedInUser(getServletContext());
        EventDao ed = new EventDao();
        Event subscribeTo = ed.findById(eventId);

        ed.getEntityManager().getTransaction().begin();
        subscribeTo.getVisitorsList().remove(loggedInUser);
        ed.getEntityManager().getTransaction().commit();
        
        ed.merge(subscribeTo);

        response.sendRedirect(getServletContext().getContextPath() + "/Event/Detail?id=" + eventId);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
