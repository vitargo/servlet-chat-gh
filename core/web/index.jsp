<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>PinkLink Chat</title>
    <meta name="description" content="Servlet chat application">
    <meta name="author" content="PinkLink Team">
    <link type="text/css" rel="stylesheet" href="./style.css" />
<%--    <script acync type="text/javascript" src = "script.js"></script>--%>
</head>
<body>

<div id="title">PinkLink Chat</div>
<div style="text-align: right;">
    <span>Have not account! Sign up here</span>
    <a href="/registration.jsp">Registration form</a>
</div>
<form id="authentication" action="UserHandler" method="post" onsubmit="return false;">
    <input type="text" id="login" placeholder="Login" autofocus/>
    <input type="password" id="password" placeholder="Password"/>
    <button class="button" onclick="signIn()">Confirm</button>
    <span class="error" id="authentication-error"></span>
</form>
</body>
</html>
