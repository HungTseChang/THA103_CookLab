const sameAsBillingCheckbox = document.getElementById("sameAsBilling");
const memberNameInput = document.getElementById("memberName");
const memberAddressInput = document.getElementById("memberAddress");
const memberPhoneInput = document.getElementById("memberPhone");
const memberEmailInput = document.getElementById("memberEmail");

let promoCodeUsed = false;
let promoCodeNO = null; // 優惠碼信息
let orderTotal = 0;      // 總金額
let finalPrice = 0;		//最終金額


const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const cartDataJSON = urlParams.get("cartData");
console.log(cartDataJSON);

const cartData = JSON.parse(decodeURIComponent(cartDataJSON));


const orderDetailsList = document.getElementById("order-details-list");
const orderTotalElement = document.getElementById("order-total");




console.log(cartData);

const tbody = document.querySelector("#cart-table tbody");

cartData.forEach(function(item) {
	const productPrice = parseFloat(item.productPrice);
	const quantity = parseInt(item.quantity);
	const totalAmount = (productPrice * quantity).toFixed(0);

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


	const orderItem = document.createElement("li");
	orderItem.innerHTML = `${item.productName} <span>$${totalAmount}</span>`;
	orderDetailsList.appendChild(orderItem);


	orderTotal += parseFloat(totalAmount);

	orderTotalElement.textContent = `$${orderTotal.toFixed(0)}`;

	const finalPriceElement = document.getElementById("finalPriceInfo"); 
	finalPriceElement.textContent = `$${orderTotal.toFixed(0)}`;
	
});








$(document).ready(function() {

	$('#sameAsBilling').change(handleSameAsBillingCheckboxChange);

});

function handleSameAsBillingCheckboxChange() {
	const sameAsBillingCheckbox = document.getElementById("sameAsBilling");
	const memberNameInput = document.getElementById("memberName");
	const memberAddressInput = document.getElementById("memberAddress");
	const memberPhoneInput = document.getElementById("memberPhone");
	const memberEmailInput = document.getElementById("memberEmail");

	if (sameAsBillingCheckbox.checked) {
		$.ajax({
			url: '/CookLab/MemberOrderServlet',
			type: 'GET', 
			data: { action: "memberMessage" },
			dataType: 'json', 
			success: function(data) {
				memberNameInput.value = data.memberNickname; 
				memberAddressInput.value = data.memberAddress;
				memberPhoneInput.value = data.memberCellphone;
				memberEmailInput.value = data.memberMail;
			},
			error: function(xhr) {
				console.error('AJAX请求失败：' + xhr.status);
			},
		});
	} else {
		memberNameInput.value = '';
		memberAddressInput.value = '';
		memberPhoneInput.value = '';
		memberEmailInput.value = '';
	}
}

$('#promocodeuse').click(function() {
	if (promoCodeUsed) {
		alert("優惠碼已使用，不能重複使用。");
		return;
	}
	console.log("優惠碼按鈕");
	const couponCode = $('#coupon-code').val();

	$.ajax({
		url: '/CookLab/MemberOrderServlet', 
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
			console.error('AJAX请求失败：' + xhr.status);
		},
	});
});

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

