package step3_00_boardEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao {

	private BoardDao() {}
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}
	
	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	public Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/step3_board_ex?serverTimezone=UTC" , "root" , "1234"); 
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;
		
	}
	
	// 게시글을 추가하는 DAO
	public void insertBoard(BoardDto boardDto) {
		
		try {
			conn  = getConnection();
			String sql = "INSERT INTO BOARD(WRITER, EMAIL, SUBJECT, PASSWORD, REG_DATE, READ_COUNT, CONTENT)";
				   sql += "VALUES(?,?,?,?,NOW(),0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getWriter());
			pstmt.setString(2, boardDto.getEmail());
			pstmt.setString(3, boardDto.getSubject());
			pstmt.setString(4, boardDto.getPassword());
			pstmt.setString(5, boardDto.getContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	
	// 전체 게시글을 조회하는 DAO
	public ArrayList<BoardDto> getAllboard() {// 한개 이상의 DTO이기 때문에 어레이리스트 사용
		
		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>(); // 내용이 많으므로 리스트에 담아와서 한번에 호출
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 다중 데이터이므로 while문 사용
				
				// 자바와 db를 이어주는 과정 
				BoardDto boardDto = new BoardDto(); //  리스트에 내용 담아주기 
				boardDto.setNum(rs.getInt("NUM"));
				boardDto.setWriter(rs.getString("WRITER"));
				boardDto.setEmail(rs.getString("EMAIL"));
				boardDto.setSubject(rs.getString("SUBJECT"));
				boardDto.setPassword(rs.getString("PASSWORD"));
				boardDto.setRegDate(rs.getDate("REG_DATE"));
				boardDto.setReadCount(rs.getInt("READ_COUNT"));
				boardDto.setContent(rs.getString("CONTENT"));
				boardList.add(boardDto); // 추가 해주는 과정 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return boardList; 
	}
	
	// 하나의 게시글을 조회하는 DAO
	public BoardDto getOneBoard(int num) { // 단일 데이터이므로 리스트 사용x
		
		BoardDto boardDto = new BoardDto();
		
		try {
			conn  = getConnection();
			
			//조회수 올려주는 쿼리
			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT = READ_COUNT + 1 WHERE NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//조회하는 쿼리
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 단일 데이터이므로 if사용 
				boardDto.setNum(rs.getInt("NUM"));
				boardDto.setWriter(rs.getString("WRITER"));
				boardDto.setEmail(rs.getString("EMAIL"));
				boardDto.setSubject(rs.getString("SUBJECT"));
				boardDto.setPassword(rs.getString("PASSWORD"));
				boardDto.setRegDate(rs.getDate("REG_DATE"));
				boardDto.setReadCount(rs.getInt("READ_COUNT"));
				boardDto.setContent(rs.getString("CONTENT"));
				  
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return boardDto;
	}
	
	
}

