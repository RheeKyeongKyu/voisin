<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.member.MemberVO" %>

<%
int mno = (Integer)request.getAttribute("mno");
%>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
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
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="./jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
function send(){
  // 패스워드 동일한지 검사
  if ($('#passwd').val() == $('#passwd2').val()){ 
    return true;
  }else{
    $('#panel_passwd').css('color', '#FF0000');
    $('#panel_passwd').html('패스워드가 일치하지 않습니다.');
    $('#passwd').focus();
    return false;      
  }
}
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
 <div class="container">

      <form class="form-signin" name='frm' method='POST' action='./passwd.do' onsubmit = 'return send();'>
       <input type='hidden' name='mno' value='<%=mno%>'> 
       <input type="hidden" name="col" value="${searchDTO.col}">
       <input type="hidden" name="word" value="${searchDTO.word}">        
       <input type="hidden" name="nowPage" value="${searchDTO.nowPage}">   
      
        <h2 class="form-signin-heading">비밀번호 변경</h2>
        
        <label for="email" class="sr-only">Email address</label>
     
        <label class="sr-only" for='old_passwd' style='width: 200px;'>현재 패스워드</label>
        <input class="form-control" type='password' name='old_passwd' id='old_passwd' value='' placeholder="현재 패스워드" required="required">
      
      
        <label class="sr-only" for='passwd' style='width: 200px;'>새로운 패스워드</label>
        <input class="form-control" type='password' name='passwd' id='passwd' value='' placeholder="새로운 패스워드" required="required">
        <span id='panel_passwd'></span>
     
        <label class="sr-only" for='passwd2' style='width: 200px;'>새로운 패스워드 확인</label>
        <input class="form-control" type='password' name='passwd2' id='passwd2' value='' placeholder="새로운 패스워드 확인" required="required">
       
         
        <button class="btn btn-lg btn-primary btn-block" type="submit">저장</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="history.back()">취소</button>
      </form>

    </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 