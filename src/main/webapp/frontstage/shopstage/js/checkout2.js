// 获取URL参数
const urlParams = new URLSearchParams(window.location.search);
const selectedProductsJSON = urlParams.get('selectedProducts');
let cartData = []; // 初始化为空数组
let promoCodeUsed = false;
let promoCodeNO = null; // 優惠碼信息
let orderTotal = 0;      // 總金額
let finalPrice = 0;		//最終金額
// 将JSON字符串解析为JavaScript数组
const selectedProducts = JSON.parse(selectedProductsJSON);
const selectedProductsJSON2 = selectedProducts.join(','); // 转换为逗号分隔的字符串
console.log(selectedProducts);
console.log(selectedProductsJSON2);
// 发起AJAX请求
// 获取订单详情列表和总计元素
const orderDetailsList = document.getElementById("order-details-list");
const orderTotalElement = document.getElementById("order-total");
const finalPriceElement = document.getElementById("finalPriceInfo"); // 获取最終金額元素
// 获取表格的 tbody 元素
const tbody = document.querySelector("#cart-table tbody");
// 发起AJAX请求
$(document).ready(function() {
	$.ajax({
		url: '/CookLab/CartServlet',
		type: 'GET',
		data: { action: "cartsearch2", productNo: selectedProductsJSON2 },
		dataType: 'json',
		success: function(response) {
			cartData = response; // 存储购物车数据到全局变量
			console.log(cartData);
			// 渲染购物车数据到表格
			cartData.forEach(function(item) {
				console.log("渲染");
				const productPrice = parseFloat(item.productPrice);
				const quantity = parseInt(item.quantity);
				const totalAmount = (productPrice * quantity).toFixed(0);
				console.log(productPrice);
				console.log(quantity);
				console.log(totalAmount);
				const row = `
                  <tr>
                 	<td>
                		${item.productName}
                		<img src="data:image/jpeg;base64,${item.productImage}" alt="${item.productName}Image" width="100" height="100"/>
                	</td>
                    <td>${item.productPrice}</td>
                    <td>${item.quantity}</td>
                    <td class="total-amount">${totalAmount}</td>
                  </tr>
                `;
				tbody.innerHTML += row;

				// 创建订单详情列表项
				const orderItem = document.createElement("li");
				orderItem.innerHTML = `${item.productName} <span>$${totalAmount}</span>`;
				orderDetailsList.appendChild(orderItem);
				// 更新订单总计
				orderTotal += parseFloat(totalAmount);

				orderTotalElement.textContent = `$${orderTotal.toFixed(0)}`;
				finalPriceElement.textContent = `$${orderTotal.toFixed(0)}`;
			});

			orderTotalElement.textContent = `$${orderTotal.toFixed(0)}`;

		},
		error: function(xhr) {
			console.log('AJAX请求失败：' + xhr.status);
		},
	});


	$('#sameAsBilling').change(handleSameAsBillingCheckboxChange)

	$('#promocodeuse').click(function() {
		if (promoCodeUsed) {
			alert("優惠碼已使用，不能重複使用。");
			return;
		}
		console.log("優惠碼按鈕");
		const couponCode = $('#coupon-code').val();

		$.ajax({
			url: '/CookLab/MemberOrderServlet', // 后端端点的URL
			type: 'POST', // 使用POST请求
			data: { action: "checkCoupon", couponCode: couponCode },
			dataType: 'json', // 预期的响应数据类型
			success: function(response) {
				console.log(response);
				const finalPriceElement = document.getElementById("finalPriceInfo"); // 获取最終金額元素

				if (response.message === "success") {
					const currentDateTime = new Date();
					const startTime = new Date(response.startTime);
					const endTime = new Date(response.endTime);

					if (currentDateTime < startTime) {
						alert("此優惠碼尚未生效");
					} else if (currentDateTime > endTime) {
						alert("此優惠碼已過期");
					} else {
						promoCodeInfo = response.promoCodeNo;
						const fixedDiscountAmount = parseFloat(response.fixedDiscountAmount);
						const percentageDiscountAmount = parseFloat(response.percentageDiscountAmount);
						const promoCodeInfoElement = document.getElementById("promoCodeInfo");
						const promoCodeDiscountElement = document.getElementById("promoCodeDiscount");



						if (fixedDiscountAmount > 0) {
							finalPrice = calculatePriceAfterFixedDiscount(orderTotal, fixedDiscountAmount);
							promoCodeDiscountElement.textContent = `${response.fixedDiscountAmount} 元`;
						} else if (percentageDiscountAmount > 0) {
							finalPrice = calculatePriceAfterPercentageDiscount(orderTotal, percentageDiscountAmount);
							const discount = ((1 - response.percentageDiscountAmount) * 10).toFixed(0);
							console.log(discount);
							promoCodeDiscountElement.textContent = `${discount}折`;
						} else {
							finalPrice = orderTotal; // 如果没有優惠碼，最终价格等于总金额
						}

						// 更新最終金額的文本内容
						finalPriceElement.textContent = `$${finalPrice.toFixed(0)}`;

						// 显示優惠碼信息
						promoCodeInfoElement.style.display = "block";

						alert("優惠碼使用成功");
						promoCodeUsed = true;
						$('#coupon-code').prop('disabled', true);
						$('#promocodeuse').prop('disabled', true);
					}
				} else {
					alert("無優惠碼");
				}
			},
			error: function(xhr) {
				console.error('AJAX请求失败：' + xhr.status);
			},
		});
	});

	$('#checkoutbutton').click(function() {
		const memberName = $('#memberName').val();
		const memberEmail = $('#memberEmail').val();
		const memberPhone = $('#memberPhone').val();
		const memberAddress = $('#memberAddress').val();

		if (!memberName || !memberEmail || !memberPhone || !memberAddress) {
			alert("請填寫必填字段。");
			return;
		}

		const orderData = {
			memberName: memberName,
			memberEmail: memberEmail,
			memberPhone: memberPhone,
			memberAddress: memberAddress,
			cartData: JSON.stringify(cartData),
			orderTotal: orderTotal,
			finalPrice: finalPrice,
			promoCodeInfo: promoCodeInfo,
			action: "checkout"
		};
		console.log(orderData);
		$.ajax({
			url: '/CookLab/MemberOrderServlet',
			type: 'POST',
			data: orderData,
			dataType: 'json',
			success: function(response) {
				console.log("return");
				if (response.message === 'success') {
					console.log(response.message);
					$('#lightbox').show();
					$('body').css('overflow', 'hidden');
					setTimeout(function() {
						window.location.href = 'shop.html';
					}, 10000);
				} else {
					alert("訂單產生失敗囉");
				}
			},
			error: function(xhr) {

				console.error('AJAXerror：' + xhr.status);
			},
		});
	});
});







