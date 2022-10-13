package model.vo;

public class VideoVO {
   
   private String videoNo;    //비디오번호
   private String genre;      //장르
   private String title;      //제목
   private String director;   //감독
   private String actor;      //배우
   private String expl;       //설명
   
   public VideoVO() {
      
   }
   
   public VideoVO(String videoNo, String genre, String title, String director, String actor, String expl) {
      super();
      this.videoNo = videoNo;
      this.genre = genre;
      this.title = title;
      this.director = director;
      this.actor = actor;
      this.expl = expl;
   }
   
   @Override
   public String toString() {
      return "Video_Info [videoNo=" + videoNo + ", genre=" + genre + ", title=" + title + ", director=" + director
            + ", actor=" + actor + ", expl=" + expl + "]";
   }



   public String getVideoNo() {
      return videoNo;
   }
   public void setVideoNo(String videoNo) {
      this.videoNo = videoNo;
   }
   public String getGenre() {
      return genre;
   }
   public void setGenre(String genre) {
      this.genre = genre;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getDirector() {
      return director;
   }
   public void setDirector(String director) {
      this.director = director;
   }
   public String getActor() {
      return actor;
   }
   public void setActor(String actor) {
      this.actor = actor;
   }
   public String getExpl() {
      return expl;
   }
   public void setExpl(String expl) {
      this.expl = expl;
   }
   
   

}