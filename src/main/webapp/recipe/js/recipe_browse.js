var queryString = window.location.search;
var params = new URLSearchParams(queryString);
let step = 0;
/*============================================================ function ============================================================*/
//新增一列食材
function addIngredient(ingredients) {
    let addIngredient = `<div class="row align-items-center " style="margin: 5px">
    <div class="col-md-5 ">
        <input type="text" class="form-control ingredient" readonly="readonly" value="${ingredients.ingredient}"/>
        <div class="search-results"></div>
    </div>
    <div class="col-md-4">
        <input type="text" class="form-control ingredient-quantity" readonly="readonly" value="${ingredients.ingredientQuantity}"/>
    </div>
</div>`;
    $("#listIngredient").append(addIngredient);
}
//新增一列廚具
function addKitchenware(kitchenware) {
    let addKitchenware = `<div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control kitchenware" readonly="readonly" value="${kitchenware}" />
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
            <img id="stepImg" src="data:image/*;base64,${step.stepImg}" alt="" onerror="this.style.display='none';"/>
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <span class="recipe_content col-md-2">步驟${index + 1}:</span>
            <input type="text" class="form-control col-md-3 step-time" readonly="readonly" value="${step.stepTime}分鐘" />
        </div>
        <textarea class="form-control martin-textarea step-content" aria-label="With textarea" readonly="readonly" value="${step.stepContent}"></textarea>
    </div>
 </div>`;
    $("#listStep").append(addStep);
}

/*============================================================ event ============================================================*/

/*============================================================ ajax ============================================================*/
$.ajax({
    url: "http://localhost:8081/CookLab/RecipeServlet", // 資料請求的網址
    type: "POST", // GET | POST | PUT | DELETE | PATCH
    data: { recipeNo: params.get("recipeNo"), action: "browse" }, // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
        $("#recipeName").val(data.recipeName);
        $("#coverImage").attr("src", "data:image/*;base64," + data.coverImage);
        $("#introduction").val(data.introduction);
        $("#additionalExplanation").val(data.additionalExplanation);
        //		$("#recipeQuantity").val(data.recipeQuantity);
        $("#recipeQuantity").val(data.recipeQuantity + "人份");
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
    },
    error: function (xhr) {
        console.log("ajax失敗");
        console.log(xhr);
    },
});
