const productId = getProductIdFromURL(); // 编写一个函数来获取URL参数中的商品ID
const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"



document.addEventListener("DOMContentLoaded", () => {

	let stock = null;
	console.log(productId);


	const requestData = {
		action: "getDetail2",
		productNo: productId,
	};

	//單一商品全部渲染
	$.ajax({
		url: END_POINT_URL + COLLECTION_POINT,
		type: "GET",
		data: requestData,
		dataType: "json",
		success: function(response) {
			console.log(response);
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
			console.log("AJAX失敗：" + xhr.status);
		},
	});





	//購物車按鈕
	$('#addToCartButton').click(function(e) {
		console.log("加入購物車")
		e.preventDefault();
		const quantity = $('#userquantity').val();
		console.log(quantity);
		if (quantity > stock) {
			alert('選擇的數量大於庫存數量！');
			return; //中止
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
			headers: {
				orginURL: window.location.href
			},
			success: function(response) {
				console.log(response);
				if (response.redirectURL) {
					alert("請先登入會員");
					window.location.href = `../members/login.html`;
				} else {
					console.log('商品添加到購物車囉');
					alert("商品添加到購物車囉");
				}
			},
			error: function(xhr) {
				console.log('AJAX失敗：' + xhr.status);
			}
		});
	});

	//直接購買
	$("#buybutton").click(function(e) {
		console.log("購買按鈕");
		e.preventDefault();
		const quantity = $('#userquantity').val();
		const selectedProducts = [];
		console.log(quantity);
		if (quantity > stock) {
			alert('選擇的數量大於庫存數量！');
			return;
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
			headers: {
				orginURL: window.location.href
			},
			success: function(response) {
				console.log(response);
				if (response.redirectURL) {
					alert("請先登入會員");
					window.location.href = `../members/login.html`;
				} else {
					console.log('商品添加到購物車囉');
					selectedProducts.push(productId);
					const queryParameters = new URLSearchParams();
					queryParameters.set('selectedProducts', JSON.stringify(selectedProducts));
					const targetURL = `checkout.html?${queryParameters.toString()}`;
					window.location.href = targetURL;
				}

			},
			error: function(xhr) {
				console.log('AJAX：' + xhr.status);
			}
		});
	});
	
	//搜尋
	$("#search-button").on("click", function() {
		let keyword = $("#index-searchbar").val();
		window.location.href = "./shop-grid.html?keyword=" + keyword;
	});
	//按下enter搜尋
	$("#index-searchbar").on("keydown", function(e) {
		if (e.key === "Enter") {
			e.preventDefault();
			$("#search-button").click();
		}
	});

	fetchDataAndRender2();

});

//取得GET傳送的參數
function getProductIdFromURL() {
	//window.location.href = './shop-details.html?productNo=' + productId; 上個頁面的
	//?productNo 就是了
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

	fetch(END_POINT_URL + COLLECTION_POINT + '?action=getHotKeywords')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(keywords => {
			populateHotKeywords(keywords);
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		});
}