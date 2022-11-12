var stompClient = null;
var websocketPanel = document.querySelector("#websocket_handling");
var messageInput = document.querySelector("#message_input");
var responseField = document.querySelector("#response_field");

var keyInput = document.querySelector("#key_set_input");
var valueInput = document.querySelector("#value_set_input");
var tableKV = document.querySelector("#kv_table")

function setVisibleWebsocketPanel(isVisible) {
    websocketPanel.style.display = isVisible ? "block" : "none";
}

function connect() {
    var socket = new SockJS('/echo');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setVisibleWebsocketPanel(true);
        console.log("Connected: " + frame);
        stompClient.subscribe('/topic/echo', function(messageOutput) {
            showMessage(JSON.parse(messageOutput.body))
        })
        stompClient.subscribe("/topic/kv/items", function (response) {
            updateKVTable(JSON.parse(response.body))
        })
        setInterval(sendTableUpdateRequest, 500);
    })

}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setVisibleWebsocketPanel(false);
    messageInput.value = "";
    responseField.innerHTML = "";
    console.log("Disconnected");
}

function sendMessage() {
    let message = messageInput.value;
    let requestBody = JSON.stringify( {
        "text": message
    })
    stompClient.send("/app/webs", {}, requestBody);
}

function showMessage(message) {
    if (message.text.length > 0) {
        responseField.innerHTML = "Ответ сервера: " + message.text;
    }
}

function sendKV() {
    let key = keyInput.value;
    let value = valueInput.value;
    if (key.length > 0 && value.length > 0) {
        stompClient.send("/app/kv/set", {}, JSON.stringify({"key": key, "value": value}))
    }
}

function sendDeletionKV() {
    let key = keyInput.value;
    if (key.length > 0) {
        stompClient.send("/app/kv/delete", {}, JSON.stringify({"key": key, "value": ""}))
    }
}

function updateKVTable(table) {
    tableKV.innerHTML = "<tr><th>key</th><th>value</th></tr>"
    console.log(table)
    for (let elem of table) {
        console.log(elem)
        tableKV.innerHTML += "<tr><td>" + elem["key"] + "</td><td>" + elem["value"] +"</td></tr>"
    }
}

function sendTableUpdateRequest() {
    stompClient.send("/app/kv/items")
}