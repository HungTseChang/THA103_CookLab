const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/NewsServlet";
let NewsServletAPI = END_POINT_URL + COLLECTION_POINT;

//定義網頁載入時需發送請求至資料庫索取資料的方法
let init = function () {
  $.ajax({
    type: "GET",
    url: NewsServletAPI,
    data: { action: "getAll" },
    dataType: "json",
    success: function (data) {
      console.log(data);
      let list = $(".news-list");
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data, function (i, news) {
        const newsTime = new Date(news.newsTime);

        list.append(`
        <tr>
            <td>${news.newsTitle}</td>
            <td>${news.newsContent}</td>
            <td>${newsTime.toLocaleString()}</td>
            <td>
                <a href="#" class="btn btn-primary newsupdate" data-newsindex="${i}">修改</a>
            </td>
        </tr>
    `);
      });

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

  $(document).on("click", ".newsupdate", function (e) {
    e.preventDefault();
    var newsindex = $(this).data("newsindex");
    window.location.href = "news-update.html?newsindex=" + newsindex;
  });
});
