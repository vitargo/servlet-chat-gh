<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Authentication</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<form action="UserHandler" method="post">--%>
<%--    <p><strong>Please Enter Your User Name: </strong>--%>
<%--        <input type="text" name="username" size="25">--%>
<%--    <p><p><strong>Please Enter Your Password: </strong>--%>
<%--    <input type="password" size="15" name="password">--%>
<%--    <p><p>--%>
<%--    <input type="submit" value="Submit">--%>
<%--    <input type="reset" value="Reset">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>


<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>PinkLink Chat</title>
    <meta name="description" content="Servlet chat application">
    <meta name="author" content="PinkLink Team">
    <link rel="stylesheet" href="./style.css">
    <script acync type="text/javascript" src = "script.js"></script>
</head>
<body>

<div id="title">PinkLink Chat</div>

<form id="authentication" action="UserHandler" method="post" onsubmit="return false;">
    <input type="text" id="username" placeholder="Username" autofocus/>
    <input type="password" id="password" placeholder="Password"/>
    <button class="button" onclick="signIn()">Sign In</button>
    <span class="error" id="authentication-error"></span>
</form>

<div id="contacts"></div>

<div id="chat">
    <div id="messages"></div>
    <form id="chat-controls" onsubmit="return false;">
        <input type="text" id="message" placeholder="Enter a message"/>
        <button class="button" onclick="sendMessage()">Send</button>
    </form>
</div>

</body>
</html>
