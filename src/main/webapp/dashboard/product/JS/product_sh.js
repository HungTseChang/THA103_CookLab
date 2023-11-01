var currentTime = new Date();

const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"
//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================商品=======================
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
			if (dataTable) {
				dataTable.destroy();
			}

			var tbody = $("#table1 tbody");
			tbody.empty(); // 清空表格内容
			$.each(data, function(index, item) {
				var row = $("<tr></tr>"); 

				var shelfTime = new Date(item.shelfTime);
				var offsaleTime = new Date(item.offsaleTime);
				console.log(shelfTime);
				console.log(offsaleTime);
				var statusIcon = ""; 

				if (currentTime >= shelfTime && currentTime <= offsaleTime) {
					statusIcon = "<i class='fas fa-check-circle' style='color:green'></i>";
				} else {
					statusIcon = "<i class='fas fa-times-circle' style='color:red'></i>";
				}
				console.log(statusIcon);

				row.append("<td>" + item.productNo + "</td>");
				row.append("<td><img src='data:image/png;base64," + item.productImage + "' style='max-width: 200px; max-height: 200px;'></td>");
				row.append("<td>" + item.productName + "</td>");
				row.append("<td>" + item.productPrice + "</td>");
				row.append("<td>" + statusIcon + "</td>");
				row.append("<td>" + item.searchCount + "</td>");
				row.append("<td>" + item.storageQty + "</td>");
				

				row.append("<td>" + (item.Category ? item.Category : "N/A") + "</td>");

				var detailButton = "<td><a class='btn btn-info detail-button' data-product-no='" + item.productNo + "'>詳細資料</a></td>";


				row.append(detailButton);
				tbody.append(row);
			});

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

	var productNo = $(this).data("product-no");
	var detailPageUrl = "./shopupdate.html?productNo=" + productNo;

	window.location.href = detailPageUrl;
});
