//定義表單狀態的數字對應狀態以及渲染到網頁的形式
let statusrender = function () {
  let formStatus = $(".fs");

  formStatus.each(function () {
    let status = $(this).text();

    switch (status) {
      case "未處理":
        $(this).addClass("bg-warning text-dark");
        break;
      case "處理中":
        $(this).addClass("bg-info text-dark");
        break;
      case "已結案":
        $(this).addClass("bg-success");
        break;
    }
  });
};

//定義網頁載入時需發送請求至資料庫索取資料的方法
let init = function () {
  $.ajax({
    type: "GET",
    url: "/THA103_CookLab/SupportFormAjax",
    data: { action: "getAll" },
    dataType: "json",
    success: function (data) {
      let list = $(".ticket-list");
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data, function (index, supportform) {
        const createdTimestamp = new Date(supportform.createdTimestamp);
        let responderInfo = supportform.formResponderno === 0 ? "無" : `${supportform.formRespondername}(${supportform.formResponderno})`;

        list.append(`<tr>
                      <td>${supportform.formNo}</td>
                      <td>${supportform.supportFormCategoryId}</td>
                      <td>${supportform.formTitle}</td>
                      <td>${supportform.realName}</td>
                      <td>${supportform.replyEmail}</td>
                      <td>
                          <span class="fs badge">${supportform.formStatus}</span>
                      </td>
                      <td>
                        <span data-id="${supportform.formResponderno}">
                          ${responderInfo}
                        </span>
                      </td>
                      <td>
                       ${createdTimestamp.toLocaleString()}
                      </td>
                      <td>
                          <a href="#" class="btn btn-primary" data-formid="${supportform.formNo}">詳細</a>
                      </td>
                    </tr>`);
      });

      //呼叫美化處理狀態外觀的函式
      statusrender();
      //使用Simple Datatable第三方函式提供分頁樣式及簡易搜尋
      let table1 = document.querySelector("#table1");
      let dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  init();
});
