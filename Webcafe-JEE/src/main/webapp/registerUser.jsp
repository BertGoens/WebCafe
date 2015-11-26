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

        <h2>Register<span class="subtitle"></span></h2>
        <div class="border"></div>
        <c:if test="${errors != null}">
            <ul>
                <c:forEach items="${errors}" var="entry">
                    <li>${entry.value}</li>
                    </c:forEach>
            </ul>
        </c:if>
        <div class="border"></div>
        <form id="register" method="post" action="${root}/User/Register?id=${id}" enctype="multipart/form-data">
            <c:if test="${id != null && id > 0}">
                <input type="hidden" id="id" name="id" value="${id}"/>
            </c:if>

                <ul>
                    <li>
                        <label for="email">Email: </label><input id="email" type="email" required name="email" value=""/>

                </li>

                <li><label for="pwd">Password: </label><input id="pwd" type="password" required name="pwd"/></li>
                <li><label for="name">Name: </label><input id="name" type="text" required name="name" value=""/></li>
                <li><label for="forename">Forename: </label><input id="forename" type="text" required name="forename" value=""/></li>
                <li><label for="bdate">Birthdate: </label><input id="bdate" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" placeholder="dd/mm/yyyy" required name="bdate" value=""/></li>
                <li><label for="firm">Firm: </label><input id="firm" type="text" name="firm"/></li>
                <li><label for="function">Function: </label><input id="function" type="text" name="function" /></li>
                <li>
                    <label>Photo: </label><input id="image"  type="file" name="file" required/>
                </li>
                <li><input type="submit" value="Register"/></li>
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
