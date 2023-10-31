const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/QuestionServlet";
let QuestionServletAPI = END_POINT_URL + COLLECTION_POINT;

//重置錯誤訊息渲染畫面
let reset = function () {
  $(".errCheck").remove();
};

let questionadd = function () {
  let questionGroupNo = $("#questionGroupNo").val();
  let questionTitle = $("#questionTitle").val();
  let questionContent = $("#questionContent").val();
  let action = $("#action").val();

  let data = {
    questionGroupNo: questionGroupNo,
    questionTitle: questionTitle,
    questionContent: questionContent,
    action: action,
  };

  $.ajax({
    type: "POST",
    url: QuestionServletAPI,
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        window.location.href = data.url;
      } else {
        if (data.errQGno) {
          $("#group-check").append(`<div class="text-danger errCheck">${data.errQGno}</div>`);
        }
        if (data.errTitle) {
          $("#title-check").append(`<div class="text-danger errCheck">${data.errTitle}</div>`);
        }
        if (data.errContent) {
          $("#content-check").append(`<div class="text-danger errCheck">${data.errContent}</div>`);
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
    questionadd();
  });
});
