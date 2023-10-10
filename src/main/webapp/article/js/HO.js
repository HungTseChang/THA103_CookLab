//article_edit-HO//
$(function() {
	
	$("#btn_drawft").click(function(){
		$("input[name='articleStatus']").val("3");
		//還可以進行其他操作，例如驗證或其他處理
		$("#form1").submit();
	})
});

//====  article_main   =========
$(function() {
	$(".btn.custom-btn").on("click", function() {
		$(".btn.custom-btn").removeClass(".btn HO-btn-org");
		$(this).toggleClass(".btn HO-btn-org");
	});

	$(".page-link").on("click", function() {
		$(".page-link").removeClass(".btn HO-btn-org");
		$(this).toggleClass(".btn HO-btn-org");
	});

	$('#article_link').click(function(e) {
		e.preventDefault(); // 阻止默认的链接跳转行为
		var targetUrl = $(this).attr('href'); // 获取链接的目标 URL
		window.location.href = targetUrl; // 使用 JavaScript 跳转到目标 URL
	});
});





//=======WebChat===========
window.onload = function() {
	var MyPoint = "/TogetherWS/james";//可以當作url ,why?
	//下方的四個var 都是用於動態取得專案路徑(Contentpath path)
	var host = window.location.host;
	var path = window.location.pathname; //動態取得專案路徑
	var webCtx = path.substring(0, path.indexOf('/', 1));
	//ws 是websocket的通訊協定
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	//上面的網址結果 ws://localhost:8081/WebSocketChatWeb/TogetherWS/james

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL); //把上方的網址傳進來

		//onopen 就像是init() 執行一次，當連線開始時就會執行
		webSocket.onopen = function(event) { //onxxxx(當xxx的時候)，當作JS的事件處理
			updateStatus("WebSocket Connected");
			document.getElementById('sendMessage').disabled = false;//傳送訊息按鈕開啟
			document.getElementById('connect').disabled = true;//因為連線到了所以把按鈕關掉
			document.getElementById('disconnect').disabled = false;//斷線按鈕開啟
		};

		//onmessage收到資料的時候，service() 會執行n次
		webSocket.onmessage = function(event) {//收到後推推來的資料，要顯示文字
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);//先把json資料轉成jsonObj
			//取得username以及訊息，因為是文字訊息，還要自己家換行符號"\r\n"
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;//把文字顯示到對話框當中
			//messagesArea.scrollTop = messagesArea.scrollHeight;//設定有人發訊息會跑去最新訊息
		};

		//onclose 就像 desotry() 執行一次 連線關閉時執行
		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}

	// var inputUserName = document.getElementById("userName");
	// inputUserName.focus();

	function sendMessage() {
		//var userName = inputUserName.value.trim();
		var userName = "tobby";
		//if (userName === "") {
		//	alert("Input a user name");
		//	inputUserName.focus();
		//	return;
		//}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {//如果上面兩者都有東西，就包成json字串送出去
			var jsonObj = {
				"userName": userName,
				"message": message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
};
