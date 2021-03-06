<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
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

        <h2 style="color:#848c30">Contact Us</h2>
        <div class="border"></div>
        <c:if test="${errors != null}">
            <c:if test="${type.equals(\"contact\")}">
                <ul>
                    <c:forEach items="${errors}" var="entry">
                        <li>${entry.value}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </c:if>
        <div class="border"></div>
        <form method="post" action="${root}/Contact">
            <input type="hidden" name="type" value="contact"/>
            <ul style="line-height:200%">
                <li><input name="name" required type="text" placeholder="Name *" class="input"></li>
                <li><input name="email" required type="email" class="input" placeholder="E-mail *"></li>
                <li><textarea required name="message" class="input" placeholder="Message *"></textarea></li>
                <li><button class="button normalButton" >Ok</button></li>
            </ul>
            <div style="clear:right;"></div>
        </form>
        <a id="bring-a-case"></a>
        <h2 style="color:#848c30">Do you have a case that you want to bring?</h2>
        <div class="border"></div>
        <c:if test="${errors != null}">
            <c:if test="${type.equals(\"bring-a-case\")}">
                <ul>
                    <c:forEach items="${errors}" var="entry">
                        <li>${entry.value}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </c:if>
        <div class="border"></div>
        <form method="post" action="${root}/Contact">
            <input type="hidden" name="type" value="bring-a-case"/>
            <ul style="line-height:200%">
                <li><input required type="text" name="name" placeholder="Name *" class="input"></li>
                <li><input required type="email" name="email" class="input" placeholder="E-mail *"></li>
                <li><textarea required class="input" name="message" placeholder="Message *"></textarea></li>
                <li><button class="button normalButton">Ok</button></li>
            </ul>
        </form>
        <div style="clear:left;"></div>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
