

function sendAjaxRequest(url,method,data){
    return new Promise(function(resolve,reject){
        const xhr = new XMLHttpRequest();
        xhttp.onload = function(){
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200){
                resolve(xhr.responseText);
            }else{
                reject(xhr.status);
            }
        }
        xhttp.open(method,url,true);
        xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhttp.send(data);
    });
}
function getResultXHR(url,method,data){
    sendAjaxRequest(url,method,data).then((responseText) => {
        if(responseText===1){
            alert("중복되지않은 아이디 입니다.");
           
        }else{
            alert("중복된 아이디 입니다.")
        }
        
    }).catch((error) => {
        alert(error.message);
    });
}
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
    if(id.length < 6){
        error("6글자 이상 입력해주세요")
    }else{
        delError();
        document.getElementById("pwd").classList.remove("disActive");
        // getResultXHR("/sign_up","POST",id);1
    }
});
document.getElementById("pwd").addEventListener("blur",()=>{
    const pwd = document.getElementById("pwd").value;
    if(pwd.length < 6){
        
    }else{
        document.getElementById("pwd_2").classList.remove("disActive");
    }
});
document.getElementById("pwd_2").addEventListener("blur",()=>{
    const pwd_2 = document.getElementById("pwd_2").value;
    if(pwd_2.length < 6){
        
    }else{
        if(document.getElementById("pwd").value == pwd_2){
            document.getElementById("name").classList.remove("disActive");
        }
        
    }
});
document.getElementById("name").addEventListener("blur",()=>{
    const name = document.getElementById("name").value;
    if(name.length < 3){
        
    }else{
        document.getElementById("email").classList.remove("disActive");
    }
});

document.getElementById("email").addEventListener("blur",()=>{
    const email = document.getElementById("email").value;
    if(email.length < 6){
        
    }else{
        document.getElementById("phone").classList.remove("disActive");
    }
});
document.getElementById("phone").addEventListener("blur",()=>{
    const phone = document.getElementById("phone").value;
    if(phone.length < 11){
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