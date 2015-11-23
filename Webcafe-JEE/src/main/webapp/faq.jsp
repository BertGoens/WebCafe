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

        <article>
            <h2>What is the Java Webcafé?</h2>
            <div class="border"></div>
            <p>The Java Webcafé is a new initiative taken by Katho Kortrijk to fully support its web partners and to offer a networking platform to everybody in the Web Industry in Belgium. Designers, developers - not limited to jsp &amp; servlets, interaction designers, web entrepreneurs, aficionados are all welcome to join these informal get toge
                thers; Join us!</p>
        </article>
        <article class="mini">
            <h3>Industry Updates <span class="subtitle">/ Latest Webnews</span></h3>
            <div class="smallBorder"></div>
            <p>The Java Webcafé is the ideal platform to stay up to date with Industry News brought to you by our very own evangelists. Learn more about new Java technologies and platforms. We also keep you informed about decisions regarding HTML5, CSS3, new developments, ...</p>

        </article>
        <article class="mini">
            <h3>Learn from Cases <span class="subtitle">/ Cool Projects!</span></h3>
            <div class="smallBorder"></div>
            <p>The best way to learn about our technologies is to see their power in real-life cases. Therefore we want this webcafé to be the platform for all Katho Partners to share cases and experiences with everyone. If you did a great project with Java technology, let us know and maybe you will be presenting next time!</p>
        </article>
        <article class="mini">
            <h3>Something else <span class="subtitle">/ Fun and drinks </span></h3>
            <div class="smallBorder"></div>
            <p>The Java Webcafé is the ideal platform to stay up to date with Industry News brought to you by our very own evangelists. Learn more about new Java technologies and platforms. We also keep you informed about decisions regarding HTML5, CSS3, new developments, ... .</p>
        </article>
        <article class="mini">
            <h3>Networking <span class="subtitle">/ Get to know each other</span></h3>
            <div class="smallBorder"></div>
            <p>There are many professionals in Belgium involved in design and / or development, already using Java technology or maybe not. The Webcafé is the ideal setting to get to know people, to find partners, share ideas and experiences. On this website you can add links to your profiles elsewhere, to make the networking experience complete.</p>
        </article>

        <%@include file="WEB-INF/jspf/contentContainerClose.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsOpen.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/upcoming.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/suggestACase.jspf" %>
        <%@include file="WEB-INF/jspf/widgets/widgetsClose.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
