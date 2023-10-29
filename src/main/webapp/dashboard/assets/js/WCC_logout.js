$(document).ready(function () {
	
	$("a#logout").on("click",function(e){
                e.preventDefault;
    		var formlogout = $("<form>", {
	            action: "<%=request.getContextPath()%>/LoginServlet", // 表单提交的URL
	            method: "post", // 提交方法，可以是 "post" 或 "get"，根据需求设置
	        });
		    
formlogout.append($("<input>", {
    type: "hidden",
    name: "action",
    value: "logout"
}));
		       formlogout.appendTo("body").hide();
		       formlogout.submit();
		       formlogout.remove();
    	
    	
console.log("AAAA");
                
	})
	})