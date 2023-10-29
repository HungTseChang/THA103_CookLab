$(document).ready(function() {
	console.log("Document is ready.");

	const urlParams = new URLSearchParams(window.location.search);
	const orderNo = urlParams.get("orderNo");

	$.ajax({
		url: "/CookLab/MemberOrderServlet",
		type: "GET",
		data: { action: "getDetail", orderNo: orderNo }, 
		dataType: "json",
		success: function(data) {
			console.log(data);
			$("#table input:eq(0)").val(data.memberId); 		// 會員編號
			$("#table input:eq(1)").val(data.memberNickname); 	// 會員暱稱
			$("#table input:eq(2)").val(data.shippingAddress); 	// 收貨地址W
			$("#table input:eq(3)").val(data.memberMail); 		// Email
			$("#table input:eq(4)").val(data.orderNo); 			// 訂單編號
			$("#table input:eq(5)").val(data.totalOrderAmount); // 訂單總金額
			$("#table input:eq(6)").val(data.promoCode); 		// 優惠碼
			$("#table input:eq(7)").val(data.checkoutAmount); 	// 結帳金額
			$("#table select").val(data.orderStatus); 			// 訂單狀態
			$("#table input:eq(8)").val(data.createdTimestamp); // 購買日期
			var orderStatusSelect = $("#table select");
			orderStatusSelect.val(data.orderStatus);

			orderStatusSelect.trigger("change");

			$("#table input:eq(8)").val(data.createdTimestamp); 

			var orderDetailTable = $("#table1 tbody");
			orderDetailTable.empty(); 

			console.log(data.orderDetail);
			data.orderDetail.forEach(function(detail) {
				console.log(detail);
				var row = "<tr>" +
					"<td>" + detail.productId + "</td>" + // 購買商品
					"<td>" + detail.quantity + "</td>" + // 商品數量
					"<td>" + detail.totalcount + "</td>" + // 總金額
					"</tr>";
				orderDetailTable.append(row);
			});

			var buttons = '<div class="text-end">' +
				'<a href="TYT_order_management.html"><button type="button" class="btn btn-primary">返回訂單明細</button></a>' +
				'<a><button type="button" class="btn btn-success" id="saveButton">儲存</button></a>' +
				'</div>';
			$("#table1").after(buttons);

			
			$("#saveButton").click(function() {
				var newOrderStatus = $("#table select").val();
				console.log(newOrderStatus);
					$.ajax({
						url: "/CookLab/MemberOrderServlet", 
						type: "POST", 
						data: { action: "updateOrderStatus", orderNo: orderNo, newStatus: newOrderStatus },
						dataType: "json",
						success: function(response) {
							if(response.message ==="success"){
								alert("訂單狀態已更新");
								location.reload();
							}else{
								alert("訂單狀態更新失敗");
							}
						},
						error: function(xhr) {
							console.log("失敗：" + xhr.responseText);
						}
					});
			});
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("error");
		},
	});
});


