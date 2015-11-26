package filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.UsersUtil;

/* @author BertGoens */
public class AccountFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.context = fc.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User currentUser = UsersUtil.getLoggedInUser(context);
        if (currentUser != null) {
            chain.doFilter(request, response);
            return;
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(context.getContextPath() + "/User/Login");
            return;
        }
    }

    @Override
    public void destroy() {

    }

}
