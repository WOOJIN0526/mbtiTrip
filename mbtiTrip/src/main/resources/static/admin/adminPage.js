/**
 * 주어진 선택자에 맞는 요소들의 텍스트 내용으로 배열을 생성합니다.
 * 
 * @param {String} selector - 요소를 선택하기 위한 CSS 선택자입니다.
 * @return {Array} - 선택된 요소들의 텍스트 내용을 담고 있는 배열입니다.
 */
function mkArr(selector){
	let arr = new Array();
	document.querySelectorAll(selector).forEach((dom,index)=>{
	arr[index]=dom.textContent;
	});
	return arr;
}
/**
 * 무작위 컬러 코드를 생성합니다.
 * 
 * @return {String} - 무작위 생성된 컬러 코드를 반환합니다.
 */
function getRandomColor() {
	return "#" + Math.floor(Math.random() * 16777215).toString(16);
}
/**
 * 주어진 선택자에 맞는 요소들의 수만큼 무작위 컬러 코드로 배열을 생성합니다.
 * 
 * @param {String} selector - 요소를 선택하기 위한 CSS 선택자입니다.
 * @return {Array} - 무작위 컬러 코드로 채워진 배열입니다.
 */
function colorArr(selector){
	let sizebase = mkArr(selector);
	let size =sizebase.length
	let arr =new Array();
	for(let i=0;i<size;i++){
		arr[i]=getRandomColor();
	}
	return arr
}

const radioDis = new Chart(document.querySelector('#radioDisplay').getContext('2d'), {
    type: 'doughnut',
    data: {
        labels: ['업체사용자', '일반사용자'],
        datasets: [{
            data: [ document.querySelector('#bisCtn').textContent,
            		document.querySelector('#normalCtn').textContent],
            		backgroundColor: ['#007bff', '#ffc0cb']
        }]
    }
});
const mbtiDis = new Chart(document.querySelector('#mbtiChart').getContext('2d'),{
	type:'bar',
	data:{
		labels:mkArr('.mbtiType'),
		datasets:[{
			axis:'y',
			label:'MBTI 분포도',
			data:mkArr('.mbtiCtn'),
			backgroundColor:colorArr('.mbtiType')
		}]
	},
	options:{
		indexAxis:'y'
	}
}
	
);