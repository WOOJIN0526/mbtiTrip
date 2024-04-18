let currentUrl;
console.log(currentUrl);
/**
 * 입력된 파일을 가져와서 이미지를 업로드하고, 업로드된 이미지를 표시합니다.
 * @param {object} input - 파일 입력(input[type=file]) 객체
 */
function loadImg(input) {
    // 입력된 파일 가져오기
    const file = input.files[0];
    console.dir(file);

    // FormData 객체 생성 및 파일 추가
    let formData = new FormData();
    formData.append("file", file);

    // 이미지 업로드를 위해 서버에 POST 요청 보내기
    fetch("/api/gcs/upload", {
        method: "POST",
        headers: {
            /*"Content-Type": "multipart/form-data"*/
        },
        body: formData
    })
    .then(response => {
        // 응답 상태 확인
        if (response.ok) {
            // 응답이 정상적인 경우 URL을 반환
            console.log("ok");
            return response.text();
        } else {
            // 응답이 오류인 경우 알림 표시
            alert("등록에 실패하였습니다.");
        }
    })
    .then(url => {
        // URL을 사용하여 이미지를 표시
        console.log(url);
        document.querySelector('.selectedImgView').style.backgroundImage = 'url(' + url + ')';
        currentUrl = url; // 현재 URL 저장
    })
    .catch(error => {
        // 에러 발생 시 알림 표시
        alert("등록에 실패.");
    });

    // 이전 이미지 삭제
    if (currentUrl !== undefined) {
        let formData2 = new FormData();
        console.log(currentUrl + "HERE!!!!");
        formData2.append("currentURL", currentUrl);
        fetch("/api/gcs/delete", {
            method: "POST",
            header: {},
            body: formData2
        });
    }
}

 document.querySelector('#Form').addEventListener('submit', (event) => {
				event.preventDefault(); 
                const formData = new FormData(document.querySelector('#Form'));
                //formData에 값을 등록합니다
                formData.set("userImg",currentUrl);
				console.log("==================");
                // FormData에 있는 값을 확인합니다.
                for (const [key, value] of formData.entries()) {
                    console.log(key, value);
                }
                fetch("/user/mypage/update/ck",{
					method:"POST",
					headers:{
						/*"Content-Type":"multipart/form-data"*/
					},
					body:formData
				}).then(response=>{
					if (response.ok) {
						console.log("ok");
						alert(response.text());
			        location.href="/user/mypage";
			    } else {
			        alert("등록에 실패하였습니다.");
			    }
				}).catch(error=>{
					alert("등록에 실패.")
				})
});