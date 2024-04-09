
  const questions =document.querySelectorAll(".question");
  window.addEventListener('DOMContentLoaded', function() {
    questions.forEach(function(qeustion){
        if(!qeustion.dataset.often){
            qeustion.style.display = "none";
            
        }
    });
  });
/**
 * 이 함수는 클릭한 카테고리에 따라 question 요소를 보이게 합니다.
 * @param {string} content - question 요소의 dataset과 비교할 값
 */
  function showContent(content){
    questions.forEach(function(qeustion){
        if(qeustion.dataset.often == content || qeustion.dataset.value == content){
            qeustion.style.display = "block";
        }else{
            qeustion.style.display = "none";
        }
        
    });
  }
  document.querySelector('.c-search-box button').addEventListener('click', function(){
    const search =document.querySelector('.c-search-box input').value;
    questions.forEach(function(question){
        const content =question.firstElementChild.firstElementChild.textContent;
        if(content.includes(search)){
            question.style.display = "block";
        }else{
            question.style.display = "none";
        }
    });
  });
  document.querySelectorAll('.CButton').forEach(function(button){
    button.addEventListener('click', function(){
        document.querySelectorAll('.CButton').forEach(function(b){
            b.style.backgroundColor = "white";
        });
        button.style.backgroundColor = "var(--main-color)";
    });
  })
  questions.forEach(function(question){
    question.addEventListener('toggle', function(){
        if(question.open){
            question.classList.remove('fade-out');
            question.classList.add('fade-in');
        }else{
            question.classList.remove('fade-in');
            question.classList.add('fade-out');
        }
    });
  });
/**
 * 이 함수는 oclick()의 콜백함수로 질문에 대한 답변을 적을수있도록
 * textArea를 생성하는 함수입니다.
 * textArea를 생성후 한번더 클릭될시에는 
 * 작성한 textArea의 value를 전송할수 있도록 다음 함수를 실행합니다
 *  @param {*} target 클릭한 요소
 */  
function writeReple(target){
	console.log(target.id);
	const QID =target.id;
	const parent = target.parentNode;
	if (parent.lastElementChild.classList!="repleArea") {
    	console.log(parent);
		let repleArea = document.createElement('textArea');
		repleArea.classList.add("repleArea");
		parent.append(repleArea);
    	
    	
	}else{
		submitReple(QID);
	}
}

/**
 * 파라미터로 넘겨온 값과 textArea에 작성한 내용을 formData에 담아 전송하고 결과를 받습니다.
 * @param {Integer} QID 
 */
function submitReple(QID) {
    const reple = document.querySelector('.repleArea').value;
    console.log(reple);
    const url = "/QnA/admin";

    let formData = new FormData();
    formData.append('QID', QID);
    formData.append('A_content', reple);

    fetch(url, {
        method: "POST",
        body: formData
    })
    .then(response => {
        if (response.ok) {
            alert(response);
            location.href = "/QnA";
        } else {
            alert("답변 등록에 실패했습니다.");
            location.href = "/QnA";
        }
    })
    .catch(error => {
        console.error("댓글 등록 중 에러 발생:", error);
        // 에러 처리
    });
}

