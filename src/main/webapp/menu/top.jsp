<%@ page contentType="text/html; charset=UTF-8" %>

<%
String root = request.getContextPath();
// out.println("root: " + root);
%>

<!-- 화면 상단 메뉴 --> 
<DIV id='top_menu'
        style="background-size: 100% 100%; background-image: url('<%=root %>/menu/images/top_image.jpg'); " >
  <DIV class='top_menu_label'>Camping blog 0.7</DIV>
  <NAV class='top_menu_list'>
    <A class='menu_link' href='<%=root %>/index.jsp'>HOME</A> |
    <!-- 로그인 로그아웃 시작부분 -->
    <%
      if (session.getAttribute("email") == null){
      %>
        <a class='menu_link' href='<%=root %>/member/login.do'>로그인</a>|
        <a class='menu_link' href='<%=root %>/member/create.do'>가입하기</a>|
      <%
      }else{
      %>
        <a class='menu_link' href='<%=root %>/member/logout.do'>${email} 로그아웃</a>|
       <%
       String master = (String)session.getAttribute("master");
       int mno = (Integer)session.getAttribute("mno");
       if (master.equals("M")){
       %>  
        <a class='menu_link' href='<%=root %>/member/list.do'>회원 목록</a> |
      <% 
       }else{
       %>
        <a class='menu_link' href='<%=root %>/member/read.do?mno=<%=mno%>'>프로필</a> |
       <% 
      } 
       
      }
      %>
      
    <!-- 로그인 로그아웃 끝 부분 -->
    <!-- 수정필요 관리자 부분2016.6.26 -->
    
      
  </NAV>
</DIV>




