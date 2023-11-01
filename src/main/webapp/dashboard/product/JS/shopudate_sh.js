const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"


$(document).ready(function() {
	var quill = new Quill("#full", {
		bounds: "#full-container .editor",
		modules: {
			toolbar: [[{ font: [] }, { size: [] }], ["bold", "italic", "underline", "strike"], [{ color: [] }, { background: [] }], [{ script: "super" }, { script: "sub" }], [{ list: "ordered" }, { list: "bullet" }, { indent: "-1" }, { indent: "+1" }], ["direction", { align: [] }], ["link", "image", "video"], ["clean"]],
		},
		theme: "snow",
	});
	console.log("Document is ready.");

	const urlParams = new URLSearchParams(window.location.search);
	const productNo = urlParams.get("productNo");


	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT,
		type: "GET",
		data: { action: "getDetail", productNo: productNo }, 
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


			populateSelectOptions1("foodTypeOptions", data.foodTypeOptions);
			populateSelectOptions2("kitchenTypeOptions", data.kitchenTypeOptions);

			$("#productname-vertical").val(data.productName);
			$("#productprice-vertical").val(data.productPrice);
			$("#storageQty-vertical").val(data.storageQty);
			$("#selectedPart").val(data.selectedPart);
			$("#searchCount-vertical").val(data.searchCount);
			$("#preview img.preview_img").attr("src", "data:image/jpeg;base64," + data.productImage);

			var selectedPart = data.selectedPart;
			var selectedFoodType = data.selectedFoodType;
			var selectedKitchenType = data.selectedKitchenType;

			$("#partSelect").val(selectedPart);


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


			var shelfTimeStr = data.shelfTime;
			var offsaleTimeStr = data.offsaleTime;


			var shelfTime = new Date(shelfTimeStr);
			var offsaleTime = new Date(offsaleTimeStr);


			var formattedShelfTime = formatDate(shelfTime);
			var formattedOffsaleTime = formatDate(offsaleTime);


			$("#uptime-vertical").val(formattedShelfTime);
			$("#downtime-vertical").val(formattedOffsaleTime);
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("error");
		},
	});
});



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
	const month = String(date.getMonth() + 1).padStart(2, "0"); 
	const day = String(date.getDate()).padStart(2, "0");
	const hours = String(date.getHours()).padStart(2, "0");
	const minutes = String(date.getMinutes()).padStart(2, "0");
	const seconds = String(date.getSeconds()).padStart(2, "0");

	return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

//============更新===============================
$("#update").click(function(e) {
	e.preventDefault();

	var formData = new FormData();
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

	formData.append("productIntroduction", $("#floatingTextarea").val());
	formData.append("productDescription", $("#full .ql-editor").html());
	formData.append("action", "updateProduct");

	var productImage = $("#p_file")[0].files[0];
	formData.append("productImage", productImage);
	

	const urlParams = new URLSearchParams(window.location.search);
	const productNo = urlParams.get("productNo");
	formData.append("productNo", productNo);
	console.log(formData);

	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT,
		type: "POST",
		data: formData,
		dataType: "json",
		processData: false, 
		contentType: false, 
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
				console.log(response);
				if (response.message === "success") {
					alert("更新成功");
					window.location.href = "./shopview.html";
				} 
			}
		},
		error: function(xhr) {
			console.log(xhr.responseText);
			alert("更新失败");
		},
	});
});
