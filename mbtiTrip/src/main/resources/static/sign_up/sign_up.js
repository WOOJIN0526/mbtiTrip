/**
 * 현재 페이지 URL의 경로에 따라 회원가입 유형을 선택합니다.
 * 페이지 URL이 '/user'를 포함하면 '/user'를 반환하고, 그렇지 않으면 '/bis'를 반환합니다.
 * @returns {string} - 회원가입 유형에 따라 '/user' 또는 '/bis'를 반환합니다.
 */
function signUpSelect(){
	let url =document.getElementById('url').textContent;
	let selected = url.includes("/user")?'/user':'/bis';
	return selected;
}

/*function getResultXHR(url,method,data,conType){
    sendAjaxRequest(url,method,data,conType).then((responseText) => {
        if(responseText){
            alert("됨");
            
           
        }else{
            alert("안됨");
        }
        
    }).catch((error) => {
        alert(error.message);
    });
}*/
/**
 * 화면에 오류 메시지를 표시합니다.
 * 오류 메시지가 이미 표시되어 있는 경우에는 새로운 메시지를 추가하지 않습니다.
 * @param {string} message - 화면에 출력할 오류 메시지 문자열
 */
function error(message){
	// 이미 오류 메시지가 표시되어 있는지 확인합니다.
    if(document.getElementById("totLoginPage").lastElementChild.classList.contains("error")){
		 // 이미 오류 메시지가 표시되어 있으면 추가적인 처리를 하지 않습니다.
    }else{
		// 오류 메시지가 표시되어 있지 않으면 새로운 오류 메시지를 생성하여 페이지에 추가합니다.
        const eDiv =document.createElement("div");
        eDiv.innerHTML =message;
        eDiv.classList.add("error");
        document.getElementById("totLoginPage").appendChild(eDiv);
    }
    
}
/**
 * 오류 메시지를 삭제합니다.
 * 페이지에 오류 메시지가 표시되어 있는 경우에만 실행됩니다.
 */
function delError(){
	// 페이지에 오류 메시지가 표시되어 있는지 확인합니다.
    if(document.getElementById("totLoginPage").lastElementChild.classList.contains("error")){
        // 페이지에 오류 메시지가 있으면 마지막으로 추가된 오류 메시지를 삭제합니다.
        document.getElementById("totLoginPage").removeChild(document.getElementById("totLoginPage").lastElementChild);
    }
}
document.getElementById("id").addEventListener("blur",()=>{
    const id = document.getElementById("id").value;
    if(id.length < 3){
        error("3글자 이상 입력해주세요")
    }else{
        delError();
        document.getElementById("pwd").classList.remove("disActive");
        let tString = "사용가능한 아이디 입니다."
        let fString = "중복된 아이디 입니다."
        // getResultXHR("/sign_up","POST",id,tString,fString);
    }
});
document.getElementById("pwd").addEventListener("blur",()=>{
    const pwd = document.getElementById("pwd").value;
    if(pwd.length < 3){
        
    }else{
        document.getElementById("pwd_2").classList.remove("disActive");
    }
});
document.getElementById("pwd_2").addEventListener("blur",()=>{
    const pwd_2 = document.getElementById("pwd_2").value;
    if(pwd_2.length < 3){
        
    }else{
        if(document.getElementById("pwd").value == pwd_2){
            document.getElementById("name").classList.remove("disActive");
        }
        
    }
});
document.getElementById("name").addEventListener("blur",()=>{
    const name = document.getElementById("name").value;
    if(name.length < 2){
        
    }else{
        document.getElementById("email").classList.remove("disActive");
    }
});

document.getElementById("email").addEventListener("blur",()=>{
    const email = document.getElementById("email").value;
    if(email.length < 3){
        
    }else{
        document.getElementById("phone").classList.remove("disActive");
    }
});
document.getElementById("phone").addEventListener("blur",()=>{
    const phone = document.getElementById("phone").value;
    if(phone.length < 3){
        error("전화번호 전체를 입력해 주세요")
    }else{
		if(signUpSelect()==='/user'){
			document.getElementById("mbti").classList.remove("disActive");
		}
		if(signUpSelect()==='/bis'){
			
			document.querySelector('#bisNumber').classList.remove("disActive");
		}
        
        delError();
        
    }
});
document.getElementById("mbti").addEventListener("change",()=>{
    console.log(document.getElementById("mbti").value);
        document.getElementById("sign_up_btn").classList.remove("disActive");
    
});
document.querySelector('#bisNumber').addEventListener("blur",()=>{
    const email =document.querySelector('#bisNumber').value;
    if(email.length < 3){
        
    }else{
        document.getElementById("sign_up_btn").classList.remove("disActive");
    }
});
document.getElementById("sign_up_btn").addEventListener("click",()=>{
    const id = document.getElementById("id").value;
    const pwd = document.getElementById("pwd").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    if(signUpSelect()==='/user'){
		const mbti = document.getElementById("mbti").value;
        var sign_up_data ={
		userId:id,
		password:pwd,
		userName:name,
		mbti:mbti,
		mail:email,
		phone:phone}
		var url='/signup/user';
	}else if(signUpSelect()==='/bis'){
		console.log(123);
		const bisNumber = document.querySelector('#bisNumber').value
		var sign_up_data ={
		userId:id,
		password:pwd,
		userName:name,
		mbti:bisNumber,
		mail:email,
		phone:phone}
		var url='/signup/bis';
	}
    console.log(sign_up_data);
    let jsonData = JSON.stringify(sign_up_data);
    console.log(jsonData);
    let tString = "회원가입에 성공했습니다."
    let fString = "회원가입에 실패했습니다."
    
    //getResultXHR("/signup","POST",jsonData,"JSON");
    sendAjaxRequest(url,"POST",jsonData,"JSON").then((responseText) => {
        if(responseText){
            alert("됨");
            window.location.href = "/login_A";
           
        }else{
            alert("안됨");
        }
        
    }).catch((error) => {
        alert(error.message);
    });
});