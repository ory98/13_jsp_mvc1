<%@page import="step2_00_loginEx.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginPro</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	
		String id 	  = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		// DB 연동해서 로그인 여부 확인 (자바에서 연동)
		boolean isVaildMember = MemberDao.getIntance().login(id, passwd); // 1. 싱글턴은 new를 안쓰고 객체만 가져옴 2. 불리언 변수에 담아서 사용
		
		if (isVaildMember) {
			session.setAttribute("id", id); // 등록하고 난 후 main으로 이동했을 경우 사용됨
			session.setMaxInactiveInterval(60 * 10);
	%>
		<script>
			alert("Loggde in");
			location.href = "00_main.jsp";
		</script>
	<%	
		}
		else {
	%>
		<script>
			alert("Check your Id or Password");
			history.go(-1);
		</script>
	<%	
		}
	%>

</body>
</html>