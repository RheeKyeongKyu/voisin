package dev.mvc.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.local.LocalDAO;
import dev.mvc.local.LocalVO;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberDAO")
  private MemberDAO memberDAO;
  
  @Autowired
  @Qualifier("dev.mvc.local.LocalDAO")
  private LocalDAO localDAO;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
 
  /**
   * �⺻ Ȩ �̵�
   */
  @RequestMapping(value = "/member/home.do", method = RequestMethod.GET)
  public ModelAndView home(){
    ModelAndView mav = new ModelAndView();
        
    mav.setViewName("redirect:/index.jsp");
    
    return mav;
  }
  /**
   * ���� ����
   * @return
   */
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/agreement"); // /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO,
                                         HttpServletRequest request,
                                         HttpSession session) {
    System.out.println("--> create() POST called.");
    System.out.println("����Ű Ȯ��:"+memberVO.getAuth());
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    //���� �ڵ�
    System.out.println("���� ����: "+memberVO.gettAddress());
    System.out.println("�μ� ����: "+memberVO.getmAddress());
    LocalVO localVO = localDAO.read(memberVO.getmAddress());
    
    System.out.println("��°�: "+localVO.getLocal_code());
    memberVO.setLocal_code(localVO.getLocal_code());
    
    //������ ��� ���� ���� Ȯ��
    if(memberDAO.checkMaster(memberVO.getMaster())<2){
      memberVO.setMaster("M");
    }else{
      memberVO.setMaster("N");
    }
    
    // -------------------------------------------------------------------
    // ���� ����
    // -------------------------------------------------------------------
    String file1 = "";
    String upDir = Tool.getRealPath(request, "/member/storage");
    MultipartFile file1MF = memberVO.getFile1MF();
    
    //���� ũ�� 10MB ���� ����
    if(file1MF.getSize()<10485760){
      
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        memberVO.setFile1(file1); // ���۵� ���ϸ� ����
        memberVO.setSize1(file1MF.getSize()); //���� ������ ����
        //Thumb���� ������ ������.
      }
      memberVO.setFile1(file1);
      
    if (memberDAO.create(memberVO) == 1) {
      
      msgs.add("ȸ�������� ó���Ǿ����ϴ�. ����Ű ó�� �ؾ���!!!");
      msgs.add("�������ּż� �����մϴ�.");
      
      //����Ű �̸��� ����
      class MyAuthentication extends Authenticator {
        PasswordAuthentication pa;
        public MyAuthentication(){
          //���� ���̵�� ���!!!
          pa = new PasswordAuthentication("test@nulunggi.pe.kr", "Java2015!");
        }
       
        public PasswordAuthentication getPasswordAuthentication() {
          return pa;
        }
      }
       
      String subject = "Neighboring �α��� �����ڵ� �����Դϴ�.";  // ����
      String content = "�����ڵ��Դϴ�.<br><br>";  // ����
      content += "�Ʒ��� ��ϵ� �ڵ带 �α��� �� 1ȸ�� �����Ͻø� �˴ϴ�.<br><br>";
       
      content += "���� �ڵ�: " + memberVO.getAuth();
       
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(����������)     
      String from = "testcell2013@gmail.com";   // ������ �ּ�, ��α� ������ �ּ�
      String to = memberVO.getEmail();    // �޴� ���
       
      Properties props = new Properties();  // SMTP �������� ���, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
       
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // ���� ���� �˻�
       
      try {
        Message msg = new MimeMessage(sess);   // ���� ���� ��ü ����
        msg.setFrom(new InternetAddress(from));   // ������ ��� ����
              
        // �Ѹ��Ը� ����
        InternetAddress[] address = {new InternetAddress(to)}; // �޴� ��� ����
        
        msg.setRecipients(Message.RecipientType.TO, address); // ������ �ּ� ����
              
        msg.setSubject(subject);                  // ���� ���� 
        msg.setSentDate(new Date());          // ���� ��¥ ����
              
        // msg.setText(msgText); // ���� �������� ���ڸ� ���� ���
       
        // ������ �������� HTML �������� ���� ���
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // ���� ����
       
        msgs.add("<u>���� ������ �߼۵Ǿ���ϴ�.</u><br>");
        msgs.add("<u>���� �α��� �� �ѹ��� �����Ͻø� ���������� ��� �����մϴ�.</u><br><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "�Կ��� ���� �߼��� ���� �߽��ϴ�.");
      }
      /////////////////////////////////////////����Ű �������� ��////////////////////////////////////////////
      
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ���Կ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
  }else{
    msgs.add("������ ���� ũ�Ⱑ 10MB�� �ʰ��Ͽ����ϴ�.");
    msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
    links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
  }
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do'\">���</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * �ߺ� �̸��� �˻�
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail(String email, Locale locale, Model model) {
 
    JSONObject obj = new JSONObject();
 
    int cnt = memberDAO.checkEmail(email);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * ��ü ����� ����մϴ�.
   * @return
   */
  /*@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    String root = request.getContextPath();
    
    //������ ���� �˻�
    String master = (String)session.getAttribute("master");
    Tool.checkNull(master);
    
    if("M".equals(master)){ //������ (M)�� ��� ȸ�� ������� ����.
      mav.setViewName("/member/list"); // /member/list.jsp
      mav.addObject("list", memberDAO.list());
    }else{//�Ϲ�ȸ��(N)�� ���
      msgs.add("�˼������� �����ڰ� �ƴմϴ�..");
      links.add("<button type='button' onclick=\"location.href='"+root+"/index.jsp'\">Ȩ</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      mav.setViewName("/member/message");
    }
    
    return mav;
  }*/
  
  /**
   * �˻� ���
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session, 
                                    SearchDTO searchDTO,
                                    HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    int totalRecord = 0; //�˻� ����
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    String root = request.getContextPath();
    
//    System.out.println("col:"+searchDTO.getCol());
//    System.out.println("word:"+searchDTO.getWord());
    
    //HashMap hashMap = new HashMap(); //HashMap�� ������ �پ��� �� ����ϴ� ����̴�!!!
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());  
    hashMap.put("word", searchDTO.getWord()); 
    
    //������ ���� �˻�
    String master = (String)session.getAttribute("master");
    Tool.checkNull(master);
    
    //����¡ �κ�
    int recordPerPage = 10; //�������� ����� ���ڵ� ����
    int beginOfPage = (searchDTO.getNowPage()-1)*10; //nowPage�� 0���� ����
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // �����: 1
    int endNum = beginOfPage + recordPerPage; //�����: 10
    hashMap.put("startNum",startNum);
    hashMap.put("endNum",endNum);
    
    if("M".equals(master)){ //������ (M)�� ��� ȸ�� ������� ����.
      
      mav.setViewName("/member/list"); // /member/list.jsp
      mav.addObject("list", memberDAO.list(hashMap));
    }else{//�Ϲ�ȸ��(N)�� ���
      msgs.add("�˼������� �����ڰ� �ƴմϴ�..");
      links.add("<button class='btn btn-primary'  type='button' onclick=\"location.href='"+root+"/index.jsp'\">Ȩ</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      mav.setViewName("/member/message");
    }
    
    totalRecord = memberDAO.count(hashMap);
    mav.addObject("totalRecord", memberDAO.count(hashMap)); // �˻��� ���ڵ� ����
    
    String paging = new Paging().paging1(totalRecord, searchDTO.getNowPage(), recordPerPage,
                                                      searchDTO.getCol(), searchDTO.getWord());
    
    mav.addObject("paging", paging);
     
    return mav;
  }
  
  /**
   * �α���
   * @return
   */
  @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp
    
    return mav;
  }
 
  @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
  public ModelAndView login(MemberVO memberVO, 
                                       HttpSession session, 
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.login(memberVO) == 1) {
      
      String confirm = (memberDAO.readByEmail(memberVO.getEmail())).getConfirm();
      //���� Ȯ�� �κ�
      if(confirm.equals("Y")){ //���� ������
      //session ó��
        session.setAttribute("email", memberVO.getEmail());
        session.setAttribute("passwd", memberVO.getPasswd());
        session.setAttribute("master", (memberDAO.readByEmail(memberVO.getEmail()).getMaster()));
        session.setAttribute("mno", (memberDAO.readByEmail(memberVO.getEmail()).getMno()));
      //���ϸ��� ����
      memberDAO.mileage(memberVO.getEmail());
      
      // ------------------------------------------------------------------
      // email ���� ���� ��Ű ����
      // ------------------------------------------------------------------
      String email_save = Tool.checkNull(memberVO.getEmail_save());
      if (email_save.equals("Y")){ // email ���� �� ���
        Cookie ck_email = new Cookie("ck_email", memberVO.getEmail()); // id ����
        ck_email.setMaxAge(60000); // ��
        response.addCookie(ck_email);
      }else{ // email�� �������� ���� ���
        Cookie ck_email = new Cookie("ck_email", ""); 
        ck_email.setMaxAge(0); // ��
        response.addCookie(ck_email);
      }
      // email ���� ���θ� �����ϴ� ��� ���, Y or "" ����
      Cookie ck_email_save = new Cookie("ck_email_save", memberVO.getEmail_save());
      ck_email_save.setMaxAge(60000); // ��
      response.addCookie(ck_email_save);
      // ------------------------------------------------------------------
   
      // ------------------------------------------------------------------
      // passwd ���� ���� ��Ű ����
      // ------------------------------------------------------------------
      String passwd_save = Tool.checkNull(memberVO.getPasswd_save());
      if (passwd_save.equals("Y")){ 
        Cookie ck_passwd = new Cookie("ck_passwd", memberVO.getPasswd()); 
        ck_passwd.setMaxAge(60000); // ��
        response.addCookie(ck_passwd);
        
      }else{ // passwd�� �������� ���� ���
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0); // ��
        response.addCookie(ck_passwd);
        
      }
      // passwd ���� ���θ� �����ϴ� ��� ���, Y or "" ����
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", memberVO.getPasswd_save());
      ck_passwd_save.setMaxAge(60000); // ��
      response.addCookie(ck_passwd_save);
      // ------------------------------------------------------------------
 
      String url_address = Tool.checkNull(memberVO.getUrl_address());
      if (url_address.length() > 0){
        mav.setViewName("redirect:" + memberVO.getUrl_address());
      }else{
        System.out.println("--> index.jsp �������� �̵��մϴ�.");
        mav.setViewName("redirect:/index.jsp"); // Ȯ���� ���
      }
      //���� Ȯ�� ���κ�
     }else{ //���� �� �� ��� confirm.jsp�� �̵��Ͽ� ���� ���� �ǽ�
       mav.setViewName("/member/confirm");
     }
      
    } else {
      mav.setViewName("/member/message");
      msgs.add("�α��ο� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button class='btn btn-primary' Stype='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    msgs.add("�̿����ּż� �����մϴ�.");
    msgs.add("from �մ���...");
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    
    session.invalidate(); // session ���� ����
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }  
 
  /**
   * ���� ��Ʈ��
   * @param memberVO
   * @return
   */
 @RequestMapping(value = "/member/confirm.do", method = RequestMethod.POST)
  public ModelAndView confirm(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.confirm(memberVO) == 1) {
      mav.setViewName("/member/message");
      msgs.add("������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �α������ּ���.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./login.do'\">�α���</button>");
    }else{
      mav.setViewName("/member/message");
      msgs.add("������ �����߽��ϴ�.");
      msgs.add("�˼������� ������ȣ�� �̸����� Ȯ���Ͻð� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
 /**
  * ��ȸ
  * @param mno
  * @return
  */
   @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
   public ModelAndView read(int mno,
                                       HttpSession session,
                                       SearchDTO searchDTO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/member/read"); // /member/read.jsp
     mav.addObject("memberVO", memberDAO.read(mno));
     
     //������ �κ�
     String master = (String)session.getAttribute("master");
     mav.addObject("master", master);
     
     //�˻� ���� �κ�
     mav.addObject("searchDTO", searchDTO);
     
     
   return mav;
 }
   
   /**
    * ����
    * @param mno
    * @return
    */
   @RequestMapping(value = "/member/update.do", method = RequestMethod.GET)
   public ModelAndView update(int mno, 
                                           HttpSession session,
                                           SearchDTO searchDTO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/member/update");
     mav.addObject("memberVO", memberDAO.read(mno));
     
     //������ ���ο�
     String master = (String)session.getAttribute("master");
     mav.addObject("master", master);
     
     //�˻� ���� �κ�
     mav.addObject("searchDTO", searchDTO);
     
     return mav;
   }
   
   @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
   public ModelAndView update(MemberVO memberVO,
                                           HttpServletRequest request,
                                           SearchDTO searchDTO) {
     ModelAndView mav = new ModelAndView();

     ArrayList<String> msgs = new ArrayList<String>();
     ArrayList<String> links = new ArrayList<String>();

     // -------------------------------------------------------------------
     // ���� ����
     // -------------------------------------------------------------------
     String file1 = "";

     String upDir = Tool.getRealPath(request, "/member/storage");
     MultipartFile file1MF = memberVO.getFile1MF();
  
     MemberVO oldVO = memberDAO.read(memberVO.getMno());

     if (file1MF.getSize() > 0) { // ���ϵ�� ��
       Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
       file1 = Upload.saveFileSpring(file1MF, upDir);
       memberVO.setFile1(file1); // ���۵� ���ϸ� ����
       memberVO.setSize1(file1MF.getSize());

     } else if(oldVO.getFile1()==null){//���� �� ����̸鼭 �⺻ ������ ���� ���
       memberVO.setFile1(" ");
       memberVO.setSize1(0);
       
     }else{//���� �̵���̸鼭 �⺻ ������ �ִ� ���
       file1 = oldVO.getFile1();
       long size1 = oldVO.getSize1();
       memberVO.setFile1(file1);
       memberVO.setSize1(size1);
     }  
     System.out.println("���ϸ�:"+memberVO.getFile1()); //���� Ȯ�ο�
     // -------------------------------------------------------------------
     
     //���� �ڵ� ������ ���� �ٽ��ѹ� update
     System.out.println("�����ҷ��� �ּ�: " + memberVO.getmAddress());
     LocalVO localVO = localDAO.read(memberVO.getmAddress());
     memberVO.setLocal_code(localVO.getLocal_code());
     
     if (memberDAO.update(memberVO) == 1) {
              
       // ������ ��ȸ�� �ڵ� �̵�
       mav.setViewName("redirect:/member/read.do?mno=" + memberVO.getMno()+"&col="+searchDTO.getCol()
                                 +"&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()); // Ȯ���� ���
     } else {
       msgs.add("���� ������ ���� �ϼ̽��ϴ�.");
       links
           .add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
       links
           .add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno=" 
                   + memberVO.getMno() + "'\">������</button>");
       mav.addObject("msgs", msgs);
       mav.addObject("links", links);
     }

     //�˻� ���� �κ�
     mav.addObject("searchDTO", searchDTO);
     
     return mav;
   }
   
   /**
    * ���� ����
    * @param mno
    * @return
    */
  @RequestMapping(value = "/member/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/delete"); // /webapp/member/delete.jsp
    mav.addObject("mno", mno);

    MemberVO memberVO = memberDAO.read(mno);
    mav.addObject("memberVO", memberVO);
    
    //�˻� ���� �κ�
    mav.addObject("searchDTO", searchDTO);

    return mav;
  }

  @RequestMapping(value = "/member/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(MemberVO memberVO,
                                        HttpServletRequest request,
                                        HttpSession session,
                                        SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
    int totalRecord = 0; //�˻� ����
    String eamil=(String)session.getAttribute("email"); //������ �ڽ� ���� ���θ� ���� ����
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String upDir = Tool.getRealPath(request, "/member/storage");
    MemberVO oldVO = memberDAO.read(memberVO.getMno());

    if (memberDAO.delete(memberVO.getMno()) == 1) {
      Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
      
      //HashMap hashMap = new HashMap(); //HashMap�� ������ �پ��� �� ����ϴ� ����̴�!!!
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("col", searchDTO.getCol());  
      hashMap.put("word", searchDTO.getWord()); 

      totalRecord = memberDAO.count(hashMap);
      int nowPage = searchDTO.getNowPage(); 
      
      //������ ������ �ڷḦ ������ �˻� ���� �ֽ�ȭ ��Ų��!!!
      System.out.println("����� �� �� ���ڵ� ����: "+totalRecord);
      System.out.println("����� �� �� nowPage: "+nowPage);
      if(totalRecord%10 == 0){
        searchDTO.setNowPage(searchDTO.getNowPage()-1);
        if(nowPage == 0 || nowPage == 1){ //���� �������̳� �˻� �� ������ ������ ����Ǵ� �κ�!!!
          searchDTO.setNowPage(1);
        }
      }
      
      //������ ���� �˻�
      String master = (String)session.getAttribute("master");
      if(master.equals("M")){
        mav.setViewName("redirect:/member/list.do?col="+searchDTO.getCol()+
                                  "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage());// �������� ��� ȸ�� ������� �̵�
        if(memberDAO.delete_master(eamil)==0){//������ �ڽ��� ������ ���
          session.invalidate();
        }
      }else{
        mav.setViewName("redirect:/index.jsp");// �Ϲ� ������ ��� �ε����� �̵�
        session.invalidate(); // session ���� ����
      }
      
    } else {
      msgs.add("���� ������ �����߽��ϴ�.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno=" + memberVO.getMno() + "'\">������</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    //�˻� ���� �κ�
    mav.addObject("searchDTO", searchDTO);

    return mav;
  }
  
  /**
   * �н����� ����
   * @param mno
   * @return
   */
  @RequestMapping(value = "/member/passwd.do", method = RequestMethod.GET)
  public ModelAndView passwd_ch(int mno, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd"); // /webapp/member/passwd.jsp
 
    mav.addObject("mno", mno);
    
    //�˻� ���� �κ�
    mav.addObject("searchDTO", searchDTO);
    
    return mav;
  }
  
  @RequestMapping(value = "/member/passwd.do", method = RequestMethod.POST)
  public ModelAndView passwd_ch(MemberVO memberVO, 
                                                HttpSession session,
                                                SearchDTO searchDTO) {
   // System.out.println("���� �н�����: " + memberVO.getOld_passwd());
   // System.out.println("���ο� �н�����: " + memberVO.getPasswd());
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    //������ ������ ���� ���� ����
    String master = (String)session.getAttribute("master");
    
    // ���� �н����� ��ġ ���� �˻�
    if (memberDAO.passwdCheck(memberVO) == 1){
      // �н����� ���� ����
      if (memberDAO.passwd_update(memberVO) == 1) {
        msgs.add("�н����带 �����߽��ϴ�.");
        
        //������ ���� �˻�
        if(master.equals("M")){
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">ȸ�� ���</button>");
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
        }else{
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
        }
      } else {
        msgs.add("�н����� ���濡 �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
        links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
        
        //�����ڿ� �Ϲ�ȸ�� 
        if(master.equals("M")){
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">ȸ�� ���</button>");
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
        }else{
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
        }
      }//�н����� ���� ��
      
    }else{//�н����� ��ġ ���� �˻� ��
      msgs.add("���� �н����尡 ��ġ�����ʽ��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      
      //�����ڿ� �Ϲ�ȸ�� 
      if(master.equals("M")){
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">ȸ�� ���</button>");
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      }else{
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      }
    }
 
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno="+memberVO.getMno()+"&col="+searchDTO.getCol()+
                 "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">������</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    //�˻� ���� �κ�
    mav.addObject("searchDTO", searchDTO);
 
    return mav;
  }
  
  
   
}
