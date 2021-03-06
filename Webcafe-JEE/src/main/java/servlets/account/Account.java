package servlets.account;

import dao.EventDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Event;
import model.User;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.UsersUtil;
import validator.UpdateUserValidator;

/* @author BertGoens */
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //List of subscribed events

        EventDao daoEvent = new EventDao();
        User loggedInUser = UsersUtil.getLoggedInUser(getServletContext());
        List<Event> futureEvents = daoEvent.getComingEvents();
        List<Event> subscribedEvents = new ArrayList<Event>();
        for (int i = 0; i < futureEvents.size(); i++) {
            if (futureEvents.get(i).getVisitorsList().contains(loggedInUser)) {
                subscribedEvents.add(futureEvents.get(i));
            }
        }

        request.setAttribute("subscribedEvents", subscribedEvents);
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UpdateUserValidator AccVal = new UpdateUserValidator();
        try {
            //Validate
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(request);
            if (!AccVal.validate(items)) {
                request.setAttribute("errors", AccVal.getErrors());
            }
        } catch (FileUploadException ex) {
        }
        response.sendRedirect(getServletContext().getContextPath() + "/account.jsp");
    }

}
