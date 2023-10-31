const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/SFSFront";
let SFSFrontAPI = END_POINT_URL + COLLECTION_POINT;

$(document).ready(function () {
  //重置錯誤訊息渲染畫面
  let init = function () {
    $(".errCheck").remove();
  };

  $("#formsubmit").on("click", function (e) {
    e.preventDefault();

    init();

    let realName = $("#realName").val().trim();
    let replyEmail = $("#replyEmail").val().trim();
    let supportFormCategoryId = $("#supportFormCategoryId").val();
    let formTitle = $("#formTitle").val().trim();
    let formSource = $("#formSource").val();
    let formStatus = $("#formStatus").val();
    let formSubmitter = $("#formSubmitter").val();
    let formContext = $("#formContext").val();
    let action = $("#action").val();

    let data = {
      realName: realName,
      replyEmail: replyEmail,
      supportFormCategoryId: supportFormCategoryId,
      formTitle: formTitle,
      formSource: formSource,
      formStatus: formStatus,
      formSubmitter: formSubmitter,
      formContext: formContext,
      action: action,
    };

    $.ajax({
      type: "POST",
      url: SFSFrontAPI,
      data: data,
      dataType: "json",
      success: function (data) {
        if (data.success) {
          window.location.href = data.url;
        } else {
          if (data.errNameBlank) {
            $("#name-check").append(`<div class="text-danger errCheck">${data.errNameBlank}</div>`);
          }
          if (data.errNameReg) {
            $("#name-check").append(`<div class="text-danger errCheck">${data.errNameReg}</div>`);
          }
          if (data.errEmail) {
            $("#email-check").append(`<div class="text-danger errCheck">${data.errEmail}</div>`);
          }
          if (data.errCategory) {
            $("#categoryid-check").append(`<div class="text-danger errCheck">${data.errCategory}</div>`);
          }
          if (data.errTitle) {
            $("#title-check").append(`<div class="text-danger errCheck">${data.errTitle}</div>`);
          }
          if (data.errContext) {
            $("#context-check").append(`<div class="text-danger errCheck">${data.errContext}</div>`);
          }
        }
      },
      error: function (xhr) {
        console.log(xhr);
      },
    });
  });
});
