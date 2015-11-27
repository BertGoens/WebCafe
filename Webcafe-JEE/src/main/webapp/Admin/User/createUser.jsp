<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${root}/css/webcafe_layout.css" rel="stylesheet" media="screen"/>
        <link href="${root}/css/admin.css" rel="stylesheet" media="screen" />
        <title>Admin dashboard</title>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/header.jspf" %>
        <section id="contentContainer">
            <section id="middle">
                <section class="overview">
                    <%@include file="../adminTableLinks.jspf" %>
                    <h1>${tableName}</h1>

                    <h2>Create user<span class="subtitle"></span></h2>
                    <div class="border"></div>
                    <c:if test="${errors != null}">
                        <ul>
                            <c:forEach items="${errors}" var="entry">
                                <li>${entry.value}</li>
                                </c:forEach>
                        </ul>
                        <br>
                    </c:if>
                    <div class="border"></div>
                    <form id="register" method="post" action="${root}/Admin/Tables/User/Create" enctype="multipart/form-data">
                        <ul>
                            <li>
                            <li>is admin</li>
                            <select name="isAdmin">
                                    <option value="true">True</option>
                                    <option value="false" selected="selected">False</option>
                                </select>
                            </li>
                            <li>
                                <label for="email">Email: </label>
                                <input id="email" type="email" required name="email" value=""/>
                            </li>
                            <li>
                                <label for="pwd">Password: </label>
                                <input id="pwd" type="password" required name="pwd"/>
                            </li>
                            <li>
                                <label for="name">Name: </label>
                                <input id="name" type="text" required name="name" value=""/>
                            </li>
                            <li>
                                <label for="forename">Forename: </label>
                                <input id="forename" type="text" required name="forename" value=""/>
                            </li>
                            <li>
                                <label for="bdate">Birthdate: </label>
                                <input id="bdate" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" placeholder="dd/mm/yyyy" required name="bdate" value=""/>
                            </li>
                            <li>
                                <label for="firm">Firm: </label>
                                <input id="firm" type="text" name="firm"/>
                            </li>
                            <li>
                                <label for="function">Function: </label>
                                <input id="function" type="text" name="function" />
                            </li>
                            <li>
                                <label>Photo: </label>
                                <input id="image"  type="file" name="file" required/>
                            </li>
                            <li>
                                <input type="submit" value="Register"/>
                            </li>
                        </ul>
                    </form>
                    <div style="clear:left;"></div>
                </section>
            </section>
    </body>
</html>
