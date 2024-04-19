  const clientKey = 'test_ck_D5GePWvyJnrK0W0k6' // 상점을 특정하는 키
  const customerKey = 'YbX2HuSlsC9uVJW6NMRMj' // 결제 고객을 특정하는 키
  const amount = 15_000 // 결제 금액
  const couponAmount = 5_000 // 할인 쿠폰 금액

  /*결제위젯 영역 렌더링*/
  const paymentWidget = PaymentWidget(clientKey, customerKey) // 회원 결제
  // const paymentWidget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS) // 비회원 결제
  paymentMethods = paymentWidget.renderPaymentMethods('#payment-method', amount)
  
  /*약관 영역 렌더링*/
  const paymentAgreement = paymentWidget.renderAgreement('#agreement')
  
  /*결제창 열기*/
  document.querySelector("#payment-button").addEventListener("click",()=>{
    paymentWidget.requestPayment({
      orderId: 'AD8aZDpbzXs4EQa-UkIX6',
      orderName: '토스 티셔츠',
      successUrl: 'http://localhost:8080/success',
      failUrl: 'http://localhost:8080/fail',
      customerEmail: 'customer123@gmail.com', 
      customerName: '김토스'
      }).catch(function (error) {
          if (error.code === 'USER_CANCEL') {
          // 결제 고객이 결제창을 닫았을 때 에러 처리
          } if (error.code === 'INVALID_CARD_COMPANY') {
            // 유효하지 않은 카드 코드에 대한 에러 처리
          }
      })  
  })

  /*할인 쿠폰 적용*/
  document.querySelector("#coupon").addEventListener("click", applyDiscount)
	
  function applyDiscount(e) {
    if (e.target.checked) {
      paymentMethods.updateAmount(amount - couponAmount, "쿠폰")
    } else {
      paymentMethods.updateAmount(amount)
    }
  }
  
  /*삭제 */
 function delete_item(){
	const checkbox = document.getElementsByName("checkbox");
	
	for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
             console.log(checkbox[i].value);
        }
    }
	
 }
 
 /*전체 삭제*/
  function delete_all() {
  const checkbox = document.getElementsByName("checkbox");
  for (var i = 0; i < checkbox.length; i++) {
      checkbox[i].checked=true;
     /* $("#deletALL").submit();*/
        
    }
}
  
  
  
  
  