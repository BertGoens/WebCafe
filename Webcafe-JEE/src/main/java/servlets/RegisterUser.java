package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import validator.RegisterUserValidator;

/* @author BertGoens */
public class RegisterUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPara = request.getParameter("id");
        int id = -1;
        if (idPara != null && idPara.matches("[+]?\\d*\\.?\\d+")) {
            id = Integer.parseInt(idPara);
            request.setAttribute("id", id);
        }
        request.getRequestDispatcher("/registerUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPara = request.getParameter("id");
        int id = -1;
        if (idPara != null && idPara.matches("[+]?\\d*\\.?\\d+")) {
            id = Integer.parseInt(idPara);
            if (id == -1) {
                id = (int) request.getAttribute("id");
            }
        }

        User registeredUser = null;
        RegisterUserValidator registerValidator = new RegisterUserValidator();
        try {
            //Validate
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(request);

            registeredUser = registerValidator.validate(items);
            if (!registerValidator.getErrors().isEmpty()) {
                //return
            }

        } catch (FileUploadException ex) {
        }

        //Incorrect parameters, ask for correct ones
        if (!registerValidator.getErrors().isEmpty()) {
            request.setAttribute("errors", registerValidator.getErrors());
            String rd = "/User/RegisterUser";
            if (id > 0) {
                rd += "?=" + id;
            }
            request.getRequestDispatcher(rd).forward(request, response);
            return;
        }

        //store the user
        getServletContext().setAttribute("loggedInUser", registeredUser);
        //adjust live users
        util.UsersUtil.setLoggedInUser(getServletContext(), registeredUser);
        //finaly done
        String rd = "/Event/Register";
        if (id > 0) {
            rd += "?=" + id;
        }
        request.getRequestDispatcher(rd).forward(request, response);
    }

}
