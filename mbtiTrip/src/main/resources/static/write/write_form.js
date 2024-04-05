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



 document.querySelector('#Form').addEventListener('submit', (event) => {
                event.preventDefault(); // 폼 제출 기본 동작 중지
				const content = CKEcontent.getData();
        		console.log(content);
                const formData = new FormData(document.querySelector('#Form'));
				console.log("==================");
                // FormData에 있는 값을 확인합니다.
                for (const [key, value] of formData.entries()) {
                    console.log(key, value);
                }
            });