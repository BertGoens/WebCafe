package servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/* @author BertGoens */
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Admin/adminDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ;
    }

}
