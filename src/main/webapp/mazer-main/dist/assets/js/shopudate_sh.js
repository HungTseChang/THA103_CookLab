// 在页面加载后执行
$(document).ready(function () {
  console.log("Document is ready.");
  // 获取URL参数
  const urlParams = new URLSearchParams(window.location.search);
  const productNo = urlParams.get("productNo");

  // 执行AJAX请求来获取商品详细信息
  $.ajax({
    url: "/CookLab/ProductServlet",
    type: "GET",
    data: { action: "getDetail", productNo: productNo }, // 传递商品编号
    dataType: "json",
    success: function (data) {
      console.log(data);

      // 填充食材和廚具種類的下拉選項
      console.log(data.foodTypeOptions);
      console.log(data.kitchenTypeOptions);
      populateSelectOptions1("foodTypeOptions", data.foodTypeOptions);
      populateSelectOptions2("kitchenTypeOptions", data.kitchenTypeOptions);

      // 使用返回的商品详细信息填充表单字段
      $("#productname-vertical").val(data.productName);
      $("#productprice-vertical").val(data.productPrice);
      $("#saleQty-vertical").val(data.saleQty);
      $("#storageQty-vertical").val(data.storageQty);
      $("#selectedPart").val(data.selectedPart);

      // 从服务器端接收的数据
      var selectedPart = data.selectedPart;
      var selectedFoodType = data.selectedFoodType;
      var selectedKitchenType = data.selectedKitchenType;
      console.log(selectedFoodType);
      console.log(selectedKitchenType);

      // 设置选择框的值
      $("#partSelect").val(selectedPart);

      // 根据 selectedPart 显示相应的选项
      if (selectedPart === "foodType") {
        // $("#foodTypeOptions select").val(selectedFoodType);
        $("#foodTypeOptions select").val(selectedFoodType).prop("selected", true);
        $("#partSelect").val("foodType");
        $("#foodTypeOptions").show();
        $("#kitchenTypeOptions").hide();
      } else if (selectedPart === "kitchenType") {
        // $("#kitchenTypeOptions select").val(selectedKitchenType);
        // document.querySelector("#kitchenTypeOptions select").value = selectedKitchenType;
        $("#kitchenTypeOptions select").val(selectedKitchenType).prop("selected", true);
        $("#partSelect").val("kitchenType");
        $("#foodTypeOptions").hide();
        $("#kitchenTypeOptions").show();
      }

      // 从服务器接收的日期字符串
      var shelfTimeStr = data.shelfTime;
      var offsaleTimeStr = data.offsaleTime;

      // 使用Date对象解析日期
      var shelfTime = new Date(shelfTimeStr);
      var offsaleTime = new Date(offsaleTimeStr);

      // 使用Date对象格式化日期为所需格式
      var formattedShelfTime = formatDate(shelfTime);
      var formattedOffsaleTime = formatDate(offsaleTime);

      // 将格式化后的日期设置到表单字段中
      $("#uptime-vertical").val(formattedShelfTime);
      $("#downtime-vertical").val(formattedOffsaleTime);

      // 商品簡介和詳情的填充
      $("#floatingTextarea").val(data.productIntroduction);

      // 找到包含商品详情的 div 元素
      var editorDiv = $(".ql-editor");
      // 在该 div 中找到 p 标签
      var pTag = editorDiv.find("p");
      // 设置 p 标签的文本内容为商品详情
      pTag.html(data.productDescription);
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("请求数据失败");
    },
  });
});

// 在填充食材和廚具種類的下拉選項时，确保为每个选项设置了 value 属性

///廚具
function populateSelectOptions1(containerId, options) {
  var select = $("#" + containerId + " select");
  select.empty();
  for (var i = 0; i < options.length; i++) {
    var option = $("<option></option>").attr("value", options[i].ingredientCategoryNo).text(options[i].categoryName);
    select.append(option);
    // console.log(select.append(option));
  }
}

/// 食材
function populateSelectOptions2(containerId, options) {
  var select = $("#" + containerId + " select");
  select.empty();
  for (var i = 0; i < options.length; i++) {
    var option = $("<option></option>").attr("value", options[i].kitchenwareCategoryNo).text(options[i].categoryName);
    select.append(option);
    // console.log(select.append(option));
  }
}
