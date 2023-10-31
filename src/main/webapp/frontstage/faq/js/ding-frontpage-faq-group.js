const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/QSFront";
let QSFrontAPI = END_POINT_URL + COLLECTION_POINT;
//取得當前網址的參數
const urlParams = new URLSearchParams(window.location.search);
//取得qGroup的值
const qGroup = urlParams.get("qGroup");
const pagesize = 10;

let renderPagination = function (totalPages, currentPage) {
  let paginationList = $(".pagination");
  paginationList.empty();

  for (let i = 1; i <= totalPages; i++) {
    paginationList.append(`<li class="page-item pages"><a class="page-link" href="#">${i}</a></li>`);
  }

  // Add active class to the current page
  $(`.pages a:contains(${currentPage})`).addClass("active");
};

//清除資料
let cleardata = function () {
  $(".question-data").remove();
};

// 網頁點擊分頁後取得不同分頁的資料
let dataload = function (currentPage) {
  $.ajax({
    type: "POST",
    url: QSFrontAPI,
    data: { action: "getQbyGroup", qGroup: qGroup, page: currentPage, pagesize: pagesize },
    dataType: "json",
    success: function (data) {
      cleardata();
      let list = $("#faqinfo");
      $.each(data.jsondata, function (index, jsondata) {
        list.append(`
          <div class="card question-data">
          <div class="card-header" id="${"heading" + index}">
            <h2 class="mb-0">
              <button class="btn btn-link" type="button" data-toggle="collapse" data-target="${"#collapse" + index}" 
              aria-expanded="true" aria-controls="${"collapse" + index}">${jsondata.questionTitle}</button>
            </h2>
          </div>
          <div id="${"collapse" + index}" class="collapse" aria-labelledby="${"heading" + index}" data-parent="#faqinfo">
            <div class="card-body">
              <p>${jsondata.questionContent}</p>
            </div>
          </div>
        </div>
    `);
      });
      console.log("總頁數" + data.totalPages);
      renderPagination(data.totalPages, currentPage);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

let init = function () {
  $.ajax({
    type: "GET",
    url: QSFrontAPI,
    data: { action: "getQbyGroup", qGroup: qGroup, page: 1, pagesize: pagesize },
    dataType: "json",
    success: function (data) {
      if (!data.errorMsgs) {
        $(".groupname").text(data.groupname);
        let list = $("#faqinfo");
        $.each(data.jsondata, function (index, jsondata) {
          list.append(`
          <div class="card question-data">
          <div class="card-header" id="${"heading" + index}">
            <h2 class="mb-0">
              <button class="btn btn-link" type="button" data-toggle="collapse" data-target="${"#collapse" + index}" 
              aria-expanded="true" aria-controls="${"collapse" + index}">${jsondata.questionTitle}</button>
            </h2>
          </div>
          <div id="${"collapse" + index}" class="collapse" aria-labelledby="${"heading" + index}" data-parent="#faqinfo">
            <div class="card-body">
              <p>${jsondata.questionContent}</p>
            </div>
          </div>
        </div>
    `);
        });
        renderPagination(data.totalPages, 1);
      } else {
        alert(data.error);
        window.location.href = data.url;
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  init();

  $(document).on("click", ".page-link", function () {
    cleardata();
    dataload($(this).text()); //取得當前頁數
    $(".page-item").removeClass("active");
    $(this).parent().addClass("active");
  });
});
