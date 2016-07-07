<%@ page contentType="text/html; charset=UTF-8" %>
 
<%
String url_address = request.getParameter("url_address"); // 로그인 후 이동할 주소
 
Cookie[] cookies = request.getCookies();
Cookie cookie = null;
String ck_email = "";
String ck_email_save = "";
String ck_passwd = "";
String ck_passwd_save = "";
 
for (int i=0; i < cookies.length; i++){
  cookie = cookies[i];
  
  if (cookie.getName().equals("ck_email")){
    ck_email = cookie.getValue();         // test1
  }else if(cookie.getName().equals("ck_email_save")){
    ck_email_save = cookie.getValue();  // Y, N
  }else if (cookie.getName().equals("ck_passwd")){
    ck_passwd = cookie.getValue();         // test1@mail.com
  }else if(cookie.getName().equals("ck_passwd_save")){
    ck_passwd_save = cookie.getValue();  // Y, N
  }
}
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8">
<title>로그인</title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
 <!-- -------------------------------------------------------- -->
 
   <div class="container">

      <form class="form-signin" name='frm' method='POST' action='./login.do'>
      <input type='hidden' name='url_address' value='<%=url_address %>'>
      
        <h2 class="form-signin-heading">로그인 해주세요^^</h2>
        
        <label for="email" class="sr-only">Email address</label>
        <%-- <input class="form-control" type='email' name='email' id='email' value='<%=ck_email %>' autofocus autocomplete="off" required="required"> --%>
         <input class="form-control" type='email' name='email' id='email' value='test13@mail.com' autofocus autocomplete="off" required="required">
          
        <label for="passwd" class="sr-only">Password</label>
        <%-- <input class="form-control"  type='password' name='passwd' id='passwd' value='<%=ck_passwd %>'  autocomplete="off" required="required"> --%>
        <input class="form-control"  type='password' name='passwd' id='passwd' value='1234'  autocomplete="off" required="required">
          <%
          if (ck_email_save.equals("Y")){
          %>
            <input type='checkbox' name='email_save' value='Y' checked="checked"> Email 저장
          <%
          }else{
          %>
            <input type='checkbox' name='email_save' value='Y'> Email 저장 
          <%  
          }
         
          if (ck_passwd_save.equals("Y")){
          %>
            <input type='checkbox' name='passwd_save' value='Y' checked="checked"> password 저장
          <%
          }else{
          %>
            <input type='checkbox' name='passwd_save' value='Y'> password 저장
          <%  
          }
          %>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>

    </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
    
<!-- -------------------------------------------------------- -->
 
<!-- -------------------------------------------- -->

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 