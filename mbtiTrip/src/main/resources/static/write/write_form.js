import editor from '/ckeditor5-build-classic/editor.js';
/**
 * DOMContentLoaded 이벤트가 발생하면 CKEditor5 에디터를 초기화합니다.
 */
let CKEcontent;
document.addEventListener('DOMContentLoaded', function() {
	/**
     * CKEditor5 에디터를 초기화하고 인스턴스를 받아옵니다.
     * @param {string} target - 에디터를 적용할 요소의 CSS 선택자
     * @returns {Promise} 에디터 인스턴스를 반환하는 프로미스
     */
	editor("#content").then(function(instance) {
		 CKEcontent= instance;
    });  
});


var element = document.querySelector('#itemID');

if (element !== null) {
    // 돔이 존재하는 경우
    console.log("도메인이 존재합니다.");
} else {
    // 돔이 존재하지 않는 경우
    console.log("도메인이 존재하지 않습니다.");
}
 document.querySelector('#Form').addEventListener('submit', (event) => {
	    // 폼 제출 기본 동작 중지
	    event.preventDefault();

	    // editor내부의 값을 가져옵니다
		const content = CKEcontent.getData();
	    const formData = new FormData(document.querySelector('#Form'));
	    let postType;
	    //formData에 값을 등록합니다
	    formData.set("content",content);
	    //review일때만 값을 등록합니다
	    var element = document.querySelector('#itemID');
		if (element !== null) {
			const itemID =element.dataset.value;
			formData.set("itemID",itemID);
		}
	    if(formData.get('postCategoryID')==='1'){
			
			var url ="/post/review/create";
			postType='review';
		}else{
			var url="/post/noticeBoard/create";
			postType='noticeBoard';
		}
		
		
		
	    fetch(url,{
			method:"POST",
			headers:{
				/*"Content-Type":"multipart/form-data"*/
			},
			body:formData
		}).then(response => {
		    if (response.ok) {
		        return response.text(); // 리스폰스의 텍스트 내용을 반환
		    } else {
		        throw new Error('Network response was not ok.');
		    }
		}).then(data => {
		    alert(data); // 반환된 텍스트를 알림으로 표시
		    window.location.href = `/post/${postType}/list`;
		}).catch(error => {
		    console.error('There was a problem with the fetch operation:', error);
		});
});  



