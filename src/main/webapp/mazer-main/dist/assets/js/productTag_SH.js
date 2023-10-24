//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================食材、廚具=======================
document.addEventListener("DOMContentLoaded", function () {
  // 在页面加载后执行AJAX请求
  let requestData = {
    action: "search", // 你要执行的操作
  };
  $.ajax({
    url: "/CookLab/IngredientServlet", // 替换为你的Servlet映射的URL
    type: "GET",
    data: requestData,
    dataType: "json",
    statusCode: {
      // 状态码
      200: function (res) {},
      404: function (res) {},
      500: function (res) {},
    },
    success: function (data) {
      // 首先销毁当前的simpleDatatables实例
      if (dataTable) {
        dataTable.destroy();
      }

      // 数据请求成功后，处理数据并填充表格
      var table = $("#table1");
      var tbody = table.find("tbody");
      tbody.empty(); // 清空表格内容

      console.log("Data:", data); // 查看整个数据
      $.each(data, function (index, item) {
        console.log("Item:", item); // 查看每个项目
        // 创建修改按钮并包含数据属性，以便在点击时获取相关数据
        var updateButton = `<button  class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}" >修改</button>`;

        var row = `<tr>
          <td>${item.categoryTag}</td>
          <td>${item.categoryName}</td>
          <td>${updateButton}</td>
        </tr>`;

        tbody.append(row);
        console.log("Row:", row); // 查看生成的行
      });

      // 初始化Simple Datatable
      dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("请求数据失败");
    },
  });
});

function refreshData() {
  // 在页面加载后执行AJAX请求
  let requestData = {
    action: "search", // 你要执行的操作
  };
  $.ajax({
    url: "/CookLab/IngredientServlet", // 替换为你的Servlet映射的URL
    type: "GET",
    data: requestData,
    dataType: "json",
    statusCode: {
      // 状态码
      200: function (res) {},
      404: function (res) {},
      500: function (res) {},
    },
    success: function (data) {
      // 首先销毁当前的simpleDatatables实例
      if (dataTable) {
        dataTable.destroy();
      }

      // 数据请求成功后，处理数据并填充表格
      var table = $("#table1");
      var tbody = table.find("tbody");
      tbody.empty(); // 清空表格内容

      console.log("Data:", data); // 查看整个数据
      $.each(data, function (index, item) {
        console.log("Item:", item); // 查看每个项目
        // 创建修改按钮并包含数据属性，以便在点击时获取相关数据
        var updateButton = `<button  class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}" >修改</button>`;

        var row = `<tr>
          <td>${item.categoryTag}</td>
          <td>${item.categoryName}</td>
          <td>${updateButton}</td>
        </tr>`;

        tbody.append(row);
        console.log("Row:", row); // 查看生成的行
      });

      // 初始化Simple Datatable
      dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("请求数据失败");
    },
  });
}

// 定义函数用于更新数据表格
function updateDataTable(data) {
  // 这里可以根据新数据更新你的数据表格
  // 你需要根据你的前端框架或页面结构来实现更新逻辑
  // 以下是一个伪代码示例
  var table = $("#table1");
  var tbody = table.find("tbody");
  tbody.empty();

  $.each(data, function (index, item) {
    var row = `<tr>
            <td>${item.categoryTag}</td>
            <td>${item.categoryName}</td>
            <td><button class="btn btn-primary update-button" data-category-id="${item.categoryId}" data-category-tag="${item.categoryTag}" data-category-name="${item.categoryName}">修改</button></td>
        </tr>`;
    tbody.append(row);
  });

  // 重新初始化数据表格
  dataTable = new simpleDatatables.DataTable(table1);
}

//=========================食材=======================
// 找到"食材標籤"按鈕
const foodTagButton = document.getElementById("foodTagButton");

