  // CKEeditor 적용
ClassicEditor.create(document.querySelector('#content'))
			 .catch(error =>{
				 console.error(error);
			 });
// document.querySelector('button').addEventListener('click',function() {
//     const content =document.querySelector('#content');
//     console.log(content.value);
//     console.dir(content);
// });


/*  document.getElementById('Form').addEventListener('submit', function(event) {

    event.preventDefault();


    var title = document.getElementById('title').value;
    var content = CKEDITOR.instances.content.getData();


    console.log('제목:', title);
    console.log('내용:', content);


  });*/