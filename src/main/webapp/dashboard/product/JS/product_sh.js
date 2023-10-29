var currentTime = new Date();
//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================商品=======================
document.addEventListener("DOMContentLoaded", function() {
	// 在页面加载后执行AJAX请求
	let requestData = {
		action: "search", // 你要执行的操作
	};
	$.ajax({
		url: "/CookLab/ProductServlet",
		type: "GET",
		data: requestData,
		dataType: "json",
		statusCode: {
			// 状态码
			200: function(res) { },
			404: function(res) { },
			500: function(res) { },
		},
		success: function(data) {
			// 首先销毁当前的simpleDatatables实例
			if (dataTable) {
				dataTable.destroy();
			}

			// 获取表格的 tbody 元素
			var tbody = $("#table1 tbody");
			tbody.empty(); // 清空表格内容

			// 遍历数据并创建表格行
			$.each(data, function(index, item) {
				var row = $("<tr></tr>"); // 创建行
				// 将上架和下架时间从字符串转换为日期对象
				var shelfTime = new Date(item.shelfTime);
				var offsaleTime = new Date(item.offsaleTime);
				console.log(shelfTime);
				console.log(offsaleTime);
				var statusIcon = ""; // 初始为空字符串
				// 根据当前时间判断上下架状态
				if (currentTime >= shelfTime && currentTime <= offsaleTime) {
					// 如果当前时间在上架和下架时间之间，显示上架图标
					statusIcon = "<i class='fas fa-check-circle' style='color:green'></i>";
				} else {
					// 否则，显示下架图标
					statusIcon = "<i class='fas fa-times-circle' style='color:red'></i>";
				}
				console.log(statusIcon);
				// 填充表格列数据
				row.append("<td>" + item.productNo + "</td>");
				row.append("<td><img src='data:image/png;base64," + item.productImage + "' style='max-width: 200px; max-height: 200px;'></td>");
				row.append("<td>" + item.productName + "</td>");
				row.append("<td>" + item.productPrice + "</td>");
				row.append("<td>" + statusIcon + "</td>");
				row.append("<td>" + item.searchCount + "</td>");
				row.append("<td>" + item.storageQty + "</td>");
				
				// 这里需要根据数据结构填充商品种类的列
				// 如果数据中有商品种类字段，你需要根据字段填充，否则可以使用默认值
				row.append("<td>" + (item.Category ? item.Category : "N/A") + "</td>");

				var detailButton = "<td><a class='btn btn-info detail-button' data-product-no='" + item.productNo + "'>詳細資料</a></td>";


				row.append(detailButton);

				// 将行添加到表格的 tbody
				tbody.append(row);
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
	// 获取所选商品的编号
	var productNo = $(this).data("product-no");

	// 构建详细信息页面的URL，包括商品编号作为参数
	var detailPageUrl = "./shopupdate.html?productNo=" + productNo;

	// 导航到详细信息页面
	window.location.href = detailPageUrl;
});
