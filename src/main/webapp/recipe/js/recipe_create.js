$("#btnTag").on("click", function () {
    $("#tagBox").removeClass("none");
});

$(".btn_modal_close").on("click", function () {
    $("#tagBox").addClass("none");
});

// modal 中的半透明黑色區域
$("#tagBox").on("click", function () {
    $(this).addClass("none");
});

// 點擊 lightbox 中的白色區域，不會關掉 modal
$("#tagBox article").on("click", function (e) {
    e.stopPropagation();
});

$(".addTag").on("click", function (e) {
    let that = this;
    let tagText = $(that).text();
    let clone = $(this).clone();
    $("#selectTag input").before(clone.removeClass("addTag").addClass("deleteTag"));
    $(this).addClass("disabled-button");
    clone.on("mouseover", function () {
        $(this).append(" x");
    });
    clone.on("mouseout", function () {
        $(this).text(tagText);
    });
    clone.on("click", function () {
        $(this).remove();
        $(that).removeClass("disabled-button");
    });
});
