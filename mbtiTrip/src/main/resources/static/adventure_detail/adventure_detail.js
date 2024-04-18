// 메인으로 보여줄 사진 하나만 놔두고 none
document.querySelectorAll('.img_box').forEach((item,index)=>{
	if(index!=0){
		item.style.display='none';
	}
});
/**
 * 메인에서 보여주는 사진을 교체하는 함수입니다.
 * 하단의 축소된 사진을 클릭하여 data-value에 저장된 값을 통홰 처리합니다.
 * @param {HTMLElement} target - 이미지를 변경할 대상 요소
 */
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
                let price = parseFloat(document.querySelector('.price').dataset.value);
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

function addCart(){
	let formData = new FormData();
	const itemID = document.querySelector('.container').dataset.value;
	console.log(document.querySelector('#start').value);
	let StartDate =document.querySelector('#start').value;
	console.log(StartDate);
	let endDate =document.querySelector('#end').value;
	formData.set("itemId",itemID);
	formData.set("StartDate",StartDate);
	formData.set("endDate",endDate);
	fetch("/mypage/cart/replace/input",{
		method:"POST",
		headers:{
/*			"Content-Type":"application/json"*/
		},
		body:formData
	}).then(response => {
	    if (response.ok) {
	        return response.text();
	    } else {
	        throw new Error(response.statusText);
	    }
	})
	.then(data => {
	   	alert(data);
	})
	.catch(err => {
	    console.error(err);
	});
}
function uprating(){
	const uprating = document.querySelector('#uprating').textContent;
	const id = document.querySelector('.container').dataset.value;
	fetch(`/suggestion/${id}`).then(response=>{
		if(response.ok){
			location.reload(true);
		}else{
			alert("이미 추천을 눌렀습니다.");
		}
	}).catch(err=>{
		alert("이미 추천을 눌렀습니다.")
		console.err(err.message);
	});
}
