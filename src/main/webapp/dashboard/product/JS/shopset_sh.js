const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/IngredientServlet"
const COLLECTION_POINT3 = "/KitchenwaretServlet"



$("#partSelect").on("change", function() {
	var selectedValue = $(this).val(); 
	console.log("Selected value: " + selectedValue);

	$("#foodTypeOptions").hide();
	$("#kitchenTypeOptions").hide();


	if (selectedValue === "foodType") {
		$("#foodTypeOptions").show();

		$.ajax({
			type: "GET",
			url: END_POINT_URL+COLLECTION_POINT2,
			data: { action: "searchFoodTags" },
			dataType: "json",
			success: function(data) {
				console.log(data);

				var select = $("#foodTypeOptions select");
				select.empty();

				$.each(data, function(index, item) {
					var option = $("<option></option>").attr("value", item.ingredientCategoryNo).text(item.categoryName);
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
		$.ajax({
			type: "GET",
			url: END_POINT_URL+COLLECTION_POINT3,
			data: { action: "searchKitchenwareTags" },
			dataType: "json",
			success: function(data) {

				$("#kitchenTypeOptions select").empty();
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


$("#foodTypeOptions").hide();
$("#kitchenTypeOptions").hide();

//=================新增=======================
$("#insert").click(function(e) {
	e.preventDefault();
	console.log("a");

	var formData = new FormData();
	
	var selectedFoodType = $("#foodTypeOptions select").val();
	var selectedKitchenType = $("#kitchenTypeOptions select").val();

	formData.append("productname", $("#productname-vertical").val());
	formData.append("productprice", $("#productprice-vertical").val());
	formData.append("storageQty", $("#storageQty-vertical").val());
	
	formData.append("uptime", $("#uptime-vertical").val());
	formData.append("downtime", $("#downtime-vertical").val());
	
	formData.append("selectedPart", $("#partSelect").val());
	formData.append("selectedFoodType", selectedFoodType);
	formData.append("selectedKitchenType", selectedKitchenType);
	console.log($("#partSelect").val());

	var productImage = $("#p_file")[0].files[0];
	formData.append("productImage", productImage);
	if (productImage === undefined) {
		productImage = null; 
	}
	console.log(productImage);

	formData.append("productIntroduction", $("#floatingTextarea").val());
	formData.append("productDescription", $("#hiddenContent").val());

	formData.append("action", "insertOne");
	console.log(formData);

	$.ajax({
		type: "POST", 
		url: END_POINT_URL+COLLECTION_POINT, 
		data: formData,
		processData: false, 
		contentType: false, 
		dataType: "json", 
		success: function(response) {
			console.log(response);
			console.log(response.message);
			if (response.message === "error") {
				alert("格式有錯");
				if (response.errProductName) {
					$("#productname-error").text(response.errProductName);
				} else {
					$("#productname-error").text(""); 
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
