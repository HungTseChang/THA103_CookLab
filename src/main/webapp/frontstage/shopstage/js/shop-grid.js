$(document).ready(function() {
	// 获取URL中的查询关键字
	const urlParams = new URLSearchParams(window.location.search);
	let keyword = urlParams.get("keyword");

	// 首先定义全局变量
	let currentPage = 1; // 当前页码
	let pageSize = 6; // 每页显示的商品数量
	let totalProductCount = 0; // 商品总数量
	let totalPages = 0; // 总页数

	// 获取分页按钮元素
	const previousPageButton = $("#previous-page");
	const nextPageButton = $("#next-page");

	// 发起AJAX请求以获取搜索结果
	function loadPage(currentPage) {
		$.ajax({
			url: "/CookLab/ProductServlet", // 用于搜索的Servlet地址
			type: "GET",
			data: {
				keywords: keyword,
				action: "searchkeyword",
				page: currentPage, // 传递当前页码
				pageSize: pageSize, // 传递每页显示数量
			},
			dataType: "json",
			success: function(data) {
				const productContainer = $("#product-container");
				console.log(data);
				console.log(data[0].totalProductCount);
				// 更新商品数量
				$("#productCount").text(data[0].totalProductCount);

				// 清空已有的内容
				productContainer.empty();

				data.forEach(function(result) {
					console.log(result.productImage);
					const productContainer = document.createElement("div");
					productContainer.classList.add("col-lg-4", "col-md-4", "col-sm-6", "mix", "oranges", "fresh-meat");

					const featuredItem = document.createElement("div");
					featuredItem.classList.add("featured__item");
					productContainer.appendChild(featuredItem);

					const imageDiv = document.createElement("div");
					imageDiv.classList.add("featured__item__pic", "set-bg");
					imageDiv.style.backgroundImage = `url(data:image/jpeg;base64,${result.productImage})`;
					featuredItem.appendChild(imageDiv);

					const ul = document.createElement("ul");
					ul.classList.add("featured__item__pic__hover");
					imageDiv.appendChild(ul);

					const li = document.createElement("li");
					ul.appendChild(li);

					const a = document.createElement("a");
					li.appendChild(a);

					const icon = document.createElement("i");
					icon.classList.add("fa", "fa-shopping-cart");
					a.appendChild(icon);

					const textDiv = document.createElement("div");
					textDiv.classList.add("featured__item__text");
					featuredItem.appendChild(textDiv);

					const h6 = document.createElement("h6");
					const aTitle = document.createElement("a");
					aTitle.href = '#'; // 添加商品链接
					aTitle.textContent = result.productName; // 添加商品名称
					aTitle.setAttribute('data-product-id', result.productNo); // 设置商品的ID
					h6.appendChild(aTitle);
					textDiv.appendChild(h6);

					const h5 = document.createElement('h5');
					h5.textContent = `$${result.productPrice}`; // 添加商品价格
					textDiv.appendChild(h5);

					// 将新创建的商品元素添加到页面
					const productDetailsContainer = document.getElementById('product-container');
					productDetailsContainer.appendChild(productContainer);
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
				// 更新总商品数量和页数
				totalProductCount = data[0].totalProductCount;
				totalPages = Math.ceil(totalProductCount / pageSize);
				console.log(totalPages);
				// 更新分页按钮状态
				updatePaginationButtons();
			},
			error: function(xhr) {
				console.log("请求失败，状态码：" + xhr.status);
				console.log(xhr.responseText);
				console.log(xhr);
			},
		});
	}

	function updatePaginationButtons() {
		const paginationContainer = $("#pagination-container");
		const paginationList = paginationContainer.find(".pagination");
		paginationList.empty(); // 清空分页按钮

		if (totalPages <= 1) {
			// 如果只有一页，不显示分页按钮
			paginationContainer.hide();
		} else {
			paginationContainer.show();

			// 添加Previous按钮
			const previousButton = $("<li class='page-item' id='previous-page'><a class='page-link' href='#'>Previous</a></li>");
			previousButton.click(function() {
				if (currentPage > 1) {
					currentPage--;
					loadPage(currentPage);
					updatePaginationButtons(); // 更新按钮状态
				}
			});
			paginationList.append(previousButton);

			// 添加数字页码按钮
			for (let page = 1; page <= totalPages; page++) {
				const pageButton = $(`<li class='page-item'><a class='page-link' href='#'>${page}</a></li>`);
				pageButton.click(function() {
					loadPage(page);
					currentPage = page; // 设置当前页码
					updatePaginationButtons(); // 更新按钮状态
				});
				if (page === currentPage) {
					pageButton.addClass("active");
				}
				paginationList.append(pageButton);
			}

			// 添加Next按钮
			const nextButton = $("<li class='page-item' id='next-page'><a class='page-link' href='#'>Next</a></li>");
			nextButton.click(function() {
				if (currentPage < totalPages) {
					currentPage++;
					loadPage(currentPage);
					updatePaginationButtons(); // 更新按钮状态
				}
			});
			paginationList.append(nextButton);
		}
	}

	// 分页按钮点击事件处理
	previousPageButton.click(function() {
		if (currentPage > 1) {
			currentPage--;
			loadPage(currentPage);
		}
	});

	nextPageButton.click(function() {
		if (currentPage < totalPages) {
			currentPage++;
			loadPage(currentPage);
		}
	});

	loadPage(currentPage);

	// 监听搜索按钮的点击事件和 Enter 键的按下事件
	$("#search-button").on("click keypress", function(e) {
		if (e.type === "click" || e.which === 13) {
			// 如果是点击事件或者 Enter 键被按下
			// 获取搜索关键字
			const newKeyword = $("#index-searchbar").val();
			keyword = newKeyword; // 更新关键字为新的关键字
			// 执行搜索
			currentPage = 1; // 重置页码为第一页
			loadPage(currentPage);
			// 更新URL参数以反映新的关键字
			const newUrl = `shop-grid.html?keyword=${newKeyword}`;
			window.history.pushState({}, "", newUrl);
		}
	});

	$("#search-button").on("click", function() {
		let keyword = $("#index-searchbar").val();
		console.log("搜尋功能");
		// 构建跳转URL并将关键字作为查询参数传递
		window.location.href = "./shop-grid.html?keyword=" + keyword;
	});
	
	fetchDataAndRender2()
});

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



