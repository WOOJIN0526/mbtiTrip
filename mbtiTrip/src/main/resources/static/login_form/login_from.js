const idElement = document.getElementById("id");
const pwdElement = document.getElementById("pwd");
const loginButton = document.getElementById("login-btn");

loginButton.addEventListener("click",()=>{
    console.log(idElement.value);
    console.log(pwdElement.value);
})