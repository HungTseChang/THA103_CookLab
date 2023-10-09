// 設定 blot 用於放入 YouTube 影片
class YoutubeBlot extends Quill.import("blots/block") {
  static create(value) {
    const node = super.create();
    node.setAttribute("src", value);
    node.setAttribute("frameborder", "0");
    node.setAttribute("allowfullscreen", true);
    return node;
  }

  static value(node) {
    return node.getAttribute("src");
  }
}

// 呼叫自訂的blot變數
Quill.register(YoutubeBlot);

// 配置quill的工具欄包含影片、圖片、文字編輯
var toolbarOptions = [
  ["bold", "italic", "underline", "strike"], // toggled buttons
  ["blockquote", "code-block"],

  [{ header: 1 }, { header: 2 }], // custom button values
  [{ list: "ordered" }, { list: "bullet" }],
  [{ script: "sub" }, { script: "super" }], // superscript/subscript
  [{ indent: "-1" }, { indent: "+1" }], // outdent/indent
  [{ direction: "rtl" }], // text direction

  [{ size: ["small", false, "large", "huge"] }], // custom dropdown
  [{ header: [1, 2, 3, 4, 5, 6, false] }],

  [{ color: [] }, { background: [] }], // dropdown with defaults from theme
  [{ font: [] }],
  [{ align: [] }],

  ["clean"], // remove formatting button
  ["image"], // 這是上傳圖片的圖示
  [
    {
      video: "true", // 自訂按鈕標示
      handler: function () {
        // 按鈕被點擊時
        //跳出對話框讓使用者輸入影片url，插入編輯器
        const videoUrl = prompt("請輸入影片的URL：");
        if (videoUrl) {
          //分析影片 ID（從youtube網址分析）
          const videoId = getVideoIdFromUrl(videoUrl);

          if (videoId) {
            // 建立放入鑲嵌youtube影片的變數
            const youtubeUrl = `<iframe src="https://www.youtube.com/embed/${videoId}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>`;

            // 插入 iframe code到編輯器
            quill.clipboard.dangerouslyPasteHTML(
              quill.getSelection(true).index,
              youtubeUrl
            );
          } else {
            alert("此影片無法支援");
          }
        }
      },
    },
  ],
];

var quill = new Quill("#editor", {
  modules: {
    toolbar: toolbarOptions,
  },
  theme: "snow",
});

// 從YouTube 連接中獲得影片 ID 的輔助函數
function getVideoIdFromUrl(url) {
  const videoIdRegex =
    /(?:youtube\.com\/(?:.*?(?:\/|%2F)(?:v|e|embed|user|c|channel|playlist|watch)\?(?:.*?v=|v=|v\/|embed\/|user\/|c\/|channel\/|watch\?v=|%2F))|(?:youtu\.be\/))([^?&"'>]+)/i;
  const match = url.match(videoIdRegex);
  return match ? match[1] : null;
}
//編輯器效果
$(function(){
  $("#editor").one("click",function(){//one可以只讓效果只觸發一次，
      $("h2").text("");
  })


});