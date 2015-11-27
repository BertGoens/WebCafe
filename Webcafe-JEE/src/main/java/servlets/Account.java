package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import validator.UpdateUserValidator;

/* @author BertGoens */
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //List of subscribed events
        //UserDao daoUser = new UserDao();
        //User loggedIn = UsersUtil.getLoggedInUser(getServletContext());
        //List<Event> futureEvents = daoUser.getRegistredEvents(loggedIn);
        //request.setAttribute("futureEvents", futureEvents);
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
                request.getRequestDispatcher("/Account").forward(request, response);
                return;
            }
        } catch (FileUploadException ex) {
        }

        response.sendRedirect(getServletContext().getContextPath() + "/Home");
    }

}
