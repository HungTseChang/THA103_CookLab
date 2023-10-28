// 在页面加载后执行
$(document).ready(function() {
	var quill = new Quill("#full", {
		bounds: "#full-container .editor",
		modules: {
			toolbar: [[{ font: [] }, { size: [] }], ["bold", "italic", "underline", "strike"], [{ color: [] }, { background: [] }], [{ script: "super" }, { script: "sub" }], [{ list: "ordered" }, { list: "bullet" }, { indent: "-1" }, { indent: "+1" }], ["direction", { align: [] }], ["link", "image", "video"], ["clean"]],
		},
		theme: "snow",
	});
	console.log("Document is ready.");
	// 获取URL参数
	const urlParams = new URLSearchParams(window.location.search);
	const productNo = urlParams.get("productNo");

	// 执行AJAX请求来获取商品详细信息
	$.ajax({
		url: "/CookLab/ProductServlet",
		type: "GET",
		data: { action: "getDetail", productNo: productNo }, // 传递商品编号
		dataType: "json",
		success: function(data) {
			console.log(data);
			console.log(data.productDescription);
			var img = new Image();
			img.src = "data:image/jpeg;base64," + data.productImage;
			img.onload = function() {
				console.log("Image loaded successfully.");
			};
			img.onerror = function() {
				console.log("Image failed to load.");
			};

			// 填充食材和廚具種類的下拉選項
			populateSelectOptions1("foodTypeOptions", data.foodTypeOptions);
			populateSelectOptions2("kitchenTypeOptions", data.kitchenTypeOptions);

			// 使用返回的商品详细信息填充表单字段
			$("#productname-vertical").val(data.productName);
			$("#productprice-vertical").val(data.productPrice);
			$("#saleQty-vertical").val(data.saleQty);
			$("#storageQty-vertical").val(data.storageQty);
			$("#selectedPart").val(data.selectedPart);
			$("#preview img.preview_img").attr("src", "data:image/jpeg;base64," + data.productImage);

			// 从服务器端接收的数据
			var selectedPart = data.selectedPart;
			var selectedFoodType = data.selectedFoodType;
			var selectedKitchenType = data.selectedKitchenType;
			// 设置选择框的值
			$("#partSelect").val(selectedPart);

			// 根据 selectedPart 显示相应的选项
			if (selectedPart === "foodType") {
				// $("#foodTypeOptions select").val(selectedFoodType);
				$("#foodTypeOptions select").val(selectedFoodType).prop("selected", true);
				$("#partSelect").val("foodType");
				$("#foodTypeOptions").show();
				$("#kitchenTypeOptions").hide();
			} else if (selectedPart === "kitchenType") {
				// $("#kitchenTypeOptions select").val(selectedKitchenType);
				// document.querySelector("#kitchenTypeOptions select").value = selectedKitchenType;
				$("#kitchenTypeOptions select").val(selectedKitchenType).prop("selected", true);
				$("#partSelect").val("kitchenType");
				$("#foodTypeOptions").hide();
				$("#kitchenTypeOptions").show();
			}

			// 商品簡介和詳情的填充
			$("#floatingTextarea").text(data.productIntroduction);
			$("#full .ql-editor").html(data.productDescription);

			// 从服务器接收的日期字符串
			var shelfTimeStr = data.shelfTime;
			var offsaleTimeStr = data.offsaleTime;

			// 使用Date对象解析日期
			var shelfTime = new Date(shelfTimeStr);
			var offsaleTime = new Date(offsaleTimeStr);

			// 使用Date对象格式化日期为所需格式
			var formattedShelfTime = formatDate(shelfTime);
			var formattedOffsaleTime = formatDate(offsaleTime);

			// 将格式化后的日期设置到表单字段中
			$("#uptime-vertical").val(formattedShelfTime);
			$("#downtime-vertical").val(formattedOffsaleTime);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("error");
		},
	});
});

// 在填充食材和廚具種類的下拉選項时，确保为每个选项设置了 value 属性

///廚具
function populateSelectOptions1(containerId, options) {
	var select = $("#" + containerId + " select");
	select.empty();
	for (var i = 0; i < options.length; i++) {
		var option = $("<option></option>").attr("value", options[i].ingredientCategoryNo).text(options[i].categoryName);
		select.append(option);
		// console.log(select.append(option));
	}
}

