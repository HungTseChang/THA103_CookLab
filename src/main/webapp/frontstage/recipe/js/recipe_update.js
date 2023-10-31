const RECIPETOUPDATE_POINT = "/RecipeOverviewServlet";
const UPDATE_POINT = "/RecipeUpdateServlet";
var queryString = window.location.search;
var params = new URLSearchParams(queryString);
var recipeNo = params.get("recipeNo");
/*============================================================ function ============================================================*/

$(function () {
    //載入食譜資料
    $.ajax({
        url: END_POINT_URL + RECIPETOUPDATE_POINT, // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: { recipeNo: params.get("recipeNo"), action: "browse" }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            $("#recipeName").val(data.recipeName);
            $("#coverImage").attr("src", "data:image/*;base64," + data.coverImage);
            $("#introduction").val(data.introduction);
            $("#additionalExplanation").val(data.additionalExplanation);
            $("#recipeQuantity")
                .next()
                .find("li")
                .eq(data.recipeQuantity - 1)
                .click();
            // $("#createdTimestamp")
            $(data.ingredient).each(function (index, element) {
                $("#addIngredient").click();
                $(".ingredient").eq(index).val(element.ingredient);
                $(".ingredient-quantity").eq(index).val(element.ingredientQuantity);
            });
            $(data.kitchenware).each(function (index, element) {
                $("#addKitchenware").click();
                $(".kitchenware").eq(index).val(element.kitchenware);
            });
            $(data.step).each(function (index, element) {
                $("#addStep").click();
                $(".step-time").eq(index).val(element.stepTime);
                $(".step-content").eq(index).val(element.stepContent);
                if (element.stepImg) $(".step-img-view").eq(index).append(`<img src = "data:image/*;base64,${element.stepImg}" class = "step-img" >`);
            });
            $(data.recipeHashtag).each(function (index, element) {
                $("#tagBox .addTag").each(function (i, buttonel) {
                    if (buttonel.textContent == element) {
                        buttonel.click();
                    }
                });
            });
        },
        error: function (xhr) {
            console.log("ajax失敗");
            console.log(xhr);
        },
    });
    /*============================================================ event ============================================================*/
    $("#update").on("click", function () {
        let ingredient = [];
        let kitchenware = [];
        let step = [];
        let recipeHashtag = [];
        $("#listIngredient .ingredients").each(function (index, element) {
            ingredient[index] = {
                ingredient: $(element).find(".ingredient").val(),
                ingredientQuantity: $(element).find(".ingredient-quantity").val(),
            };
        });
        $("#listKitchenware .kitchenwares").each(function (index, element) {
            kitchenware[index] = $(element).find(".kitchenware").val();
        });
        $("#listStep .step").each(function (index, element) {
            step[index] = {
                stepImg: stepImgBase64[index],
                stepTime: $(element).find(".step-time").val(),
                stepContent: $(element).find(".step-content").val(),
            };
        });
        $("#selectTag button").each(function (index, element) {
            recipeHashtag[index] = $(element).text();
        });
        //送出的資料
        console.log(step[0].stepImg);
        let RecipeCreateDTO = {
            recipeNo: params.get("recipeNo"),
            recipeName: $("#recipeName").val(),
            coverImage: coverImageBase64,
            recipeQuantity: $("#recipeQuantity").val(),
            introduction: $("#introduction").val(),
            additionalExplanation: $("#additionalExplanation").val(),
            region: $("#region").val(),
            ingredient: ingredient,
            kitchenware: kitchenware,
            step: step,
            recipeHashtag: recipeHashtag,
        };

        $.ajax({
            url: END_POINT_URL + UPDATE_POINT, // 資料請求的網址
            type: "GET", // GET | POST | PUT | DELETE | PATCH
            data: RecipeUpdateDTO, // 將物件資料(不用雙引號) 傳送到指定的 url
            dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
            success: function (data) {},
            error: function (xhr) {
                console.log("ajax失敗");
                console.log(xhr);
            },
        });
    });
});
