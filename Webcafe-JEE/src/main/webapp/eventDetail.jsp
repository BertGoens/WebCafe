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


        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <c:set var="currentEventId" value="1" />
        <c:if test="${currentEventId == nextEvent.getId()}">
            <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        </c:if>
        <c:if test="${currentEventId != nextEvent.getId()}">
            <%@include file="WEB-INF/jspf/widgets/joinUs.jspf" %>
        </c:if>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
