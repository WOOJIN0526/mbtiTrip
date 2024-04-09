let currentUrl;
console.log(currentUrl);
function loadImg(input) {
    const file = input.files[0];
	console.dir(file);
	let formData = new FormData();
	formData.append("file",file);
	
	fetch("/api/gcs/upload",{
		method:"POST",
		headers:{
			/*"Content-Type":"multipart/form-data"*/
		},
		body:formData
	}).then(response=>{
		if (response.ok) {
			console.log("ok");
        return response.text();
    } else {
        alert("등록에 실패하였습니다.");
    }
	}).then(url=>{
		console.log(url);
		document.querySelector('.selectedImgView').style.backgroundImage = 'url(' + url + ')';
		currentUrl =url
	}).catch(error=>{
		alert("등록에 실패.")
	})
	if(currentUrl!==undefined){
		let formData2 = new FormData();
		console.log(currentUrl+"HERE!!!!");
		formData2.append("currentURL",currentUrl);
		fetch("/api/gcs/delete",{
			method:"POST",header:{
				
			},body:formData2
		})
	}
}
 document.querySelector('#Form').addEventListener('submit', (event) => {
				event.preventDefault(); 
                const formData = new FormData(document.querySelector('#Form'));
                //formData에 값을 등록합니다
                formData.set("userImg",currentUrl);
				console.log("==================");
                // FormData에 있는 값을 확인합니다.
                for (const [key, value] of formData.entries()) {
                    console.log(key, value);
                }
                fetch("/user/mypage/update/ck",{
					method:"POST",
					headers:{
						/*"Content-Type":"multipart/form-data"*/
					},
					body:formData
				}).then(response=>{
					if (response.ok) {
						console.log("ok");
						alert(response);
			        location.href="/user/mypage";
			    } else {
			        alert("등록에 실패하였습니다.");
			    }
				}).catch(error=>{
					alert("등록에 실패.")
				})
});