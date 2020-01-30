$(document).ready(function(){
	$('#signup_btn').click(function(){
		alert("button clicked.");
		console.log("signup clicked!!!");
		
		var username = $('#signup_username').val();
		var password = $('#signup_password').val();
		
		if(!username || !password) {
			alert("필수 항목을 채워주세요.");
			return;
		}
		
		var param = {
				username: username,
				password: password
		}
		
		$.ajax({
	        url: "/signup",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {
	    	alert("Sign Up Success.")
	    }, function(err) {
	    	alert("계정 정보를 확인해주세요.");
	    });
		return false;
		
		/*
		*	POST /user API 연동 Ajax 코드 작성
		*   성공 시 "회원 가입이 되었습니다." 얼럿 후 /login 페이지로 redirection
		*	실패 시 페이지 Reload
		*/
	});
});