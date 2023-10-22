//article_edit-HO//
$(function() {

	$("#btn_drawft").click(function() {
		$("input[name='articleStatus']").val("3");
		//還可以進行其他操作，例如驗證或其他處理
		$("#form1").submit();
	})
});

//====  article_main   =========
$(document).ready(function() {
	$(".btn.custom-btn[value='1']").addClass("HO-btn-org");
	        

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

//===============article_content==========================================
//
//$(function(){
//  $("a#creator").on("click", function(e){ 
//    e.preventDefault();
//  });
//});
//
//$(document).ready(function(){
//	
//	$('#replyForm').submit(function(e){
//		e.preventDefault();
//		
//		$('html,body').animate({
//			scrollTop:$(document).height()
//		},'fast');
//	});
//});


//=======WebChat===========

	
var MyPoint = "/TogetherWS/james";
var host = window.location.host;
var path = window.location.pathname; //動態取得專案路徑
var webCtx = path.substring(0, path.indexOf('/', 1));
//ws 是websocket的通訊協定
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
// ws://localhost:8081/WebSocketChatWeb/TogetherWS/james
var statusOutput = document.getElementById("statusOutput");
var webSocket;

function connect() {
	// create a websocket
	webSocket = new WebSocket(endPointURL); //把上方的網址傳進來

	//onopen 就像是init() 執行一次
	webSocket.onopen = function(event) { //onxxxx(當xxx的時候)，當作JS的事件處理
		updateStatus("CookTALK Connected");
		document.getElementById('sendMessage').disabled = false;
		//document.getElementById('connect').disabled = true;
		//document.getElementById('disconnect').disabled = false;

	};

	//onmessage收到資料的時候，service() 會執行n次
	webSocket.onmessage = function(event) {
		var messagesArea = document.getElementById("messagesArea");
		var jsonObj = JSON.parse(event.data);
		var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
		messagesArea.value = messagesArea.value + message;
		//messagesArea.scrollTop = messagesArea.scrollHeight;
		//設定有人發訊息會跑去最新訊息
	};

	//onclose 就像 desotry() 執行一次
	//webSocket.onclose = function(event) {
		//updateStatus("WebSocket Disconnected");
	//};
}

//var inputUserName = document.getElementById("userName");
// inputUserName.focus(); 這段出現錯誤暫時封住

function sendMessage() {
	var userName = "天上天下唯我獨尊";
	var inputMessage = document.getElementById("message");
	var message = inputMessage.value.trim();
	
	var currentDate = new Date(); // 先抓到目前的時間
	var hours = currentDate.getHours().toString().padStart(2, '0');
	var minutes = currentDate.getMinutes().toString().padStart(2, '0');
	var currentTime = hours + ':' + minutes;

	if (message === "") {
		alert("Input a message");
		inputMessage.focus();
	} else {
		var jsonObj = {
			"userName": userName,
			"message": message,
			"time" : currentDate
		};
		webSocket.send(JSON.stringify(jsonObj));
		inputMessage.value = "";
		inputMessage.focus();
		
	
		document.getElementById('messageForm').submit();
	}
}


function updateStatus(newStatus) {
	statusOutput.innerHTML = newStatus;
	}


