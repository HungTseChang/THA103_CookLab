var queryString = window.location.search;
var params = new URLSearchParams(queryString);
const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const RECIPEBROWSE_POINT = "/frontstage/recipe/recipe_browse.jsp";
const COVERIMAGE_POINT = "/RecipeOverviewImgServlet";
const RECIPEOVERVIEW_POINT = "/RecipeOverviewServlet";
let search = ""; //初始搜尋
/*============================================================ function ============================================================*/
// //拿到頁數
function getpage(cloumn, search, desc) {
    $.ajax({
        url: END_POINT_URL + RECIPEOVERVIEW_POINT,
        type: "GET",
        data: { action: "getPage", search: search },
        dataType: "json",
        success: function (data) {
            $("#page").html("");
            for (let i = 0; i < data; i++) $("#page").append(`<a class="page" href="javascript:void(0);" onclick="recipeOverview(${i},'${cloumn}','${search}',${desc})">&nbsp;${i + 1}&nbsp;</a>`);
            $(".page").eq(0).addClass("disabled");
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
}
//輸入頁數拿到食譜和搜尋
function recipeOverview(page, cloumn, search, desc) {
    $.ajax({
        url: END_POINT_URL + RECIPEOVERVIEW_POINT,
        type: "GET",
        data: { cloumn: cloumn, desc: desc, page: page, search: search, action: "overview" },
        dataType: "json",
        success: function (data) {
            $("#recipeList").html("");
            $(data).each(function (index, element) {
                addListRecipe(element);
            });
            if (data.length == 0) $("#recipeList").append('<div class="no-recipe-message"><i class="fa fa-exclamation-circle"></i><br>没有搜索到食譜</div>');
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
}
//放一個食譜到頁面
function addListRecipe(recipe) {
    let addrecipe = `<div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="blog__item">
                                <div class="blog__item__pic">
                                    <img src="${END_POINT_URL + COVERIMAGE_POINT + "?recipeNo=" + recipe.recipeNo}" alt="" />
                                </div>
                                <div class="blog__item__text">
                                    <ul>
                                        <li><i class="fa fa-calendar-o"/>${recipe.createdTimestamp}</li>
                                        <li><i class="fa fa-comment-o"/>留言數:5</li>
                                    </ul>
                                    <h5><a href="${END_POINT_URL + RECIPEBROWSE_POINT + "?recipeNo=" + recipe.recipeNo}">${recipe.recipeName}</a></h5>
                                    <p>${recipe.introduction}</p>
                                </div>
                            </div>
                        </div>`;
    $("#recipeList").append(addrecipe);
}
//排序
function sort(sort) {
    switch (sort) {
        case 1:
            recipeOverview(0, "recipeNo", search, true);
            getpage("recipeNo", search, true);
            break;
        case 2:
            recipeOverview(0, "recipeNo", search, false);
            getpage("recipeNo", search, false);
            break;
        case 3:
            recipeOverview(0, "viewCount", search, true);
            getpage("viewCount", search, true);
            break;
        case 4:
            recipeOverview(0, "viewCount", search, false);
            getpage("viewCount", search, false);
            break;
    }
}
/*============================================================ loading ============================================================*/
$(function () {
    sort(1);
    /*============================================================ event ============================================================*/
    //換頁效果
    $("#page").on("click", ".page", function () {
        $(".page").removeClass("disabled");
        $(this).addClass("disabled");
        $("html, body").animate({ scrollTop: 450 }, "slow");
    });
    //按下搜尋按紐
    $("#search button").on("click", function () {
        search = $("#search input").val();
        sort(1);
    });
    //按下enter搜尋
    $("#search input").on("keydown", function (e) {
        if (e.key === "Enter") {
            e.preventDefault();
            $("#search button").click();
        }
    });
});
