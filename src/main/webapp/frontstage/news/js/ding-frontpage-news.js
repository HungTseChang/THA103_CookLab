const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/NSFront";
let NSFrontAPI = END_POINT_URL + COLLECTION_POINT;

let totalPages = 10;
let maxVisiblePages = 5;

let renderPagination = function (totalPages, currentPage) {
  let paginationList = $(".pagination");
  paginationList.empty();

  // Calculate the range of visible pages
  let startPage = currentPage - Math.floor(maxVisiblePages / 2);
  let endPage = currentPage + Math.floor(maxVisiblePages / 2);

  // Ensure that the range stays within bounds
  if (startPage < 1) {
    startPage = 1;
    endPage = Math.min(totalPages, maxVisiblePages);
  }

  if (endPage > totalPages) {
    endPage = totalPages;
    startPage = Math.max(1, totalPages - maxVisiblePages + 1);
  }

  // Render page numbers with ellipsis
  if (startPage > 1) {
    paginationList.append(
      `<li class="page-item">
        <a class="page-link" href="#">1</a>
      </li>`
    );
    if (startPage > 2) {
      paginationList.append(`<li class="page-item disabled"><span class="page-link">...</span></li>`);
    }
  }

  for (let i = startPage; i <= endPage; i++) {
    paginationList.append(`<li class="page-item pages"><a class="page-link" href="#">${i}</a></li>`);
  }

  if (endPage < totalPages) {
    if (endPage < totalPages - 1) {
      paginationList.append(`<li class="page-item disabled"><span class="page-link">...</span></li>`);
    }
    paginationList.append(
      `<li class="page-item">
        <a class="page-link" href="#">${totalPages}</a>
      </li>`
    );
  }

  // Add active class to the current page
  $(`.pages a:contains(${currentPage})`).addClass("active");
};

//清除資料
let cleardata = function () {
  $(".news-data").remove();
};

// 網頁點擊分頁後取得不同分頁的資料
let dataload = function (currentPage) {
  $.ajax({
    type: "POST",
    url: NSFrontAPI,
    data: { action: "getten", currentPage: currentPage },
    dataType: "json",
    success: function (data) {
      cleardata();
      let list = $(".news-list");
      $.each(data.redisdata, function (i, news) {
        const newsTime = new Date(news.newsTime);

        list.append(`
          <li class="list-group-item news-data">
            <div class="ding-newstime text-secondary">${newsTime.toLocaleString()}</div>
            <div class="ding-newstitle">${news.newsTitle}</div>
            <div class="ding-newscontent">${news.newsContent}</div>
          </li>
        `);
      });
      renderPagination(data.totalPages, currentPage);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  $.ajax({
    type: "POST",
    url: NSFrontAPI,
    data: { action: "getten", currentPage: 1 },
    dataType: "json",
    success: function (data) {
      let list = $(".news-list");
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data.redisdata, function (i, news) {
        const newsTime = new Date(news.newsTime);

        list.append(`
            <li class="list-group-item news-data">
                <div class="ding-newstime text-secondary">${newsTime.toLocaleString()}</div>
                <div class="ding-newstitle">${news.newsTitle}</div>
                <div class="ding-newscontent">${news.newsContent}</div>
            </li>
    `);
      });
      renderPagination(data.totalPages, 1);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });

  $(document).on("click", ".page-link", function () {
    if ($(this).text() !== "Previous" && $(this).text() !== "Next") {
      cleardata();
      dataload($(this).text());
      $(".page-item").removeClass("active");
      $(this).parent().addClass("active");
    }
  });
});
