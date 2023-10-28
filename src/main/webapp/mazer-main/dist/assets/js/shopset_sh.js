// 给选择框 partSelect 绑定 change 事件
$("#partSelect").on("change", function() {
	var selectedValue = $(this).val(); // 获取所选的值
	console.log("Selected value: " + selectedValue);
	// 先隐藏所有部分
	$("#foodTypeOptions").hide();
	$("#kitchenTypeOptions").hide();

	// 根据所选值决定显示哪个部分
	if (selectedValue === "foodType") {
		$("#foodTypeOptions").show();
		// 发送AJAX请求获取食材数据
		$.ajax({
			type: "GET",
			url: "/CookLab/IngredientServlet",
			data: { action: "searchFoodTags" },
			dataType: "json",
			success: function(data) {
				console.log(data);

				var select = $("#foodTypeOptions select");
				select.empty();
				// 循环数据并创建 <option> 元素
				$.each(data, function(index, item) {
					// 创建一个包含数据的 <option> 元素
					var option = $("<option></option>").attr("value", item.ingredientCategoryNo).text(item.categoryName);

					// 将创建的 <option> 元素添加到选择框中
					select.append(option);

					console.log(option);
				});
			},
			error: function(xhr, status, error) {
				console.error("AJAX: " + error);
			},
		});
	} else if (selectedValue === "kitchenType") {
		$("#kitchenTypeOptions").show();
		// 发送AJAX请求获取厨具数据
		$.ajax({
			type: "GET",
			url: "/CookLab/KitchenwaretServlet",
			data: { action: "searchKitchenwareTags" },
			dataType: "json",
			success: function(data) {
				// 清空厨具部分的选择框
				$("#kitchenTypeOptions select").empty();
				// 循环数据并创建 <option> 元素
				$.each(data, function(index, item) {
					var option = $("<option></option>").attr("value", item.kitchenwareCategoryNo).text(item.categoryName);
					$("#kitchenTypeOptions select").append(option);
				});
			},
			error: function(xhr, status, error) {
				console.error("AJAX: " + error);
			},
		});
	}
});

// 初始情况下，隐藏食材和厨具部分
$("#foodTypeOptions").hide();
$("#kitchenTypeOptions").hide();

//=================新增=======================
$("#insert").click(function(e) {
	e.preventDefault();
	console.log("a");
	// 创建一个 FormData 对象
	var formData = new FormData();
	// 获取选中的食材或廚具種類的值
	var selectedFoodType = $("#foodTypeOptions select").val();
	var selectedKitchenType = $("#kitchenTypeOptions select").val();

	// 添加表单数据到 FormData 对象
	formData.append("productname", $("#productname-vertical").val());
	formData.append("productprice", $("#productprice-vertical").val());
	formData.append("storageQty", $("#storageQty-vertical").val());
	formData.append("uptime", $("#uptime-vertical").val());
	formData.append("downtime", $("#downtime-vertical").val());
	formData.append("selectedPart", $("#partSelect").val());
	formData.append("selectedFoodType", selectedFoodType);
	formData.append("selectedKitchenType", selectedKitchenType);
	console.log($("#partSelect").val());
	// 获取文件输入字段的值
	var productImage = $("#p_file")[0].files[0];
	formData.append("productImage", productImage);
	if (productImage === undefined) {
		productImage = null; // 或者设置为其他默认值
	}
	console.log(productImage);
	// 添加商品简介和详细描述到 FormData 对象
	formData.append("productIntroduction", $("#floatingTextarea").val());
	formData.append("productDescription", $("#hiddenContent").val());

	formData.append("action", "insertOne");
	console.log(formData);

	$.ajax({
		type: "POST", // 使用POST方法发送数据
		url: "/CookLab/ProductServlet", // 这里的URL应该是Servlet的URL
		data: formData,
		processData: false, // 不要处理数据
		contentType: false, // 不要设置内容类型
		dataType: "json", // 响应数据类型为JSON
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
				console.log("Data submitted successfully: " + response.message);
				console.log("成功新增")
				alert("成功新增");
				window.location.href = "shopview.html";
			}
		},
		error: function(xhr, status, error) {
			console.error("AJAX request failed: " + error);
		},
	});
});
