<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <span class="bold">What time</span>: ${evt.getStartTime()}h - ${evt.getEndTime()}h
                            </li>
                            <li>
                                <span class="bold">Fee</span>: ${evt.getFee()}
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
                                    <div class="button normalButton" ><a href="detail.html">
                                            <!-- Onze knop dient, afhankelijk van het aantal beschikbare plaatsen een andere tekst weer te geven
                                            Graag hadden we standaard View agenda te zien gekregen op deze knop, wanneer
                                            er dan echter geen zitplaatsen meer beshcikbaar zijn hadden we graag de tekst
                                            'View more' zien verschijnen. hint: ons event object beschikt over een methode hasSeatsAvailable-->

                                            View agenda


                                        </a>
                                    </div>
                                    <div style="clear:right;"></div>
                                </section>
                                <div style="clear:both"></div>
            </article>
        </c:forEach>
        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
