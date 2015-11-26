<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <c:forEach items="${nextEventList}" var="evt">
            <article>

                <h2>Java Webcaf&#233; @ <i>&#34;</i>${evt.getVenue()}<i>&#34;</i> <span class="subtitle"> </span></h2>
                <div class="border"></div>
                <section class="content-left">
                    <ul>
                        <li>
                            <ol>
                                <li>
                                    <span class="bold">Venue </span>: ${evt.getVenue()}
                                </li>
                                <li>
                                    ${evt.getLocation()}
                                </li>
                                <li>
                                    ${evt.getCity().getCode()} ${evt.getCity().getName()}
                                </li>
                            </ol>
                        </li>
                        <li>
                            <ol>
                                <li>
                                    <span class="bold">When </span>: ${evt.getDate()}
                                </li>
                                <li>
                                    <span class="bold">What time</span>: <fmt:formatDate pattern="H-mm" value="${evt.getStartTime()}" />h - <fmt:formatDate pattern="H-mm" value="${evt.getEndTime()}" />h
                                </li>
                                <li>
                                    <span class="bold">Fee</span>:
                                    <c:if test="${evt.getFee() == 0}">Free</c:if>
                                    <c:if test="${evt.getFee() > 0}">&euro;<fmt:formatNumber type="number" maxFractionDigits="2" value="${evt.getFee()}" /></c:if>
                                    </li>
                                    <li>
                                        <span class="bold">For who</span>: ${evt.getForWho()}
                                </li>
                            </ol>
                        </li>
                    </ul>
                </section>
                <section class="content-right">

                    <p><img width="150" height="100" src="${evt.getImagePath()}"/>
                        ${evt.getEventDescription()}
                    </p>
                    <div class="button normalButton" >
                        <a href="${root}/Event/Detail?id=${evt.getId()}">View agenda</a>
                    </div>
                    <div style="clear:right;"></div>
                </section>
                <div style="clear:both"></div>
            </article>
        </c:forEach>

        <ul class="paginatorList">
            <c:forEach begin="1" end="${endPage + 1}" var="p">
                <c:if test="${p == currentPage + 1}">
                    <li class="paginatorCurr paginatorLast">${p}</li>
                    </c:if>
                        <c:if test="${p != currentPage + 1}">
                        <li><a href="${root}/Home?page=${p}">${p}</a></li>
                    </c:if>
                </c:forEach>
        </ul>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/joinUs.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
