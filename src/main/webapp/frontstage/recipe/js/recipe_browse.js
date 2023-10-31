const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/RecipeCollectionServlet";
const COLLECTIONSTATUS_POINT = "/RecipeCollectionStatusServlet";
const RECIPE_POINT = "/RecipeOverviewServlet";
const COMMENTS_POINT = "/RecipeCommentsServlet";
var queryString = window.location.search;
var params = new URLSearchParams(queryString);
var recipeNo = params.get("recipeNo");
let step = 0;
let recipeCollectionNo;
/*============================================================ function ============================================================*/
//新增一列食材
function addIngredient(ingredients) {
    let addIngredient = `<div class="row align-items-center " style="margin: 5px">
                            <div class="col-md-5 ">
                                <span class="form-control ingredient" product=${ingredients.productNo}>${ingredients.ingredient}</span>
                                <div class="search-results"></div>
                            </div>
                            <div class="col-md-4">
                                <span type="text" class="form-control ingredient-quantity">${ingredients.ingredientQuantity}</span>
                            </div>
                        </div>`;
    $("#listIngredient").append(addIngredient);
}
//新增一列廚具
function addKitchenware(kitchenware) {
    let addKitchenware = `<div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <span class="form-control kitchenware" product=${kitchenware.productNo}>${kitchenware.kitchenware}</span>
                                <div class="search-results"></div>
                            </div>
                          </div>`;
    $("#listKitchenware").append(addKitchenware);
}
//新增一列步驟
function addStep(index, step) {
    let addStep = `<div class="row step" step="${index + 1}">
    <div class="col-md-3 text-center">
        <div class="step-img-view">
            <span class="text">無上傳圖片</span>
            ${step.stepImg != null ? '<img id="stepImg" src=data:image/*;base64,' + step.stepImg + ' alt="" onerror="this.style.display="none"/>' : ""}
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <span class="recipe_content col-md-2">步驟${index + 1}:</span>
            <span type="text" class="form-control col-md-3 step-time">${step.stepTime}分鐘</span>
        </div>
        <textarea class="form-control martin-textarea step-content" aria-label="With textarea" readonly="readonly" value="${step.stepContent}"></textarea>
    </div>
 </div>`;
    $("#listStep").append(addStep);
}
//新增一列留言
function addComments(comments) {
    let addComments = `<div class="comment" style="padding: 20px; border: 1px solid black; border-radius: 0.25rem">
    <span class="username">${comments.name}</span>
    <p>${comments.content}</p>
    <span class="timestamp">${comments.createdTime}</span>
</div>`;
    $("#comments").append(addComments);
}
$(function () {
    //載入食譜資料
    $.ajax({
        url: END_POINT_URL + RECIPE_POINT, // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: { recipeNo: params.get("recipeNo"), action: "browse" }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            $("#recipeName").text(data.recipeName);
            $("#coverImage").attr("src", "data:image/*;base64," + data.coverImage);
            $("#introduction").val(data.introduction);
            $("#additionalExplanation").val(data.additionalExplanation);
            //		$("#recipeQuantity").val(data.recipeQuantity);
            $("#recipeQuantity").text(data.recipeQuantity + "人份");
            // $("#createdTimestamp")
            $(data.ingredient).each(function (index, element) {
                addIngredient(element);
            });
            $(data.kitchenware).each(function (index, element) {
                addKitchenware(element);
            });
            $(data.step).each(function (index, element) {
                addStep(index, element);
            });
            $(data.recipeHashtag).each(function (index, element) {
                $("#useHashTag").append(`<button class="addTag badge badge-secondary">${data.recipeHashtag}</button>`);
            });
            $(data.comments).each(function (index, element) {
                addComments(element);
            });
            $("#author").html(data.memberName);
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
    //載入收藏狀態
    $.ajax({
        url: END_POINT_URL + COLLECTIONSTATUS_POINT, // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: { recipeNo }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            data ? $("#removeCollection").removeClass("none") : $("#addCollection").removeClass("none");
            recipeCollectionNo = data;
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
    //還原登入後留言
    $("#inputComment").val(sessionStorage.getItem("comment"));
    /*============================================================ event ============================================================*/
    //新增收藏
    $("#addCollection").on("click", function () {
        $.ajax({
            url: END_POINT_URL + COLLECTION_POINT,
            type: "POST",
            data: { recipeNo, action: "insert" },
            dataType: "json",
            headers: {
                orginURL: window.location.href,
            },
            success: function (data) {
                if (data.redirectURL != null) {
                    window.location.href = data.redirectURL;
                } else {
                    recipeCollectionNo = data;
                    $("#addCollection").addClass("none");
                    $("#removeCollection").removeClass("none");
                }
            },
            error: function (xhr) {
                console.log("ajax失敗");
                console.log(xhr);
            },
        });
    });
    //取消收藏
    $("#removeCollection").on("click", function () {
        $.ajax({
            url: END_POINT_URL + COLLECTION_POINT,
            type: "POST",
            data: { recipeCollectionNo, action: "delete" },
            dataType: "json",
            headers: {
                orginURL: window.location.href,
            },
            success: function (data) {
                if (data.redirectURL != null) {
                    window.location.href = data.redirectURL;
                } else {
                    $("#removeCollection").addClass("none");
                    $("#addCollection").removeClass("none");
                }
            },
            error: function (xhr) {
                console.log("ajax失敗");
                console.log(xhr);
            },
        });
    });
    //新增留言
    $("#submitComment").on("click", function () {
        $.ajax({
            url: END_POINT_URL + COMMENTS_POINT,
            type: "POST",
            data: { recipeNo, action: "insert", commentContent: $("#inputComment").val() },
            dataType: "json",
            headers: {
                orginURL: window.location.href,
            },
            success: function (data) {
                if (data.redirectURL != null) {
                    window.location.href = data.redirectURL;
                    sessionStorage.setItem("comment", $("#inputComment").val());
                } else {
                    sessionStorage.removeItem("comment");
                    addComments(data);
                    $("#inputComment").val("");
                    $("html, body").animate({ scrollTop: 200 }, "slow");
                }
            },
            error: function (xhr) {
                console.log("ajax失敗");
                console.log(xhr);
            },
        });
    });
});
