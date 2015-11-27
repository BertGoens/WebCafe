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

                    <c:if test="${errors != null}">
                        <ul>
                            <c:forEach items="${errors}" var="entry">
                                <li>${entry.value}</li>
                                </c:forEach>
                        </ul>
                        <br>
                    </c:if>

                    <a href="${root}/Admin/Tables/User/Create">
                        <img src="${root}/images/create_user.png" width="40" height="40" style="padding: 5px;">
                    </a>
                    <table>
                        <tr>
                                <th>#</th>
                                <th>usr_id</th>
                                <th>isAdmin</th>
                            <th>forename</th>
                            <th>name</th>
                            <th>password</th>
                            <th>email</th>
                            <th>firm</th>
                            <th>firmFunction</th>
                            <th>birthday</th>
                            <th>imagePath</th>
                            <th>-Actions-</th>
                        </tr>
                        <c:if test="${findAll != null}">
                            <c:forEach items="${findAll}" var="user" varStatus="i">
                                <tr>
                                    <td>${i.count}</td>
                                    <td>${user.getId()}</td>
                                    <td>${user.getIsAdmin()}</td>
                                    <td>${user.getForename()}</td>
                                    <td>${user.getName()}</td>
                                    <td>${user.getPassword()}</td>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getFirm()}</td>
                                    <td>${user.getFirmFunction()}</td>
                                    <td>${user.getBirthday()}</td>
                                    <td>
                                        <c:if test="${user.getImagePath() != null}">
                                            <a href="${root}${user.getImagePath()}" target="_empty" />
                                            <img src="${root}${user.getImagePath()}" height="60" width="60" />
                                            </a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="${root}/Admin/Tables/User/Edit?id=${user.getId()}">
                                            <img src="${root}/images/edit_icon.png" height="30" width="30" style="padding: 3px;">
                                        </a>
                                        <a href="${root}/Admin/Tables/User/Delete?id=${user.getId()}">
                                            <img src="${root}/images/delete_icon.png" height="30" width="30" style="padding: 3px;">
                                        </a>
                                        </ul>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>

                        <div style="clear:left;"></div>
                </section>
            </section>
        </body>
        </html>
