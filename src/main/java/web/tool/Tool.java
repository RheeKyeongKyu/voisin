package web.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

public class Tool {
  
  /**
   * 중복되지 않는 키를 생성합니다.
   * 예) ABC + 현재시간: ABC1234567890123
   * @return
   */
  public static synchronized String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26자)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su; //코드를 문자로 변경
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
 
    code = code + new Date().getTime();
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
  
  /**
   * 정수형 파일 크기에 데이터 단위를 추가합니다.
   * @param length
   * @return
   */
  public static synchronized String unit(long length){
    String str = "";

    if (length < 1024){  // Byte: 0 ~ 1023
      str = length + " Byte";   
    }else if (length < (1024 * 1024)){ // Byte: 1024 ~ 1048575
      str = length / 1024 + " KB";
    }else if(length < (1024 * 1024 * 1024)){ // Byte: 1048576 ~ 1073741823
      str = length / 1024 / 1024 + " MB";
    }else if(length < (1024L * 1024 * 1024 * 1024)){ // Byte: 1073741824 ~
      str = length / 1024 / 1024 / 1024+ " GB";
    }
    
    return str;
  }
  
  /**
   * 태그의 기능을 단순만 문자로 표현하여 출력합니다.
   * @param str
   * @return
   */
  public static synchronized String convertChar(String str){
    str = str.replaceAll("<", "& lt;");
    str = str.replaceAll(">", "& gt;");
    str = str.replaceAll("'", "& #39;");     // '
    str = str.replaceAll("\"", "& quot;"); // "
    str = str.replaceAll("\r\n", "<BR>");
    
    return str;
  }
  
  /**
   * 문자열이 null이면 공백으로 리턴되고, null이 아니면 원본 문자열이
   * 리턴됩니다.
   * @param str
   * @return
   */
  public static synchronized String checkNull(String str){
    if (str == null){
      return "";  // null -> "null" 방지
    }else if (str.equals("null")){
      return "";
    }else{
      return str;
    }
  }
  
  /**
   * 주어진 날짜와 기간을 계산하여 새글 여부 판단
   * 현재 날짜가 2013-02-04, 글을 등록날짜 2013-02-02
   * isNew("2013-02-04", 2) : 글 작성 후 2틀 전까지 새글 처리 
   * 
   * @param date 문자열로 된 날짜
   * @param period 새글로 지정할 기간
   * @return
   */
  public static synchronized boolean isNew(String date, int period) {
    boolean sw = false;

    Date _date = new Date();
    // 숫자 형태인 날짜를 '년월일 시분초'의 형태로 변환
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    try {
      _date = sd.parse(date);
    } catch (Exception e) {
    }
    // System.out.println(date);
    // 1970년1월1일부터 시간을 1000을 1초로하여 계산하여 리턴
    long time = _date.getTime(); // 글을 작성한 시간

    // 현재 시간을 1970년 1월 1일부터 수치형식으로 리턴
    long currentTime = System.currentTimeMillis();

    // 현재 시간과 글 등록시간의 차이를 계산
    long diff = currentTime - time;

    // 1일 86,400,000: 1초를 1000으로 하루를 계산

    // ceil: 아주 작은 소수점이 있어도 정수로 반올림 처리됨.
    // 0.0001 --> 1: 오늘 날짜
    // 1.00002 --> 2: 어제 날짜
    int day = (int) Math.ceil(((double) diff / 86400000)); // 지난 날짜 계산

    if (day <= period) {
      sw = true; // 최신글 처리
    }
    return sw;
  }

  /**
   * 이미지 파일인지 검사합니다.
   * @param filename 검사할 파일명
   * @return
   */
  public static synchronized boolean isImage(String filename){
    boolean bol = false;  // 이미지 여부 저장
    
    filename = filename.toLowerCase(); // 소문자로 변경
    
    if (filename.endsWith(".jpg") ||
        filename.endsWith(".jpeg") ||
        filename.endsWith(".png") ||
        filename.endsWith(".gif") ||
        filename.endsWith(".bmp")){
      bol = true;
    }
    
    return bol;
  }
  
  /**
   * 이미지 사이즈를 변경하여 새로운 이미지를 생성합니다.
   * @param src 원본 이미지
   * @param dest 생성되는 이미지
   * @param width 생성될 이미지 너비
   * @param height 생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
   * @throws IOException
   */
  public static synchronized String imgResize(File src, File dest, int width, int height) {
    int RATIO = 0;
    int SAME = -1;
    
    Image srcImg = null;
    // 파일의 확장자 추출
    String name = src.getName().toLowerCase(); // 파일명 추출
    // 이미지 파일인지 검사
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png") || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성
        int srcWidth = srcImg.getWidth(null);   // 원본 이미지 너비 추출
        int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
        int destWidth = -1, destHeight = -1;    // 대상 이미지 크기 초기화
        
        if (width == SAME) {    // width가 같은 경우
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width;    // 새로운 width를 할당
        }
        
        if (height == SAME) {    // 높이가 같은 경우
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height;  // 새로운 높이로 할당
        }
        
        // 비율에 따른 크기 계산
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }

        // 메모리에 대상 이미지 생성
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
     
        // 파일에 기록
        ImageIO.write(destImg, "jpg", dest);
        
        System.out.println(dest.getName() +  " 이미지를 생성했습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      name = dest.getName(); // 새로 생성된 파일명
    } 
    
    return name;
  }
  
  /**
   * 이미지 사이즈를 변경하여 새로운 Preview 이미지를 생성합니다.
   <pre>
   사용예): Too.preview(folder 명, 원본 파일명, 200, 150)
   </pre>
   * @param upDir 원본 이미지 폴더
   * @param _src 원본 파일명
   * @param width 생성될 이미지 너비
   * @param height  생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
   * @return src.jpg 파일을 이용하여 src_t.jpg 파일을 생성하여 파일명 리턴
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
    String preview_name = "";

    File src = new File(upDir + "/" + _src); // 원본 파일 객체 생성
    String srcname = src.getName(); // 원본 파일명 추출

    // 순수 파일명 추출, mt.jpg -> mt 만 추출
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // 축소 이미지 조합 /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;
    // 파일의 확장자 추출
    String name = src.getName().toLowerCase(); // 파일명 추출
    // 이미지 파일인지 검사
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성
        int srcWidth = srcImg.getWidth(null); // 원본 이미지 너비 추출
        int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
        int destWidth = -1, destHeight = -1; // 대상 이미지 크기 초기화

        if (width == SAME) { // width가 같은 경우
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // 새로운 width를 할당
        }

        if (height == SAME) { // 높이가 같은 경우
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // 새로운 높이로 할당
        }

        // 비율에 따른 크기 계산
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }

        // 메모리에 대상 이미지 생성
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // 파일에 기록
        ImageIO.write(destImg, "jpg", dest);

        System.out.println(dest.getName() + " 이미지를 생성했습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      }
      preview_name = dest.getName(); // 새로 생성된 파일명
    }else{
      preview_name = ""; // 이미지  파일이 아닌 경우
    }

    return preview_name;
  }

  public static synchronized String youtube(String str, int width, int height){
    int index1 = str.indexOf("\"");   // 첫번째 문자 검색
    int index2 = str.indexOf("\"", index1+1); // 첫번째 이후부터 검색
    int index3 = str.indexOf("\"", index2+1); // 두번째 이후부터 검색
    int index4 = str.indexOf("\"", index3+1); // 세번째 이후부터 검색
    // System.out.println("--> index1: " + index1);
    // System.out.println("--> index2: " + index2);
    // System.out.println("--> index3: " + index3);
    // System.out.println("--> index4: " + index4);

    String str1 = str.substring(0, index1+1); // <iframe width='
    String str2 = str.substring(index2, index3+1); //  ' height='
    String str3 = str.substring(index4);   //  ' src=이후의 모든 문자열
    String url = str1 + width + "px" + str2 + height + "px" + str3;

    // System.out.println("--> str1: " + str1);
    // System.out.println("--> str1: " + str2);
    // System.out.println("--> str1: " + str3);
    return url;
  }
  
  /**  
   * 파일을 삭제합니다.  
  */ 
  public static synchronized boolean deleteFile(String folder, String fileName){ 
      boolean ret = false; 
       
      try{ 
          if ( fileName != null){ // 기존에 파일이 존재하는 경우 삭제 
              File file = new File(folder + "/" + fileName); 
              if (file.exists()){
                ret = file.delete();
              }
          } 
      }catch(Exception e){ 
          e.printStackTrace(); 
      } 
       
      return ret; 
  } 

  /**
   * 회원으로 로그인 했는지 검사합니다.
   * @param request 
   * @return true: 로그인 회원, false: 손님
   */
  public static synchronized  boolean isUser(HttpServletRequest request){
    boolean sw = false; // 로그인 여부 저장
    
    // web 상의 session 객체 추출
    HttpSession session = request.getSession();     
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }else{
      sw = false;
    }

    return sw;
  }

  /**
   * 폴더를 입력받아 절대 경로를 산출합니다. 
   * 예) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir 절대 경로를 구할 폴더명
   * @return 절대 경로 리턴
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }

    return path;
  }

  /**
   * 파일 삭제
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
      if (file.exists()){
        ret = file.delete();
      }
    
    return ret;
  }
  
  /**
   * 문자열의 길이가 length 보다 크면 "..." 을 표시하는 메소드
   * @param length 선별할 문자열 길이
   * @param str 문자열
   * @return
   */
  public static synchronized String textLength(int length, String str) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0, length) + "...";
      }
    } else {
      str = "";
    }

    return str;
  }
  
  /**
   * 마스터 계정인지 검사합니다.
   * @param request
   * @return true: 마스터 계정
   */
  public static synchronized boolean isMaster(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession(); //세션 추출
    String master = (String)session.getAttribute("master");//세션 변수 추출
    // System.out.println("--> Tool.java act: " + act);
    
    if (master != null){
      if (master.equals("M")){
        sw = true;
      }
    }
    return sw;
  }
 
  /**
   * 일반 관리자 계정인지 검사합니다.
   * @param request
   * @return true: 일반 관리자
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String master = (String)session.getAttribute("master");
    
    if (master != null){
      if (master.equals("N")){ //2016.7.6 프로젝트 적용함!!!
        sw = true;
      }
    }
    return sw;
  }
  
  /**
   * 2010-12-14 형식의 날짜를 리턴합니다.
   * 
   * @return 2010-12-14 형식의 문자열 리턴
   */
  public static String getDate(int year, int month, int day) {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    String date = sd.format(new Date(year - 1900, month, day));

    return date;
  }
  
}

