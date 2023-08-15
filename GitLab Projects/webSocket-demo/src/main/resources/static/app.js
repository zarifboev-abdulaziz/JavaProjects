var stompClient = null;
var currentUser = null;

async function getCurrentUser() {
    await fetch('/api/users/me')
        .then(function (response) {
            response.json()
                .then(data => {
                    currentUser = data;
                })
        })
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function usersChangeHandle(selectedObj) {
    $("#greetings").empty();
    getMessagesByUserId(selectedObj.value);
}

function getMessagesByUserId(userId) {
    fetch("/api/messages/" + userId)
        .then(function (response) {
            if (response.ok) {
                response.json().then(res => {
                    res.map(value => {
                        appendNewMessage(value)
                    })
                })
            }
        })
}


function getUsersFromServer() {
    fetch("/api/users")
        .then(function (response) {
            if (response.ok) {
                response.json().then(res => {
                    getMessagesByUserId(res[0].id);

                    res.map(value => {
                        showUsers(value)
                    })
                })
            }
        })
}


async function connect() {
    await getCurrentUser();

    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log(currentUser);
        getUsersFromServer();
        console.log('Connected: ' + frame);
        stompClient.subscribe(
            '/user/' + currentUser.id + '/queue/messages',
            function (messageDto) {
                const dataObj = JSON.parse(messageDto.body);
                    appendNewMessage(dataObj);
            }
        );
    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    $("#users").empty()
}

function sendMessage() {
    let dataStr = JSON.stringify(
        {
            'text': $("#name").val(),
            'receiverId': $("#users").val(),
            'receiverFullName': $("#users").find(":selected").text(),
            'senderId': currentUser.id,
            'senderFullName': currentUser.fullName
        }
    );
    stompClient.send(
        "/app/hello",
        {},
        dataStr
    );
}

function appendNewMessage(message) {
    let tdAlign = message.senderId === currentUser.id ? 'right' : 'left';
    $("#greetings").append("<tr>" +
        "<td align='" + tdAlign + "'>" +
        "<h6>" + message.senderFullName + "</h6>" +
        "<h3>" + message.text + "</h3>" +
        "<p>" + message.localDateTime + "</p>" +
        "<p>" + message.messageStatus + "</p>" +
        "</td>" +
        "</tr>");
}

function showUsers(user) {
    console.log(user);
    $("#users").append(
        "<option value=" + user.id + ">" + user.fullName + "</option>"
    );
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });
});