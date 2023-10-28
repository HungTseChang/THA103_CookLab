//定義網頁載入時需發送請求至資料庫索取資料的方法
let init = function () {
  $.ajax({
    type: "GET",
    url: "/THA103_CookLab/QuestionServlet",
    data: { action: "getAll" },
    dataType: "json",
    success: function (data) {
      console.log(data);
      let list = $(".question-list");
      $.each(data, function (index, QuestionDTO) {
        const createdTimestamp = new Date(QuestionDTO.createdTimestamp);

        list.append(`
                    <tr>
                      <td>${QuestionDTO.questionNo}</td>
                      <td>${QuestionDTO.questionGroupName}</td>
                      <td>${QuestionDTO.questionTitle}</td>
                      <td>${QuestionDTO.questionContent}</td>
                      <td>${createdTimestamp.toLocaleString()}</td>
                      <td>
                        <a href="#" class="btn btn-primary question-update" data-questionno="${QuestionDTO.questionNo}">更新</a>
                        <a href="#" class="btn btn-primary question-delete" data-questionno="${QuestionDTO.questionNo}">刪除</a>
                      </td>
                    </tr>
                    `);
      });

      //使用Simple Datatable第三方函式提供分頁樣式及簡易搜尋
      let table1 = document.querySelector("#table1");
      let dataTable = new simpleDatatables.DataTable(table1);
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

//刪除功能
let deletequesion = function (questionNo) {
  $.ajax({
    type: "GET",
    url: "/THA103_CookLab/QuestionServlet",
    data: { action: "delete", questionNo: questionNo },
    dataType: "json",
    success: function (data) {
      if (data.success) {
        window.location.reload();
      } else {
        alert("刪除失敗");
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
};

$(document).ready(function () {
  init();

  $(document).on("click", ".question-update", function (e) {
    e.preventDefault();
    var questionNo = $(this).data("questionno");
    window.location.href = "question-update.html?questionNo=" + questionNo;
  });

  $(document).on("click", ".question-delete", function (e) {
    e.preventDefault();
    var questionNo = $(this).data("questionno");
    deletequesion(questionNo);
  });
});
