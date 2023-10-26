let cartData = [];

$(document).ready(function() {
	// 发起AJAX请求
	$.ajax({
        url: '/CookLab/CartServlet',
        type: 'GET',
        data: { action: "cartsearch" },
        dataType: 'json',
        success: function(response) {
            cartData = response; // 存储购物车数据到全局变量
            renderCart(cartData);
            console.log("success");
        },
        error: function(xhr) {
            console.log('AJAX请求失败：' + xhr.status);
        },
    });
});

function renderCart(cartdata) {
	const cartTable = $('#cart-table');
	const tbody = cartTable.find('tbody');
	const grandTotalElement = $('#grand-total');
	console.log(cartTable);
	console.log(tbody);

	tbody.empty();

	let grandTotal = 0;

	cartdata.forEach(function(item) {
		console.log("迴圈執行");

		const productPrice = parseFloat(item.productPrice);
		const quantity = parseInt(item.quantity);
		const totalAmount = (productPrice * quantity);

		grandTotal += totalAmount;

		const row = `
      <tr>
        <td>
          <input type="checkbox" class="product-checkbox" />
        </td>
        <td>${item.productName}</td>
        <td>${item.productPrice}</td>
        <td>${item.quantity}</td>
        <td class="total-amount">${totalAmount}</td>
      </tr>
    `;
		tbody.append(row);
	});
	grandTotalElement.html(`總計 <span>$${grandTotal.toFixed(0)}</span>`);
}


function checkout() {
    // 将购物车数据转换为 JSON 字符串
    const cartDataJSON = JSON.stringify(cartData);
	console.log(cartDataJSON);
    // 构建查询参数，将购物车数据附加到 URL 中
    const queryParameters = `?cartData=${encodeURIComponent(cartDataJSON)}`;

    // 构建目标 URL，这将是 check.html 页面的 URL，同时附带了购物车数据的查询参数
    const targetURL = `checkout.html${queryParameters}`;

    // 使用 window.location.href 重定向用户到目标 URL
    window.location.href = targetURL;
}

// 在结帐按钮上绑定点击事件
$('#checkout-button').click(checkout);



