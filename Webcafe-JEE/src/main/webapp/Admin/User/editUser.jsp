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

                    <h2>Edit user<span class="subtitle"></span></h2>
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
                    <form id="register" method="post" action="${root}/Admin/Tables/User/Edit" enctype="multipart/form-data">
                        <ul>
                            <li>
                                <label for="userId">user id</label>
                                <input type="text" readonly name="userId" id="userId" value="${editUser.getId()}">
                            </li>
                            <li>
                                <label>is admin</label>
                                <select name="isAdmin">
                                    <c:if test="${editUser.getIsAdmin()}">
                                        <option value="true" selected="selected">True</option>
                                        <option value="false">False</option>
                                    </c:if>
                                        <c:if test="${!editUser.getIsAdmin()}">
                                            <option value="true">True</option>
                                            <option value="false" selected="selected">False</option>
                                        </c:if>
                                </select>
                            </li>
                            <li>
                                <label for="email">Email: </label>
                                <input id="email" type="email" required name="email"
                                       value="${editUser.getEmail()}"/>
                            </li>
                            <li>
                                <label for="pwd">Password: </label>
                                <input id="pwd" type="text" required name="pwd"
                                       value="${editUser.getPassword()}"/>
                            </li>
                            <li>
                                <label for="name">Name: </label>
                                <input id="name" type="text" required name="name"
                                       value="${editUser.getName()}"/>
                            </li>
                            <li>
                                <label for="forename">Forename: </label>
                                <input id="forename" type="text" required name="forename"
                                       value="${editUser.getForename()}"/>
                            </li>
                            <li>
                                <label for="bdate">Birthday: </label>
                                <input id="bdate" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                                       placeholder="dd/mm/yyyy" required name="bdate"
                                       value="<fmt:formatDate pattern="dd/MM/yyyy" value="${editUser.getBirthday()}" />"/>
                            </li>
                            <li>
                                <label for="firm">Firm: </label>
                                <input id="firm" type="text" name="firm"
                                       value="${editUser.getFirm()}"/>
                            </li>
                            <li>
                                <label for="function">Function: </label>
                                <input id="function" type="text" name="function"
                                       value="${editUser.getFirmFunction()}" />
                            </li>
                            <li>
                                <label>Photo: </label>
                                <input id="image" type="file" name="file"/>
                                <c:if test="${editUser.getImagePath() != null}">
                                    <img src="${root}/${editUser.getImagePath()}" width="30" height="30" />
                                </c:if>
                            </li>
                            <li>
                                <input type="submit" value="Update"/>
                            </li>
                        </ul>
                    </form>
                    <div style="clear:left;"></div>
                </section>
            </section>
    </body>
</html>
