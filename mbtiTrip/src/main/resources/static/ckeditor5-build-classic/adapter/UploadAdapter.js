/**
 * editor.js에서 불러올
 * 커스텀 업로드 어댑터 클래스입니다.
 */
export default class UploadAdapter {
    /**
     * 업로더를 초기화합니다.
     * @param {Loader} loader - 업로더 인스턴스
     */
    constructor(loader) {
        this.loader = loader;
    }
	/**
     * 파일을 업로드합니다.
     * @returns {Promise} 업로드 작업을 수행하는 프로미스
     */
    upload() {
        return this.loader.file.then( file => new Promise(((resolve, reject) => {
            this._initRequest();
            this._initListeners( resolve, reject, file );
            this._sendRequest( file );
        })))
    }
	/**
     * XMLHttpRequest를 초기화합니다.
     */
    _initRequest() {
        const xhr = this.xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/gcs/upload', true);
        xhr.responseType = 'text';
    }
	/**
     * XMLHttpRequest 이벤트 리스너를 초기화합니다.
     * @param {function} resolve - 성공 콜백 함수
     * @param {function} reject - 실패 콜백 함수
     * @param {File} file - 업로드할 파일
     */
    _initListeners(resolve, reject, file) {
        const xhr = this.xhr;
        const loader = this.loader;
        const genericErrorText = '파일을 업로드 할 수 없습니다.'

        xhr.addEventListener('error', () => {reject(genericErrorText)})
        xhr.addEventListener('abort', () => reject())
        xhr.addEventListener('load', () => {
            const response = xhr.response
            if(!response || response.error) {
                return reject( response && response.error ? response.error.message : genericErrorText );
            }

            resolve({
                default: response.url //업로드된 파일 주소
            })
        })
    }
	/**
     * 요청을 전송합니다.
     * @param {File} file - 전송할 파일
     */
    _sendRequest(file) {
        const data = new FormData()
        data.append('file',file)
        this.xhr.send(data)
    }
}