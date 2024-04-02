document.querySelector("#questionCategory").addEventListener("change", function() {
        // 직접입력을 누를 때 나타남
        if (document.querySelector("#questionCategory").value === "direct") {
            selboxDirect.style.display = "block";
            document.querySelector("#questionCategory").removeAttribute("name");
        } else {
            selboxDirect.style.display = "none";
            document.querySelector("#questionCategory").removeAttribute("name");
            document.querySelector("#questionCategory").setAttribute("name","QName");

	}
});
function logFormData() {
            // FormData 객체를 생성하여 폼 데이터를 가져옴
            var formData = new FormData(document.getElementById("questionForm"));
            
            // FormData의 값을 콘솔에 출력
            for (var pair of formData.entries()) {
                console.log(pair[0] + ': ' + pair[1]);
            }

            // 폼을 제출하지 않도록 반환값을 false로 설정
            return false;
}

document.querySelector('.btn-submit').addEventListener('click',function(){
	let QName = document.querySelector('#questionCategory').value;
	if(QName==='direct'){
		QName=document.querySelector('#selboxDirect').value;
	}
	const contents=document.querySelector('#contents').value;
	const data={
		
		title:QName,
		contents:contents		
	}
	
	let jsonData = JSON.stringify(data);
	console.dir(jsonData);
	const url ='/QnA/create';
	const method ='post';
	const conType ='JSON';
/*	sendAjaxRequest(url,method,jsonData,conType).then((responseText) => {
        if(responseText === 'true'){
			console.log(responseText);
            alert("등록되었습니다.");
            window.location.href = "/QnA/"; 
           
        }else{
            alert("등록에실패했습니다.");
        }
        
    }).catch((error) => {
        alert(error.message);
    });*/
   const option = {
  method: 'POST',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json; charset=utf-8'
  },
  body:jsonData
};

fetch(url, option)
  .then(response => response.text())
  .then(responseText => {
    if (responseText === 'true') {
      console.log(responseText);
      alert("등록되었습니다.");
      window.location.href = "/QnA";
    } else {
      alert("등록에 실패했습니다.");
    }
  })
  .catch(error => {
    console.error('Error:', error);
    alert("등록에 실패했습니다.");
  });

    
    
    
});
      