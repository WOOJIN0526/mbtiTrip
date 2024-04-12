function mkArr(selector){
	let arr = new Array();
	document.querySelectorAll(selector).forEach((dom,index)=>{
	arr[index]=dom.textContent;
	});
	return arr;
}
function getRandomColor() {
	return "#" + Math.floor(Math.random() * 16777215).toString(16);
}
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