// 创建一个函数来处理 AJAX 请求和渲染数据
function fetchDataAndRender() {
	// 发起 Fetch 请求
	fetch('/CookLab//ProductServlet?action=Indexget')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json(); // 解析 JSON 数据
		})
		.then(data => {
			// 在此处处理从服务器接收到的数据
			// 数据将包含从服务器返回的商品信息
			renderData(data); // 调用渲染函数，将数据渲染到页面
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		});
}

function renderData(data) {
	const container = document.getElementById('productContainer');
	container.innerHTML = '';

	// 仅保留前9条数据
	const limitedData = data.slice(0, 9);

	limitedData.forEach(item => {
		const card = document.createElement('div');
		card.classList.add('col-lg-4', 'col-md-4', 'col-sm-6', 'mix', 'oranges', 'fresh-meat');

		const featuredItem = document.createElement('div');
		featuredItem.classList.add('featured__item');
		card.appendChild(featuredItem);

		const imageDiv = document.createElement('div');
		imageDiv.classList.add('featured__item__pic', 'set-bg');
		imageDiv.style.backgroundImage = `url(data:image/jpeg;base64,${item.productImage})`;
		featuredItem.appendChild(imageDiv);

		const ul = document.createElement('ul');
		ul.classList.add('featured__item__pic__hover');
		imageDiv.appendChild(ul);

		const li = document.createElement('li');
		ul.appendChild(li);

		const a = document.createElement('a');
		li.appendChild(a);

		const icon = document.createElement('i');
		icon.classList.add('fa', 'fa-shopping-cart');
		a.appendChild(icon);

		const textDiv = document.createElement('div');
		textDiv.classList.add('featured__item__text');
		featuredItem.appendChild(textDiv);

		const h6 = document.createElement('h6');
		const aTitle = document.createElement('a');
		aTitle.href = '#'; // 添加商品链接
		aTitle.textContent = item.productName; // 添加商品名称
		aTitle.setAttribute('data-product-id', item.productNo); // 设置商品的ID
		h6.appendChild(aTitle);
		textDiv.appendChild(h6);

		const h5 = document.createElement('h5');
		h5.textContent = `$${item.productPrice}`; // 添加商品价格
		textDiv.appendChild(h5);

		container.appendChild(card);

		// 为每个商品标题添加事件监听器
		aTitle.addEventListener('click', function(event) {
			event.preventDefault(); // 阻止默认的链接跳转行为

			const productId = this.getAttribute('data-product-id'); // 获取商品ID
			const requestData = {
				action: 'getDetail', // 指定要调用的方法
				productNo: productId // 商品的ID，根据您的需要设置
			};
			$.ajax({
				url: '/CookLab/ProductServlet', // 服务器端URL
				type: 'GET', // 使用GET请求
				data: requestData, // 发送的参数
				dataType: 'json', // 预期的响应数据类型
				success: function(response) {
					// 在成功回调中处理从服务器获取的商品详细信息
					// 这里的response参数包含了您在Servlet中返回的JSON数据
					// 您可以访问其中的字段，例如：response.productName, response.productPrice 等
					console.log(response);

					// 跳转到商品详情页面，传递商品ID作为查询参数
					window.location.href = './shop-details.html?productNo=' + productId;
				},
				error: function(xhr) {
					console.log('AJAX请求失败：' + xhr.status);
				}
			});
		});
	});
}
function fetchDataAndRender2() {
    // 发起 Fetch 请求到 /ProductServlet?action=getHotKeywords
    fetch('/CookLab/ProductServlet?action=getHotKeywords')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 解析 JSON 数据
        })
        .then(keywords => {
            // 将商品名称填充到热门关键字部分
            populateHotKeywords(keywords);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}


function populateHotKeywords(keywords) {
    const topSearchWordsMenu = document.querySelector('.topsearchwords-menu');

    keywords.forEach(item => {
        const li = document.createElement('li');
        li.classList.add('topsearchwords-item');
        const a = document.createElement('a');
        a.href = '#';
        a.textContent = item.keyword;

        a.addEventListener('click', () => {

            const clickedKeyword = item.keyword;
            window.location.href = `./shop-grid.html?keyword=${clickedKeyword}`;
        });
        li.appendChild(a);
        topSearchWordsMenu.appendChild(li);
    });
}


	fetchDataAndRender();
	fetchDataAndRender2();
// 等待页面加载完成后执行
document.addEventListener('DOMContentLoaded', () => {

});



/*============================== 搜尋功能 ==============================*/
$(document).ready(function() {
	$("#search-button").on("click", function() {
		let keyword = $("#index-searchbar").val();

		// 构建跳转URL并将关键字作为查询参数传递
		window.location.href = "./shop-grid.html?keyword=" + keyword;
	});
});

/*============================== 購物車功能 ==============================*/
//$("#fa fa-shopping-cart").on("click", function() {
//		letproductInfo = {
//        productNo: ${productVO.productNo},
//        productName: "${productVO.productName}",
//        productPrice: ${productVO.productPrice},
//        productImage: "<%= request.getContextPath() %>/ProductImgServlet?productNo=${productVO.productNo}",
//        quantity: 1 // 默认购买数量为1
//    }
//		console.log(keyword)
//		$.ajax({
//			url: "/CookLab/ProductServlet2", // 資料請求的網址
//			type: "GET", // GET | POST | PUT | DELETE | PATCH
//			data: keyword, // 將物件資料(不用雙引號) 傳送到指定的 url
//			dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
//			beforeSend: function() {
//				// 在 request 發送之前執行
//			},
//			headers: {
//				// request 如果有表頭資料想要設定的話
//				// "X-CSRF-Token":"abcde"   // 參考寫法
//			},
//			statusCode: {
//				// 狀態碼
//				200: function(res) { },
//				404: function(res) { },
//				500: function(res) { },
//			},
//			success: function(data) {
//				// request 成功取得回應後執行
//				window.location.href = "/frontstage/shopstage/shop-details.jsp"
//				console.log(data);
//				console.log("ajax成功");
//			},
//			error: function(xhr) {
//				// request 發生錯誤的話執行
//				console.log("請求失敗，狀態碼：" + xhr.status);
//				console.log(xhr.responseText)
//				console.log(xhr);
//			},
//			complete: function(xhr) {
//				// request 完成之後執行(在 success / error 事件之後執行)
//				console.log(xhr);
//			},
//		});
//	});