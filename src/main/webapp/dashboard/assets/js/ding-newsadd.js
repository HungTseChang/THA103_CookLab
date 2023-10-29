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
    url: "/THA103_CookLab/newsAjax",
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
