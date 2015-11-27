package servlets.admin.tables.user;

import dao.UserDao;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import validator.UpdateUserValidator;

/* @author BertGoens */
public class UserEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tableName", "User");
        int userId = -1;
        try {
            userId = Integer.valueOf(req.getParameter("id"));
        } catch (Exception e) {
            Map<String, String> errors = new HashMap();
            errors.put("userId", "Unknown userId");
            req.getRequestDispatcher("/Admin/User/editUser.jsp").forward(req, resp);
            return;
        }
        UserDao ud = new UserDao();
        User editUser = ud.findById(userId);
        req.setAttribute("editUser", editUser);
        req.getRequestDispatcher("/Admin/User/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateUserValidator updateVal = new UpdateUserValidator();
        try {
            //Validate
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(req);
            if (!updateVal.validate(items)) {
                req.setAttribute("errors", updateVal.getErrors());
                req.getRequestDispatcher("/Admin/Tables/User/Edit").forward(req, resp);
                return;
            }
        } catch (FileUploadException ex) {
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/Admin/Tables/User/List");
    }

}
