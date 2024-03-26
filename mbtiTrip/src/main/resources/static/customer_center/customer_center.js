
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
