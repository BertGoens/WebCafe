package servlets;

import java.awt.Desktop;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import validator.ContactValidator;

/* @author BertGoens */
public class Contact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("type", request.getParameter("type"));
        //Validate
        ContactValidator cv = new ContactValidator();
        if (!cv.validate(request.getParameterMap())) {
            //not all data filled in, return with errors explaining
            request.setAttribute("errors", cv.getErrors());
            request.getRequestDispatcher("/contact.jsp").forward(request, response);
        } else {
            //open email client - send email
            Desktop desktop;
            if (Desktop.isDesktopSupported()
                    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                try {
                    String email, name, subject, type;
                    email = URLEncoder.encode("bertgoens@gmail.com", "UTF-8");
                    name = URLEncoder.encode(request.getParameter("name"), "UTF-8");
                    subject = URLEncoder.encode(request.getParameter("subject"), "UTF-8");
                    type = URLEncoder.encode(request.getParameter("type"), "UTF-8");
                    URI mailto = new URI("mailto:" + email + "?subject=" + type + name + "?body=" + subject);
                    desktop.mail(mailto);
                } catch (URISyntaxException ex) {
                    //No email client / not supported
                    HashMap<String, String> errors = new HashMap<>();
                    errors.put("URISyntaxException", "There was a problem processing your email. Please send it from your mail client.");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/contact.jsp").forward(request, response);
                    return;
                }
            } else {
                // Desktop not supported
                // TODO fallback to some Runtime.exec(..) voodoo?
                HashMap<String, String> errors = new HashMap<>();
                errors.put("DesktopNotSupported", "There was a problem processing your email. Please send it from your mail client.");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/contact.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("/Home").forward(request, response);
        }
    }

}
