
var sakura = new Sakura('.mainHeader',{fallSpeed :0.7});



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


