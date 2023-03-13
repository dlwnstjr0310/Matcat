// 각 상품의 합계를 계산
const row1Total = 20000 * 2;
const row2Total = 15000 * 1;
const row3Total = 20000 * 3; 

// 합계 누적 계산
const totalAmount = row1Total + row2Total + row3Total;

const totalPriceElement = document.getElementById("total-price");

// 결과 출력
totalPriceElement.textContent = totalAmount;
console.log = totalAmount;
