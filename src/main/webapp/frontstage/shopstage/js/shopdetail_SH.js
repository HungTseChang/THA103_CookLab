// 等待页面加载完成后执行
document.addEventListener("DOMContentLoaded", () => {
	// 获取商品ID（从URL参数中获取）
	const productId = getProductIdFromURL(); // 编写一个函数来获取URL参数中的商品ID
	console.log(productId);
	// 创建一个包含商品ID的请求数据
	const requestData = {
		action: "getDetail", // 指定要调用的方法
		productNo: productId, // 商品的ID，从URL参数中获取
	};

	// 发起AJAX请求，获取商品详细信息
	$.ajax({
		url: "/CookLab/ProductServlet", // 服务器端URL
		type: "GET", // 使用GET请求
		data: requestData, // 发送的参数
		dataType: "json", // 预期的响应数据类型
		success: function(response) {
			console.log(response);
			// 在成功回调中处理从服务器获取的商品详细信息
			// 这里的response参数包含了您在Servlet中返回的JSON数据
			// 您可以访问其中的字段，例如：response.productName, response.productPrice 等

			$("#product-image-container").html(`<img class="product__details__pic__item--large" src="data:image/jpeg;base64,${response.productImage}" alt="">`);
			$("#product-name").text(response.productName);
			$("#product-price").text(`$${response.productPrice}`);
			$("#product-introduction").text(response.productIntroduction);
			$("#product-description").html(`<h6>商品詳情</h6><p>${response.productDescription}</p>`);
		},
		error: function(xhr) {
			console.log("AJAX请求失败：" + xhr.status);
		},
	});






	$('.addToCartButton').click(function(e) {
		e.preventDefault(); 

		const quantity = $(this).closest('.product__details__quantity').find('.product-quantity').val(); // 获取所选数量

		const requestData = {
			action: 'buttonadd2', 
			productNo: productId, 
			quantity: quantity 
		};

		$.ajax({
			url: '/CookLab/CartServlet', 
			type: 'GET', 
			data: requestData, 
			dataType: 'json', 
			success: function(response) {
				
				console.log('商品已添加到购物车');
				alert("商品添加到購物車囉");
			},
			error: function(xhr) {
				console.log('AJAX请求失败：' + xhr.status);
			}
		});
	});


});

// 编写一个函数来从URL参数中获取商品ID
function getProductIdFromURL() {
	// 从URL中获取productNo参数的值
	const urlParams = new URLSearchParams(window.location.search);
	return urlParams.get("productNo");
}
