/**
 * 지정된 URL에 대한 AJAX 요청을 보냅니다.
 * @param {string} url - 요청을 보낼 URL
 * @param {string} method - HTTP 메소드 (GET, POST 등)
 * @param {string} data - 요청에 포함될 데이터
 * @param {string} conType - 컨텐츠 유형 (JSON 또는 x-www-form-urlencoded)
 * @returns {Promise} - AJAX 요청의 결과를 담은 Promise 객체
 */
function sendAjaxRequest(url,method,data,conType){
    return new Promise(function(resolve,reject){
        const xhr = new XMLHttpRequest();
        xhr.onload = function(){
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200){
                resolve(xhr.responseText);
            }else{
                reject(xhr.response);
            }
        }
        xhr.open(method,url,true);
       
        if(conType=="JSON"){
			xhr.setRequestHeader("Content-Type", "application/json");

		}else{
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		}
		console.log(xhr);
        xhr.send(data);
    });
}
/**
 * 로그인되어 있는지 확인하기 위한 함수입니다.
 * @return {Promise} response상태에 따라 값을 나눕니다.
 */
function isLoggedIn() {
    return new Promise((resolve, reject) => {
        fetch('/check-login')
            .then(response => {
                if (response.ok) {
                    resolve(response.json()); // JSON 형식으로 응답 파싱하여 반환
                } else {
                    resolve(false); // 로그인되어 있지 않음
                }
            })
            .catch(error => {
                console.error('Error checking login status:', error);
                reject(error);
            });
    });
}

isLoggedIn().then(response => {
    if (response === false) {
        console.log("로그인되지 않은 사용자 입니다.")
        return null;
    } else {
        // 서버에서 반환된 응답을 JSON 형식으로 파싱하여 사용
        console.log(response);
        return response.userImg;
        
    }
}).then(imgUrl=>{
	if(imgUrl!==null){
		document.querySelector('.userImg').src=imgUrl;
	}
});
