<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${root}/css/webcafe_layout.css" rel="stylesheet" media="screen"/>
        <title>Admin dashboard</title>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/header.jspf" %>
        <section id="contentContainer">
            <section id="middle">
                <section class="overview">
                    <p>Welcome to the admin dashboard</p>
                    <%@include file="adminTableLinks.jspf" %>
                    <div style="clear:left;"></div>
                </section>
            </section>
        </body>
</html>
