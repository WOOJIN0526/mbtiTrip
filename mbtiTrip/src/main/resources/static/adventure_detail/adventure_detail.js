document.querySelectorAll('.img_box').forEach((item,index)=>{
	if(index!=0){
		item.style.display='none';
	}
});
function changeImg(target){
	const value=target.dataset.value;
	document.querySelectorAll('.img_box').forEach((item,index)=>{
	if(index!=value){
		item.style.display='none';
	}else{
		item.style.display='block';
	}
	});
}
function chkdate(target){
	console.dir(target);
	const currentDate = new Date();
	const selectedDate = new Date(target.value);
	if(selectedDate < currentDate){
		alert("시작일이 현재일자보다 빠름니다.");
		const formattedDate = `${currentDate.getFullYear()}-${String(currentDate.getMonth() + 1).padStart(2, '0')}-${String(currentDate.getDate()).padStart(2, '0')}`;
		console.log(formattedDate);
		target.value = formattedDate;
	}
}
// onchange 이벤트 핸들러
function handleDateChange(target) {
    chkdateEnd(target)
        .then(({ currentDate, selectedDate, startDate }) => {
            if (selectedDate < currentDate) {
                alert("종료일이 현재일자보다 빠름니다.");
                const formattedDate = `${currentDate.getFullYear()}-${String(currentDate.getMonth() + 1).padStart(2, '0')}-${String(currentDate.getDate()).padStart(2, '0')}`;
                target.value = formattedDate;
                return null; // 체인 중단
            }
            return { selectedDate, startDate };
        })
        .then(data => {
            if (data === null) {
                console.error("체인 중단됨");
                return null;
            } else {
                const diff = getDateDiff(data.startDate, data.selectedDate);
                return diff; // 숙박일 수 반환
            }
        })
        .then(diff => {
            if (diff !== 0) {
                let price = parseFloat(document.querySelector('.price').dataset.value); // 가격 파싱
                price *= diff; // 가격 계산
                console.log("총 가격:", price);
				document.querySelector('.price').textContent = price;
            }
        })
        .catch(error => {
            console.error("에러 발생:", error);
        });
}


// chkdateEnd 함수
function chkdateEnd(target) {
    return new Promise((resolve, reject) => {
        console.dir(target);
        const currentDate = new Date();
        const selectedDate = new Date(target.value);
        const startDate = new Date(document.querySelector('#start').value);

        if (!isNaN(startDate.getTime())) {
            resolve({ currentDate, selectedDate, startDate });
        } else {
            alert("시작일을 선택해주세요.");
            reject("Invalid date");
        }
    });
}

const getDateDiff = (d1, d2) => {
    const diffDate = d1.getTime() - d2.getTime();
    return Math.abs(diffDate / (1000 * 60 * 60 * 24)); // 밀리세컨 * 초 * 분 * 시 = 일
};

/*function addCart(){
	fetch("/adventure/input",{
		method:"POST",
		headers:{
			"Content-Type":"multipart/form-data"
		},
		body:formData
	})
}*/