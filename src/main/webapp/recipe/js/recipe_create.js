$("#btnTag").on("click", function () {
    $("#tagBox").removeClass("none");
});

$(".btn_modal_close").on("click", function () {
    $("#tagBox").addClass("none");
});

// modal 中的半透明黑色區域
$("#tagBox").on("click", function () {
    $(this).addClass("none");
});

// 點擊 lightbox 中的白色區域，不會關掉 modal
$("#tagBox article").on("click", function (e) {
    e.stopPropagation();
});

$(".addTag").on("click", function (e) {
    let that = this;
    let tagText = $(that).text();
    let clone = $(this).clone();
    $("#selectTag input").before(clone.removeClass("addTag").addClass("deleteTag"));
    $(this).addClass("disabled-button");
    clone.on("mouseover", function () {
        $(this).append(" x");
    });
    clone.on("mouseout", function () {
        $(this).text(tagText);
    });
    clone.on("click", function () {
        $(this).remove();
        $(that).removeClass("disabled-button");
    });
});

$("#publish").on("click", function () {
    console.log("sss");
    let createRecipe = {
        // recipeName: $("#recipeName").val(),
        // coverImage: $("#coverImage").val(),
        // introduction: $("#introduction").val(),
        // additionalExplanation: $("#additionalExplanation").val(),
        // region: $("#region").val(),
        // recipeStatus: $("#recipeStatus").val(),
        // reportCount: $("#reportCount").val(),
        // viewCount: $("#viewCount").val(),
        // recipeQuantity: $("#recipeQuantity").val(),
        recipeName: "食譜名稱",
        coverImage: null,
        introduction: "簡介",
        additionalExplanation: "補充說明",
        region: "地區",
        recipeStatus: 1,
        reportCount: 2,
        viewCount: 3,
        recipeQuantity: 4,
        action: "insert"
    };
    $.ajax({
        url: "http://localhost:8081/CookLab/RecipeServlet", // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: createRecipe  , // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {
            // 在 request 發送之前執行
        },
        headers: {
            // request 如果有表頭資料想要設定的話
            // "X-CSRF-Token":"abcde"   // 參考寫法
        },
        statusCode: {
            // 狀態碼
            200: function (res) {},
            404: function (res) {},
            500: function (res) {},
        },
        success: function (data) {
            // request 成功取得回應後執行
            console.log(data);
        },
        error: function (xhr) {
            // request 發生錯誤的話執行
            console.log(xhr);
        },
        complete: function (xhr) {
            // request 完成之後執行(在 success / error 事件之後執行)
            console.log(xhr);
        },
    });
});
