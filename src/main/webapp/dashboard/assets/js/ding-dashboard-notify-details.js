const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/NotifyCenterServlet";
let NotifyCenterServletAPI = END_POINT_URL + COLLECTION_POINT;

//定義通知狀態的數字對應狀態以及渲染到網頁的形式
let statusrender = function () {
  let formStatus = $(".nr");

  formStatus.each(function () {
    let status = $(this).text();

    switch (status) {
      case "未讀取":
        $(this).addClass("bg-warning text-dark");
        break;
      case "已讀取":
        $(this).addClass("bg-success");
        break;
      case "隱藏":
        $(this).addClass("bg-secondary");
        break;
    }
  });
};

//定義網頁載入時需發送請求至資料庫索取資料的方法
let notifyload = function () {
  //取得當前網址的參數
  const urlParams = new URLSearchParams(window.location.search);

  //取得notifyNo的值
  const notifyNo = urlParams.get("notifyNo");

  $.ajax({
    type: "GET",
    url: NotifyCenterServletAPI,
    data: { action: "getOne", notifyNo: notifyNo },
    dataType: "json",
    success: function (data) {
      //將通知內容資料渲染到網頁上
      $("#notifyNo").text(notifyNo);
      $("#memberId").text(data.memberId);
      $("#memberAccount").text(data.memberAccount);
      $("#memberNickname").text(data.memberNickname);
      $("#notifyType").text(data.notifyType);
      $("#notifyRead").text(data.notifyRead);
      $("#notifyContent").text(data.notifyContent);

      const notifyTime = data.notifyTime;
      const date1 = new Date(notifyTime);
      const formattedDate1 = date1.toLocaleString();
      $("#notifyTime").text(formattedDate1);

      const createdTimestamp = data.createdTimestamp;
      const date2 = new Date(createdTimestamp);
      const formattedDate2 = date2.toLocaleString();
      $("#createdTimestamp").text(formattedDate2);
      statusrender();
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//進入修改狀態
let upadatemode = function () {
  let memberId = $("#memberId").text();
  let notifyTime = $("#notifyTime").text();
  let notifyContent = $("#notifyContent").text();

  $("#memberId").replaceWith(`<input type="text" class="form-control w-50" id="memberId" value="${memberId}" />`);
  $("#notifyType").replaceWith(`
    <select class="form-select w-50" id="notifyType">
      <option value="default" selected>請選擇通知類別</option>
      <option value="0">系統通知</option>
      <option value="1">食譜通知</option>
      <option value="2">討論區通知</option>
      <option value="3">商城通知</option>
    </select>
  `);
  $("#notifyRead").replaceWith(`
    <select class="form-select w-50" id="notifyRead">
      <option value="default" selected>請選擇通知狀態</option>
      <option value="0">未讀取</option>
      <option value="1">已讀取</option>
      <option value="2">隱藏</option>
    </select>
  `);
  $("#notifyTime").replaceWith(`<input type="date" class="form-control flatpickr-no-config bg-white w-50" value="${notifyTime}" id="notifyTime" />`);
  $("#notifyContent").replaceWith(`<textarea class="form-control" id="notifyContent" rows="3">${notifyContent}</textarea>`);

  let updatenotify_ele = $("#updatenotify");
  updatenotify_ele.replaceWith(`
    <button class="btn btn-primary" id="updateconfirm">確認修改</button>
    <button class="btn btn-secondary ms-2" id="updatecancel">取消</button>
  `);

  let newInput = document.getElementById("notifyTime");
  if (newInput) {
    flatpickr(newInput, {
      enableTime: true,
      dateFormat: "Y-m-d H:i:ss",
    });
  }
};

//清除錯誤提示
let updatereset = function () {
  $(".errCheck").remove();
};

//修改資料功能
let updatedata = function (notifyType, memberId, notifyTime, notifyContent, notifyRead) {
  updatereset();
  let notifyNo = $("#notifyNo").text();

  let data = {
    notifyNo: notifyNo,
    notifyType: notifyType,
    memberId: memberId,
    notifyTime: notifyTime,
    notifyContent: notifyContent,
    notifyRead: notifyRead,
    action: "update",
  };

  $.ajax({
    type: "POST",
    url: NotifyCenterServletAPI,
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        window.location.reload();
      } else {
        if (data.errIdblank) {
          $("#id-check").append(`<div class="text-danger errCheck">${data.errIdblank}</div>`);
        }
        if (data.errId) {
          $("#id-check").append(`<div class="text-danger errCheck">${data.errId}</div>`);
        }
        if (data.errType) {
          $("#type-check").append(`<div class="text-danger errCheck">${data.errType}</div>`);
        }
        if (data.errTime) {
          $("#time-check").append(`<div class="text-danger errCheck">${data.errTime}</div>`);
        }
        if (data.errContent) {
          $("#content-check").append(`<div class="text-danger errCheck">${data.errContent}</div>`);
        }
        if (data.errRead) {
          $("#read-check").append(`<div class="text-danger errCheck">${data.errRead}</div>`);
        }
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  notifyload();

  $("#updatenotify").on("click", function () {
    upadatemode();
  });

  $(document).on("click", "#updateconfirm", function () {
    let memberId = $("#memberId").val();
    let notifyTime = $("#notifyTime").val();
    let notifyContent = $("#notifyContent").val();
    let notifyType = $("#notifyType").val();
    let notifyRead = $("#notifyRead").val();
    console.log(notifyRead);
    updatedata(notifyType, memberId, notifyTime, notifyContent, notifyRead);
  });

  $(document).on("click", "#updatecancel", function () {
    window.location.reload();
  });
});
