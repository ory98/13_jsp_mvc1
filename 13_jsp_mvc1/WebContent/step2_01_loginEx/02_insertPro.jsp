<%@page import="step2_00_loginEx.MemberDao"%>
<%@page import="step2_00_loginEx.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertPro</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	
		MemberDto memberDto = new MemberDto(); // 압축파일 만들어주기
		
		memberDto.setId(request.getParameter("id")); // 압축파일에 담아 주기 
		memberDto.setPasswd(request.getParameter("passwd"));
		memberDto.setName(request.getParameter("name"));
		
		boolean isFirstMember = MemberDao.getIntance().insertMember(memberDto); 
		// = new MemberDao를 만들어주고, insertMember에 memberDao를 넘겨주어 확인한다.
		
		if (isFirstMember) { // 아이디가 없을 경우  
	%>		
			<script>
				alert("You are now a member.");
				location,href = "00_main.jsp";
			</script>
	<%	
		}
		else { // 아이디가 있을 경우
	%>
		<script>
			alert("This is a duplicated ID"); // 중복된 아이디입니다. 
			history.go(-1);
		</script>
	<%		
		}
	%>

</body>
</html>