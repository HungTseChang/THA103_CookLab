const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/NotifyCenterServlet";
let NotifyCenterServletAPI = END_POINT_URL + COLLECTION_POINT;

//定義通知狀態的數字對應狀態以及渲染到網頁的形式
let statusrender = function () {
  let NotifyReadStatus = $(".nr");

  NotifyReadStatus.each(function () {
    let status = $(this).text();

    switch (status) {
      case "未讀取":
        $(this).addClass("bg-warning text-dark");
        break;
      case "已讀取":
        $(this).addClass("bg-success");
        break;
      case "隱藏":
        $(this).addClass("bg-secondary");
        break;
    }
  });
};

//定義網頁載入時需發送請求至資料庫索取資料的方法
let init = function () {
  $.ajax({
    type: "GET",
    url: NotifyCenterServletAPI,
    data: { action: "getAll" },
    dataType: "json",
    success: function (data) {
      let list = $(".notify-list");
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data, function (index, notifycenter) {
        const createdTimestamp = new Date(notifycenter.createdTimestamp);
        const notifytime = new Date(notifycenter.notifyTime);

        list.append(`
                    <tr>
                      <td>${notifycenter.notifyNo}</td>
                      <td>${notifycenter.memberId}</td>
                      <td>${notifycenter.memberNickname}</td>
                      <td>${notifycenter.notifyType}</td>
                      <td><span class="nr badge">${notifycenter.notifyRead}</span></td>
                      <td>${notifytime.toLocaleString()}</td>
                      <td>${notifycenter.notifyContent}</td>
                      <td>${createdTimestamp.toLocaleString()}</td>
                      <td>
                        <a href="#" class="btn btn-primary notify-details" data-notifyno="${notifycenter.notifyNo}">詳細</a>
                      </td>
                    </tr>
                    `);
      });

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

  $(document).on("click", ".notify-details", function (e) {
    e.preventDefault();
    var notifyNo = $(this).data("notifyno");
    window.location.href = "notify-details.html?notifyNo=" + notifyNo;
  });
});
