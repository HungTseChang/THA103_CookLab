const HOST = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf("/", 1));
const END_POINT_URL = "http://" + HOST + webCtx;
const COLLECTION_POINT = "/ProductServlet";
const COLLECTION_POINT2 = "/CartServlet"
const COLLECTION_POINT3= "/MemberOrderServlet"


const urlParams = new URLSearchParams(window.location.search);
const selectedProductsJSON = urlParams.get('selectedProducts');
let cartData = []; 

let promoCodeUsed = false;
let promoCodeNO = null; // 優惠碼信息
let orderTotal = 0;      // 總金額
let finalPrice = 0;		//最終金額
let promoCodeInfo = null;


const selectedProducts = JSON.parse(selectedProductsJSON);
const selectedProductsJSON2 = selectedProducts.join(','); 
console.log(selectedProducts);
console.log(selectedProductsJSON2);


// 获取订单详情列表和总计元素
const orderDetailsList = document.getElementById("order-details-list");
const orderTotalElement = document.getElementById("order-total");
const finalPriceElement = document.getElementById("finalPriceInfo"); 
const tbody = document.querySelector("#cart-table tbody");

$(document).ready(function() {
	$.ajax({
		url: END_POINT_URL+COLLECTION_POINT2,
		type: 'GET',
		data: { action: "cartsearch2", productNo: selectedProductsJSON2 },
		dataType: 'json',
		success: function(response) {
			cartData = response; //全域變數
			console.log(cartData);
			//渲染
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

				// 訂單詳情
				const orderItem = document.createElement("li");
				orderItem.innerHTML = `${item.productName} <span>$${totalAmount}</span>`;
				orderDetailsList.appendChild(orderItem);
				
				// 更新
				orderTotal += parseFloat(totalAmount);
				finalPrice = orderTotal	;
				orderTotalElement.textContent = `$${orderTotal.toFixed(0)}`;
				finalPriceElement.textContent = `$${orderTotal.toFixed(0)}`;
				
			});

			orderTotalElement.textContent = `$${orderTotal.toFixed(0)}`;
		

		},
		error: function(xhr) {
			console.log('AJAX：' + xhr.status);
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
			url: END_POINT_URL+COLLECTION_POINT3, 
			type: 'POST', 
			data: { action: "checkCoupon", couponCode: couponCode },
			dataType: 'json', 
			success: function(response) {
				console.log(response);
				const finalPriceElement = document.getElementById("finalPriceInfo"); 

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
							finalPrice = orderTotal; 
						}

						finalPriceElement.textContent = `$${finalPrice.toFixed(0)}`;

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
				console.error('AJAX：' + xhr.status);
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
			url: END_POINT_URL+COLLECTION_POINT3,
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

	const returnButton = document.getElementById("returnButton");

	returnButton.addEventListener("click", function() {
		window.location.href = "shop.html"; 
	});

});








function handleSameAsBillingCheckboxChange() {
	const sameAsBillingCheckbox = document.getElementById("sameAsBilling");
	const memberNameInput = document.getElementById("memberName");
	const memberAddressInput = document.getElementById("memberAddress");
	const memberPhoneInput = document.getElementById("memberPhone");
	const memberEmailInput = document.getElementById("memberEmail");

	if (sameAsBillingCheckbox.checked) { 

		$.ajax({
			url: END_POINT_URL+COLLECTION_POINT3, 
			type: 'GET', 
			data: { action: "memberMessage" },
			dataType: 'json', // 
			success: function(data) {
				
				memberNameInput.value = data.memberNickname; 
				memberAddressInput.value = data.memberAddress;
				memberPhoneInput.value = data.memberCellphone;
				memberEmailInput.value = data.memberMail;
			},
			error: function(xhr) {
				console.error('AJAX：' + xhr.status);
			},
		});
	} else {
		
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


function calculatePriceAfterPercentageDiscount(totalAmount, percentageDiscountAmount) {
	if (percentageDiscountAmount <= 0 || percentageDiscountAmount >= 1) {
		return totalAmount;
	} else {
		return totalAmount * (1 - percentageDiscountAmount);
	}
}

