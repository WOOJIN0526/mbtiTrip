window.addEventListener('DOMContentLoaded', function() {
  showContent('A');
});

function showContent(content) {
  var contentA = document.getElementById("frequently_question");
  var contentB = document.getElementById("profile_question");
  var contentC = document.getElementById("center_question");
  var contentD = document.getElementById("question_question");
  var contentE = document.getElementById("service_question");

  var cButtonA = document.getElementById("categoryButton1");
  var cButtonB = document.getElementById("categoryButton2");
  var cButtonC = document.getElementById("categoryButton3");
  var cButtonD = document.getElementById("categoryButton4");
  var cButtonE = document.getElementById("categoryButton5");

  contentA.style.display = "none";
  contentB.style.display = "none";
  contentC.style.display = "none";
  contentD.style.display = "none";
  contentE.style.display = "none";

  // 선택한 내용 보이기
  if (content === "A") {
    contentA.style.display = "block";
    cButtonA.style.backgroundColor = "red";
    cButtonB.style.backgroundColor = "white";
    cButtonC.style.backgroundColor = "white";
    cButtonD.style.backgroundColor = "white";
    cButtonE.style.backgroundColor = "white";
  }if (content === "B") {
    contentB.style.display = "block"; 
    cButtonB.style.backgroundColor = "red";
    cButtonA.style.backgroundColor = "white";
    cButtonC.style.backgroundColor = "white";
    cButtonD.style.backgroundColor = "white";
    cButtonE.style.backgroundColor = "white";
  }if (content === "C") {
    contentC.style.display = "block"; 
    cButtonC.style.backgroundColor = "red";
    cButtonA.style.backgroundColor = "white";
    cButtonB.style.backgroundColor = "white";
    cButtonD.style.backgroundColor = "white";
    cButtonE.style.backgroundColor = "white";
  }if (content === "D") {
    contentD.style.display = "block";
    cButtonD.style.backgroundColor = "red";
    cButtonA.style.backgroundColor = "white";
    cButtonB.style.backgroundColor = "white";
    cButtonC.style.backgroundColor = "white";
    cButtonE.style.backgroundColor = "white";
  }if (content === "E") {
    contentE.style.display = "block";
    cButtonE.style.backgroundColor = "red";
    cButtonA.style.backgroundColor = "white";
    cButtonB.style.backgroundColor = "white";
    cButtonC.style.backgroundColor = "white";
    cButtonD.style.backgroundColor = "white";
    
  } 
}





