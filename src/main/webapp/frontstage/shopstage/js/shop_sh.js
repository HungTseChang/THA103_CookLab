const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"
const COLLECTION_POINT3 = "/AdvertiseServlet2"


//Fetch練習


function fetchDataAndRender() {
	fetch(END_POINT_URL + COLLECTION_POINT + '?action=Indexget')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			renderData(data);
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		});
}

function renderData(data) {
	const container = document.getElementById('productContainer');
	container.innerHTML = '';

	// 前端控制數量(練習)
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
		a.href = '#';
		li.appendChild(a);

		const icon = document.createElement('i');
		icon.classList.add('fa', 'fa-shopping-cart');
		a.appendChild(icon);

		const textDiv = document.createElement('div');
		textDiv.classList.add('featured__item__text');
		featuredItem.appendChild(textDiv);

		//商品
		const h6 = document.createElement('h6');
		const aTitle = document.createElement('a');
		aTitle.href = '#'; // 連結
		aTitle.textContent = item.productName; // 名稱
		aTitle.setAttribute('data-product-id', item.productNo); // ID
		h6.appendChild(aTitle);
		textDiv.appendChild(h6);

		const h5 = document.createElement('h5');
		h5.textContent = `$${item.productPrice}`; // 價格
		textDiv.appendChild(h5);

		container.appendChild(card);


		//商品名稱連結
		aTitle.addEventListener('click', function(event) {
			event.preventDefault();

			const productId = this.getAttribute('data-product-id');
			window.location.href = './shop-details.html?productNo=' + productId;
		});


		//購物車按鈕連結
		icon.addEventListener('click', function(event) {
			event.preventDefault();
			const productId = aTitle.getAttribute('data-product-id');
			const requestData = {
				action: 'buttonadd1',
				productNo: productId,
				quantity: 1
			};
			console.log(requestData);
			console.log(productId);
			$.ajax({
				url: '/CookLab/CartServlet',
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

//關鍵字渲染
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


//廣告渲染
function fetchDataAndRender3() {
	fetch(END_POINT_URL + COLLECTION_POINT3 + '?action=getjson')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			renderData2(data);
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		});
}


function renderData2(data) {
	const Adcontainer = document.getElementById('adhighlight');
	Adcontainer.innerHTML = '';

	const limitedData = data.slice(0, 3);

	limitedData.forEach(item => {
		const card = document.createElement('div');
		card.classList.add('col-lg-4', 'col-md-4', 'col-sm-6', 'mix', 'oranges', 'fresh-meat');

		const featuredItem = document.createElement('div');
		featuredItem.classList.add('featured__item');
		card.appendChild(featuredItem);

		const imageDiv = document.createElement('div');
		imageDiv.classList.add('featured__item__pic', 'set-bg');
		imageDiv.style.backgroundImage = `url(data:image/jpeg;base64,${item.advertise_img})`;
		featuredItem.appendChild(imageDiv);


		const a = document.createElement('a');
		a.href = '#';
		featuredItem.appendChild(a);

		const textDiv = document.createElement('div');
		textDiv.classList.add('featured__item__text');
		featuredItem.appendChild(textDiv);

		//商品
		const h6 = document.createElement('h6');
		const aTitle = document.createElement('a');
		aTitle.href = '#'; // 連結
		aTitle.textContent = item.advertise_name; // 名稱
		aTitle.setAttribute('data-product-id', item.advertise_url); // ID
		h6.appendChild(aTitle);
		textDiv.appendChild(h6);

		Adcontainer.appendChild(card);
		//商品名稱連結
		aTitle.addEventListener('click', function(event) {
			event.preventDefault();
			const productId = this.getAttribute('data-product-id');
			window.location.href = './shop-details.html?productNo=' + productId;
		});

	});
}






/*============================== 搜尋功能 ==============================*/
$(document).ready(function() {
	$("#search-button").on("click", function(event) {
			event.preventDefault();
			let keyword = $(this).val();
			window.location.href = "./shop-grid.html?keyword=" + keyword;
	});
	//按下enter搜尋
	$("#index-searchbar").on("keydown", function(e) {
		if (e.key === "Enter") {
			e.preventDefault();
			$("#search-button").click();
		}
	});



	fetchDataAndRender();
	fetchDataAndRender2();
	fetchDataAndRender3();


	const loadMoreButton = document.getElementById('loadMoreButton');
	// 点击事件处理程序
	loadMoreButton.addEventListener('click', function() {
		window.location.href = "./shop-grid.html";
	});



});



