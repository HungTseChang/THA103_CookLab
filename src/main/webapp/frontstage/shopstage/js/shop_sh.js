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
		a.href = '#';
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
			window.location.href = './shop-details.html?productNo=' + productId;
		});
		// 为每个购物车图标添加事件监听器
		icon.addEventListener('click', function(event) {
			event.preventDefault(); // 阻止默认的按钮点击行为

			const productId = aTitle.getAttribute('data-product-id'); // 获取商品ID
			const requestData = {
				action: 'buttonadd1', // 指定要调用的方法，例如 'addToCart'
				productNo: productId, // 商品的ID
				quantity: 1
			};
			console.log(requestData);
			console.log(productId);
			$.ajax({
				url: '/CookLab/CartServlet', // 服务器端URL
				type: 'GET', // 使用GET请求
				data: requestData, // 发送的参数
				dataType: 'json', // 预期的响应数据类型
				success: function(response) {
					// 处理成功添加到购物车的响应
					console.log('商品已添加到购物车');
					alert("商品添加到購物車囉");
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



