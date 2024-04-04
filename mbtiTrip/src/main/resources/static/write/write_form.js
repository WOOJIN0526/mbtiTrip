import editor from '/ckeditor5-build-classic/editor.js';
/**
 * DOMContentLoaded 이벤트가 발생하면 CKEditor5 에디터를 초기화합니다.
 */
document.addEventListener('DOMContentLoaded', function() {
	/**
     * CKEditor5 에디터를 초기화하고 인스턴스를 받아옵니다.
     * @param {string} target - 에디터를 적용할 요소의 CSS 선택자
     * @returns {Promise} 에디터 인스턴스를 반환하는 프로미스
     */
	editor("#content").then(function(instance) {

    });  
});