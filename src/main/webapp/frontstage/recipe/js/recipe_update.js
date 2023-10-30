const RECIPETOUPDATE_POINT = "/RecipeOverviewServlet";
const UPDATE_POINT = "/RecipeUpdateServlet";
var queryString = window.location.search;
var params = new URLSearchParams(queryString);
var recipeNo = params.get("recipeNo");
/*============================================================ function ============================================================*/
/*============================================================ event ============================================================*/
//載入食譜資料
$(function() {
	$.ajax({
		url: END_POINT_URL + RECIPETOUPDATE_POINT, // 資料請求的網址
		type: "POST", // GET | POST | PUT | DELETE | PATCH
		data: { recipeNo: params.get("recipeNo"), action: "browse" }, // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
		success: function(data) {
			$("#recipeName").val(data.recipeName);
			$("#coverImage").attr("src", "data:image/*;base64," + data.coverImage);
			$("#introduction").val(data.introduction);
			$("#additionalExplanation").val(data.additionalExplanation);
			document.addEventListener("DOMContentLoaded", function() {
				$("##recipeQuantity").val(5);
				$(".selector").val();

				4、获取当前选中项的text

				$(".selector").find("option:selected").text();
			});
			document.querySelector("#recipeQuantity").value = 5;
			// $("#createdTimestamp")
			$(data.ingredient).each(function(index, element) {
				$("#addIngredient").click();
				$(".ingredient").eq(index).val(element.ingredient);
				$(".ingredient-quantity").eq(index).val(element.ingredientQuantity);
			});
			$(data.kitchenware).each(function(index, element) {
				$("#addKitchenware").click();
				$(".kitchenware").eq(index).val(element.kitchenware);
			});
			$(data.step).each(function(index, element) {
				$("#addStep").click();
				$(".step-time").eq(index).val(element.stepTime);
				$(".step-content").eq(index).val(element.stepContent);
				$(".step-img-view").eq(index).append(`<img src = "data:image/*;base64,${element.stepImg}" class = "step-img" >`);
			});
			$(data.recipeHashtag).each(function(index, element) {
				$("#useHashTag").append(`<button class="addTag badge badge-secondary">${data.recipeHashtag}</button>`);
			});
		},
		error: function(xhr) {
			console.log("ajax失敗");
			console.log(xhr);
		},
	});
});
