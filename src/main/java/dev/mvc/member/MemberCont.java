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
   * 기본 홈 이동
   */
  @RequestMapping(value = "/member/home.do", method = RequestMethod.GET)
  public ModelAndView home(){
    ModelAndView mav = new ModelAndView();
        
    mav.setViewName("redirect:/index.jsp");
    
    return mav;
  }
  /**
   * 계정 생성
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
    System.out.println("인증키 확인:"+memberVO.getAuth());
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    //지역 코드
    System.out.println("메인 지역: "+memberVO.gettAddress());
    System.out.println("부수 지역: "+memberVO.getmAddress());
    LocalVO localVO = localDAO.read(memberVO.getmAddress());
    
    System.out.println("출력값: "+localVO.getLocal_code());
    memberVO.setLocal_code(localVO.getLocal_code());
    
    //관리자 등록 가능 여부 확인
    if(memberDAO.checkMaster(memberVO.getMaster())<2){
      memberVO.setMaster("M");
    }else{
      memberVO.setMaster("N");
    }
    
    // -------------------------------------------------------------------
    // 파일 전송
    // -------------------------------------------------------------------
    String file1 = "";
    String upDir = Tool.getRealPath(request, "/member/storage");
    MultipartFile file1MF = memberVO.getFile1MF();
    
    //파일 크기 10MB 제한 설정
    if(file1MF.getSize()<10485760){
      
      if (file1MF.getSize() > 0) {
        file1 = Upload.saveFileSpring(file1MF, upDir);
        memberVO.setFile1(file1); // 전송된 파일명 저장
        memberVO.setSize1(file1MF.getSize()); //파일 사이즈 저장
        //Thumb파일 생성은 생략함.
      }
      memberVO.setFile1(file1);
      
    if (memberDAO.create(memberVO) == 1) {
      
      msgs.add("회원가입이 처리되었습니다. 인증키 처리 해야함!!!");
      msgs.add("가입해주셔서 감사합니다.");
      
      //인증키 이메일 전송
      class MyAuthentication extends Authenticator {
        PasswordAuthentication pa;
        public MyAuthentication(){
          //서버 아이디와 비번!!!
          pa = new PasswordAuthentication("test@nulunggi.pe.kr", "Java2015!");
        }
       
        public PasswordAuthentication getPasswordAuthentication() {
          return pa;
        }
      }
       
      String subject = "Neighboring 로그인 인증코드 메일입니다.";  // 제목
      String content = "인증코드입니다.<br><br>";  // 내용
      content += "아래에 등록된 코드를 로그인 시 1회만 인증하시면 됩니다.<br><br>";
       
      content += "인증 코드: " + memberVO.getAuth();
       
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(서버관리자)     
      String from = "testcell2013@gmail.com";   // 보내는 주소, 블로그 관리자 주소
      String to = memberVO.getEmail();    // 받는 사람
       
      Properties props = new Properties();  // SMTP 프로토콜 사용, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
       
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // 계정 인증 검사
       
      try {
        Message msg = new MimeMessage(sess);   // 메일 내용 객체 생성
        msg.setFrom(new InternetAddress(from));   // 보내는 사람 설정
              
        // 한명에게만 보냄
        InternetAddress[] address = {new InternetAddress(to)}; // 받는 사람 설정
        
        msg.setRecipients(Message.RecipientType.TO, address); // 수령인 주소 설정
              
        msg.setSubject(subject);                  // 제목 설정 
        msg.setSentDate(new Date());          // 보낸 날짜 설정
              
        // msg.setText(msgText); // 메일 내용으로 문자만 보낼 경우
       
        // 보내는 내용으로 HTML 형식으로 보낼 경우
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // 메일 전송
       
        msgs.add("<u>인증 메일이 발송되어습니다.</u><br>");
        msgs.add("<u>최초 로그인 시 한번만 인증하시면 정상적으로 사용 가능합니다.</u><br><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "님에게 메일 발송을 실패 했습니다.");
      }
      /////////////////////////////////////////인증키 메일전송 끝////////////////////////////////////////////
      
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./login.do'\">로그인</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    } else {
      msgs.add("회원 가입에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
  }else{
    msgs.add("프로필 사진 크기가 10MB를 초과하였습니다.");
    msgs.add("죄송하지만 다시한번 시도해주세요.");
    links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
  }
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do'\">목록</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * 중복 이메일 검사
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
   * 전체 목록을 출력합니다.
   * @return
   */
  /*@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    String root = request.getContextPath();
    
    //관리자 권한 검사
    String master = (String)session.getAttribute("master");
    Tool.checkNull(master);
    
    if("M".equals(master)){ //관리자 (M)일 경우 회원 목록으로 간다.
      mav.setViewName("/member/list"); // /member/list.jsp
      mav.addObject("list", memberDAO.list());
    }else{//일반회원(N)일 경우
      msgs.add("죄송하지만 관리자가 아닙니다..");
      links.add("<button type='button' onclick=\"location.href='"+root+"/index.jsp'\">홈</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      mav.setViewName("/member/message");
    }
    
    return mav;
  }*/
  
  /**
   * 검색 목록
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session, 
                                    SearchDTO searchDTO,
                                    HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    int totalRecord = 0; //검색 갯수
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    String root = request.getContextPath();
    
//    System.out.println("col:"+searchDTO.getCol());
//    System.out.println("word:"+searchDTO.getWord());
    
    //HashMap hashMap = new HashMap(); //HashMap은 값들이 다양할 때 사용하는 방법이다!!!
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());  
    hashMap.put("word", searchDTO.getWord()); 
    
    //관리자 권한 검사
    String master = (String)session.getAttribute("master");
    Tool.checkNull(master);
    
    //페이징 부분
    int recordPerPage = 10; //페이지당 출력할 레코드 갯수
    int beginOfPage = (searchDTO.getNowPage()-1)*10; //nowPage는 0부터 시작
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // 결과값: 1
    int endNum = beginOfPage + recordPerPage; //결과값: 10
    hashMap.put("startNum",startNum);
    hashMap.put("endNum",endNum);
    
    if("M".equals(master)){ //관리자 (M)일 경우 회원 목록으로 간다.
      
      mav.setViewName("/member/list"); // /member/list.jsp
      mav.addObject("list", memberDAO.list(hashMap));
    }else{//일반회원(N)일 경우
      msgs.add("죄송하지만 관리자가 아닙니다..");
      links.add("<button class='btn btn-primary'  type='button' onclick=\"location.href='"+root+"/index.jsp'\">홈</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      mav.setViewName("/member/message");
    }
    
    totalRecord = memberDAO.count(hashMap);
    mav.addObject("totalRecord", memberDAO.count(hashMap)); // 검색된 레코드 갯수
    
    String paging = new Paging().paging1(totalRecord, searchDTO.getNowPage(), recordPerPage,
                                                      searchDTO.getCol(), searchDTO.getWord());
    
    mav.addObject("paging", paging);
     
    return mav;
  }
  
  /**
   * 로그인
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
      //인증 확인 부분
      if(confirm.equals("Y")){ //인증 성공시
      //session 처리
        session.setAttribute("email", memberVO.getEmail());
        session.setAttribute("passwd", memberVO.getPasswd());
        session.setAttribute("master", (memberDAO.readByEmail(memberVO.getEmail()).getMaster()));
        session.setAttribute("mno", (memberDAO.readByEmail(memberVO.getEmail()).getMno()));
      //마일리지 적립
      memberDAO.mileage(memberVO.getEmail());
      
      // ------------------------------------------------------------------
      // email 저장 관련 쿠키 저장
      // ------------------------------------------------------------------
      String email_save = Tool.checkNull(memberVO.getEmail_save());
      if (email_save.equals("Y")){ // email 저장 할 경우
        Cookie ck_email = new Cookie("ck_email", memberVO.getEmail()); // id 저장
        ck_email.setMaxAge(60000); // 초
        response.addCookie(ck_email);
      }else{ // email를 저장하지 않을 경우
        Cookie ck_email = new Cookie("ck_email", ""); 
        ck_email.setMaxAge(0); // 초
        response.addCookie(ck_email);
      }
      // email 저장 여부를 결정하는 쿠기 기록, Y or "" 저장
      Cookie ck_email_save = new Cookie("ck_email_save", memberVO.getEmail_save());
      ck_email_save.setMaxAge(60000); // 초
      response.addCookie(ck_email_save);
      // ------------------------------------------------------------------
   
      // ------------------------------------------------------------------
      // passwd 저장 관련 쿠키 저장
      // ------------------------------------------------------------------
      String passwd_save = Tool.checkNull(memberVO.getPasswd_save());
      if (passwd_save.equals("Y")){ 
        Cookie ck_passwd = new Cookie("ck_passwd", memberVO.getPasswd()); 
        ck_passwd.setMaxAge(60000); // 초
        response.addCookie(ck_passwd);
        
      }else{ // passwd를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0); // 초
        response.addCookie(ck_passwd);
        
      }
      // passwd 저장 여부를 결정하는 쿠기 기록, Y or "" 저장
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", memberVO.getPasswd_save());
      ck_passwd_save.setMaxAge(60000); // 초
      response.addCookie(ck_passwd_save);
      // ------------------------------------------------------------------
 
      String url_address = Tool.checkNull(memberVO.getUrl_address());
      if (url_address.length() > 0){
        mav.setViewName("redirect:" + memberVO.getUrl_address());
      }else{
        System.out.println("--> index.jsp 페이지로 이동합니다.");
        mav.setViewName("redirect:/index.jsp"); // 확장자 명시
      }
      //인증 확인 끝부분
     }else{ //인증 안 한 경우 confirm.jsp로 이동하여 인증 절차 실시
       mav.setViewName("/member/confirm");
     }
      
    } else {
      mav.setViewName("/member/message");
      msgs.add("로그인에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button class='btn btn-primary' Stype='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
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
    
    msgs.add("이용해주셔서 감사합니다.");
    msgs.add("from 왕눈이...");
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    
    session.invalidate(); // session 변수 삭제
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }  
 
  /**
   * 인증 컨트롤
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
      msgs.add("인증에 성공했습니다.");
      msgs.add("죄송하지만 다시한번 로그인해주세요.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./login.do'\">로그인</button>");
    }else{
      mav.setViewName("/member/message");
      msgs.add("인증에 실패했습니다.");
      msgs.add("죄송하지만 인증번호와 이메일을 확인하시고 다시한번 시도해주세요.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
 /**
  * 조회
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
     
     //관리자 부분
     String master = (String)session.getAttribute("master");
     mav.addObject("master", master);
     
     //검색 관련 부분
     mav.addObject("searchDTO", searchDTO);
     
     
   return mav;
 }
   
   /**
    * 수정
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
     
     //관리자 여부용
     String master = (String)session.getAttribute("master");
     mav.addObject("master", master);
     
     //검색 관련 부분
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
     // 파일 전송
     // -------------------------------------------------------------------
     String file1 = "";

     String upDir = Tool.getRealPath(request, "/member/storage");
     MultipartFile file1MF = memberVO.getFile1MF();
  
     MemberVO oldVO = memberDAO.read(memberVO.getMno());

     if (file1MF.getSize() > 0) { // 파일등록 시
       Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
       file1 = Upload.saveFileSpring(file1MF, upDir);
       memberVO.setFile1(file1); // 전송된 파일명 저장
       memberVO.setSize1(file1MF.getSize());

     } else if(oldVO.getFile1()==null){//파일 미 등록이면서 기본 파일이 없는 경우
       memberVO.setFile1(" ");
       memberVO.setSize1(0);
       
     }else{//파일 미등록이면서 기본 파일이 있는 경우
       file1 = oldVO.getFile1();
       long size1 = oldVO.getSize1();
       memberVO.setFile1(file1);
       memberVO.setSize1(size1);
     }  
     System.out.println("파일명:"+memberVO.getFile1()); //파일 확인용
     // -------------------------------------------------------------------
     
     //지역 코드 수정을 위해 다시한번 update
     System.out.println("변경할려는 주소: " + memberVO.getmAddress());
     LocalVO localVO = localDAO.read(memberVO.getmAddress());
     memberVO.setLocal_code(localVO.getLocal_code());
     
     if (memberDAO.update(memberVO) == 1) {
              
       // 수정후 조회로 자동 이동
       mav.setViewName("redirect:/member/read.do?mno=" + memberVO.getMno()+"&col="+searchDTO.getCol()
                                 +"&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()); // 확장자 명시
     } else {
       msgs.add("정보 수정에 실패 하셨습니다.");
       links
           .add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시 시도</button>");
       links
           .add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno=" 
                   + memberVO.getMno() + "'\">프로필</button>");
       mav.addObject("msgs", msgs);
       mav.addObject("links", links);
     }

     //검색 관련 부분
     mav.addObject("searchDTO", searchDTO);
     
     return mav;
   }
   
   /**
    * 계정 삭제
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
    
    //검색 관련 부분
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
    int totalRecord = 0; //검색 갯수
    String eamil=(String)session.getAttribute("email"); //관리자 자신 삭제 여부를 위한 변수
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String upDir = Tool.getRealPath(request, "/member/storage");
    MemberVO oldVO = memberDAO.read(memberVO.getMno());

    if (memberDAO.delete(memberVO.getMno()) == 1) {
      Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
      
      //HashMap hashMap = new HashMap(); //HashMap은 값들이 다양할 때 사용하는 방법이다!!!
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("col", searchDTO.getCol());  
      hashMap.put("word", searchDTO.getWord()); 

      totalRecord = memberDAO.count(hashMap);
      int nowPage = searchDTO.getNowPage(); 
      
      //마지막 페이지 자료를 삭제시 검색 수를 최신화 시킨다!!!
      System.out.println("지우고 난 후 레코드 갯수: "+totalRecord);
      System.out.println("지우고 난 후 nowPage: "+nowPage);
      if(totalRecord%10 == 0){
        searchDTO.setNowPage(searchDTO.getNowPage()-1);
        if(nowPage == 0 || nowPage == 1){ //최종 마지막이나 검색 후 내용을 삭제시 적용되는 부분!!!
          searchDTO.setNowPage(1);
        }
      }
      
      //관리자 권한 검사
      String master = (String)session.getAttribute("master");
      if(master.equals("M")){
        mav.setViewName("redirect:/member/list.do?col="+searchDTO.getCol()+
                                  "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage());// 관리자일 경우 회원 목록으로 이동
        if(memberDAO.delete_master(eamil)==0){//관리자 자신을 삭제한 경우
          session.invalidate();
        }
      }else{
        mav.setViewName("redirect:/index.jsp");// 일반 계정일 경우 인덱스로 이동
        session.invalidate(); // session 변수 삭제
      }
      
    } else {
      msgs.add("계정 삭제에 실패했습니다.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno=" + memberVO.getMno() + "'\">프로필</button>");
    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    //검색 관련 부분
    mav.addObject("searchDTO", searchDTO);

    return mav;
  }
  
  /**
   * 패스워드 변경
   * @param mno
   * @return
   */
  @RequestMapping(value = "/member/passwd.do", method = RequestMethod.GET)
  public ModelAndView passwd_ch(int mno, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd"); // /webapp/member/passwd.jsp
 
    mav.addObject("mno", mno);
    
    //검색 관련 부분
    mav.addObject("searchDTO", searchDTO);
    
    return mav;
  }
  
  @RequestMapping(value = "/member/passwd.do", method = RequestMethod.POST)
  public ModelAndView passwd_ch(MemberVO memberVO, 
                                                HttpSession session,
                                                SearchDTO searchDTO) {
   // System.out.println("기존 패스워드: " + memberVO.getOld_passwd());
   // System.out.println("새로운 패스워드: " + memberVO.getPasswd());
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    //관리자 권한을 위한 전역 변수
    String master = (String)session.getAttribute("master");
    
    // 현재 패스워드 일치 여부 검사
    if (memberDAO.passwdCheck(memberVO) == 1){
      // 패스워드 변경 실행
      if (memberDAO.passwd_update(memberVO) == 1) {
        msgs.add("패스워드를 변경했습니다.");
        
        //관리자 권한 검사
        if(master.equals("M")){
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">회원 목록</button>");
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
        }else{
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
        }
      } else {
        msgs.add("패스워드 변경에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요.");
        links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
        
        //관리자와 일반회원 
        if(master.equals("M")){
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">회원 목록</button>");
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
        }else{
          links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
        }
      }//패스워드 변경 끝
      
    }else{//패스워드 일치 여부 검사 끝
      msgs.add("현재 패스워드가 일치하지않습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button class='btn btn-primary' type='button' onclick=\"history.back()\">다시시도</button>");
      
      //관리자와 일반회원 
      if(master.equals("M")){
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./list.do?col="+searchDTO.getCol()+
                       "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">회원 목록</button>");
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      }else{
        links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
      }
    }
 
    links.add("<button class='btn btn-primary' type='button' onclick=\"location.href='./read.do?mno="+memberVO.getMno()+"&col="+searchDTO.getCol()+
                 "&word="+searchDTO.getWord()+"&nowPage="+searchDTO.getNowPage()+"'\">프로필</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    //검색 관련 부분
    mav.addObject("searchDTO", searchDTO);
 
    return mav;
  }
  
  
   
}
