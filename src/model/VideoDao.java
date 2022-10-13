package model;

import java.util.ArrayList;

import model.vo.VideoVO;

public interface VideoDao {
	public void      insertVideo(VideoVO vo, int count) throws Exception;      // DB 입력 
	public ArrayList selectVideo(int idx, String word) throws Exception;    // 검색
	                               //int idx, String word , String text, String box
    public VideoVO   selectByVnum(int vNum) throws Exception;                  //검색한 정보가 옆에 뜨게끔
    public int deleteVideo(int vNum) throws Exception;                         // 삭제
    public int modifyVideo(VideoVO vo) throws Exception;	     	           // 수정
	
	
}
