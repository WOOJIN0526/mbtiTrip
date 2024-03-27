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
                reject(xhr.status);
            }
        }
        xhr.open(method,url,true);
       
        if(conType=="JSON"){
			xhr.setRequestHeader("Content-Type", "application/json");

		}else{
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		}
        xhr.send(data);
    });
}
