package util;

import java.util.HashMap;
import javax.servlet.ServletContext;
import model.User;

/* @author BertGoens */
public final class UsersUtil {

    public static void setLoggedInUser(ServletContext context, User loggedInUser) {
        HashMap<User, Integer> userMap = (HashMap< User, Integer>) context.getAttribute("userMap");

        if (userMap != null && userMap.containsKey(loggedInUser)) {
            Integer count = userMap.get(loggedInUser);
            userMap.put(loggedInUser, count + 1);
        } else {
            userMap.put(loggedInUser, 1);
            Integer usersLoggedIn = (Integer) context.getAttribute("usersLoggedIn");
            usersLoggedIn++;
            context.setAttribute("usersLoggedIn", usersLoggedIn);
        }

        context.setAttribute("userMap", userMap);
        context.setAttribute("loggedInUser", loggedInUser);
    }

    public static User getLoggedInUser(ServletContext context) {
        User loggedInUser;
        loggedInUser = (User) context.getAttribute("loggedInUser");
        return loggedInUser;
    }

}
