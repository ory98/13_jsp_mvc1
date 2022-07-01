package step2_00_loginEx;

import java.util.Date;

// Dto (Date Transfer Object) : 데이터 전송 모델 객체 (압축파일 느낌)
public class MemberDto { // 테이블과 맞춰서 만들어야하는 규칙이 있다. 

	private String id; // 테이블과 맞춰 변수의 속성을 맞춰준다. ****
	private String passwd;
	private String name;
	private Date joinDate; // 자바는 언더바 대신 카멜케이스 사용***
	
	// getter&setter 만들어 준다.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
	
	
}

