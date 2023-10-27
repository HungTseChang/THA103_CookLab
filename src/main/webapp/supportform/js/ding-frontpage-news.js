//定義網頁載入時需發送請求至資料庫索取資料的方法
let init = function () {
  $.ajax({
    type: "GET",
    url: "/THA103_CookLab/newsAjax",
    data: { action: "getten" },
    dataType: "json",
    success: function (data) {
      console.log(data);
      let list = $(".news-list");
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data, function (i, news) {
        const newsTime = new Date(news.newsTime);

        list.append(`
            <li class="list-group-item">
                <div class="ding-newstime text-secondary">${newsTime.toLocaleString()}</div>
                <div class="ding-newstitle">${news.newsTitle}</div>
                <div class="ding-newscontent">${news.newsContent}</div>
            </li>
    `);
      });
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  init();
});
