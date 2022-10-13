package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.RentDao;
import model.vo.CustomerVO;

public class RentDaoImpl implements RentDao {

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	final static String USER = "scott";
	final static String PASS = "tiger";

	// Connection con;

	public RentDaoImpl() throws Exception {
		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");

	}

	// 대여
	public void rentVideo(String tel, int vnum) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
             
			
			// 3. sql 문장 만들기
			String sql = "INSERT INTO RENT(RENTNO,PNO,VIDEONO,RENTDATE,RETURNYN)  "
					+ " VALUES(SEQ_RENT1.NEXTVAL, ?,?,sysdate, 'N')  ";

			// 4. sql 전송객체 (PreparedStatement)
			ps = con.prepareStatement(sql);

			// 5. sql 전송

			ps.setString(1, tel);
			ps.setInt(2, vnum);

			ps.executeUpdate();

		}  finally {
			// 6. 닫기
			ps.close();
			con.close();
		}

	}

	// 반납
	public void returnVideo(int vnum) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// 3. SQL 문장
			String sql = "UPDATE RENT SET RETURNYN ='Y' WHERE VIDEONO=? AND RETURNYN='N'";

			// 4. sql 전송객체 (PreparedStatement)
			ps = con.prepareStatement(sql);

			ps.setInt(1, vnum);

			// 5. sql 전송
			ps.executeUpdate();

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}

	}

	// 미납목록검색
	public ArrayList selectList() throws Exception {

		ArrayList data = new ArrayList();

		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		
		//	3. sql 문장
			String sql="select    v.videono videono, v.title title, c.name name, "
		               + "   c.pno tel, r.rentdate+7 returndate, r.returnyn return "
		               + " from  customer c inner join rent r on c.pno=r.pno "
		               + " inner join video v on v.videono=r.videono "
		               + " where r.returnyn = 'N' ";
			
		//  4. 전송객체 
			ps = con.prepareStatement(sql);
		// 5. 전송
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {

		      ArrayList temp = new ArrayList();
			  temp.add(rs.getString("videono"));
			  temp.add(rs.getString("title"));
			  temp.add(rs.getString("name"));
			  temp.add(rs.getString("tel"));
			  temp.add(rs.getString("returndate"));
			  temp.add(rs.getString("return"));
							
			data.add(temp);

			}

			} finally {
		 // 6. 닫기
			ps.close();
			con.close();
		}
		
		return data;
	}

	// 전화번호 입력하고 엔터치면 고객명 뜨게
	public String searchname(String tel) throws Exception {
		// 2. Connection 연결객체 얻어오기
		

		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
        String name= null;
        ResultSet rs = null;
        
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// 3. SQL 문장
			String sql = "SELECT NAME FROM CUSTOMER WHERE PNO=?";

			// 4. sql 전송객체 (PreparedStatement)
			ps = con.prepareStatement(sql);

			ps.setString(1, tel);

			// 5. sql 전송
			  rs =ps.executeQuery();
			  if(rs.next()) {
				 name = rs.getString("NAME");
		
			  }
			  
			  return name;

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}

	}

}
