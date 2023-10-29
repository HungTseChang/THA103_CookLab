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
$(function(){
  $("a#creator").on("click", function(e){ 
    e.preventDefault();
  });
});


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



   
//    $(document).ready(function(){
//        $(".button-group").each(function(){
//            const $group = $(this);
//            $group.find(".clickable").click(function (){
//                const $image = $(this);
//                const isEnlarged = $image.data("enlarged");
//
//                $group.find(".clickable").css("transform","scale(1");
//                $(this).css("transform","scale(1.2)");
//
//                if (!isEnlarged){
//                    $image.css("transform","scale(1)");
//                    $image.data("enlared",false);
//                }else{
//                    $group.find(".clickable").css("transform", "scale(1)"); 
//                    $image.css("transform", "scale(1.2)");
//                    $image.data("enlarged", true);
//                }
//            });
//
//        })
//    })
   

//==========content like and dislike===
//$(document).ready(function () {
//	$(".like, .dislike").click(function () {
//	  const $image = $(this);
//	  const $container =$image.closest('.like-dislike');//尋找最近的該元素
//	  // 这里指向HTML的图像，使用JS操作图像放大及复原的状态
//	  console.log($container);
//	  const isEnlarged = $image.data("enlarged"); 
//	  let value = parseInt($image.attr("data-gjStatus")); // 这里指向data-gjStatus
//	  var dislikeStatus = parseInt($(".dislike").attr("data-gjStatus"));
//	  var likeStatus = parseInt($(".like").attr("data-gjStatus"));
//
//	  let likeValue = parseInt($(this).next(".likeValue").text(), 10);//追加.next可以抓到likeValue的值
//	  let dislikeValue = parseInt($(this).next(".dislikeValue").text(), 10);
//  
//	  if ($image.hasClass("like")) {
//		if (dislikeStatus === 2 &&  value === 0 ){
//			const $clickableDislike = $container.find('.clickable.dislike');
//			$clickableDislike.css("transform", "scale(1)");
//
//			$image.css("transform", "scale(2)");//把like class的图像放两倍大
//			$image.data("enlarged", true);
//			value = 1;
//			likeValue += 1;
//			dislikeValue = parseInt($container.find('.dislikeValue').text(),10);
//			dislikeValue -= 1;
//			console.log("dislike後:"+ dislikeValue);
//			$(this).next(".likeValue").text(likeValue);
//			$container.find(".dislikeValue").text(dislikeValue);
//
//			$clickableDislike.attr("data-gjStatus", 0);
//			console.log("啟用like+1 ,dislike-1");
//		}
//		else if (value === 0) {
//		  // 点击未放大的图像，执行放大操作
//			
//		  $container.find(".clickable").css("transform", "scale(1)"); // 还原所有图像大小
//		  $image.css("transform", "scale(2)");//把like class的图像放两倍大
//		  $image.data("enlarged", true);
//		  value = 1;
//		  likeValue += 1;
//		  console.log("After likeValue += 1:", likeValue);
//		  $(this).next(".likeValue").text(likeValue);
//		  $(this).next(".dislikeValue").text(dislikeValue);
//		  console.log("啟用likeValue + 1");
//
//		} else { // like图片已经放大，还原大小
//		  $image.css("transform", "scale(1)");//把like class的图像放两倍大
//		  $image.data("enlarged", false);
//		  value = 0;
//		  likeValue -= 1;
//		  $(this).next(".likeValue").text(likeValue);
//		  $(this).next(".dislikeValue").text(dislikeValue);
//		  console.log("啟用likeValue-1")
//
//		}
//	  } else if ($image.hasClass("dislike")) {
//		if (likeStatus === 1 && value === 0 ){
//			const $clickablelike = $container.find('.clickable.like');
//			$clickablelike.css("transform", "scale(1)");
//
//			$image.css("transform", "scale(2)");//把like class的图像放两倍大
//			$image.data("enlarged", true);
//			
//			
//			value = 1;
//			dislikeValue += 1;
//			
//			likeValue = parseInt($container.find('.likeValue').text(),10);
//			likeValue -= 1;
//			
//			$(this).next(".dislikeValue").text(dislikeValue);
//			$container.find(".likeValue").text(likeValue);
//
//
//			$clickablelike.attr("data-gjStatus", 0);
//			console.log("dislike +1,like -1")
//		}
//		else if (value === 0) {
//		  // dislike图片未放大，执行放大操作
//		  $container.find(".clickable").css("transform", "scale(1)"); // 还原所有图像大小
//		  $image.css("transform", "scale(2)");
//		  $image.data("enlarged", true);
//		  value = 2;
//		  dislikeValue += 1;
//		  $(this).next(".likeValue").text(likeValue);
//		  $(this).next(".dislikeValue").text(dislikeValue);
//
//
//		} else {
//		  // dislike圖片以放大還原大小
//		  $image.css("transform", "scale(1)");
//		  $image.data("enlarged", false);
//		  value = 0;
//		  dislikeValue -= 1;
//		  $(this).next(".likeValue").text(likeValue);
//		  $(this).next(".dislikeValue").text(dislikeValue);
//		}
//	  }
//
//	  $image.attr("data-gjStatus", value); //這行用於更改data-gjStatus屬性的值
//	});
//  });
  