const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/IngredientServlet"
const COLLECTION_POINT3 = "/KitchenwaretServlet"

//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================食材、廚具=======================
document.addEventListener("DOMContentLoaded", function() {
	let requestData = {
		action: "search", 
	};
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2,
		type: "GET",
		data: requestData,
		dataType: "json",
		statusCode: {
			200: function(res) { },
			404: function(res) { },
			500: function(res) { },
		},
		success: function(data) {
			// 首先销毁当前的simpleDatatables实例
			if (dataTable) {
				dataTable.destroy();
			}

			// 数据请求成功后，处理数据并填充表格
			var table = $("#table1");
			var tbody = table.find("tbody");
			tbody.empty(); // 清空表格内容

			console.log("Data:", data); 
			$.each(data, function(index, item) {
				console.log("Item:", item); 

				var updateButton = `<button  class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}" >修改</button>`;

				var deleteButton = `<button class="btn btn-danger delete-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}">删除</button>`;
				var row = `<tr>
          <td>${item.categoryTag}</td>
          <td>${item.categoryName}</td>
           <td>${updateButton} ${deleteButton}</td>
        </tr>`;

				tbody.append(row);
				console.log("Row:", row); 
			});

			// 初始化Simple Datatable
			dataTable = new simpleDatatables.DataTable(table1);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("请求数据失败");
		},
	});
});

function refreshData() {
	let requestData = {
		action: "search", 
	};
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2, 
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

			var table = $("#table1");
			var tbody = table.find("tbody");
			tbody.empty(); 

			console.log("Data:", data);
			$.each(data, function(index, item) {
				console.log("Item:", item); 

				var updateButton = `<button  class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}" >修改</button>`;
				var deleteButton = `<button class="btn btn-danger delete-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}">删除</button>`;
				var row = `<tr>
          <td>${item.categoryTag}</td>
          <td>${item.categoryName}</td>
          <td>${updateButton} ${deleteButton}</td>
        </tr>`;

				tbody.append(row);
				console.log("Row:", row); 
			});

			dataTable = new simpleDatatables.DataTable(table1);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("请求数据失败");
		},
	});
}


function updateDataTable(data) {
	var table = $("#table1");
	var tbody = table.find("tbody");
	tbody.empty();

	$.each(data, function(index, item) {
		var row = `<tr>
            <td>${item.categoryTag}</td>
            <td>${item.categoryName}</td>
            <td><button class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}">修改</button></td>
            
        </tr>`;
		tbody.append(row);
	});

	// 重新初始化数据表格
	dataTable = new simpleDatatables.DataTable(table1);
}




//=========================食材=======================

const foodTagButton = document.getElementById("foodTagButton");

foodTagButton.addEventListener("click", function(e) {
	e.preventDefault(); 


	let requestData = {
		action: "searchFoodTags", 
	};
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2, 
		type: "GET",
		data: requestData,
		dataType: "json",
		success: function(data) {
			if (dataTable) {
				dataTable.destroy();
			}
			var table = $("#table1");
			var tbody = table.find("tbody");
			tbody.empty(); // 清空表格內容

			var tableHeaders = table.find("thead th");
			$(tableHeaders[0]).text("食材種類編號"); 
			$(tableHeaders[1]).text("種類名稱"); 

			$.each(data, function(index, item) {
				var updateButton = `<button class="btn btn-primary update-button" data-category-id="${item.ingredientCategoryNo}" data-category-tag="食材" data-category-name="${item.categoryName}">修改</button>`;
				var deleteButton = `<button class="btn btn-danger delete-button" data-category-id="${item.ingredientCategoryNo}" data-category-tag="食材" data-category-name="${item.categoryName}">删除</button>`;
				var row = `<tr><td>${item.ingredientCategoryNo}</td>
                        <td>${item.categoryName}</td>
                        <td>${updateButton} ${deleteButton}</td></tr>`;
				console.log(row);
				tbody.append(row);
			});

			// 初始化Simple Datatable
			dataTable = new simpleDatatables.DataTable(table1);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("請求資料失敗");
		},
	});
});

//=========================廚具=======================

const KitchenwareTagButton = document.getElementById("KitchenwareTagButton");

KitchenwareTagButton.addEventListener("click", function(e) {
	e.preventDefault(); 

	let requestData = {
		action: "searchKitchenwareTags", 
	};
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT3, 
		type: "GET",
		data: requestData,
		dataType: "json",
		success: function(data) {
			if (dataTable) {
				dataTable.destroy();
			}

			// 數據請求成功後，填充表格
			var table = $("#table1");
			var tbody = table.find("tbody");
			tbody.empty(); // 清空表格內容

			var tableHeaders = table.find("thead th");
			$(tableHeaders[0]).text("廚具種類編號"); 
			$(tableHeaders[1]).text("種類名稱"); 
			$.each(data, function(index, item) {

				var updateButton = `<button class="btn btn-primary update-button" data-category-id="${item.kitchenwareCategoryNo}" data-category-tag="廚具" data-category-name="${item.categoryName}">修改</button>`;
				var deleteButton = `<button class="btn btn-danger delete-button" data-category-id="${item.kitchenwareCategoryNo}" data-category-tag="廚具" data-category-name="${item.categoryName}">删除</button>`;
				var row = `<tr><td>${item.kitchenwareCategoryNo}</td>
                        <td>${item.categoryName}</td>
                        <td>${updateButton} ${deleteButton}</td></tr>`;
				console.log(row);
				tbody.append(row);
			});

			// 初始化Simple Datatable
			dataTable = new simpleDatatables.DataTable(table1);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("請求資料失敗");
		},
	});
});

