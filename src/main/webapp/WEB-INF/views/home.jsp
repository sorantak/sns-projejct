<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {
		$("#checkJson").click(function() {
			var user = {
				id : "4",
				username : "nesol",
				password : "1212"
			};

			$.ajax({
				type : "POST",
				url : "/mysns/signup",
				contentType : "application/json",
				data : JSON.stringify(user),
				success : function(data, textStatus) {
					alert(data);
				},
				error : function(data, textStatus) {
					alert("Occured Error.");
				},
				complete : function(data, textStatus) {
				}
			});
		});
	});
</script>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>비밀번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" id="checkJson" value="Sign Up">

</body>
</html>