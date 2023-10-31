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

let newsadd = function () {
  let newsTitle = $("#newsTitle").val().trim();
  let newsContent = $("#newsContent").val().trim();
  let action = $("#action").val();

  let data = {
    newsTitle: newsTitle,
    newsContent: newsContent,
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

$(document).ready(function () {
  $("#formsubmit").on("click", function (e) {
    e.preventDefault();
    reset();
    newsadd();
  });
});
