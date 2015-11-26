<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/webcafe_layout.css" rel="stylesheet" media="screen"/>
        <title>Java Webcafe</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <%@include file="WEB-INF/jspf/contentContainerOpen.jspf" %>

        <ul>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
            <li><a href="${root}/Admin?table="></a></li>
        </ul>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
