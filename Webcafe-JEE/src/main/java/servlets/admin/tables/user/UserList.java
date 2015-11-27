package servlets.admin.tables.user;

import dao.UserDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;

/* @author BertGoens */
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao ud = new UserDao();
        List<User> findAll = ud.findAll();
        req.setAttribute("findAll", findAll);
        req.setAttribute("tableName", "User");
        req.getRequestDispatcher("/Admin/User/listUsers.jsp").forward(req, resp);
    }
}
