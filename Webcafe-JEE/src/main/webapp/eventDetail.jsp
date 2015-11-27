<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
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

        <c:if test="${detailEvent.getVisitorsList().contains(loggedInUser)}">
            <p>I can't go, <a href="${root}/Account/Event/Unregister?id=${detailEvent.getId()}">unsubscribe</a> from the event.</p>
            <br />
        </c:if>

        <article>
            <h2>${detailEvent.getName()} <span class="subtitle"> </span></h2>
            <div class="border"></div>
            <section class="content-left">
                <img width="250" height="200"style="margin-top:0.5em" src="${root}${detailEvent.getImagePath()}">
            </section>
                <section class="content-right">
                    <div id="google_map" address="${detailEvent.getLocation()}, ${detailEvent.getCity().getCode()} ${detailEvent.getCity().getName()}, ${detailEvent.getCity().getProvince().getName()}"></div>
                </section>
                <div style="clear:both"></div>
                <p>${detailEvent.getEventDescription()}</p>

        </article>
            <article>
                <h2>Agenda:</h2>
                <ul class="agenda">
                    <c:forEach items="${detailEvent.getAgendaItemList()}" var="ag" >
                        <p>${evtAgenda}</p>
                        <li>
                            <fmt:formatDate pattern="H-mm" value="${ag.getStartDate()}" />h -
                            <fmt:formatDate pattern="H-mm" value="${ag.getEndDate()}" />h :
                            ${ag.getThemeTitel()}
                            <p>${ag.getDescription()}</p>
                        </li>
                    </c:forEach>
                </ul>
            </article>
            <article>
                <h2>Venue</h2>
                <div class="border"></div>
                <ul class="venue">
                <li>
                    <ol>
                        <ol>
                            <li>
                                <span class="bold">Venue </span>: ${detailEvent.getVenue()}
                            </li>
                            <li>
                                ${detailEvent.getLocation()}
                            </li>
                            <li>
                                ${detailEvent.getCity().getCode()} ${detailEvent.getCity().getName()}
                            </li>
                        </ol>
                </li>
                <li>
                    <ol>
                        <li>
                            <span class="bold">When </span>: <fmt:formatDate pattern="dd/MM/yyyy" value="${detailEvent.getDate()}" />
                        </li>
                        <li>
                            <span class="bold">What time</span>:
                            <fmt:formatDate pattern="HH-mm" value="${detailEvent.getStartTime()}" />h -
                            <fmt:formatDate pattern="HH-mm" value="${detailEvent.getEndTime()}" />h
                        </li>
                        <li>
                            <span class="bold">Fee</span> :
                            <c:if test="${detailEvent.getFee() == 0}">Free</c:if>
                            <c:if test="${detailEvent.getFee() > 0}">&euro; <fmt:formatNumber type="number" maxFractionDigits="2" value="${detailEvent.getFee()}" /></c:if>
                            </li>
                            <li>
                                <span class="bold">For who</span>: ${detailEvent.getForWho()}
                        </li>
                    </ol>
                </li>
            </ul>
        </article>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <c:set var="currentEventId" value="1" />
        <c:if test="${detailEvent.getId() != nextEvent.getId()}">
            <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        </c:if>
        <c:if test="${detailEvent.getId() == nextEvent.getId()}">
            <%@include file="WEB-INF/jspf/widgets/joinUs.jspf" %>
        </c:if>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
