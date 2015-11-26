package util;

import java.util.HashMap;
import javax.servlet.ServletContext;
import model.User;

/* @author BertGoens */
public final class UsersUtil {

    //Application scope strings
    public static String AS_USERSLOGGEDIN = "usersLoggedIn";
    public static String AS_USERMAP = "userMap";
    //Session scope strings
    public static String SS_LOGGEDINUSER = "loggedInUser";
    public static String SS_USERSLOGGEDIN = "usersLoggedIn";

    public static void setLoggedInUser(ServletContext context, User loginUser) {
        HashMap<User, Integer> userMap = (HashMap< User, Integer>) context.getAttribute(AS_USERMAP);
        User currentlyLoggedInUser = getLoggedInUser(context);

        if (currentlyLoggedInUser != null) {
            logoutCurrentUser(context);
        }

        if (userMap != null && userMap.containsKey(loginUser)) {
            Integer count = userMap.get(loginUser);
            userMap.put(loginUser, count + 1);
        } else {
            userMap.put(loginUser, 1);
            Integer usersLoggedIn = (Integer) context.getAttribute(AS_USERSLOGGEDIN);
            usersLoggedIn++;
            context.setAttribute(AS_USERSLOGGEDIN, usersLoggedIn);
        }

        context.setAttribute(AS_USERMAP, userMap);
        context.setAttribute(SS_LOGGEDINUSER, loginUser);
    }

    public static User getLoggedInUser(ServletContext context) {
        return (User) context.getAttribute(SS_LOGGEDINUSER);
    }

    public static void logoutCurrentUser(ServletContext context) {
        HashMap<User, Integer> userMap = (HashMap< User, Integer>) context.getAttribute(AS_USERMAP);
        User loggedInUser = getLoggedInUser(context);
        Integer usersLoggedIn = (Integer) context.getAttribute(SS_USERSLOGGEDIN);

        if (userMap.containsKey(loggedInUser)) {
            Integer count = userMap.get(loggedInUser);
            userMap.put(loggedInUser, count - 1);

            //meerdere sessies open
        } else {
            userMap.remove(loggedInUser);
            usersLoggedIn--;
            context.setAttribute(SS_USERSLOGGEDIN, usersLoggedIn);
        }

        context.setAttribute(AS_USERMAP, userMap);

        context.setAttribute(SS_LOGGEDINUSER, null);

    }
}
