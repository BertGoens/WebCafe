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

        <h2 style="color:#848c30">Contact Us</h2>
        <div class="border"></div>
        <form method="post">
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
        <form method="post">
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
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
