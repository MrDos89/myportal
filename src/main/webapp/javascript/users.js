function checkEmail(event) {
	// 가입 폼 #check-email을 누르면 email 텍스트 상자의 value를 중복되어있는지
	// Ajax로 체크 
	const obj = event.target;
	const target = obj.getAttribute("data-target");
	const frm = event.target.form;
	const email = frm.email.value;
	
	if (email.trim().length === 0) {
		alert("이메일을 입력해 주세요");
		frm.email.focus();
		return;
	}
	
	fetch(`${target}?email=${email}`)
	.then(response => {
		console.log(response);
		return response.json();
	})
	.then(data => {
		console.log(data);
	})
	.catch(error => {
		console.error("Error: " + error);
	})
}

window.addEventListener("load", (event) => {
	document.getElementById("check-email").addEventListener("click", checkEmail);
})