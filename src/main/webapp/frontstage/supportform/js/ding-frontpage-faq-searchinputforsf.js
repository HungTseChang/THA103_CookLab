let performSearch = function () {
  let keyword = $("#t_SC_search_field").val();

  if (!keyword) {
    alert("請輸入關鍵字");
    return;
  }

  let searchResultURL = "/CookLab/frontstage/faq/faqresult.html?keyword=" + keyword;

  window.location.href = searchResultURL;
};

$(document).ready(function () {
  $(document).on("click", "#t_SC_search_btn", function (e) {
    e.preventDefault();
    performSearch();
  });

  $(document).on("keydown", "#t_SC_search_field", function (e) {
    if (e.keyCode === 13) {
      e.preventDefault();
      performSearch();
    }
  });
});
