<%@page import="step3_00_boardEx.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="step3_00_boardEx.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bList</title>
</head>
<body>

	<h1>게시글 보기</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	<%
		// 내용의 한줄이 BoardDto(틀) , BoardDto를 어레이리스트에 담는다. 
		// BoardDad에 있는 불러온 내용을 리스트 칸에 넣어준다. 
		ArrayList<BoardDto> boardList = BoardDao.getInstance().getAllboard();
		for (BoardDto boardDto : boardList) {
	%>		
			<tr>
				<td><%=boardDto.getNum() %></td>
				<td><a href="05_bInfo.jsp?num=<%=boardDto.getNum()%>"><%=boardDto.getSubject() %></a></td>
				<td><%=boardDto.getWriter() %></td>
				<td><%=boardDto.getRegDate() %></td>
				<td><%=boardDto.getReadCount() %></td>
			</tr>
			
	<%		
		}
	
	%>	
		<tr>
			<td colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='02_bWrite.jsp'">
			</td>
		</tr>
	
	</table>


</body>
</html>