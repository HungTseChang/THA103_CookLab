//定義網頁載入時需發送請求至資料庫索取資料的方法
let notifyload = function () {
  //取得當前網址的參數
  const urlParams = new URLSearchParams(window.location.search);

  //取得notifyNo的值
  const notifyNo = urlParams.get("notifyNo");

  $.ajax({
    type: "GET",
    url: "/THA103_CookLab/NotifyCenterAjax",
    data: { action: "getOne", notifyNo: notifyNo },
    dataType: "json",
    success: function (data) {
      //將通知內容資料渲染到網頁上
      $("#notifyNo").val(notifyNo);
      $("#memberId").val(data.memberId);
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
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  notifyload();
});
