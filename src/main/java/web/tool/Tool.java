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
   * �ߺ����� �ʴ� Ű�� �����մϴ�.
   * ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public static synchronized String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26��)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su; //�ڵ带 ���ڷ� ����
    
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
   * ������ ���� ũ�⿡ ������ ������ �߰��մϴ�.
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
   * �±��� ����� �ܼ��� ���ڷ� ǥ���Ͽ� ����մϴ�.
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
   * ���ڿ��� null�̸� �������� ���ϵǰ�, null�� �ƴϸ� ���� ���ڿ���
   * ���ϵ˴ϴ�.
   * @param str
   * @return
   */
  public static synchronized String checkNull(String str){
    if (str == null){
      return "";  // null -> "null" ����
    }else if (str.equals("null")){
      return "";
    }else{
      return str;
    }
  }
  
  /**
   * �־��� ��¥�� �Ⱓ�� ����Ͽ� ���� ���� �Ǵ�
   * ���� ��¥�� 2013-02-04, ���� ��ϳ�¥ 2013-02-02
   * isNew("2013-02-04", 2) : �� �ۼ� �� 2Ʋ ������ ���� ó�� 
   * 
   * @param date ���ڿ��� �� ��¥
   * @param period ���۷� ������ �Ⱓ
   * @return
   */
  public static synchronized boolean isNew(String date, int period) {
    boolean sw = false;

    Date _date = new Date();
    // ���� ������ ��¥�� '����� �ú���'�� ���·� ��ȯ
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    try {
      _date = sd.parse(date);
    } catch (Exception e) {
    }
    // System.out.println(date);
    // 1970��1��1�Ϻ��� �ð��� 1000�� 1�ʷ��Ͽ� ����Ͽ� ����
    long time = _date.getTime(); // ���� �ۼ��� �ð�

    // ���� �ð��� 1970�� 1�� 1�Ϻ��� ��ġ�������� ����
    long currentTime = System.currentTimeMillis();

    // ���� �ð��� �� ��Ͻð��� ���̸� ���
    long diff = currentTime - time;

    // 1�� 86,400,000: 1�ʸ� 1000���� �Ϸ縦 ���

    // ceil: ���� ���� �Ҽ����� �־ ������ �ݿø� ó����.
    // 0.0001 --> 1: ���� ��¥
    // 1.00002 --> 2: ���� ��¥
    int day = (int) Math.ceil(((double) diff / 86400000)); // ���� ��¥ ���

    if (day <= period) {
      sw = true; // �ֽű� ó��
    }
    return sw;
  }

  /**
   * �̹��� �������� �˻��մϴ�.
   * @param filename �˻��� ���ϸ�
   * @return
   */
  public static synchronized boolean isImage(String filename){
    boolean bol = false;  // �̹��� ���� ����
    
    filename = filename.toLowerCase(); // �ҹ��ڷ� ����
    
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
   * �̹��� ����� �����Ͽ� ���ο� �̹����� �����մϴ�.
   * @param src ���� �̹���
   * @param dest �����Ǵ� �̹���
   * @param width ������ �̹��� �ʺ�
   * @param height ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @throws IOException
   */
  public static synchronized String imgResize(File src, File dest, int width, int height) {
    int RATIO = 0;
    int SAME = -1;
    
    Image srcImg = null;
    // ������ Ȯ���� ����
    String name = src.getName().toLowerCase(); // ���ϸ� ����
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png") || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����
        int srcWidth = srcImg.getWidth(null);   // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1;    // ��� �̹��� ũ�� �ʱ�ȭ
        
        if (width == SAME) {    // width�� ���� ���
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width;    // ���ο� width�� �Ҵ�
        }
        
        if (height == SAME) {    // ���̰� ���� ���
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height;  // ���ο� ���̷� �Ҵ�
        }
        
        // ������ ���� ũ�� ���
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

        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
     
        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);
        
        System.out.println(dest.getName() +  " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      name = dest.getName(); // ���� ������ ���ϸ�
    } 
    
    return name;
  }
  
  /**
   * �̹��� ����� �����Ͽ� ���ο� Preview �̹����� �����մϴ�.
   <pre>
   ��뿹): Too.preview(folder ��, ���� ���ϸ�, 200, 150)
   </pre>
   * @param upDir ���� �̹��� ����
   * @param _src ���� ���ϸ�
   * @param width ������ �̹��� �ʺ�
   * @param height  ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return src.jpg ������ �̿��Ͽ� src_t.jpg ������ �����Ͽ� ���ϸ� ����
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
    String preview_name = "";

    File src = new File(upDir + "/" + _src); // ���� ���� ��ü ����
    String srcname = src.getName(); // ���� ���ϸ� ����

    // ���� ���ϸ� ����, mt.jpg -> mt �� ����
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // ��� �̹��� ���� /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;
    // ������ Ȯ���� ����
    String name = src.getName().toLowerCase(); // ���ϸ� ����
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ

        if (width == SAME) { // width�� ���� ���
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // ���ο� width�� �Ҵ�
        }

        if (height == SAME) { // ���̰� ���� ���
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // ���ο� ���̷� �Ҵ�
        }

        // ������ ���� ũ�� ���
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

        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);

        System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
      preview_name = dest.getName(); // ���� ������ ���ϸ�
    }else{
      preview_name = ""; // �̹���  ������ �ƴ� ���
    }

    return preview_name;
  }

  public static synchronized String youtube(String str, int width, int height){
    int index1 = str.indexOf("\"");   // ù��° ���� �˻�
    int index2 = str.indexOf("\"", index1+1); // ù��° ���ĺ��� �˻�
    int index3 = str.indexOf("\"", index2+1); // �ι�° ���ĺ��� �˻�
    int index4 = str.indexOf("\"", index3+1); // ����° ���ĺ��� �˻�
    // System.out.println("--> index1: " + index1);
    // System.out.println("--> index2: " + index2);
    // System.out.println("--> index3: " + index3);
    // System.out.println("--> index4: " + index4);

    String str1 = str.substring(0, index1+1); // <iframe width='
    String str2 = str.substring(index2, index3+1); //  ' height='
    String str3 = str.substring(index4);   //  ' src=������ ��� ���ڿ�
    String url = str1 + width + "px" + str2 + height + "px" + str3;

    // System.out.println("--> str1: " + str1);
    // System.out.println("--> str1: " + str2);
    // System.out.println("--> str1: " + str3);
    return url;
  }
  
  /**  
   * ������ �����մϴ�.  
  */ 
  public static synchronized boolean deleteFile(String folder, String fileName){ 
      boolean ret = false; 
       
      try{ 
          if ( fileName != null){ // ������ ������ �����ϴ� ��� ���� 
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
   * ȸ������ �α��� �ߴ��� �˻��մϴ�.
   * @param request 
   * @return true: �α��� ȸ��, false: �մ�
   */
  public static synchronized  boolean isUser(HttpServletRequest request){
    boolean sw = false; // �α��� ���� ����
    
    // web ���� session ��ü ����
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
   * ������ �Է¹޾� ���� ��θ� �����մϴ�. 
   * ��) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir ���� ��θ� ���� ������
   * @return ���� ��� ����
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
   * ���� ����
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
   * ���ڿ��� ���̰� length ���� ũ�� "..." �� ǥ���ϴ� �޼ҵ�
   * @param length ������ ���ڿ� ����
   * @param str ���ڿ�
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
   * ������ �������� �˻��մϴ�.
   * @param request
   * @return true: ������ ����
   */
  public static synchronized boolean isMaster(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession(); //���� ����
    String master = (String)session.getAttribute("master");//���� ���� ����
    // System.out.println("--> Tool.java act: " + act);
    
    if (master != null){
      if (master.equals("M")){
        sw = true;
      }
    }
    return sw;
  }
 
  /**
   * �Ϲ� ������ �������� �˻��մϴ�.
   * @param request
   * @return true: �Ϲ� ������
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
    boolean sw = false;
    
    HttpSession session = request.getSession();
    String master = (String)session.getAttribute("master");
    
    if (master != null){
      if (master.equals("N")){ //2016.7.6 ������Ʈ ������!!!
        sw = true;
      }
    }
    return sw;
  }
  
  /**
   * 2010-12-14 ������ ��¥�� �����մϴ�.
   * 
   * @return 2010-12-14 ������ ���ڿ� ����
   */
  public static String getDate(int year, int month, int day) {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    String date = sd.format(new Date(year - 1900, month, day));

    return date;
  }
  
}
