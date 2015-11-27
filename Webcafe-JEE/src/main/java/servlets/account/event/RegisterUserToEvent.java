package servlets.account.event;

import dao.EventDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;
import model.User;
import util.UsersUtil;

/* @author BertGoens */
public class RegisterUserToEvent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int eventId = -1;
        try {
            eventId = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            response.sendRedirect(getServletContext().getContextPath() + "/Home");
            return;
        }

        User loggedInUser = UsersUtil.getLoggedInUser(getServletContext());

        EventDao ed = new EventDao();
        Event subscribeTo = ed.findById(eventId);
        subscribeTo.getVisitorsList().add(loggedInUser);
        ed.merge(subscribeTo);
        response.sendRedirect(getServletContext().getContextPath() + "/Home");

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
