var queryString = window.location.search;
var params = new URLSearchParams(queryString);
const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const RECIPEBROWSE_POINT = "/recipe/recipe_browse.jsp";
const COVERIMAGE_POINT = "/RecipeOverviewImgServlet";
const RECIPEOVERVIEW_POINT = "/RecipeOverviewServlet";
let cloumn = "recipeNo"; //初始欄位排序
let desc = true; //初始順序
let search = ""; //初始搜尋
/*============================================================ function ============================================================*/
// //拿到頁數
function getpage(cloumnParam, searchParam, descParam) {
    $.ajax({
        url: END_POINT_URL + RECIPEOVERVIEW_POINT,
        type: "GET",
        data: { action: "getPage", search: searchParam },
        dataType: "json",
        success: function (data) {
            $("#page").html("");
            for (let i = 0; i < data; i++)
                $("#page").append(`<a class="page" href="javascript:void(0);" onclick="recipeOverview(${i},'${cloumnParam}','${searchParam}',${descParam})">&nbsp;${i + 1}&nbsp;</a>`);
            $(".page").eq(0).addClass("disabled");
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
}
//輸入頁數拿到食譜和搜尋
function recipeOverview(pageParam, cloumnParam, searchParam, descParam) {
    $.ajax({
        url: END_POINT_URL + RECIPEOVERVIEW_POINT,
        type: "GET",
        data: { cloumn: cloumnParam, desc: descParam, page: pageParam, search: searchParam, action: "overview" },
        dataType: "json",
        success: function (data) {
            $("#recipeList").html("");
            $(data).each(function (index, element) {
                addListRecipe(element);
            });
            getpage(cloumnParam, searchParam, descParam);
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
            recipeOverview(0, "recipeNo", $("#search input").val(), true);
            break;
        case 2:
            recipeOverview(0, "recipeNo", $("#search input").val(), false);
            break;
        case 3:
            recipeOverview(0, "viewCount", $("#search input").val(), true);
            break;
        case 4:
            recipeOverview(0, "viewCount", $("#search input").val(), false);
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

    $("#search button").on("click", function () {
        recipeOverview(0, "recipeNo", $("#search input").val(), true);
        console.log($("#search input"));
        $;
    });
});
