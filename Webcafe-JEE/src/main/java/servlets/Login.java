package servlets;

import dao.UserDao;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.User;
import util.UsersUtil;
import validator.LoginValidator;

/* @author BertGoens */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPara = request.getParameter("id");
        int id = -1;
        if (idPara != null && idPara.matches("[+]?\\d*\\.?\\d+")) {
            id = Integer.parseInt(idPara);
            request.setAttribute("id", id);
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPara = request.getParameter("id");
        int id = -1;
        if (idPara != null && idPara.matches("[+]?\\d*\\.?\\d+")) {
            id = Integer.parseInt(idPara);
            request.setAttribute("id", id);
            if (id == -1) {
                id = (int) request.getAttribute("id");
            }
        }

        UserDao daoUser = new UserDao();

        LoginValidator lv = new LoginValidator();
        if (!lv.validate(request.getParameterMap())) {
            //not all data filled in, return with errors explaining
            request.setAttribute("errors", lv.getErrors());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");

            if (daoUser.loginUserCorrect(email, pwd)) {
                //correct login
                User loggedInUser = daoUser.findByEmail(email);
                UsersUtil.setLoggedInUser(getServletContext(), loggedInUser);
                if (id > 0) {
                    response.sendRedirect(request.getContextPath() + "/Account/Event/Register?id=" + id);
                } else {
                    response.sendRedirect(request.getContextPath() + "/Home");
                }
            } else {
                HashMap<String, String> errors = new HashMap();
                errors.put("incorrect login", "The email or password is incorrect");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
}
