package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.VideoDao;
import model.vo.VideoVO;

public class VideoDaoImpl implements VideoDao {

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	final static String USER = "scott";
	final static String PASS = "tiger";

	public VideoDaoImpl() throws Exception {

		// 1. 드라이버로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");

	}

	/*
	 * DB 입력 메소드
	 */
	
	
	public void insertVideo(VideoVO vo, int count) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// 3. sql 문장 만들기
			String sql = "INSERT INTO Video(VIDEONO,GENERE, TITLE, DIRECTOR, ACTOR,EXPL) VALUES(seq_Video.nextval, ?,?,?,?,?)";
			// 4. sql 전송객체 (PreparedStatement)
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getGenre());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getDirector());
			ps.setString(4, vo.getActor());
			ps.setString(5, vo.getExpl());

			// 5. sql 전송 -- 여기서 count는 tfinsertcount다!!

			for (int i = 0; i < count; i++) {
				ps.executeUpdate();
			}

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}
	}

	 /*
	  * 검색 메소드
	  */
	
	public ArrayList selectVideo(int idx ,String word) throws Exception {
		// int idx ,String word   / String text, String box

		ArrayList data = new ArrayList();

		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			
			// 선생님 방법 index
			//  3. sql 문장 
			String [] colNames = {"TITLE","DIRECTOR"}; 
			String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR FROM VIDEO WHERE "+ colNames[idx]
			  +"  LIKE '%"+ word +"%'" ; // ?를 사용하면 ''가 또 인식됨, 그래서 이렇게 잘라야함
			
			 
			//4. 전송객체
			 ps = con.prepareStatement(sql);
			 
 
			/*
			// 3. sql 문장, 4. 전송객체
			if (box.equals("제목")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR FROM VIDEO WHERE TITLE like '%'||?||'%'";

				ps = con.prepareStatement(sql);
				ps.setString(1, text);

			} else if (box.equals("감독")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR FROM VIDEO WHERE DIRECTOR like'%'||?||'%'";

				ps = con.prepareStatement(sql);
				ps.setString(1, text);

			} else if (box.equals("")) {
				String sql = "SELECT VIDEONO, TITLE, DIRECTOR, ACTOR FROM VIDEO";

				ps = con.prepareStatement(sql);
				ps.setString(1, text);

			}
            
            */
			
			// 5. 전송
			 rs = ps.executeQuery();
			while (rs.next()) {

				ArrayList temp = new ArrayList();
				temp.add(rs.getInt("VIDEONO"));
				temp.add(rs.getString("TITLE"));
				temp.add(rs.getString("DIRECTOR"));
				temp.add(rs.getString("ACTOR"));
				data.add(temp);

			}

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}

		return data;

	}

	 /*
	  * 검색한 정보가 옆에 뜨게끔 하는 메소드
	  */
	
	
	public VideoVO selectByVnum(int vNum) throws Exception {
		VideoVO vo = new VideoVO();
		// 2. Connection 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		ResultSet rs= null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// 3. sql 문장 만들기
			String sql = "SELECT * FROM VIDEO WHERE VIDEONO=?";

			// 4. 전송객체
			ps = con.prepareStatement(sql);
			ps.setInt(1, vNum);
			// vNum이 인트니께

			// 5. 전송
			 rs = ps.executeQuery();
			if (rs.next()) {
				vo.setVideoNo(rs.getString("VIDEONO"));
				vo.setGenre(rs.getString("GENERE"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setDirector(rs.getString("DIRECTOR"));
				vo.setActor(rs.getString("ACTOR"));
				vo.setExpl(rs.getString("EXPL"));

			}

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}

		return vo;

	}

	/*
 	 *  정보 업데이트(수정) 메소드
	 */
	public int modifyVideo(VideoVO vo) throws Exception {
		// 2. Connection 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null; // 선언을 바깥에서 해야 다 가져다 쓸 수 있음
		int result=0;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASS);

			// 3. sql 문장 만들기
			String sql = "UPDATE Video SET GENERE=?, TITLE=?, DIRECTOR=?, ACTOR=?,EXPL=? WHERE VIDEONO=? ";
			// 4. sql 전송객체 (PreparedStatement)
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getGenre());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getDirector());
			ps.setString(4, vo.getActor());
			ps.setString(5, vo.getExpl());
			ps.setString(6, vo.getVideoNo());

			// 5. sql 전송 

				ps.executeUpdate();
			

		} finally {
			// 6. 닫기
			ps.close();
			con.close();
		}
		return result;
	}

	

	/*
 	 *  삭제 메소드
	 */
	

	public int deleteVideo(int vNum) throws Exception {
		 //2. 연결객체 얻어오기
		 
		 Connection con= null;
		 PreparedStatement ps= null; //선언을 바깥에서 해야 다 가져다 쓸 수 있음
		 int result = 0;
		
		 try {
		 con= DriverManager.getConnection(URL,USER,PASS);
		 
		 //3. sql 문장 만들기
		 String sql=" DELETE FROM VIDEO WHERE VIDEONO=? ";
		 //4. 전송객체 얻어오기
		 
		 ps = con.prepareStatement(sql);
		 ps.setInt(1, vNum);
		 
		 //5. 전송
		 
		 ps.executeUpdate();
	
		 
		 }finally {
			 //6. 닫기
			 
		     ps.close();
			 con.close();
	     }

		return result;
	}

}
