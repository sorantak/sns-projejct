$(document).ready(function() {
	$('#login_btn').click(function() {
		// console.log는 브라우저 f12를 누르면 나오는 console창에 뜨게 함
		console.log("log in clicked!!!");

		var username = $('#login_username').val();
		var password = $('#login_password').val();

		if (!username || !password) {
			alert("This field is mandatory..");
			return;
		}

		var param = {
			username : username,
			password : password
		}

		$.ajax({
			url : "/auth",
			method : "POST",
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(param)
		}).then(function(data) {
			document.cookie = "accesstoken=" + data.data.token;
			document.cookie = "userId=" + data.data.userId;
			window.location.reload();
		}, function(err) {
			alert("Check your account information please.");
			window.location.href = '/index';
		});
		return false;
	});
});