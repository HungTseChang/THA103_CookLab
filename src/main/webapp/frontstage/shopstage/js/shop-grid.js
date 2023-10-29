$(document).ready(function() {

	//url參數獲取
	const urlParams = new URLSearchParams(window.location.search);
	let keyword = urlParams.get("keyword");

	//搜尋參數設定
	let currentAction = "searchkeyword" //行為設定
	let hotkey = "";	//熱門搜尋設定


	let currentPage = 1; // 當前頁數
	let pageSize = 6; // 每頁數量
	let totalProductCount = 0; // 商品總數量
	let totalPages = 0; // 總頁數

	//分頁按鈕
	const previousPageButton = $("#previous-page");
	const nextPageButton = $("#next-page");

	//搜尋功能
	function loadPage(currentPage, action) {
		$.ajax({
			url: "/CookLab/ProductServlet",
			type: "GET",
			data: {
				keywords: keyword,
				action: action,
				page: currentPage, // 當前頁數
				pageSize: pageSize, // 每頁數量
				hotkey: hotkey, //熱門搜尋參數設定
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
					aTitle.href = '#';
					aTitle.textContent = result.productName;
					aTitle.setAttribute('data-product-id', result.productNo);
					h6.appendChild(aTitle);
					textDiv.appendChild(h6);

					const h5 = document.createElement('h5');
					h5.textContent = `$${result.productPrice}`;
					textDiv.appendChild(h5);


					const productDetailsContainer = document.getElementById('product-container');
					productDetailsContainer.appendChild(productContainer);

					aTitle.addEventListener('click', function(event) {
						event.preventDefault();
						const productId = this.getAttribute('data-product-id');
						window.location.href = './shop-details.html?productNo=' + productId;
					});

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
							success: function(response) {

								console.log('商品添加到購物車囉');
								alert("商品添加到購物車囉");
							},
							error: function(xhr) {
								console.log('AJAX：' + xhr.status);
							}
						});
					});

				});

				// 更新商品總數 頁數
				totalProductCount = data[0].totalProductCount;
				totalPages = Math.ceil(totalProductCount / pageSize);
				console.log(totalPages);
				// 更新分頁按鈕狀態
				updatePaginationButtons();
			},
			error: function(xhr) {
				console.log("error：" + xhr.status);
				console.log(xhr.responseText);
				console.log(xhr);
			},
		});
	}

	function updatePaginationButtons() {
		const paginationContainer = $("#pagination-container");
		const paginationList = paginationContainer.find(".pagination");
		paginationList.empty();
		if (totalPages <= 1) {
			paginationContainer.hide();
		} else {
			paginationContainer.show();
			const previousButton = $("<li class='page-item' id='previous-page'><a class='page-link' href='#'>Previous</a></li>");
			previousButton.click(function() {
				if (currentPage > 1) {
					currentPage--;
					loadPage(currentPage, currentAction);
					updatePaginationButtons();
				}
			});
			paginationList.append(previousButton);

			for (let page = 1; page <= totalPages; page++) {
				const pageButton = $(`<li class='page-item'><a class='page-link' href='#'>${page}</a></li>`);
				pageButton.click(function() {
					loadPage(page, currentAction);
					currentPage = page;
					updatePaginationButtons();
				});
				if (page === currentPage) {
					pageButton.addClass("active");
				}
				paginationList.append(pageButton);
			}
			const nextButton = $("<li class='page-item' id='next-page'><a class='page-link' href='#'>Next</a></li>");
			nextButton.click(function() {
				if (currentPage < totalPages) {
					currentPage++;
					loadPage(currentPage, currentAction);
					updatePaginationButtons();
				}
			});
			paginationList.append(nextButton);
		}
	}
	previousPageButton.click(function() {
		if (currentPage > 1) {
			currentPage--;
			loadPage(currentPage, currentAction);
		}
	});

	nextPageButton.click(function() {
		if (currentPage < totalPages) {
			currentPage++;
			loadPage(currentPage, currentAction);
		}
	});

	loadPage(currentPage, currentAction);


	$("#search-button").on("click keypress", function(e) {
		if (e.type === "click" || e.which === 13) {

			const newKeyword = $("#index-searchbar").val();
			currentAction = "searchkeyword"
			keyword = newKeyword;
			currentPage = 1;
			loadPage(currentPage, currentAction);
			const newUrl = `shop-grid.html?keyword=${newKeyword}`;
			window.history.pushState({}, "", newUrl);
		}
	});

	$("#overview-button").on("click", function() {
		console.log("總覽觸發");
		keyword = "";
		currentPage = 1;
		currentAction = "searchkeyword"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});

	$("#ingredient-button").on("click", function() {
		console.log("食材觸發");
		keyword = "ingredient";
		currentPage = 1;
		currentAction = "catergorysearch"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});

	$("#kitchenware-button").on("click", function() {
		console.log("廚具觸發");
		keyword = "kitchenware";
		currentPage = 1;
		currentAction = "catergorysearch"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});

	$("#hot-button").on("click", function() {
		console.log("熱門商品");
		currentPage = 1;
		currentAction = "hotProduct"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});

	//篩選
	$("#mutilseletingredient").on("click", function() {
		console.log("食材複合觸發");
		currentPage = 1;
		currentAction = "mutilseletingredient"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});

	$("#mutilseletkitchenware").on("click", function() {
		console.log("廚具複合觸發");
		currentPage = 1;
		currentAction = "mutilseletkitchenware"
		loadPage(currentPage, currentAction);
		const newUrl = `shop-grid.html`;
		window.history.pushState({}, "", newUrl);
	});




	//排序


	//關鍵字渲染
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



