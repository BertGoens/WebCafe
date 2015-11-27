package servlets.admin.tables.user;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import validator.CreateUserValidator;

/* @author BertGoens */
public class UserCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tableName", "User");
        req.getRequestDispatcher("/Admin/User/createUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User registeredUser = null;
        CreateUserValidator rVal = new CreateUserValidator();
        try {
            //Validate
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(req);
            registeredUser = rVal.validate(items);
        } catch (FileUploadException ex) {
        }

        if (registeredUser == null || !rVal.getErrors().isEmpty()) {
            req.setAttribute("errors", rVal.getErrors());
            req.getRequestDispatcher(getServletContext().getContextPath() + "/Admin/User/createUser.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/Admin/Tables/User/List");
    }

}
