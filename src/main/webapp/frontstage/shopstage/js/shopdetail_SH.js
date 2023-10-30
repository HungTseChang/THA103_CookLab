const productId = getProductIdFromURL(); // 编写一个函数来获取URL参数中的商品ID
const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"

var jqxhr;
// 等待页面加载完成后执行
document.addEventListener("DOMContentLoaded", () => {
	// 获取商品ID（从URL参数中获取）

	let stock = null;
	console.log(productId);
	// 创建一个包含商品ID的请求数据
	const requestData = {
		action: "getDetail2", // 指定要调用的方法
		productNo: productId, // 商品的ID，从URL参数中获取
	};

	// 发起AJAX请求，获取商品详细信息
	$.ajax({
		url: END_POINT_URL + COLLECTION_POINT, // 服务器端URL
		type: "GET", // 使用GET请求
		data: requestData, // 发送的参数
		dataType: "json", // 预期的响应数据类型
		success: function(response) {
			console.log("a");
			console.log(response);
			// 在成功回调中处理从服务器获取的商品详细信息
			// 这里的response参数包含了您在Servlet中返回的JSON数据
			// 您可以访问其中的字段，例如：response.productName, response.productPrice 等

			$("#product-image-container").html(`<img class="product__details__pic__item--large" src="data:image/jpeg;base64,${response.productImage}" alt="">`);
			$("#product-name").text(response.productName);
			$("#product-price").text(`$${response.productPrice}`);
			$("#product-introduction").text(response.productIntroduction);
			$("#product-description").html(`<h6>商品詳情</h6><p>${response.productDescription}</p>`);
			stock = response.storageQty;
			$("#productqty span").text(`當前商品庫存: ${stock}`);
			console.log(response.storageQty);
		},
		error: function(xhr) {
			console.log("AJAX请求失败：" + xhr.status);
		},
	});






	$('#addToCartButton').click(function(e) {
		console.log("加入購物車")
		e.preventDefault();
		const quantity = $('#userquantity').val();
		console.log(quantity);
		if (quantity > stock) {
			alert('選擇的數量大於庫存數量！');
			return; // 防止执行添加到购物车操作
		}
		const requestData = {
			action: 'buttonadd2',
			productNo: productId,
			quantity: quantity
		};
		console.log(requestData);
		$.ajax({
			url: END_POINT_URL + COLLECTION_POINT2,
			type: 'GET',
			data: requestData,
			dataType: 'json',
			success: function(response) {

				console.log('商品已添加到购物车');
				alert("商品添加到購物車囉");
			},
			error: function(xhr) {
				if (xhr.status == 200) {
					alert("請先登入會員");
					window.location.href = `../members/login.html`;
				}
				console.log('AJAX请求失败：' + xhr.status);
			}
		});
	});

	$("#buybutton").click(function(e) {
		console.log("加入購物車");
		e.preventDefault();
		const quantity = $('#userquantity').val();
		// 创建一个空数组来存储商品编号
		const selectedProducts = [];
		console.log(quantity);
		if (quantity > stock) {
			alert('選擇的數量大於庫存數量！');
			return; // 防止执行添加到购物车操作
		}
		const requestData = {
			action: 'buttonadd2',
			productNo: productId, // 获取成功添加到购物车的商品编号
			quantity: quantity
		};
		console.log(requestData);

		$.ajax({
			url: END_POINT_URL + COLLECTION_POINT2,
			type: 'GET',
			data: requestData,
			dataType: 'json',
			success: function(response) {
				console.log('商品已添加到购物车');
				// 将成功添加到购物车的商品编号添加到数组
				selectedProducts.push(productId);
				// 创建一个包含商品编号的查询参数
				const queryParameters = new URLSearchParams();
				queryParameters.set('selectedProducts', JSON.stringify(selectedProducts));
				// 构建要跳转到的checkout.html页面的URL，将查询参数添加到URL中
				const targetURL = `checkout.html?${queryParameters.toString()}`;

				// 执行页面跳转
				window.location.href = targetURL;
			},
			error: function(xhr) {
				if (xhr == 200) {
					alert("請先登入會員");
					window.location.href = `../members/login.html`;
				}
				console.log('AJAX请求失败：' + xhr.status);
			}
		});
	});
	//搜尋
	$("#search-button").on("click", function() {
		let keyword = $("#index-searchbar").val();


		window.location.href = "./shop-grid.html?keyword=" + keyword;
	});

	fetchDataAndRender2();

});

// 编写一个函数来从URL参数中获取商品ID
function getProductIdFromURL() {
	// 从URL中获取productNo参数的值
	const urlParams = new URLSearchParams(window.location.search);
	return urlParams.get("productNo");
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

function fetchDataAndRender2() {
	// 发起 Fetch 请求到 /ProductServlet?action=getHotKeywords
	fetch(END_POINT_URL + COLLECTION_POINT + '?action=getHotKeywords')
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