/// 食材
function populateSelectOptions2(containerId, options) {
	var select = $("#" + containerId + " select");
	select.empty();
	for (var i = 0; i < options.length; i++) {
		var option = $("<option></option>").attr("value", options[i].kitchenwareCategoryNo).text(options[i].categoryName);
		select.append(option);
		// console.log(select.append(option));
	}
}

// 格式化日期函数
function formatDate(date) {
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0"); // 月份从0开始，所以需要加1
	const day = String(date.getDate()).padStart(2, "0");
	const hours = String(date.getHours()).padStart(2, "0");
	const minutes = String(date.getMinutes()).padStart(2, "0");
	const seconds = String(date.getSeconds()).padStart(2, "0");

	return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
//============更新===============================
$("#update").click(function(e) {
	e.preventDefault();

	// 收集表单数据
	var formData = new FormData();
	e.preventDefault(); // 阻止默认表单提交行为
	console.log("a");
	// 创建一个 FormData 对象
	var formData = new FormData();
	// 获取选中的食材或廚具種類的值
	var selectedFoodType = $("#foodTypeOptions select").val();
	var selectedKitchenType = $("#kitchenTypeOptions select").val();
	// 添加表单数据到 FormData 对象
	formData.append("productname", $("#productname-vertical").val());
	formData.append("productprice", $("#productprice-vertical").val());
	formData.append("saleQty", $("#saleQty-vertical").val());
	formData.append("storageQty", $("#storageQty-vertical").val());
	formData.append("uptime", $("#uptime-vertical").val());
	formData.append("downtime", $("#downtime-vertical").val());
	formData.append("selectedPart", $("#partSelect").val());
	formData.append("selectedFoodType", selectedFoodType);
	formData.append("selectedKitchenType", selectedKitchenType);
	// 添加商品简介和详细描述到 FormData 对象
	formData.append("productIntroduction", $("#floatingTextarea").val());
	formData.append("productDescription", $("#full .ql-editor").html());
	formData.append("action", "updateProduct");
	// 图像文件
	var productImage = $("#p_file")[0].files[0];
	formData.append("productImage", productImage);
	// 商品编号
	const urlParams = new URLSearchParams(window.location.search);
	const productNo = urlParams.get("productNo");
	formData.append("productNo", productNo);
	console.log(formData);
	// 执行AJAX请求
	$.ajax({
		url: "/CookLab/ProductServlet",
		type: "POST",
		data: formData,
		dataType: "json",
		processData: false, // 不对数据进行序列化处理
		contentType: false, // 不设置请求头
		success: function(response) {
			console.log(response);
			console.log(response.message);
			if (response.message === "error") {
				alert("格式有錯");
				if (response.errProductName) {
					$("#productname-error").text(response.errProductName);
				} else {
					$("#productname-error").text(""); // 清空错误消息
				}

				if (response.errProductPrice) {
					$("#productprice-error").text(response.errProductPrice);
				} else {
					$("#productprice-error").text("");
				}

				if (response.errSaleQty) {
					$("#productSaleqty-error").text(response.errSaleQty);
				} else {
					$("#productSaleqty-error").text("");
				}

				if (response.errStorageQty) {
					$("#productQty-error").text(response.errStorageQty);
				} else {
					$("#productQty-error").text("");
				}

				if (response.errShelfTime) {
					$("#productUptime-error").text(response.errShelfTime);
				} else {
					$("#productUptime-error").text("");
				}

				if (response.erroffsaleTime) {
					$("#productDowntime-error").text(response.erroffsaleTime);
				} else {
					$("#productDowntime-error").text("");
				}

				if (response.errIntro) {
					$("#productDec-error").text(response.errIntro);
				} else {
					$("#productDec-error").text("");
				}

				if (response.errProductDec) {
					$("#productIntro-error").text(response.errProductDec);
				} else {
					$("#productIntro-error").text("");
				}

				if (response.errCategory) {
					$("#productCategory-error").text(response.errCategory);
				} else {
					$("#productCategory-error").text("");
				}

				if (response.errProductImage) {
					$("#productImg-error").text(response.errProductImage);
				} else {
					$("#productImg-error").text("");
				}
				return;
			} else {
				console.log(response);
				if (response.message === "success") {
					alert("更新成功");
					window.location.href = "./shopview.html";
				} 
			}
		},
		error: function(xhr) {
			// 处理错误响应
			console.log(xhr.responseText);
			alert("更新失败");
		},
	});
});
