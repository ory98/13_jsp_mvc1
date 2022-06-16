<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert</title>
</head>
<body>

	<form action="02_insertPro.jsp" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<p>Id : <input type="text" name="id"></p>
			<p>Password : <input type="password" name="passwd"></p>
			<p>Name : <input type="text" name="name"></p>
			<input type="submit" value="Join">
		</fieldset>
	</form>

</body>
</html>