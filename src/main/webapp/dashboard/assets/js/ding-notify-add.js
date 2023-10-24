//重置錯誤訊息渲染畫面
let reset = function () {
  $(".errCheck").remove();
};

let notifyload = function () {
  let memberId = $("#memberId").val().trim();
  let notifyType = $("#notifyType").val();
  let notifyTime = $("#notifyTime").val();
  let notifyContent = $("#notifyContent").val();
  let action = $("#action").val();

  let data = {
    memberId: memberId,
    notifyType: notifyType,
    notifyTime: notifyTime,
    notifyContent: notifyContent,
    action: action,
  };

  $.ajax({
    type: "POST",
    url: "/THA103_CookLab/NotifyCenterAjax",
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        window.location.href = data.url;
      } else {
        if (data.errIdblank) {
          console.log(data.errIdblank);
          $("#idcheck").append(`<div class="text-danger errCheck">${data.errIdblank}</div>`);
        }
        if (data.errId) {
          console.log(data.errId);
          $("#idcheck").append(`<div class="text-danger errCheck">${data.errId}</div>`);
        }
        if (data.errType) {
          $("#type-check").append(`<div class="text-danger errCheck">${data.errType}</div>`);
        }
        if (data.errTime) {
          $("#time-check").append(`<div class="text-danger errCheck">${data.errTime}</div>`);
        }
        if (data.errContentt) {
          $("#content-check").append(`<div class="text-danger errCheck">${data.errContentt}</div>`);
        }
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  flatpickr(".flatpickr-no-config", {
    enableTime: true,
    dateFormat: "Y-m-d H:i:ss",
  });

  $("#formsubmit").on("click", function (e) {
    e.preventDefault();
    reset();
    notifyload();
  });
});