//======================燈箱修改(全部)==========================
$(document).on("click", ".update-button", function() {
	var categoryTag = $(this).data("category-tag");
	var categoryName = $(this).data("category-name");
	var categoryId = $(this).data("category-id");

	console.log("Category Tag:", categoryTag);
	console.log("Category Name:", categoryName);
	console.log("Category Id:", categoryId);
	console.log("a");

	$("#categoryTag").val(categoryTag);
	$("#categoryName").val(categoryName);
	$("#categoryId").val(categoryId);

	$("#updateModal").modal("show");
});


$("#saveChangesButton").on("click", function() {

	var updatedCategoryTag = $("#categoryTag").val();
	var updatedCategoryName = $("#categoryName").val();
	var categoryId = $("#categoryId").val();


	var dataToSend = {
		action: "update", 
		categoryTag: updatedCategoryTag,
		categoryName: updatedCategoryName,
		categoryId: categoryId,
	};

	var url = "";
	if (updatedCategoryTag === "食材") {
		url = END_POINT_URL+COLLECTION_POINT2;
	} else if (updatedCategoryTag === "廚具") {
		url = END_POINT_URL+COLLECTION_POINT3;
	}
	console.log(url);
	$.ajax({
		url: url, 
		type: "POST", 
		data: dataToSend, 
		dataType: "json",
		success: function(response) {
			if (response.message === "true") {
				alert("更新成功");
				$("#updateModal").modal("hide");
				refreshData();
			} else {
				alert(response.message);
			}
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("重新整理下");
		},
	});

	$("#updateModal").modal("hide");
});




var closeModalButton = document.getElementById("closeModalButton");
var cancelButton = document.getElementById("cancelButton");


closeModalButton.addEventListener("click", function() {
	$("#updateModal").modal("hide");
});


cancelButton.addEventListener("click", function() {
	$("#updateModal").modal("hide");
});

//=========================新增========================
$(document).ready(function() {
	$("#addTagButton").click(function() {
		$("#insertmodel").modal("show");
	});
});

$(document).ready(function() {
	$("#insertbutton").on("click", function() {
		console.log("新增按钮点击");

		var categoryTag = $("#tagType").val();
		var categoryName = $("#tagName").val();

		console.log("Category Tag:", categoryTag);
		console.log("Category Name:", categoryName);


		var data = {
			categoryTag: categoryTag,
			categoryName: categoryName,
			action: "insert",
		};
		console.log(data);

		var url = "";
		if (categoryTag === "食材") {
			url = END_POINT_URL+COLLECTION_POINT2;
		} else if (categoryTag === "廚具") {
			url =	END_POINT_URL+COLLECTION_POINT3;
		}
		console.log("目标 URL:", url);
		$.ajax({
			type: "POST",
			url: url,
			data: data,
			success: function(response) {
				console.log("AJAX");
				if (response.message === "true") {
					alert("新增成功");
					$("#insertmodel").modal("hide");
					refreshData();
				} else {
					console.log("新增失败：");
					$("#insertmodel").modal("hide");
				}
			},
			error: function(error) {
				console.error("AJAX", error);
				$("#insertmodel").modal("hide");
			},
		});


	});


	$("#closeModalButton2").on("click", function() {
		$("#insertmodel").modal("hide");
		console.log("關閉");
	});


	$("#cancelButton2").on("click", function() {
		$("#insertmodel").modal("hide");
		console.log("取消");
	});
});

//========================刪除=========================

$(document).on("click", ".delete-button", function() {
	var categoryTag = $(this).data("category-tag");
	var categoryName = $(this).data("category-name");
	var categoryId = $(this).data("category-id");

	console.log("Delete Category Tag:", categoryTag);
	console.log("Delete Category Name:", categoryName);
	console.log("Delete Category Id:", categoryId);


	var url = "";
	if (categoryTag === "食材") {
		url = END_POINT_URL+COLLECTION_POINT2; 
	} else if (categoryTag === "廚具") {
		url = END_POINT_URL+COLLECTION_POINT3; 
	}

	$.ajax({
		type: "POST",
		url: url, 
		data: {
			action: "delete", 
			categoryId: categoryId, 
		},
		success: function(response) {

			console.log(response);
			console.log("删除成功");
			alert(response.message); 
			refreshData();

		},
		error: function(error) {
	
			console.error("Error:", error);
		},
	});
});


