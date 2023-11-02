
document.addEventListener("DOMContentLoaded", function () {
$("a#logout").on("click",function(e){
    e.preventDefault();
var formlogout = $("<form>", {
action: "<%=request.getContextPath()%>/LoginServlet", 
    method: "post", 
});
formlogout.append($("<input>", {
type: "hidden",
name: "action",
value: "logout"
}));
   formlogout.appendTo("body").hide();
   formlogout.submit();
   formlogout.remove(); 
})
$("a#design").on("click",function(e){
        e.preventDefault();
    var formdesign = $("<form>", {
    action: "<%=request.getContextPath()%>/AdminsServlet", // 表单提交的URL
        method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
    });
    formdesign.append($("<input>", {
    type: "hidden",
    name: "action",
    value: "design"
    }));
    formdesign.append($("<input>", {
    type: "hidden",
    name: "action",
    value: "design"
    }));
    formdesign.appendTo("body").hide();
    formdesign.submit();
    formdesign.remove();
})
    let table1 = document.querySelector("#table1");
    let dataTable = new simpleDatatables.DataTable(table1);
})
