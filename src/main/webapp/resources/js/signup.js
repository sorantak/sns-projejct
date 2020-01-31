$(document).ready(function(){
	$('#signup_btn').click(function(){
		var username = $('#signup_username').val();
		var password = $('#signup_password').val();
		
		if(!username || !password) {
			alert("This field is mandatory.");
			return;
		}
		
		var user = {
				username: username,
				password: password
		}
		
		$.ajax({
	        url: "/user",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(user)
	    }).then(function(data) {
	    	alert("Sign Up Success.")
	    }, function(err) {
	    	alert("Sign Up Failed.");
	    	// 현재 success가 되지 않기 때문에 임시로 fail 이후 login.ftl 화면으로 넘어가도록 설정
	    	window.location.href = '/login';
	    });
		return false;
		
		/*
		*	POST /user API 연동 Ajax 코드 작성
		*   성공 시 "회원 가입이 되었습니다." 얼럿 후 /login 페이지로 redirection
		*	실패 시 페이지 Reload
		*/
	});
});