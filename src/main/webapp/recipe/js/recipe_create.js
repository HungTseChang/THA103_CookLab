let coverImageBase64;
let step;
let stepImgBase64 = [];
/*============================================================ function ============================================================*/
//去除base64標頭
function removeDataUrlHeader(dataUrl) {
    if (dataUrl.startsWith("data:")) {
        const commaIndex = dataUrl.indexOf(",");
        if (commaIndex !== -1) {
            return dataUrl.substring(commaIndex + 1);
        }
    }
    return dataUrl;
}
//刪除按鈕限制
function checkDeleteButtonStatus(listDelete) {
    if (listDelete.length > 1) {
        listDelete.each(function (index) {
            $(this).prop("style", "display:inlineblock"); // 启用所有删除按钮
        });
    } else {
        listDelete.prop("style", "display:none");
    }
}

/*============================================================ event ============================================================*/
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

$("#tagBox div").on("click", ".addTag", function () {
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

/*============================== 新增成品圖 ==============================*/

//讓成品圖可以觸發選圖
$("#coverImageView").on("click", function () {
    document.getElementById("coverImageInput").click(); //使用jQuery會產生問題
});
//透過File取得預覽圖
$("#coverImageInput").on("change", function () {
    let fileInput = document.getElementById("coverImageInput");
    let coverImageView = document.getElementById("coverImageView");

    if (fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            let img_str = `<img src = "${e.target.result}" id = "coverImage" >`;
            $("#coverImageView img").remove();
            $("#coverImageView").append(img_str);
            coverImageBase64 = removeDataUrlHeader(e.target.result);
        };
        reader.readAsDataURL(fileInput.files[0]); //觸發了 FileReader 對象處理讀取所選文件的數據並將其轉換為數據URL
    }
});
/*============================== 新增食材 ==============================*/

//新增一列食材
$("#addIngredient").on("click", function () {
    let addIngredient = `<div class="row align-items-center " style="margin: 5px">
                             <div class="col-md-5 ">
                                 <input type="text" class="form-control ingredient" placeholder="請輸入食材" category="Ingredient" oninput="searchProduct(this)"/>
                                 <div class="search-results"></div>
                             </div>
                             <div class="col-md-4">
                                 <input type="text" class="form-control ingredient-quantity" placeholder="份量" />
                             </div>
                         <i class="bi bi-list">&emsp;</i>
                         <i class="bi bi-trash3-fill delete-ingredient"></i>
                         </div>`;
    $("#listIngredient").append(addIngredient);
    checkDeleteButtonStatus($(".delete-ingredient"));
});
//刪除一列食材
$("#listIngredient").on("click", ".delete-ingredient", function () {
    $(this).parent().remove();
    checkDeleteButtonStatus($(".delete-ingredient"));
});

/*============================== 新增廚具 ==============================*/
//廚具新增一列
$("#addKitchenware").on("click", function () {
    let addKitchenware = `<div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control kitchenware" placeholder="請輸入廚具" category="Kitchenware" oninput="searchProduct(this)"/>
                                <div class="search-results"></div>
                            </div>
                            <i class="bi bi-list">&emsp;</i>
                            <i class="bi bi-trash3-fill delete-kitchenware"></i>
                          </div>`;
    $("#listKitchenware").append(addKitchenware);
});
//刪除一列食材
$("#listKitchenware").on("click", ".delete-kitchenware", function () {
    $(this).parent().remove();
});
/*============================== 新增步驟 ==============================*/

//步驟新增一列
$("#addStep").on("click", function () {
    step = parseInt($("#listStep .step:last").attr("step")) + 1;
    console.log($("#listStep .row:last"));
    let addStep = `<div class="row step" step="${step}">
                    <div class="col-md-3 text-center">
                        <div class="step-img-view">
                        	<span class="text">步驟圖片</span>
                        	<input type="file" class="step-img-input" accept="image/*" style="display: none" />
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <span class="recipe_content col-md-2">步驟${step}:</span>
                            <input type="text" class="form-control col-md-3 step-time" placeholder="花費時間(分鐘)" />
                        </div>
                        <textarea class="form-control martin-textarea step-content" aria-label="With textarea" placeholder="步驟說明"></textarea>
                    </div>
                    <i class="bi bi-list">&emsp;</i>
                    <i class="bi bi-trash3-fill delete-step"></i>
                 </div>`;
    $("#listStep").append(addStep);
    checkDeleteButtonStatus($(".delete-step"));
});
//刪除一列步驟
$("#listStep").on("click", ".delete-step", function () {
    $(this).parent().remove();
    checkDeleteButtonStatus($(".delete-step"));
});
//讓成品圖可以觸發選圖
$("#listStep").on("click", ".step-img-view", function () {
    this.querySelector(".step-img-input").click(); //使用jQuery會產生問題
});
//透過File取得預覽圖
$("#listStep").on("change", ".step-img-input", function () {
    let fileInput = this;
    let stepImgView = $(this).parent();
    if (fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            let img_str = `<img src = "${e.target.result}" class = "step-img" >`;
            stepImgView.find("img").remove();
            stepImgView.append(img_str);

            step = parseInt($(fileInput).parents(".step").attr("step"));
            stepImgBase64[step - 1] = removeDataUrlHeader(e.target.result);
        };
        reader.readAsDataURL(fileInput.files[0]); //觸發了 FileReader 對象處理讀取所選文件的數據並將其轉換為數據URL
    }
});