// 監聽按鈕點擊事件
foodTagButton.addEventListener("click", function (e) {
  e.preventDefault(); // 防止默認行為，例如頁面刷新

  // 執行AJAX請求以獲取食材相關的資料
  let requestData = {
    action: "searchFoodTags", // 自定義操作名稱，用於伺服器端識別
  };
  $.ajax({
    url: "/CookLab/IngredientServlet", // 替換為你的Servlet映射的URL
    type: "GET",
    data: requestData,
    dataType: "json",
    success: function (data) {
      // 首先销毁当前的simpleDatatables实例
      if (dataTable) {
        dataTable.destroy();
      }
      // 數據請求成功後，填充表格
      var table = $("#table1");
      var tbody = table.find("tbody");
      tbody.empty(); // 清空表格內容

      // 更新表头标题
      var tableHeaders = table.find("thead th");
      $(tableHeaders[0]).text("食材種類編號"); // 更新第一个标题
      $(tableHeaders[1]).text("種類名稱"); // 更新第二个标题

      $.each(data, function (index, item) {
        // 创建修改按钮并包含数据属性，以便在点击时获取相关数据
        var updateButton = `<button class="btn btn-primary update-button" data-category-id="${item.ingredientCategoryNo}" data-category-tag="食材" data-category-name="${item.categoryName}">修改</button>`;

        var row = `<tr><td>${item.ingredientCategoryNo}</td>
                        <td>${item.categoryName}</td>
                        <td>${updateButton}</td></tr>`;
        console.log(row);
        tbody.append(row);
      });

      // 初始化Simple Datatable
      dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("請求資料失敗");
    },
  });
});

//=========================廚具=======================
// 找到"廚具標籤"按鈕
const KitchenwareTagButton = document.getElementById("KitchenwareTagButton");

// 監聽按鈕點擊事件
KitchenwareTagButton.addEventListener("click", function (e) {
  e.preventDefault(); // 防止默認行為，例如頁面刷新

  // 執行AJAX請求以獲取食材相關的資料
  let requestData = {
    action: "searchKitchenwareTags", // 自定義操作名稱，用於伺服器端識別
  };
  $.ajax({
    url: "/CookLab/KitchenwaretServlet", // 替換為你的Servlet映射的URL
    type: "GET",
    data: requestData,
    dataType: "json",
    success: function (data) {
      // 首先销毁当前的simpleDatatables实例
      if (dataTable) {
        dataTable.destroy();
      }

      // 數據請求成功後，填充表格
      var table = $("#table1");
      var tbody = table.find("tbody");
      tbody.empty(); // 清空表格內容

      // 更新表头标题
      var tableHeaders = table.find("thead th");
      $(tableHeaders[0]).text("廚具種類編號"); // 更新第一个标题
      $(tableHeaders[1]).text("種類名稱"); // 更新第二个标题

      $.each(data, function (index, item) {
        // 创建修改按钮并包含数据属性，以便在点击时获取相关数据
        var updateButton = `<button class="btn btn-primary update-button" data-category-id="${item.kitchenwareCategoryNo}" data-category-tag="廚具" data-category-name="${item.categoryName}">修改</button>`;

        var row = `<tr><td>${item.kitchenwareCategoryNo}</td>
                        <td>${item.categoryName}</td>
                        <td>${updateButton}</td></tr>`;
        console.log(row);
        tbody.append(row);
      });

      // 初始化Simple Datatable
      dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("請求資料失敗");
    },
  });
});

//======================燈箱修改(全部)==========================
$(document).on("click", ".update-button", function () {
  var categoryTag = $(this).data("category-tag");
  var categoryName = $(this).data("category-name");
  var categoryId = $(this).data("category-id");
  // 现在你可以使用 categoryTag 和 categoryName 进行进一步的处理
  console.log("Category Tag:", categoryTag);
  console.log("Category Name:", categoryName);
  console.log("Category Id:", categoryId);
  console.log("a");
  // 将数据填充到模态框中
  $("#categoryTag").val(categoryTag);
  $("#categoryName").val(categoryName);
  $("#categoryId").val(categoryId);
  // 显示模态框
  $("#updateModal").modal("show");
});

