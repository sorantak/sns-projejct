$(document).ready(function() {
	$('#signup_btn').click(function() {
		var username = $('#signup_username').val();
		var password = $('#signup_password').val();

		if (!username || !password) {
			alert("This field is mandatory.");
			return;
		}

		var user = {
			username : username,
			password : password
		}

		$.ajax({
			// POST /user API 연동
			url : "/user",
			method : "POST",
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(user)
		}).then(function(data) {
			// 성공 시 얼럿 후 /login 페이지로 redirection
			// 쿠키는 무슨 역할?
			document.cookie = "accesstoken=" + data.data.token;
			document.cookie = "userId=" + data.data.userId;
			alert("Sign Up Success.")
			window.location.href = '/login';
		}, function(err) {
			// 실패 시 페이지 reload
			alert("Sign Up Failed.");
			window.location.reload();
		});

		return false;

		/*
		 * POST /user API 연동 Ajax 코드 작성 성공 시 "회원 가입이 되었습니다." 얼럿 후 /login 페이지로
		 * redirection 실패 시 페이지 Reload
		 */
	});
});