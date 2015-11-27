package servlets.admin.tables.user;

import dao.UserDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;

/* @author BertGoens */
public class UserDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get parameter
        int userId = -1;
        try {
            userId = Integer.valueOf(req.getParameter("id"));
        } catch (Exception e) {
            Map<String, String> errors = new HashMap();
            errors.put("userId", "invalid userId");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/Admin/User/List").forward(req, resp);
            return;
        }
        //find user
        UserDao ud = new UserDao();
        User delUser = ud.findById(userId);
        //delete user
        if (delUser != null) {
            ud.delete(delUser);
        }
        //return
        resp.sendRedirect("/Admin/User/List");
    }

}
