/*============================================================ ajax ============================================================*/

/*============================== 搜尋功能 ==============================*/
$(document).ready(function() {
	$("#search-button").on("click", function() {
		let keyword = {
			keywords: $("#index-searchbar").val(),
			action : "search"
		}
		console.log(keyword)
		$.ajax({
			url: "/CookLab/ProductServlet2", // 資料請求的網址
			type: "GET", // GET | POST | PUT | DELETE | PATCH
			data: keyword, // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {
				// 在 request 發送之前執行
			},
			headers: {
				// request 如果有表頭資料想要設定的話
				// "X-CSRF-Token":"abcde"   // 參考寫法
			},
			statusCode: {
				// 狀態碼
				200: function(res) { },
				404: function(res) { },
				500: function(res) { },
			},
			success: function(data) {
				// request 成功取得回應後執行
				window.location.href = "/frontstage/shopstage/shop-details.jsp"
				console.log(data);
				console.log("ajax成功");
			},
			error: function(xhr) {
				// request 發生錯誤的話執行
				console.log("請求失敗，狀態碼：" + xhr.status);
				console.log(xhr.responseText)
				console.log(xhr);
			},
			complete: function(xhr) {
				// request 完成之後執行(在 success / error 事件之後執行)
				console.log(xhr);
			},
		});
	});
})
/*============================== 購物車功能 ==============================*/
//$("#fa fa-shopping-cart").on("click", function() {
//		letproductInfo = {
//        productNo: ${productVO.productNo},
//        productName: "${productVO.productName}",
//        productPrice: ${productVO.productPrice},
//        productImage: "<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}",
//        quantity: 1 // 默认购买数量为1
//    }
//		console.log(keyword)
//		$.ajax({
//			url: "/CookLab/ProductServlet2", // 資料請求的網址
//			type: "GET", // GET | POST | PUT | DELETE | PATCH
//			data: keyword, // 將物件資料(不用雙引號) 傳送到指定的 url
//			dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
//			beforeSend: function() {
//				// 在 request 發送之前執行
//			},
//			headers: {
//				// request 如果有表頭資料想要設定的話
//				// "X-CSRF-Token":"abcde"   // 參考寫法
//			},
//			statusCode: {
//				// 狀態碼
//				200: function(res) { },
//				404: function(res) { },
//				500: function(res) { },
//			},
//			success: function(data) {
//				// request 成功取得回應後執行
//				window.location.href = "/frontstage/shopstage/shop-details.jsp"
//				console.log(data);
//				console.log("ajax成功");
//			},
//			error: function(xhr) {
//				// request 發生錯誤的話執行
//				console.log("請求失敗，狀態碼：" + xhr.status);
//				console.log(xhr.responseText)
//				console.log(xhr);
//			},
//			complete: function(xhr) {
//				// request 完成之後執行(在 success / error 事件之後執行)
//				console.log(xhr);
//			},
//		});
//	});