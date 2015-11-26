package servlets;

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
        int id = -1;
        String idPara = request.getParameter("id");
        if (idPara != null && idPara.matches("[+]?\\d*\\.?\\d+")) {
            id = Integer.parseInt(idPara);
        }

        User loggedInUser = UsersUtil.getLoggedInUser(getServletContext());
        if (id < 1 || loggedInUser == null) {
            //incorrect id or void user
            response.sendRedirect(getServletContext().getContextPath() + "/Home");
        } else {
            //Register on event
            EventDao ed = new EventDao();
            Event subscribeTo = ed.findById(id);
            subscribeTo.getVisitorsList().add(loggedInUser);
            ed.merge(subscribeTo);
            response.sendRedirect(getServletContext().getContextPath() + "/Home");
            //Unregister on event
        }

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
