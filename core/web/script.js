
var socket;
var currentUser;

function signIn() {

    var credentials = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value

    };
    console.log(credentials);
    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8081/auth");
    request.setRequestHeader("Content-Type", "application/json");
    console.log(request);

    request.onreadystatechange = function () {

        if (request.readyState === XMLHttpRequest.DONE) {

            switch (request.status) {

                case 200:
                    currentUser = credentials.username;
                    var webSocketAccessToken = JSON.parse(request.responseText);
                    openSocket(webSocketAccessToken.token);
                    break;

                case 403:
                    currentUser = null;
                    document.getElementById("authentication-error").innerHTML = "Credentials are invalid.";
                    break;

                default:
                    document.getElementById("authentication-error").innerHTML = "Oops... Something goes wrong.";
            }
        }
    };

    request.send(JSON.stringify(credentials));
}

function registration() {}

function openSocket(accessToken) {

    if (socket) {
        socket.close();
    }

    socket = new WebSocket("ws://localhost:8081/chat?access-token=" + accessToken);

    socket.onopen = function (event) {
        document.getElementById("authentication").style.display = "none";
        document.getElementById("contacts").style.display = "block";
        document.getElementById("chat").style.display = "block";
        document.getElementById("message").focus();
    };

    socket.onmessage = function (event) {

        if (typeof event.data === "string") {

            var webSocketMessage = JSON.parse(event.data);
            switch (webSocketMessage.type) {

                case "welcomeUser":
                    displayConnectedUserMessage(webSocketMessage.payload.username);
                    break;

                case "broadcastTextMessage":
                    displayMessage(webSocketMessage.payload.username, webSocketMessage.payload.content);
                    break;

                case "broadcastConnectedUser":
                    displayConnectedUserMessage(webSocketMessage.payload.username);
                    break;

                case "broadcastDisconnectedUser":
                    displayDisconnectedUserMessage(webSocketMessage.payload.username);
                    break;

                case "broadcastAvailableUsers":
                    cleanAvailableUsers();
                    for (var i = 0; i < webSocketMessage.payload.usernames.length; i++) {
                        addAvailableUsers(webSocketMessage.payload.usernames[i]);
                    }
                    break;
            }
        }
    };
}

function sendMessage() {

    var text = document.getElementById("message").value;
    document.getElementById("message").value = "";

    var payload = {
        content: text
    };

    var webSocketMessage = {
        type: "sendTextMessage"
    };

    webSocketMessage.payload = payload;

    socket.send(JSON.stringify(webSocketMessage));
}

function displayMessage(username, text) {

    var sentByCurrentUer = currentUser === username;

    var message = document.createElement("div");
    message.setAttribute("class", sentByCurrentUer === true ? "message sent" : "message received");
    message.dataset.sender = username;

    var sender = document.createElement("span");
    sender.setAttribute("class", "sender");
    sender.appendChild(document.createTextNode(sentByCurrentUer === true ? "You" : username));
    message.appendChild(sender);

    var content = document.createElement("span");
    content.setAttribute("class", "content");
    content.appendChild(document.createTextNode(text));
    message.appendChild(content);

    var messages = document.getElementById("messages");
    var lastMessage = messages.lastChild;
    if (lastMessage && lastMessage.dataset.sender && lastMessage.dataset.sender === username) {
        message.className += " same-sender-previous-message";
    }

    messages.appendChild(message);
    messages.scrollTop = messages.scrollHeight;
}

function displayConnectedUserMessage(username) {

    var sentByCurrentUer = currentUser === username;

    var message = document.createElement("div");
    message.setAttribute("class", "message event");

    var text = sentByCurrentUer === true ? "Welcome " + username : username + " joined the chat";
    var content = document.createElement("span");
    content.setAttribute("class", "content");
    content.appendChild(document.createTextNode(text));
    message.appendChild(content);

    var messages = document.getElementById("messages");
    messages.appendChild(message);
}

function displayDisconnectedUserMessage(username) {

    var message = document.createElement("div");
    message.setAttribute("class", "message event");

    var text = username + " left the chat";
    var content = document.createElement("span");
    content.setAttribute("class", "content");
    content.appendChild(document.createTextNode(text));
    message.appendChild(content);

    var messages = document.getElementById("messages");
    messages.appendChild(message);
}

function addAvailableUsers(username) {

    var contact = document.createElement("div");
    contact.setAttribute("class", "contact");

    var status = document.createElement("div");
    status.setAttribute("class", "status");
    contact.appendChild(status);

    var content = document.createElement("span");
    content.setAttribute("class", "name");
    content.appendChild(document.createTextNode(username));
    contact.appendChild(content);

    var contacts = document.getElementById("contacts");
    contacts.appendChild(contact);
}

function cleanAvailableUsers() {
    var contacts = document.getElementById("contacts");
    while (contacts.hasChildNodes()) {
        contacts.removeChild(contacts.lastChild);
    }
}
