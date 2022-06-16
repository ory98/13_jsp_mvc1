<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>

	<%
		//session.removeAttribute("id"); 한개씩 지우기 가능
		session.invalidate(); // 전체 다 끊기
	%>

	<script>
		alert("You are logged out.");
		location.href = "00_main.jsp";
	</script>
	
</body>
</html>