// 复选框状态改变时触发的函数
function handleSameAsBillingCheckboxChange() {
	const sameAsBillingCheckbox = document.getElementById("sameAsBilling");
	const memberNameInput = document.getElementById("memberName");
	const memberAddressInput = document.getElementById("memberAddress");
	const memberPhoneInput = document.getElementById("memberPhone");
	const memberEmailInput = document.getElementById("memberEmail");

	if (sameAsBillingCheckbox.checked) { // 使用 .checked 检查复选框是否被选中
		// 复选框被选中，向后端请求数据
		$.ajax({
			url: '/CookLab/MemberOrderServlet', // 后端端点的URL
			type: 'GET', // 使用GET请求
			data: { action: "memberMessage" },
			dataType: 'json', // 预期的响应数据类型
			success: function(data) {
				// 更新表单字段的值
				memberNameInput.value = data.memberNickname; // 使用 .value 设置字段的值
				memberAddressInput.value = data.memberAddress;
				memberPhoneInput.value = data.memberCellphone;
				memberEmailInput.value = data.memberMail;
			},
			error: function(xhr) {
				console.error('AJAX请求失败：' + xhr.status);
			},
		});
	} else {
		// 复选框未被选中，清空表单字段
		memberNameInput.value = '';
		memberAddressInput.value = '';
		memberPhoneInput.value = '';
		memberEmailInput.value = '';
	}
}



function calculatePriceAfterFixedDiscount(totalAmount, fixedDiscountAmount) {
	if (totalAmount <= fixedDiscountAmount) {
		return 0;
	} else {
		return totalAmount - fixedDiscountAmount;
	}
}

// 计算百分比折扣后的价格
function calculatePriceAfterPercentageDiscount(totalAmount, percentageDiscountAmount) {
	if (percentageDiscountAmount <= 0 || percentageDiscountAmount >= 1) {
		return totalAmount;
	} else {
		return totalAmount * (1 - percentageDiscountAmount);
	}
}

