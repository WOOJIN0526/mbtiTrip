  // CKEeditor 적용
  CKEDITOR.replace('content', {
    // CKEeditor 옵션 설정
    // 예를 들어, toolbar 설정 등을 여기에 추가할 수 있습니다.
    height:400
    
  });
// document.querySelector('button').addEventListener('click',function() {
//     const content =document.querySelector('#content');
//     console.log(content.value);
//     console.dir(content);
// });

  // 폼 제출 시 이벤트 리스너 추가
  document.getElementById('Form').addEventListener('submit', function(event) {
    // 기본 제출 동작 방지
    event.preventDefault();

    // 입력 데이터 가져오기
    var title = document.getElementById('title').value;
    var content = CKEDITOR.instances.content.getData();

    // 콘솔에 출력
    console.log('제목:', title);
    console.log('내용:', content);

    // 여기서 데이터를 사용하여 필요한 작업을 수행할 수 있습니다.
    // 예를 들어, 서버로 데이터를 전송하는 등의 작업을 수행할 수 있습니다.
    
    // 실제로는 이제 데이터를 서버로 보내야 하지만, 여기서는 콘솔에만 출력하는 예시입니다.
  });