/*============================== xxx ==============================*/

//img_drop.addEventListener("dragover", function (e) {
//    e.preventDefault();
//    e.target.classList.add("-on"); // 加上 class
//});
//// 拖曳離開
//img_drop.addEventListener("dragleave", function (e) {
//    e.target.classList.remove("-on"); // 移除 class
//});
//
//img_drop.addEventListener("drop", function (e) {
//    e.preventDefault(); // 如果是從電腦檔案拖曳過來，就停掉預設行為
//    e.target.classList.remove("-on"); // 移除 class
//
//    reader_file(e.dataTransfer.files[0]);
//    p_file_el.value = ""; // 將 type="file" 那個清空
//});
//
//let coverImageView_el = $("#coverImageView");

// // 定义经度和纬度坐标
// var latitude = 40.7128; // 例如，纽约市的纬度
// var longitude = -74.006; // 例如，纽约市的经度

// // 使用 Google Maps Geocoding API 请求逆地理编码
// var geocoder = new google.maps.Geocoder();
// var location = new google.maps.LatLng(latitude, longitude);

// geocoder.geocode({ location: location }, function (results, status) {
//     if (status === "OK") {
//         if (results[0]) {
//             // 获取国家信息
//             for (var i = 0; i < results[0].address_components.length; i++) {
//                 var component = results[0].address_components[i];
//                 if (component.types.includes("country")) {
//                     var country = component.long_name;
//                     console.log("国家: " + country);
//                     break;
//                 }
//             }
//         } else {
//             console.log("无法获取位置信息");
//         }
//     } else {
//         console.log("逆地理编码失败: " + status);
//     }
// });

/*============================================================ ajax ============================================================*/
/*============================== 載入標籤 ==============================*/
$.ajax({
    url: `http://localhost:8081/CookLab/HashtagServlet`,
    type: "POST",
    data: { action: "getToRecipe" },
    dataType: "json",
    success: function (data) {
        Object.keys(data).forEach(function (key) {
            data[key].forEach(function (keys) {
                $(`#${key}-hashtag`).append(`<button class="addTag badge badge-secondary">${keys}</button>`);
            });
        });
    },
    error: function (xhr) {
        console.log("ajax失敗");
        console.log(xhr);
    },
});

