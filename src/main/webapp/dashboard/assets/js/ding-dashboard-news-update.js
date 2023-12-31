const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/NewsServlet";
let NewsServletAPI = END_POINT_URL + COLLECTION_POINT;

//重置錯誤訊息渲染畫面
let reset = function () {
  $(".errCheck").remove();
};

//更新消息
let newsupdate = function () {
  //取得當前網址的參數
  const urlParams = new URLSearchParams(window.location.search);

  //取得newsindex的值
  const newsindex = urlParams.get("newsindex");

  let newsTitle = $("#newsTitle").val().trim();
  let newsContent = $("#newsContent").val().trim();
  let newsTime = $("#newsTime").text();
  let action = $("#action").val();

  let data = {
    newsTitle: newsTitle,
    newsContent: newsContent,
    newsTime: newsTime,
    newsindex: newsindex,
    action: action,
  };

  $.ajax({
    type: "POST",
    url: NewsServletAPI,
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        window.location.href = data.url;
      } else {
        if (data.errTitle) {
          $("#title-check").append(`<div class="text-danger errCheck">${data.errTitle}</div>`);
        }
        if (data.errContent) {
          $("#context-check").append(`<div class="text-danger errCheck">${data.errContent}</div>`);
        }
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//取得後端回傳的Redis資料
let newsload = function () {
  //取得當前網址的參數
  const urlParams = new URLSearchParams(window.location.search);

  //取得newsindex的值
  const newsindex = urlParams.get("newsindex");

  $.ajax({
    type: "GET",
    url: NewsServletAPI,
    data: { action: "getOne", newsindex: newsindex },
    dataType: "json",
    success: function (data) {
      //將消息內容資料渲染到網頁上
      $("#newsTitle").val(data.newsTitle);
      $("#newsContent").val(data.newsContent);
      $("#newsTime").text(data.newsTime);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  newsload();
  $("#formsubmit").on("click", function (e) {
    e.preventDefault();
    reset();
    newsupdate();
  });
});
