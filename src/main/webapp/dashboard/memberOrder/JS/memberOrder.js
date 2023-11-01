const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/MemberOrderServlet";

//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================訂單=======================
document.addEventListener("DOMContentLoaded", function() {

	let requestData = {
		action: "search", 
	};
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT,
		type: "GET",
		data: requestData,
		dataType: "json",
		statusCode: {
			200: function(res) { },
			404: function(res) { },
			500: function(res) { },
		},
		success: function(data) {

			console.log(data);
			if (dataTable) {
				dataTable.destroy();
			}

			var tbody = $("#table1 tbody");
			tbody.empty(); 

			$.each(data, function(index, item) {
				console.log(item.createdTimestamp);
				var row = $("<tr></tr>"); 

				var statusBadge;
				switch (item.orderStatus) {
					case "0":
						statusBadge = '<span class="badge bg-secondary">待出貨</span>';
						break;
					case "1":
						statusBadge = '<span class="badge bg-info">待收貨</span>';
						break;
					case "2":
						statusBadge = '<span class="badge bg-success">已完成</span>';
						break;
					case "3":
						statusBadge = '<span class="badge bg-warning">退貨中</span>';
						break;
					case "4":
						statusBadge = '<span class="badge bg-dark">已退貨</span>';
						break;
					default:
						statusBadge = '<span class="badge bg-secondary">註銷</span>';
				}

				row.append("<td>" + item.orderNo + "</td>");
				row.append("<td>" + item.members + "</td>");
				row.append("<td>" + item.checkoutAmount + "</td>");
				row.append("<td>" + statusBadge + "</td>");
				row.append("<td class='d-flex justify-content-between align-items-center'>" + item.createdTimestamp + "</td>");
				var detailButton = "<td><a class='btn btn-outline-secondary detail-button' data-order-no='" + item.orderNo + "'>修改/查看</a></td>";
				row.append(detailButton);

				tbody.append(row);
				console.log(row);
			});

			// 初始化Simple Datatable
			dataTable = new simpleDatatables.DataTable(document.getElementById("table1"));
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("请求数据失败");
		},
	});

});

//======================詳細資料跳轉==========================
$(document).on("click", ".detail-button", function() {
	console.log("跳轉");
		var orderNo = $(this).data("order-no");

		var url = "./TYT_order_detail.html?orderNo=" + orderNo;

		window.location.href = url;
});
