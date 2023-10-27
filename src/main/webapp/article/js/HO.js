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

//==========content like and dislike===
$(document).ready(function () {
  $(".like,.dislike").click(function () {
    const $image = $(this);
	//這邊指向html的圖片，使用JS操作圖片放大及復原的狀態
    const isEnlarged = $image.data("enlarged"); 
    let value = parseInt($image.data("value")); //這邊指向data-value
	let value2 = parseInt($image.attr("data-value"));

	let likeValue = parseInt($(".likeValue").text(),10);//把文字轉為數字，設定為10進位
	let dislikeValue = parseInt($(".dislikeValue").text(), 10);

    if ($image.hasClass("like")) {
      if ($(".dislike").attr("data-value") === "2" && value2 === 0 ) {
        //當dislike圖片已經放大，跳來點like圖片時的操作
        $(".clickable").css("transform", "scale(1)"); // 還原所有圖像大小
        $image.css("transform", "scale(2)");//把like class的圖片放兩倍大
        $image.data("enlarged", true);
		value  = 1;
		value2 = 1
		likeValue +=1;
		dislikeValue -=1;
		$(".dislike").attr("data-value", "0");//把dislike的data-value值轉為0
	  	}else if(value2 === 0 ) {
			// 點擊未放大的圖片，執行放大操作
			$(".clickable").css("transform", "scale(1)"); // 還原所有圖像大小
			$image.css("transform", "scale(2)");//把like class的圖片放兩倍大
			$image.data("enlarged", true);
			value  = 1;
			value2 = 1
			likeValue +=1;
        } else  {//like 圖片已經放大，還原大小
			$image.css("transform", "scale(1)");//把like class的圖片放兩倍大
			$image.data("enlarged", false);
			value = 0;
			value2 = 0;
			likeValue -=1;
		
		}
    } else if ($image.hasClass("dislike")){
		if ($(".like").attr("data-value") === "1" && value2 === 0 ) {
			//當like圖片已經放大，跳來點dislike圖片時的操作
			$(".clickable").css("transform", "scale(1)"); // 還原所有圖像大小
			$image.css("transform", "scale(2)");
			$image.data("enlarged", true);
			value = 2;
			value2 = 2;
			dislikeValue+=1;
			likeValue -=1;
			$(".like").attr("data-value", "0");//把like的data-value值轉為0
		}else if (value2 === 0) {
			// dislike 圖片未放大，執行放大操作
			$(".clickable").css("transform", "scale(1)"); // 還原所有圖像大小
			$image.css("transform", "scale(2)");
			$image.data("enlarged", true);
			value = 2;
			value2 = 2;
			dislikeValue+=1;
		} else {
			// dislike 圖片已經放大，還原大小
			$image.css("transform", "scale(1)");
			$image.data("enlarged", false);
			value = 0;
			value2 = 0;
			dislikeValue-=1;
		}
	}
	// 更新 HTML 中喜歡與不喜歡的數字
	$(".likeValue").text(likeValue);
	$(".dislikeValue").text(dislikeValue);
	

	$image.data("value", value);//這行把圖片的data-value 顯示在HTML
	$image.attr("data-value", value);
	// $image.data("value", value);//這行把圖片的data-value 轉為資料
    // 在這裡，可以把value發送到後端
    alert("value:" + value);
	
  });
});