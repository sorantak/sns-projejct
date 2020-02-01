$(document).ready(function() {
	$('#login_btn').click(function() {
		// console.log는 브라우저 f12를 누르면 나오는 console창에 뜨게 함
		console.log("log in clicked!!!");

		var username = $('#login_username').val();
		var password = $('#login_password').val();

		if (!username || !password) {
			alert("This field is mandatory.");
			return;
		}

		var user = {
			username : username,
			password : password
		}

		$.ajax({
			// POST /auth API 연동
			url : "/auth",
			method : "POST",
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(user)
		}).then(function(data) {
			// 성공 시 얼럿 후 /index 페이지로 redirection
			// 쿠키는 무슨 역할?
			document.cookie = "accesstoken=" + data.data.token;
			document.cookie = "userId=" + data.data.userId;
			alert("Log In Success.")
	    	window.location.href = '/';
		}, function(err) {
			// 실패 시 페이지 reload
			alert("Check your account information please.");
			window.location.reload();
		});
		
		return false;
		
	});
});