/*============================== 搜尋商品 ==============================*/
//顯示搜尋完的結果
function displaySearchResults(results, product) {
    let searchResults = product.nextElementSibling;
    searchResults.innerHTML = "";
    results.forEach(function (result) {
        const resultItem = `<div class="result-item">${result}</div>`;
        $(searchResults).append(resultItem);
    });
}
function searchProduct(product) {
    const search = product.value;
    const category = product.getAttribute("category");
    if (search.trim() != "") {
        $.ajax({
            url: `http://localhost:8081/CookLab/Recipe${category}Servlet`,
            type: "POST",
            data: { search, action: "search", category },
            dataType: "json",
            beforeSend: function () {},
            headers: {},
            success: function (data) {
                displaySearchResults(data, product);
            },
            error: function (xhr) {
                console.log("ajax失敗");
                console.log(xhr);
            },
        });
    } else {
        product.nextElementSibling.innerHTML = "";
    }
}
//點擊搜尋結果放進輸入框
$(".search-init").on("click", ".result-item", function () {
    $(this).parent().prev().val($(this).text());
    $(".search-init .search-results").html("");
});
//點擊其他地方搜尋結果消失
$(document).on("mousedown", function (event) {
    var target = $(event.target);
    if (!target.is(".search-init .search-results") && !target.parents(".search-init .search-results").length) {
        $(".search-init .search-results").html("");
    }
});
/*============================== 發布食譜 ==============================*/

$("#publish").on("click", function () {
    let ingredient = document.querySelectorAll(".ingredient"); //所有食材
    let ingredientArray = []; //食材陣列
    let ingredientQuantity = document.querySelectorAll(".ingredient-quantity"); //所有食材
    let ingredientQuantitytArray = []; //食材份量陣列
    let kitchenware = document.querySelectorAll(".kitchenware"); //所有廚具
    let kitchenwareArray = []; //廚具陣列
    let stepTime = document.querySelectorAll(".step-time"); //所有步驟時間
    let stepTimeArray = []; //步驟時間陣列
    let stepContent = document.querySelectorAll(".step-content"); //所有步驟內容
    let stepContentArray = []; //步驟內容陣列
    let recipeHashtagArray = []; //標籤內容陣列

    //把食材的值放入陣列
    for (var i = 0; i < ingredient.length; i++) {
        var ingredientValue = ingredient[i].value;
        var quantityValue = ingredientQuantity[i].value;
        ingredientArray.push(ingredientValue);
        ingredientQuantitytArray.push(quantityValue);
    }
    //把廚具的值放入陣列
    for (var i = 0; i < kitchenware.length; i++) {
        var value = kitchenware[i].value;
        kitchenwareArray.push(value);
    }
    //把步驟時間的值放入陣列
    for (var i = 0; i < stepTime.length; i++) {
        // var imgValue =
        var timeValue = stepTime[i].value;
        var contentValue = stepContent[i].value;
        stepTimeArray.push(timeValue);
        stepContentArray.push(contentValue);
    }
    //把標籤的值放入陣列
    $("#selectTag button").each(function (index, element) {
        recipeHashtagArray.push($(element).text());
    });
    //送出的資料
    const createRecipe = {
        //食譜table
        recipeName: $("#recipeName").val(),
        coverImage: coverImageBase64,
        introduction: $("#introduction").val(),
        additionalExplanation: $("#additionalExplanation").val(),
        // region: $("#region").val(),
        region: "地區", //測試
        recipeStatus: 1,
        recipeQuantity: $("#recipeQuantity").val(),
        //食材table
        ingredient: JSON.stringify(ingredientArray),
        ingredientQuantity: JSON.stringify(ingredientQuantitytArray),
        //廚具table
        kitchenware: JSON.stringify(kitchenwareArray),
        //步驟table
        stepImg: JSON.stringify(stepImgBase64),
        stepTime: JSON.stringify(stepTimeArray),
        stepContent: JSON.stringify(stepContentArray),
        //食譜使用標籤table
        recipeHashtag: JSON.stringify(recipeHashtagArray),

        action: "insert",
    };
    //ajax送新增請求
    $.ajax({
        url: "http://localhost:8081/CookLab/RecipeServlet", // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: createRecipe, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {
            // 在 request 發送之前執行
        },
        headers: {
            // request 如果有表頭資料想要設定的話
            // "X-CSRF-Token":"abcde"   // 參考寫法
        },
        success: function (data) {
            if (data.sucess !=null) {
                window.location.href = "http://localhost:8081/CookLab/recipe/recipe_browse.html?recipeNo="+data.sucess;
            } else {
                alert(data);
            }
        },
        error: function (xhr) {
            // request 發生錯誤的話執行
            console.log("ajax失敗");
            console.log(xhr);
        },
        complete: function (xhr) {
            // request 完成之後執行(在 success / error 事件之後執行)
            console.log(xhr);
        },
    });
});
