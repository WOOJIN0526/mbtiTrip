import UploadAdapter from '/ckeditor5-build-classic/adapter/UploadAdapter.js'
/**
 * write_form.js에서 입력한 타겟 요소에 커스텀 업로드 어댑터 플러그인이 추가된 새로운 에디터 인스턴스를 생성합니다.
 * @param {string} target - 에디터가 생성될 대상 요소의 CSS 선택자
 * @returns {Promise} 생성된 에디터 인스턴스를 해결하는 프로미스
 */
export default function makeEditor(target) {
	// 주어진 대상 요소와 커스텀 플러그인이 추가된 ClassicEditor 인스턴스를 생성합니다.
    return ClassicEditor.create(document.querySelector(target), {
		// extraPlugins 옵션에 커스텀 플러그인을 추가합니다.
        extraPlugins: [MyCustomUploadAdapterPlugin]
    })

}
/**
 * CKEditor를 위한 커스텀 업로드 어댑터 플러그인입니다.
 * @param {Editor} editor - CKEditor 인스턴스
 */
function MyCustomUploadAdapterPlugin(editor) {
	// FileRepository 플러그인의 기본 createUploadAdapter 함수를 커스텀 구현체로 교체합니다.
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
		// 주어진 로더를 사용하여 새 UploadAdapter 인스턴스를 반환합니다.
        return new UploadAdapter(loader)
    }
}