//article_edit-HO//
$(function() {

	$("#btn_drawft").click(function() {
		$("input[name='articleStatus']").val("1");
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





