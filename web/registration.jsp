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
<div id="title">PinkLink Registration Form</div>
<a href="/main.jsp">Chat</a>
<form id="registration" action="UserHandler" method="post">
  <input type="text" id="username" placeholder="Username" autofocus/>
  <input type="password" id="password" placeholder="Password"/>
  <input type="text" name="nickname" placeholder="Nickname" autofocus/>
  <input type="text" name="first_name" placeholder="First name" />
  <input type="text" name="last_name" placeholder="Last name" />
  <input type="text" name="login" placeholder="Login" /></td>
  <input type="password" name="password" placeholder="Password"/>
  <input type="password" name="passwordConfirm" placeholder="Confirm your password" />
  <input type="text" name="email" placeholder="Email" />
  <input type="text" name="contact" placeholder="Phone number" />
  <button class="button" onclick="signIn()">Sign In</button>
  <span class="error" id="registration-error"></span>
</form>
</body>
</html>