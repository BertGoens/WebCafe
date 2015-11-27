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

        <h2>Your account<span class="subtitle"></span></h2>
        <div class="border"></div>
        <c:if test="${errors != null}">
            <ul>
                <c:forEach items="${errors}" var="entry">
                    <li>${entry.value}</li>
                    </c:forEach>
            </ul>
            <br>
        </c:if>
        <div class="border"></div>

        <form id="register" method="post" action="${root}/Account" enctype="multipart/form-data">
            <ul>
                <li>
                    <input type="hidden" readonly name="userId" id="userId" value="${loggedInUser.getId()}">
                </li>
                <li>
                    <label for="email">Email: </label>
                    <input id="email" type="email" required name="email"
                           value="${loggedInUser.getEmail()}"/>
                </li>
                <li>
                    <label for="pwd">Password: </label>
                    <input id="pwd" type="password" required name="pwd"/>
                </li>
                <li>
                    <label for="name">Name: </label>
                    <input id="name" type="text" required name="name"
                           value="${loggedInUser.getName()}"/>
                </li>
                <li>
                    <label for="forename">Forename: </label>
                    <input id="forename" type="text" required name="forename"
                           value="${loggedInUser.getForename()}"/>
                </li>
                <li>
                    <label for="bdate">Birthday: </label>
                    <input id="bdate" type="text" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                           placeholder="dd/mm/yyyy" required name="bdate"
                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${loggedInUser.getBirthday()}" />"/>
                </li>
                <li>
                    <label for="firm">Firm: </label>
                    <input id="firm" type="text" name="firm"
                           value="${loggedInUser.getFirm()}"/>
                </li>
                <li>
                    <label for="function">Function: </label>
                    <input id="function" type="text" name="function"
                           value="${loggedInUser.getFirmFunction()}" />
                </li>
                <li>
                    <label>Photo: </label>
                    <c:if test="${loggedInUser.getImagePath() != null}">
                        <img src="${root}/${loggedInUser.getImagePath()}" width="30" height="30" />
                    </c:if>
                    <input id="image"  type="file" name="file"/>
                </li>
                <li>
                    <input type="submit" value="Update"/>
                </li>
            </ul>
        </form>
        <br>

                           <h2>Your WebCafés<span class="subtitle"></span></h2>
                           <div class="border"></div>
                           <c:if test="${futureEvents != null}">
                               <section>
                                   <c:forEach items="${futureEvents}" var="event">
                                       <article>
                                           <h2>${event.getVenue()}</h2>
                                           <li>${event.getCode()} ${event.getCity()}</li>
                                           <li>
                                               ${evt.getDate()}
                                               <fmt:formatDate pattern="HH-mm" value="${evt.getStartTime()}" />h -
                                               <fmt:formatDate pattern="HH-mm" value="${evt.getEndTime()}" />h
                                           </li>
                                           <li>
                                               <span class="bold">Fee</span>:
                                               <c:if test="${event.getFee() == 0}">Free</c:if>
                                               <c:if test="${event.getFee() > 0}">&euro;<fmt:formatNumber type="number" maxFractionDigits="2" value="${event.getFee()}" /></c:if>
                                               </li>
                                               <li>
                                                   <span class="bold">For who</span>: ${event.getForWho()}
                                           </li>
                                           <li><a href="${root}/Event/Detail?id=${event.getId()}">View details</a></li>
                                       </article>
                                   </c:forEach>
                               </section>
                           </c:if>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
