import { fetchData } from '/common/dataModule.js';
import { filterData } from '/common/searchModule.js';

/**
 * 지정된 옵션을 사용하여 Sakura 인스턴스를 생성합니다.
 * @param {string|Element} target - 사쿠라 효과가 적용될 대상 요소 또는 선택자입니다.
 * @param {object} options - 사쿠라 효과를 구성하는 옵션입니다.
 */
var sakura = new Sakura('.mainHeader',{fallSpeed :0.7});


/**
 * 일정 시간 간격으로 실행되는 함수입니다. 사쿠라 요소의 수를 확인하여 사쿠라 효과를 시작하거나 중지합니다.
 */
setInterval(()=>{
	var sakuraCtn = document.querySelectorAll('.sakura');
	if(sakuraCtn.length>50){
		sakura.stop(true);
	}else if(sakuraCtn.length<10){
		sakura.start();
	}
},10000);

/**
 * 지정된 옵션을 사용하여 slide 인스턴스를 생성합니다.
 * @param {object} cssSelector - 슬라이더를 적용할 선택자로 구성합니다.
 */
var slide =new Slide({
	slideCon:'.slide',
	prvBtn:'.slide_prev_button',
	nxtBtn:'.slide_next_button',
	sItem:'.slide_item',
	sPagin:'.slide_pagination'
});


let dataList;
let nowIndex = 0;
/**
 * 데이터를 가져와서 전역 변수에 저장합니다.
 * 
 * @param {String} url - 데이터를 가져올 URL입니다.
 */
fetchData('/main/dataList.json').then(data => {
  dataList = data;
});

document.querySelector('.search__input').addEventListener('keyup',(event)=>{
  const value = document.querySelector('.search__input').value.trim();
  
  const matchDataList = filterData(dataList, value);

  switch (event.keyCode) {
    // UP KEY
    case 38:
      nowIndex = Math.max(nowIndex - 1, 0);
      break;

    // DOWN KEY
    case 40:
      nowIndex = Math.min(nowIndex + 1, matchDataList.length - 1);
      break;

    // ENTER KEY
    case 13:
      document.querySelector('.search__input').value = matchDataList[nowIndex] || "";
      // 초기화
      nowIndex = 0;
      matchDataList.length = 0;
      break;

    // 그외 다시 초기화
    default:
      nowIndex = 0;
      break;
  }
  
  // 리스트 보여주기
  showList(matchDataList, value, nowIndex);
});
/**
 * 데이터 리스트를 HTML 요소에 표시합니다.
 * 
 * @param {Array} data - 표시할 데이터의 배열입니다.
 * @param {String} value - 검색어 또는 필터링할 값을 나타내는 문자열입니다.
 * @param {Number} nowIndex - 현재 선택된 항목의 인덱스입니다.
 */
const showList = (data, value, nowIndex) => {
  // 정규식으로 변환
  const regex = new RegExp(`(${value})`, "g");
  
  document.querySelector('.autocomplete').innerHTML = data
    .map(
      (label, index) => `
        <div class='${nowIndex === index ? "active" : ""}'>
          ${label.replace(regex, "<mark>$1</mark>")}
        </div>
      `
    )
    .join("");
};

document.getElementById('searchForm').addEventListener('keypress', function(event) {
  if (event.key === 'Enter') {
    event.preventDefault(); // 엔터 키의 기본 동작인 폼 서브밋을 막음
  }
});
/*<th:block th:text="${#strings.substring(replaceDTO.content, 0, 5)}" />*/