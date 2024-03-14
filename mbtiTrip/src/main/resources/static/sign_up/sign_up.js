const nameElement = document.getElementById("name");
const idElement = document.getElementById("id");
const pwdElement = document.getElementById("pwd");
const pwd_2Element = document.getElementById("pwd_2");
// const mbtiElement = document.getElementById("mbti");
const emailElement = document.getElementById("email");
const phoneElement = document.getElementById("phone");
// const genderElement = document.getElementById("gender");
const signupButton = document.getElementById("sign_up_btn");

function ex(obj) {
	console.log(obj.value);
}

function mt(obj){
    console.log(obj.value);
}

signupButton.addEventListener("click",()=>{
   
    console.log(nameElement.value);
    console.log(idElement.value);
    console.log(pwdElement.value);
    console.log(pwd_2Element.value);
    // console.log(mbtiElement.value);
    console.log(emailElement.value);
    console.log(phoneElement.value);
    // console.log(obj.value);
})