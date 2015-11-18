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

        <article class="archive">
            <c:forEach items="${pastEventList}" var="evt">
                <h2>Java Webcaf&#233; @ <i>&#34;</i>${evt.getLocation()}<i>&#34;</i>
                    <span class="subtitle">
                        <c:forEach items="${evt.getAgendaItemList()}" var="agendaItem">
                            ${agendaItem.getThemeTitel()}
                        </c:forEach>
                    </span></h2>
                <div class="border"></div>
                <p><img width="300" src="${evt.getImagePath()}"/>
                    ${evt.getEventDescription()}
                <div class="button normalButton" ><a href="Event/Detail?Id=${evt.getId()}">View more</a></div>

                <div style="clear:both"></div>
            </c:forEach>
        </article>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
