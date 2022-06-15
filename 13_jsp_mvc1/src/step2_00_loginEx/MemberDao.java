package step2_00_loginEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DAO(Date Access Object) : 데이터 접근 객체 (DB와 붙는 역할) 
public class MemberDao {
	
	// SingleTon 패턴(하나의 객체만 만듬) > 보안 적절 + new로 해서 사용 가능 
	private MemberDao() {}
	private static MemberDao instance = new MemberDao(); // static : 새로 만들어지는것이 아니라 계속 사용 가능 
	public static MemberDao getIntance() { // 꺼내주는 역할 
		return instance;
	}

	private Connection conn 		= null; 
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null; // SELECT가 있다.
	
	// 반환타입은 Connection객체이며, 메서드명은 관례적으로 getConnection으로 작성한다. 
	public Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC" , "root" , "1234"); 
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;
		
	}
	
	// login DAO (두가지 경우이기 때문에 boolean 사용)
	public boolean login(String id , String passwd) {
		
		boolean isVaildMember = false; // 최종 결과는 거짓 
		
		try {
			
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID =? AND PASSWD = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs 	  = pstmt.executeQuery();
			
			if (rs.next()) { // 아이디와 비밀번호가 있음
				isVaildMember = true; // 1. 결과는 진실
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} 	 catch (SQLException e) {e.printStackTrace();}
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return isVaildMember; // 2. 결과가 거짓이면 그대로 내보냄 
		
	}
	
	
	
}
