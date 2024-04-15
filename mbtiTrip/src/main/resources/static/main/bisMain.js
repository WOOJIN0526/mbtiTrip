const itemCtn = document.querySelectorAll('.search__box');
console.log(itemCtn.length+"개")
let currentTranslateX=0;
document.querySelector('.next').addEventListener('click', function(){
    const handler = document.querySelector('.handler');
    const currentTransform = handler.style.transform;
    currentTranslateX = parseInt(currentTransform.replace(/.*translateX\((.*)px\).*/, '$1'));
    if(isNaN(currentTranslateX)){
		currentTranslateX=0;
	}else{
		console.log(currentTranslateX);
		if(currentTranslateX===(itemCtn.length-1)*-800){
			alert("마지막입니다");
			currentTranslateX=0;
			 handler.style.transform = `translateX(${0}px)`
			 return
		}
	}
    const newTranslateX = currentTranslateX-800; // 이동할 값
    handler.style.transform = `translateX(${newTranslateX}px)`;
});
document.querySelector('.prev').addEventListener('click', function(){
    const handler = document.querySelector('.handler');
    const currentTransform = handler.style.transform;
    currentTranslateX = parseInt(currentTransform.replace(/.*translateX\((.*)px\).*/, '$1'));
    if(isNaN(currentTranslateX)||currentTranslateX===0){
		alert("이전데이터가 없습니다");
		currentTranslateX=0;
		 handler.style.transform = `translateX(${0}px)`
		 return
	}
    const newTranslateX = currentTranslateX+800; // 이동할 값
    handler.style.transform = `translateX(${newTranslateX}px)`;
});