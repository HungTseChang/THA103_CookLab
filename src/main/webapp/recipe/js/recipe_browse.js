var queryString = window.location.search;
var params = new URLSearchParams(queryString);
/*============================================================ function ============================================================*/
/*============================================================ event ============================================================*/
/*============================================================ ajax ============================================================*/
$.ajax({
    url: "http://localhost:8081/CookLab/RecipeServlet?recipeNo=" + params.get("recipeNo"), // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    data: { action: "browse" }, // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {},
    error: function (xhr) {
        console.log("ajax失敗");
        console.log(xhr);
    },
});
