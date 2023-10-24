//======================查詢=============================
// Simple Datatable
let table1 = document.querySelector("#table1");
let dataTable = new simpleDatatables.DataTable(table1);

//======================商品=======================
document.addEventListener("DOMContentLoaded", function () {
  // 在页面加载后执行AJAX请求
  let requestData = {
    action: "search", // 你要执行的操作
  };
  $.ajax({
    url: "/CookLab/ProductServlet",
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

      // 获取表格的 tbody 元素
      var tbody = $("#table1 tbody");
      tbody.empty(); // 清空表格内容

      // 遍历数据并创建表格行
      $.each(data, function (index, item) {
        var row = $("<tr></tr>"); // 创建行

        // 填充表格列数据
        row.append("<td>" + item.productNo + "</td>");
        row.append("<td><img src='data:image/png;base64," + item.productImage + "' style='max-width: 200px; max-height: 200px;'></td>");
        row.append("<td>" + item.productName + "</td>");
        row.append("<td>" + item.productPrice + "</td>");
        row.append("<td>" + item.offsaleTime + "</td>");
        row.append("<td>" + item.shelfTime + "</td>");
        row.append("<td>" + item.saleQty + "</td>");

        // 这里需要根据数据结构填充商品种类的列
        // 如果数据中有商品种类字段，你需要根据字段填充，否则可以使用默认值
        row.append("<td>" + (item.Category ? item.Category : "N/A") + "</td>");

        var detailButton = "<td><a class='btn btn-info' href='./shopset.html'>詳細資料</a></td>";
        row.append(detailButton);

        // 将行添加到表格的 tbody
        tbody.append(row);
      });

      // 初始化Simple Datatable
      dataTable = new simpleDatatables.DataTable(document.getElementById("table1"));
    },
    error: function (xhr) {
      console.log(xhr.responseText);
      alert("请求数据失败");
    },
  });
});
