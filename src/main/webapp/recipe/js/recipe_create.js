let coverImageBase64;
let step;
let stepImgBase64;
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

//食材新增一列
$("#addIngredient").on("click", function () {
    let addIngredient = `<div class="row align-items-center " style="margin: 5px">
                             <div class="col-md-5 ">
                                 <input type="text" class="form-control ingredient" placeholder="請輸入食材" />
                             </div>
                             <div class="col-md-4">
                                 <input type="text" class="form-control ingredient-quantity" placeholder="份量" />
                             </div>
                         <i class="bi bi-list">&emsp;</i>
                         <i class="bi bi-trash3-fill delete-ingredient"></i>
                         </div>`;
    $("#listIngredient").append(addIngredient);
});
//刪除一列食材
$("#listIngredient").on("click", ".delete-ingredient", function () {
    $(this).parent().remove();
});
/*============================== 新增廚具 ==============================*/
//廚具新增一列
$("#addKitchenware").on("click", function () {
    let addKitchenware = `<div class="row align-items-center" style="margin: 5px">
                            <div class="col-md-5">
                                <input type="text" class="form-control kitchenware" placeholder="請輸入廚具" />
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
    step = parseInt($("#listStep .row:last").find(".step").attr("step")) + 1;
    let addStep = `<div class="row">
                    <div class="col-md-3 text-center">
                        <div class="preview"><span class="text">步驟圖片</span></div>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <span class="recipe_content col-md-2 step" step="${step}">步驟${step}:</span>
                            <input type="text" class="form-control col-md-3 step-time" placeholder="花費時間(分鐘)" />
                        </div>
                        <textarea class="form-control martin-textarea step_content" aria-label="With textarea" placeholder="步驟說明"></textarea>
                    </div>
                    <i class="bi bi-list">&emsp;</i>
                    <i class="bi bi-trash3-fill delete-step"></i>
                 </div>`;
    $("#listStep").append(addStep);
});
//刪除一列食材
$("#listStep").on("click", ".delete-step", function () {
    $(this).parent().remove();
});
//讓成品圖可以觸發選圖
$(".step-img-view").on("click", function () {
    this.querySelector(".step-img-input").click(); //使用jQuery會產生問題
});
//透過File取得預覽圖
$(".step-img-input").on("change", function () {
    let fileInput = this;
    let stepImgView = $(this).parent();
    console.log(fileInput);
    if (fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            let img_str = `<img src = "${e.target.result}" class = "step-img" >`;
            stepImgView.find("img").remove();
            stepImgView.append(img_str);
            stepImgBase64 = removeDataUrlHeader(e.target.result);
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

/*============================== 發布食譜 ==============================*/
$("#publish").on("click", function () {
    let createRecipe = {
        recipeName: $("#recipeName").val(),
        coverImage: coverImageBase64,
        introduction: $("#introduction").val(),
        additionalExplanation: $("#additionalExplanation").val(),
        // region: $("#region").val(),
        region: "地區", //測試
        recipeStatus: 1,
        recipeQuantity: $("#recipeQuantity").val(),
        action: "insert",
    };

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
        statusCode: {
            // 狀態碼
            200: function (res) {},
            404: function (res) {},
            500: function (res) {},
        },
        success: function (data) {
            // request 成功取得回應後執行
            console.log("ajax成功");
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
