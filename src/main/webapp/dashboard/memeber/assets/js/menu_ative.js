let menus= document.querySelectorAll("li.sidebar-item")

for(let i = 0;i<menus.length;i++){
menus[i].addEventListener("click", function(){
    for(let j = 0;j<menus.length;j++){
        if (menus[j].classList.contains("active")) {
            menus[j].classList.remove("active"); // 如果有类 "B"，则移除
          } 
    }
    menus[i].classList.add("active");
})

}