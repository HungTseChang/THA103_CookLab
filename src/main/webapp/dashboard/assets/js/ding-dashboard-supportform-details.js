const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT_FORM = "/SupportFormServlet";
const COLLECTION_POINT_RECORD = "/SupportFormRecordServlet";
let SupportFormServletAPI = END_POINT_URL + COLLECTION_POINT_FORM;
let SupportFormRecordServletAPI = END_POINT_URL + COLLECTION_POINT_RECORD;

//定義表單狀態的數字對應狀態以及渲染到網頁的形式
let statusrender = function () {
  let formStatus = $(".fs");

  formStatus.each(function () {
    let status = $(this).text();

    $(this).removeClass("badge bg-warning text-dark bg-info bg-success");

    switch (status) {
      case "未處理":
        $(this).addClass("badge bg-warning text-dark");
        break;
      case "處理中":
        $(this).addClass("badge bg-info text-dark");
        break;
      case "已結案":
        $(this).addClass("badge bg-success");
        break;
    }
  });
};

//檢查案件是否已結案
let checkclose = function () {
  if ($(".fs").text() === "已結案") {
    $("#res-change").prop("disabled", true);
    $("#preclose").prop("disabled", true);
    $("#addrecord").prop("disabled", true);
    $("#updaterecord").prop("disabled", true);
  }
};

