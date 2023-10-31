const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"
const COLLECTION_POINT3= "/MemberOrderServlet"

let cartData = [];
const selectedProducts = [];


$(document).ready(function() {
	// 发起AJAX请求
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2,
		type: 'GET',
		data: { action: "cartsearch" },
		dataType: 'json',
		success: function(response) {
			cartData = response; // 存储购物车数据到全局变量
			console.log(cartData);
			renderCart(cartData);
			console.log("success");
		},
		error: function(xhr) {
			console.log('AJAX请求失败：' + xhr.status);
		},
	});


	// 绑定删除按钮的点击事件（使用事件委托）
	$('#cart-table').on('click', '.btn-danger', function() {
		const productId = $(this).data('product-id'); // 获取要删除的商品ID
		console.log(productId);
		$.ajax({
			url: END_POINT_URL+COLLECTION_POINT2,
			type: 'POST',
			data: { action: 'remove', productId: productId },
			dataType: 'json',
			success: function(response) {
				// 如果成功删除商品，更新购物车显示
				if (response.message === "success") {
					location.reload();
					alert("商品刪除成功");
				} else {
					alert("商品刪除失敗 請在重新一次");
					console.log('商品删除失败');
				}
			},
			error: function(xhr) {
				console.log('AJAX请求失败：' + xhr.status);
			}
		});
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
  			<input type="checkbox" class="product-checkbox" data-product-id="${item.productNo}">
		</td>
        <td>
        ${item.productName}
        <img src="data:image/jpeg;base64,${item.productImage}" alt="${item.productName}Image" width="100" height="100"/>
        </td>
        <td>${item.productPrice}</td>
        <td>
        <input type="number" class="quantity-input" value="${item.quantity}" min="1" max="100">
        </td>
        <td class="total-amount">${totalAmount}</td>
        <td>
        <button type="button" class="btn btn-danger" id="deletebutton" data-product-id="${item.productNo}">删除</button>
    	</td>
      </tr>
    `;

		tbody.append(row);
		selectedProducts.push(item.productNo);
	});

	// 在AJAX请求成功后，注册复选框更改事件监听器
	$('.product-checkbox').change(function() {
		updateTotalAmount();
		console.log("a");
	});
	// 初始化时，勾选所有商品复选框
	$('.product-checkbox').prop('checked', true);

	updateTotalAmount();
	grandTotalElement.html(`總計 <span>$${grandTotal.toFixed(0)}</span>`);
}

function checkout() {
	
	const cartDataJSON = JSON.stringify(cartData);
	console.log(cartDataJSON);
	
	const queryParameters = `?cartData=${encodeURIComponent(cartDataJSON)}`;
	
	const targetURL = `checkout.html${queryParameters}`;
	
	window.location.href = targetURL;
}


function updateTotalAmount() {
	let grandTotal = 0;

	$('.product-checkbox:checked').each(function() {
		const productId = $(this).data('product-id');
		const item = cartData.find(item => item.productNo === productId);
		if (item) {
			const productPrice = parseFloat(item.productPrice);
			const quantity = parseInt(item.quantity);
			const totalAmount = productPrice * quantity;
			grandTotal += totalAmount;
		}
	});

	const grandTotalElement = $('#grand-total');
	grandTotalElement.html(`總計 <span>$${grandTotal.toFixed(0)}</span>`);
}



$('#checkout-button').click(function() {
	const selectedProducts = [];

	$('.product-checkbox:checked').each(function() {
		const productId = $(this).data('product-id');
		selectedProducts.push(productId);
	});
	console.log(selectedProducts);
	
	// 轉成JSON字串
	const selectedProductsJSON = JSON.stringify(selectedProducts);
	console.log(selectedProductsJSON);
	// 傳送商品編號
	const queryParameters = `?selectedProducts=${encodeURIComponent(selectedProductsJSON)}`;
	const targetURL = `checkout.html${queryParameters}`;
	window.location.href = targetURL;
});



function renderCart2(cartdata) {
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
  			<input type="checkbox" class="product-checkbox" data-product-id="${item.productNo}">
		</td>
        <td>
        ${item.productName}
        <img src="data:image/jpeg;base64,${item.productImage}" alt="${item.productName}Image" width="100" height="100"/>
        </td>
        <td>${item.productPrice}</td>
        <td>
        <input type="number" class="quantity-input" value="${item.quantity}" min="1" max="100">
        </td>
        <td class="total-amount">${totalAmount}</td>
        <td>
        <button type="button" class="btn btn-danger" id="deletebutton" data-product-id="${item.productNo}">删除</button>
    	</td>
      </tr>
    `;
		tbody.append(row);
		selectedProducts.push(item.productNo);
	});
	// 在AJAX请求成功后，注册复选框更改事件监听器
	$('.product-checkbox').change(function() {
		updateTotalAmount();
		console.log("a");
	});
	// 初始化时，勾选所有商品复选框
	$('.product-checkbox').prop('checked', true);
	updateTotalAmount();
	grandTotalElement.html(`總計 <span>$${grandTotal.toFixed(0)}</span>`);

}


$(document).on("change", ".quantity-input", function() {
	const sproductId = $(this).closest('tr').find('.product-checkbox').data('product-id');
	const snewQuantity = parseInt($(this).val());

	console.log(sproductId);
	console.log(snewQuantity);
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2,
		type: 'POST',
		data: {
			action: 'updateQuantity',
			productId: sproductId,
			newQuantity: snewQuantity
		},
		dataType: 'json',
		success: function(response) {
			cartData = response;
			renderCart(cartData);
		},
		error: function(xhr) {
			console.log('AJAX请求失败：' + xhr.status);
		}
	});
});



