<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${root}/css/webcafe_layout.css" rel="stylesheet" media="screen"/>
        <title>Java Webcafe</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <%@include file="WEB-INF/jspf/contentContainerOpen.jspf" %>

        <h2>Login<span class="subtitle"></span></h2>
        <div class="border"></div>
        <form id="login" method="post" action="${root}/User/Login">

            <!--<input type="hidden" id="event" name="event" value="90"/>!-->

            <ul>
                <li>
                    <label for="email">Email: </label>
                    <input id="email" type="email" required name="email"/>
                </li>
                <li>
                    <label for="pwd">Password: </label>
                    <input id="pwd" type="password" required name="pwd"/>
                </li>
                <li>
                    <input type="submit" value="Login"/><br/>
                    <a style="font-size:9pt; color:#685236;" href="registerUser.jsp">
                        Don't have an account yet?
                    </a>
                </li>
            </ul>
        </form>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