// 监听保存更改按钮的点击事件
$("#saveChangesButton").on("click", function () {
  // 获取用户输入的修改后的数据
  var updatedCategoryTag = $("#categoryTag").val();
  var updatedCategoryName = $("#categoryName").val();
  var categoryId = $("#categoryId").val(); // 假设你有一个 id 为 categoryId 的隐藏字段

  // 执行保存更改的操作，可以向后端发送数据以更新信息

  // 创建要发送的数据对象
  var dataToSend = {
    action: "update", // 表示执行更新操作
    categoryTag: updatedCategoryTag,
    categoryName: updatedCategoryName,
    categoryId: categoryId,
  };
  console.log(dataToSend);
  console.log("a");
  // 发送 AJAX 请求
  $.ajax({
    url: "/CookLab/IngredientServlet", // 替换为你的后端 Servlet 的 URL
    type: "POST", // 使用 POST 方法发送数据
    data: dataToSend, // 发送的数据
    dataType: "json", // 数据类型为 JSON，根据实际情况修改
    success: function (response) {
      if (response.success === "true") {
        // 注意此处比较的是字符串 "true"
        // 操作成功
        alert(response.message);
        // 关闭模态框
        $("#updateModal").modal("hide");
        // 刷新数据表格或进行其他操作
        refreshData();
      } else {
        // 操作失败
        alert(response.message);
      }
    },
    error: function (xhr) {
      // 处理 AJAX 请求失败的情况
      console.log(xhr.responseText);
      alert("请求失败");
    },
  });

  // 隐藏模态框
  $("#updateModal").modal("hide");
});

// 获取模态框的关闭按钮和取消按钮
var closeModalButton = document.getElementById("closeModalButton");
var cancelButton = document.getElementById("cancelButton");

// 监听关闭按钮的点击事件
closeModalButton.addEventListener("click", function () {
  // 关闭模态框
  $("#updateModal").modal("hide");
});

// 监听取消按钮的点击事件
cancelButton.addEventListener("click", function () {
  // 关闭模态框
  $("#updateModal").modal("hide");
});

//=========================新增========================
$(document).ready(function () {
  // 添加标签按钮的点击事件处理程序
  $("#addTagButton").click(function () {
    // 选择模态框元素并显示它
    $("#insertmodel").modal("show");
  });
});

$(document).ready(function () {
  // 监听点击新增按钮
  $("#insertbutton").on("click", function () {
    console.log("新增按钮点击");

    // 获取模态框中的输入值
    var categoryTag = $("#tagType").val();
    var categoryName = $("#tagName").val();

    // 在这里可以添加一些数据验证，确保输入有效

    console.log("Category Tag:", categoryTag);
    console.log("Category Name:", categoryName);

    // 创建包含要发送到服务器的数据的对象
    var data = {
      categoryTag: categoryTag,
      categoryName: categoryName,
      action: "insert",
    };
    console.log(data);

    // 确定发送到不同的 URL
    var url = "";
    if (categoryTag === "食材") {
      url = "/CookLab/IngredientServlet"; // 替换为食材的 Servlet URL
    } else if (categoryTag === "廚具") {
      url = "/CookLab/KitchenwaretServlet"; // 替换为廚具的 Servlet URL
    }
    console.log("目标 URL:", url);

    // 使用 AJAX 发送数据到服务器
    $.ajax({
      type: "POST",
      url: url, // 替换为服务器端处理请求的URL
      data: data,
      success: function (response) {
        // 在成功响应时执行的操作
        console.log("AJAX 请求成功");
        if (response.success === "true") {
          // 注意此处比较的是字符串 "true"
          // 操作成功
          alert(response.message);
          // 关闭模态框
          $("#insertmodel").modal("hide");
          // 刷新数据表格或进行其他操作
          refreshData();
        } else {
          // 新增失败，处理错误或显示错误消息
          console.log("新增失败：", response.errorMessage);
          $("#insertmodel").modal("hide");
        }
      },
      error: function (error) {
        // 处理请求错误
        console.error("AJAX 请求错误:", error);
        $("#insertmodel").modal("hide");
      },
    });
  });

  // 监听关闭按钮的点击事件
  $("#closeModalButton2").on("click", function () {
    $("#insertmodel").modal("hide");
    console.log("关闭按钮点击");
  });

  // 监听取消按钮的点击事件
  $("#cancelButton2").on("click", function () {
    $("#insertmodel").modal("hide");
    console.log("取消按钮点击");
  });
});
//======================被動監聽=======================
// 監聽"食材標籤"按鈕點擊事件
// foodTagButton.addEventListener("click", function (e) {
//   e.preventDefault(); // 防止默認行為，例如頁面刷新
//   requestDataAndPopulateTable("searchFoodTags");
// });

// 監聽"廚具標籤"按鈕點擊事件
// kitchenwareTagButton.addEventListener("click", function (e) {
//   e.preventDefault(); // 防止默認行為，例如頁面刷新
//   requestDataAndPopulateTable("searchKitchenwareTags");
// });
