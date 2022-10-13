package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.CustomerDao;
import model.vo.CustomerVO;

public class CustomerDaoImpl implements CustomerDao{

	
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	final static String USER = "scott";
	final static String PASS = "tiger";
	
	
	public CustomerDaoImpl() throws Exception{
		
	 	// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");
		
		
	}
	
	/*
	 * 메소드명 :insertCustomer
	 * 역할 : 사용자가 입력한 데이터를 DB에 저장하는 역할
	 */
	
	public void insertCustomer(CustomerVO vo) throws Exception{
		// 2. Connection 연결객체 얻어오기
		Connection con= null;
		PreparedStatement ps= null; //선언을 바깥에서 해야 다 가져다 쓸 수 있음
		 try {
			 con= DriverManager.getConnection(URL,USER,PASS);
			 
		
		// 3. sql 문장 만들기
		String sql = "INSERT INTO Customer(name, pNo, s_Pno, addr, email) VALUES(?,?,?,?,?)";
		
		// 4. sql 전송객체 (PreparedStatement)		
		 ps = con.prepareStatement(sql);
		 
		 ps.setString(1, vo.getName());
		 ps.setString(2, vo.getpNo());
		 ps.setString(3, vo.getS_Pno());
		 ps.setString(4, vo.getAddr());
		 ps.setString(5, vo.getEmail());
		// 5. sql 전송
		 ps.executeUpdate();
		 
		 }finally {
		// 6. 닫기 
			 ps.close();
			 con.close();
			 }
	}
	
	
	
	/*
	 * 메소드명 :selectByTel
	 * 인자    :검색할 전화번호
	 * 리턴값   :전화번호 검색에 따른 고객 정보
	 * 역할    :사용자가 입력한 전화번호를 받아서 해당하는 고객정보를 리턴
	 */
	
	public CustomerVO selectByTel(String tel) throws Exception{
		
		//2. 연결객체 얻어오기
		 Connection con= null;
		 PreparedStatement ps= null;
		 CustomerVO dao = new CustomerVO();
		 ResultSet rs = null;
		 
		 try {
			 con= DriverManager.getConnection(URL,USER,PASS);
		//3. sql 문장 만들기
		String sql = "SELECT * FROM Customer WHERE pNo=?";
		
		//4. 전송객체 얻어오기
		 ps = con.prepareStatement(sql);
		 ps.setString(1, tel);		
		
		//5. 전송 -- executeQuery();
		// 결과를 CustomerVO에 담기
		 rs = ps.executeQuery();
		  if(rs.next()) {
			  dao.setName(rs.getString("NAME"));
			  dao.setpNo(rs.getString("PNO"));
			  dao.setS_Pno(rs.getString("S_PNO"));
			  dao.setAddr(rs.getString("ADDR"));
			  dao.setEmail(rs.getString("EMAIL"));
		  }else { 
			  JOptionPane.showMessageDialog(null, "등록된 회원이 없습니다.");
		  }
		 
		//6. 닫기
		 }finally {
			
			 
		     ps.close();
			 con.close();
	     }
		
		return dao;
		
	}
	
	
	/*
	 * 메소드명 :updateCustomer
	 * 인자    :검색할 전화번호
	 * 리턴값   :전화번호 검색에 따른 고객 정보
	 * 역할    :사용자가 입력한 전화번호를 받아서 해당하는 고객정보를 **수정**
	 * insert랑 똑같고, 인자가 int면 int 선언 후 result 있어야함
	 * void면 없어도 됨
	 */   
	 
	public int updateCustomer(CustomerVO vo) throws Exception{
		//2. 연결객체 얻어오기
		
		 Connection con= null;
		 PreparedStatement ps= null;
		 int result = 0;
		 
		 try {
			 con= DriverManager.getConnection(URL,USER,PASS);
		
		//3. sql 문장 만들기
		String sql = "UPDATE Customer set NAME=?, S_PNO=?, ADDR=?, EMAIL=? where PNO=?";
		//4. 전송객체 얻어오기
        ps = con.prepareStatement(sql);
		ps.setString(1,vo.getName());
		ps.setString(2, vo.getS_Pno());
		ps.setString(3, vo.getAddr());
		ps.setString(4, vo.getEmail());
		ps.setString(5, vo.getpNo());
		
				
		//5. 전송
		 ps.executeUpdate();
		
		//6. 닫기
		
		 }finally {
			 //6. 닫기
			 
		     ps.close();
			 con.close();
	     }
		
		
		
		return result;
	}

	
	public CustomerVO selectByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
