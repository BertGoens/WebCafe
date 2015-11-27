<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${root}/css/webcafe_layout.css" rel="stylesheet" media="screen"/>
        <title>Missing Webcafe</title>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/header.jspf" %>
        <%@include file="../WEB-INF/jspf/contentContainerOpen.jspf" %>

        <article>
            <h2>Where did that go?!</h2>
            <div class="border"></div>
            <p>It seems like you found a broken link, please <a href="${root}/Contact">report</a> it!</p>
            <p>Or return <a href="${root}/Home">home</a>.</p>
        </article>

            <%@include file="../WEB-INF/jspf/contentContainerClose.jspf" %>
            <%@include file="../WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
            <%@include file="../WEB-INF/jspf/widgets/upcoming.jspf" %>
            <%@include file="../WEB-INF/jspf/widgets/suggestACase.jspf" %>
            <%@include file="../WEB-INF/jspf/widgets/widgetsClose.jspf" %>
            <%@include file="../WEB-INF/jspf/footer.jspf" %>
    </body>
</html>

