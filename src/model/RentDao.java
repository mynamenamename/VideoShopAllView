package model;

import java.util.ArrayList;

public interface RentDao {

	//대여
	public void rentVideo(String tel, int vnum) throws Exception;
	
	//전화번호 입력하고 엔터치면 고객명 뜨게
	public String searchname(String tel) throws Exception;
	
	//반납
	public void returnVideo(int vnum) throws Exception;
	
	//미납목록검색
	public ArrayList selectList() throws Exception;
	
}
