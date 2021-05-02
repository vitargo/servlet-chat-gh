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
<span>Have not account! Sign up here</span>
<a href="/registration.jsp">Registration form</a>
<form id="authentication" action="UserHandler" method="post" onsubmit="return false;">
    <input type="text" id="username" placeholder="Username" autofocus/>
    <input type="password" id="password" placeholder="Password"/>
    <button class="button" onclick="registration()">Confirm</button>
    <span class="error" id="authentication-error"></span>
</form>
</body>
</html>
