//console.log(document.querySelector('.userImg'));
/**
 * 현재 사용자 이미지의 소스를 sessionStorage에 저장합니다.
 * @param {string} ImgClass - 사용자 이미지를 나타내는 CSS 클래스 이름
 */
sessionStorage.setItem("Img",document.querySelector('.userImg').currentSrc);
console.log(sessionStorage.getItem("Img"));
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

var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

