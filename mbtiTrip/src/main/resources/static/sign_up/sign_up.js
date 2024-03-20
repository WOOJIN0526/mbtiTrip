

function sendAjaxRequest(url,method,data,conType){
    return new Promise(function(resolve,reject){
        const xhr = new XMLHttpRequest();
        xhr.onload = function(){
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200){
                resolve(xhr.responseText);
            }else{
                reject(xhr.status);
            }
        }
        xhr.open(method,url,true);
       
        if(conType=="JSON"){
			xhr.setRequestHeader("Content-Type", "application/json");

		}else{
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		}
        xhr.send(data);
    });
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
function error(message){
    if(document.getElementById("totLoginPage").lastElementChild.classList.contains("error")){

    }else{
        const eDiv =document.createElement("div");
        eDiv.innerHTML =message;
        eDiv.classList.add("error");
        document.getElementById("totLoginPage").appendChild(eDiv);
    }
    
}
function delError(){
    if(document.getElementById("totLoginPage").lastElementChild.classList.contains("error")){
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
        document.getElementById("mbti").classList.remove("disActive");
        delError();
        
    }
});
document.getElementById("mbti").addEventListener("change",()=>{
    console.log(document.getElementById("mbti").value);
        document.getElementById("sign_up_btn").classList.remove("disActive");
    
});
document.getElementById("sign_up_btn").addEventListener("click",()=>{
    const id = document.getElementById("id").value;
    const pwd = document.getElementById("pwd").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const mbti = document.getElementById("mbti").value;
    let sign_up_data ={
		userId:id,
		password:pwd,
		userName:name,
		mbti:mbti,
		mail:email,
		phone:phone}
    let jsonData = JSON.stringify(sign_up_data);
    console.log(jsonData);
    let tString = "회원가입에 성공했습니다."
    let fString = "회원가입에 실패했습니다."
    
    //getResultXHR("/signup","POST",jsonData,"JSON");
    sendAjaxRequest("/signup","POST",jsonData,"JSON").then((responseText) => {
        if(responseText){
            alert("됨");
            window.location.href = "/login";
           
        }else{
            alert("안됨");
        }
        
    }).catch((error) => {
        alert(error.message);
    });
});