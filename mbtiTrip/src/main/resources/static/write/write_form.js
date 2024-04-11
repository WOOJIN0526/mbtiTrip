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
                // 폼 제출 기본 동작 중지
                event.preventDefault(); 
                // editor내부의 값을 가져옵니다
				const content = CKEcontent.getData();
        		console.log(content);
                const formData = new FormData(document.querySelector('#Form'));
                //formData에 값을 등록합니다
                formData.set("content",content);
				console.log("==================");
                // FormData에 있는 값을 확인합니다.
                for (const [key, value] of formData.entries()) {
                    console.log(key, value);
                }
                if(formData.get('postCategoryID')==='1'){
					
					var url ="/post/review/create";
					console.log(url);
				}else{
					var url="/post/noticeBoard/create";
					console.log(url);
				}
				
				
				
                fetch(url,{
					method:"POST",
					headers:{
						/*"Content-Type":"multipart/form-data"*/
					},
					body:formData
				}).then(response=>{
					if(response.ok){
						alert(response);
					}
				})
});  


