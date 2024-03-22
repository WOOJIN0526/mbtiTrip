const idElement = document.getElementById("id");
const pwdElement = document.getElementById("pwd");
const loginButton = document.getElementById("login-btn");

loginButton.addEventListener("click",()=>{
    console.log(idElement.value);
    console.log(pwdElement.value);
})
document.getElementById("sign_up_btn").addEventListener("click",function(){
	window.location.assign("/signup");
});