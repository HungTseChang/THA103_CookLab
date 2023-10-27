var queryString = window.location.search;
var params = new URLSearchParams(queryString);

var recipeBrowse = "/article/article_content.jsp";
var coverImage = "/RecipeServletImg";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + host + webCtx;
//console.log(endPointURL);
///*============================================================ function ============================================================*/
//
//function addListRecipe(recipe) {
//	let addrecipe = `<div class="col-lg-4 col-md-4 col-sm-4">
//    <div class="blog__item">
//        <div class="blog__item__pic">
//            <img src="${endPointURL + coverImage + "?recipeNo=" + recipe.recipeNo}" alt="" />
//        </div>
//        <div class="blog__item__text">
//            <ul>
//                <li><i class="fa fa-calendar-o"/>${recipe.createdTimestamp}</li>
//                <li><i class="fa fa-comment-o"/>留言數:5</li>
//            </ul>
//            <h5><a href="${endPointURL + recipeBrowse + "?recipeNo=" + recipe.recipeNo}">${recipe.recipeName}</a></h5>
//            <p>${recipe.introduction}</p>
//            <a href="#" class="blog__btn">閱讀更多<span class="arrow_right"></span></a>
//        </div>
//    </div>
//</div>`;
//	$("#recipeList").append(addrecipe);
//}
///*============================================================ event ============================================================*/
//$("#page").on("click", ".page", function() {
//	$(".page").removeClass("disabled")
//	$(this).addClass("disabled");
//	$("html, body").animate({ scrollTop: 450 }, "slow");
//});
//
//
///*============================================================ ajax ============================================================*/
//
//function reipceLoad(page) {
//	$.ajax({
//		url: endPointURL + "/RecipeServlet", // 資料請求的網址
//		type: "POST", // GET | POST | PUT | DELETE | PATCH
//		data: { page: page, action: "overview" }, // 將物件資料(不用雙引號) 傳送到指定的 url
//		dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
//		success: function(data) {
//			$("#recipeList").html("");
//			$(data).each(function(index, element) {
//				addListRecipe(element);
//			});
//		},
//		error: function(xhr) {
//			console.log("ajax失敗");
//			console.log(xhr);
//		},
//	});
//}
//$.ajax({
//	url: endPointURL + "/RecipeServlet", // 資料請求的網址
//	type: "POST", // GET | POST | PUT | DELETE | PATCH
//	data: { action: "getPage" }, // 將物件資料(不用雙引號) 傳送到指定的 url
//	dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
//	success: function(data) {
//		for (let i = 0; i < data; i++)
//			$("#page").append(`<a class="page" href="javascript:void(0);" onclick="reipceLoad(${i})">&nbsp;${i + 1}&nbsp;</a>`);
//		$(".page").eq(0).addClass("disabled");
//	},
//	error: function(xhr) {
//		console.log("ajax失敗");
//		console.log(xhr);
//	},
//});
