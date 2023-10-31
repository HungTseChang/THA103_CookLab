var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + host + webCtx;
//========================== 確認使用者是否有登入 ===================================
var username = localStorage.getItem("account");

if(username == null)  //使用者沒有使用"記住帳號"功能或沒有登入
{
  //使用 Ajax 確認是否有登入過
  $.ajax({
    type: "GET",
    data: {action:"CheckLogin"},
    url: endPointURL+"/CheckLogin", // Servlet URL
    success: function(data) {
      if(data == "OK")
       loginInIconChange();
      else
       loginOutIconChange();
    },
    error: function() {
    }
  });
}
else  //使用者使用過[下次不要登入]的功能
{

    //使用 Ajax 查詢使用者的 Session
    $.ajax({
      type: "POST",
      data: {action:"getSession",account:username},
      url: endPointURL+"/CheckLogin", // Servlet URL
      success: function(data) {
        loginInIconChange();
        console.log(path);
        if(path == "/CookLab/frontstage/members/login.html")
        {
          console.log("成功進來這裡");
          console.log(data.RedirectURL);
          if(typeof data.RedirectURL === "undefined")
          	window.location.href  = endPointURL+"/frontstage/members/member-panel.jsp";
          else
        	  window.location.href  = data.RedirectURL;
        }
      },
      error: function() {
      }
    });
}
//============================ 渲染登入/登出功能 ===================================
function loginInIconChange(){
  $("a.ding-nav-text").text("登出");
  $("a.ding-nav-text").attr("href",endPointURL+"/LogOut");
  $("a.ding-nav-text").attr("onclick","return confirm('確定登出?')");
}
function loginOutIconChange(){
  $("a.ding-nav-text").text("登入/註冊");
  $("a.ding-nav-text").attr("herf",endPointURL+"/recipe/recipe_overview.jsp");
}
$(document).ready(function() {
  $("a.ding-nav-text").click(function() {
    localStorage.removeItem("account"); 
  });
});