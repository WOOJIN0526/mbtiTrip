 /**
 * 다음 우편번호 서비스 API를 사용하여 주소 검색 팝업을 띄우고, 사용자가 선택한 주소를 입력 필드에 적절히 채웁니다.
 */
 function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
}
    
var fileNo = 0;
var filesArr = new Array();

/**
 * 파일 입력 요소에서 선택된 파일을 처리하여 첨부파일 목록에 추가합니다.
 * @param {object} obj - 파일 입력 요소(input[type=file]) 객체
 */
function addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    } else {
        for (const file of obj.files) {
            // 첨부파일 검증
            if (validation(file)) {
                // 파일 배열에 담기
                var reader = new FileReader();
                reader.onload = function () {
                    filesArr.push(file);
                };
                reader.readAsDataURL(file);

                // 목록 추가
                let htmlData = '';
                htmlData += '<div id="file' + fileNo + '" class="filebox">';
                htmlData += '   <p class="name">' + file.name + '</p>';
                htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
                htmlData += '</div>';
                console.log(htmlData);
                document.querySelector('.file-list').innerHTML += htmlData;
                fileNo++;
            } else {
                continue;
            }
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/**
 * 파일을 검증하여 유효성을 확인합니다.
 * @param {object} obj - 검증할 파일 객체
 * @returns {boolean} - 파일이 유효한 경우 true, 그렇지 않은 경우 false를 반환합니다.
 */
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/**
 * 지정된 파일 번호에 해당하는 파일 요소를 삭제하고, 파일 배열에서 해당 파일을 삭제합니다.
 * @param {number} num - 삭제할 파일 번호
 */ㄴ
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}
/**
 * 폼의 제출을 중단하고 대신 submitForm() 함수를 실행합니다.
 */
document.getElementById("replaceForm").addEventListener("submit", function(event) {
  // 기본 제출 동작을 중단합니다.
  event.preventDefault();
  // submitForm() 함수를 실행합니다.
  submitForm();
});

/**
 * 폼 데이터를 수집하여 서버로 전송합니다.
 */
function submitForm() {
    var form = document.querySelector("form");
    var formData = new FormData(form);
    // 파일 배열을 순회하며 삭제되지 않은 파일만 FormData에 추가
    for (var i = 0; i < filesArr.length; i++) {
        if (filesArr[i].name !== '' && !filesArr[i].is_delete) {
            formData.append("file", filesArr[i]);
        }
    }
    for (let key of formData.keys()) {
	console.log(key, ":", formData.get(key));
	}
	fetch("/replace/create",{
		method:"POST",
		headers:{
			/*"Content-Type":"multipart/form-data"*/
		},
		body:formData
	}).then(response=>{
		if (response.ok) {
        alert("등록에 성공하였습니다.");
    } else {
        alert("등록에 실패하였습니다.");
    }
	}).catch(error=>{
		alert("등록에 실패하였습니다.")
	})
}
    