
//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================訂單=======================
document.addEventListener("DOMContentLoaded", function() {
	// 在页面加载后执行AJAX请求
	let requestData = {
		action: "search", // 你要执行的操作
	};
	$.ajax({
		url: "/CookLab/MemberOrderServlet",
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
			console.log(data);
			if (dataTable) {
				dataTable.destroy();
			}

			// 获取表格的 tbody 元素
			var tbody = $("#table1 tbody");
			tbody.empty(); // 清空表格内容

			// 遍历数据并创建表格行
			$.each(data, function(index, item) {
				console.log(item.createdTimestamp);
				var row = $("<tr></tr>"); // 创建行

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
						statusBadge = '<span class="badge bg-secondary">未知狀態</span>';
				}
				// 填充表格列数据
				row.append("<td>" + item.orderNo + "</td>");
				row.append("<td>" + item.members + "</td>");
				row.append("<td>" + item.checkoutAmount + "</td>");
				row.append("<td>" + statusBadge + "</td>");
				row.append("<td class='d-flex justify-content-between align-items-center'>" + item.createdTimestamp + "</td>");
				var detailButton = "<td><a class='btn btn-outline-secondary detail-button' data-order-no='" + item.orderNo + "'>修改/查看</a></td>";
				row.append(detailButton);
				//row.append(detailButton);

				// 将行添加到表格的 tbody
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


