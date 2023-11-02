const UPDATE_POINT = "/RecipeUpdateServlet";
var queryString = window.location.search;
var params = new URLSearchParams(queryString);
let recipeNo;
/*============================================================ function ============================================================*/

$(function () {
    //載入食譜資料
    $.ajax({
        url: END_POINT_URL + UPDATE_POINT,
        type: "POST",
        data: {},
        dataType: "json",
        headers: { recipeNo: params.get("recipeNo"), action: "getByUpdate" },
        success: function (data) {
            recipeNo = data.recipeNo;
            $("#recipeName").val(data.recipeName);
            $("#coverImage").attr("src", "data:image/*;base64," + data.coverImage);
            coverImageBase64 = data.coverImage;
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
                stepImgBase64[index] = element.stepImg;
            });
            $(data.recipeHashtag).each(function (index, element) {
                $("#addTag .addTag").each(function (i, buttonel) {
                    if (buttonel.textContent == element && !$(buttonel).hasClass("disabled-button")) {
                        buttonel.click();
                    }
                });
            });
        },
        error: function (xhr) {
            window.location.href = END_POINT_URL + "/frontstage/recipe/recipe_overview.jsp";
        },
    });
    /*============================================================ event ============================================================*/
    $("#submitUpdate").on("submit", function (e) {
        e.preventDefault();
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
        let RecipeUpdateDTO = {
            recipeNo: recipeNo,
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
            url: END_POINT_URL + UPDATE_POINT,
            type: "POST",
            data: JSON.stringify(RecipeUpdateDTO),
            headers: { recipeNo: recipeNo, action: "update" },
            dataType: "json",
            success: function (data) {
                alert("修改食譜成功囉");
                window.location.href = END_POINT_URL + BROWSE_POINT + "?recipeNo=" + data;
            },
            error: function (xhr) {
                window.location.href = END_POINT_URL + "/frontstage/recipe/recipe_overview.jsp";
            },
        });
    });
});