//定義網頁載入時需發送請求至資料庫索取資料的方法
let supportformload = function () {
  //取得當前網址的參數
  const urlParams = new URLSearchParams(window.location.search);

  //取得formNO的值
  const formNo = urlParams.get("formNo");

  $.ajax({
    type: "GET",
    url: SupportFormServletAPI,
    data: { action: "getOne", formNo: formNo },
    dataType: "json",
    success: function (data) {
      //將表單資料渲染到網頁上
      $("#formNo").text(formNo);
      $("#formSource").text(data.formSource);
      $("#supportFormCategoryId").text(data.supportFormCategoryId);
      $("#formSubmitter").text(data.formSubmitter);
      $("#formTitle").text(data.formTitle);
      $("#realName").text(data.realName);
      $("#replyEmail").text(data.replyEmail);
      $("#formContext").text(data.formContext);
      $("#formStatus").text(data.formStatus);

      const timestamp = data.createdTimestamp;
      const date = new Date(timestamp);
      const formattedDate = date.toLocaleString();
      $("#createdTimestamp").text(formattedDate);

      let responderInfo = data.formResponderno === 0 ? "無" : `${data.formRespondername}(${data.formResponderno})`;
      $("#formResponder").text(responderInfo);

      //呼叫美化處理狀態外觀的函式
      statusrender();
      checkclose();
      recordload();
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//進入修改狀態
let upadatemode = function () {
  updatelock(); //呼叫鎖定功能讓其他按鈕不得運作
  let realName = $("#realName").text();
  let realName_element = $("#realName");
  let replyEmail = $("#replyEmail").text();
  let replyEmail_element = $("#replyEmail");
  let sFCId_element = $("#supportFormCategoryId");
  let updaterecord_element = $("#updaterecord");
  updatelock();
  replyEmail_element.replaceWith(`<input type="email" class="form-control w-50" value="${replyEmail}" id="replyEmail" />`);

  sFCId_element.replaceWith(`
                                <select class="form-select w-50" id="supportFormCategoryId">
                                <option value="default" selected>請選擇問題類別</option>
                                <option value="1">食譜相關</option>
                                <option value="2">討論區相關</option>
                                <option value="3">訂單相關</option>
                                <option value="4">商品相關</option>
                              </select>
  `);

  realName_element.replaceWith(`<input type="text" class="form-control w-50" value="${realName}" id="realName" />`);

  updaterecord_element.replaceWith(`
  <button class="btn btn-primary" id="updateconfirm">確認修改</button>
  <button class="btn btn-secondary ms-2" id="updatecancel">取消</button>
  `);
};

//清除錯誤提示
let updatereset = function () {
  $(".errCheck").remove();
};

//修改狀態時鎖定其他按鈕不得操作
let updatelock = function () {
  $("#res-change").prop("disabled", true);
  $("#preclose").prop("disabled", true);
  $("#addrecord").prop("disabled", true);
};

//修改資料功能
let updatedata = function (realName, supportFormCategoryId, replyEmail) {
  updatereset();
  let formNo = $("#formNo").text();

  let data = {
    formNo: formNo,
    realName: realName,
    supportFormCategoryId: supportFormCategoryId,
    replyEmail: replyEmail,
    action: "update",
  };

  $.ajax({
    type: "POST",
    url: SupportFormServletAPI,
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        insertrecord(data.ogdata);
        window.location.reload();
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
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//取得管理員資訊
let getAdmins = function () {
  $.ajax({
    type: "GET",
    url: SupportFormServletAPI,
    data: { action: "getAdmins" },
    dataType: "json",
    success: function (data) {
      let list = $(".admins");

      $.each(data, function (index, admins) {
        let adminInfo = `${admins.adminNickname}(${admins.adminNo})`;
        list.append(`<option value="${admins.adminNo}">${adminInfo}</option>`);
      });
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//將變更後的管理員資料送進資料庫並更新畫面
let changeAdmin = function () {
  let adminNo = $(".admins").val();
  let formNo = $("#formNo").text();
  let formStatus = $("#formStatus").text();
  $.ajax({
    type: "POST",
    url: SupportFormServletAPI,
    data: { action: "changeAdmin", adminNo: adminNo, formNo: formNo, formStatus: formStatus },
    dataType: "json",
    success: function (data) {
      if (data.success) {
        $("#formResponder").text(`${data.adminNickname}(${data.adminNo})`);
        $("#formStatus").text(data.formStatus);
        $("#res").modal("hide");
        statusrender();
        let changedAdmininfo = "變更處理人員：" + data.adminNickname + "(" + data.adminNo + ")";
        recordload(changedAdmininfo);
      } else {
        if (data.errCloseCase) {
          alert(data.errCloseCase);
        }
        if (data.errStatus) {
          alert(data.errStatus);
        }
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//案件狀態變更為已結案並跳轉到指定頁面
let closecase = function () {
  let formNo = $("#formNo").text();
  let formStatus = $("formStatus").text();
  $.ajax({
    type: "POST",
    url: SupportFormServletAPI,
    data: { action: "closecase", formNo: formNo, formStatus: formStatus },
    dataType: "json",
    success: function (data) {
      if (data.success) {
        $("#formResponder").text(`${data.adminNickname}(${data.adminNo})`);
        $("#formStatus").text(data.formStatus);
        $("#clsecase").modal("hide");
        statusrender();
        insertrecord("結案");
        window.location.href = data.url;
      } else {
        alert(data.errCloseCase);
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//進入網頁載入處理紀錄
let recordload = function (recordContext) {
  if (recordContext) {
    insertrecord(recordContext);
    return;
  }

  let formNo = $("#formNo").text();
  $.ajax({
    type: "GET",
    url: SupportFormRecordServletAPI,
    data: { action: "getAll", formNo: formNo },
    dataType: "json",
    success: function (data) {
      let list = $(".record-list");
      list.empty();
      //使用juqery的$.each()方法，收到responese發來的data物件後，迭代取出資料
      $.each(data, function (index, record) {
        const timestamp = record.createdTimestamp;
        const date = new Date(timestamp);
        const formattedDate = date.toLocaleString();

        let recorderInfo = `${record.recorderName}(${record.recorderNo})`;

        list.append(`<tr>
                      <th scope="row" class="align-middle text-center">${recorderInfo}</th>
                      <td class="text-center">${formattedDate}</td>
                      <td>${record.recordContext}</td>
                    </tr>`);
      });
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//產生表單輸入區
let recordarea = function () {
  $("#recordarea").append(`
  <div class="form-group" id="newrecord">
    <label for="recordContext">新增紀錄</label>
    <textarea class="form-control" id="recordContext" rows="3" placeholder="輸入處理紀錄"></textarea>
    <div class="d-flex justify-content-center mt-2">
      <button class="btn btn-primary" id="addconfirm">確認新增</button>
      <button class="btn btn-secondary ms-2" id="addcancel">取消</button>
    </div>
  </div>
  `);
};

//新增表單紀錄
let insertrecord = function (recordContext) {
  let data = {
    formNo: $("#formNo").text(),
    recordContext: recordContext,
    action: "insert",
  };

  $.ajax({
    type: "POST",
    url: SupportFormRecordServletAPI,
    data: data,
    dataType: "json",
    success: function (data) {
      if (data.success) {
        recordload();
        $("#newrecord").remove();
      } else {
        alert("紀錄新增失敗");
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  supportformload();

  //交接按鈕的燈箱視窗觸發時載入管理員列表
  $("#res").on("shown.bs.modal", function (e) {
    let modal = $(this);
    let select = modal.find(".admins");
    select.empty();
    getAdmins();
  });

  //監控變更交接人員按鈕點擊事件
  $("#res-change-confirm").on("click", function () {
    changeAdmin();
  });

  $("#closecase").on("click", function () {
    closecase();
  });

  $("#addrecord").on("click", function () {
    $("#newrecord").remove();
    recordarea();
  });

  $(document).on("click", "#addconfirm", function () {
    let recordContext = $("#recordContext").val();
    insertrecord(recordContext);
  });

  $(document).on("click", "#addcancel", function () {
    $("#newrecord").remove();
  });

  $("#updaterecord").on("click", function () {
    upadatemode();
  });

  $(document).on("click", "#updateconfirm", function () {
    let realName = $("#realName").val();
    let supportFormCategoryId = $("#supportFormCategoryId").val();
    let replyEmail = $("#replyEmail").val();
    updatedata(realName, supportFormCategoryId, replyEmail);
  });

  $(document).on("click", "#updatecancel", function () {
    window.location.reload();
  });
});
