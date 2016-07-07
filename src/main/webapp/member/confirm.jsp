<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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
<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
  $(function(){ 
     
  });
</script>

</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<div class="container">

      <form class="form-signin" name='frm' method='POST' action='./confirm.do'>
      
      
        <h2 class="form-signin-heading">인증 페이지</h2>
        
        <label for="email" class="sr-only">Email address</label>
        <%-- <input class="form-control" type='email' name='email' id='email' value='<%=ck_email %>' autofocus autocomplete="off" required="required"> --%>
         <input class="form-control" type='email' name='email' id='email' placeholder="Email address" autofocus autocomplete="off" required="required">
          
        <label for="auth" class="sr-only">인증 코드</label>
        <input class="form-control" type='text' name='auth' id='auth' placeholder='인증 코드' required="required">
         
        <br>       
        <button class="btn btn-lg btn-primary btn-block" type="submit">인증</button>
        <button class="btn btn-lg btn-primary btn-block" type='button' onclick="history.back();">취소</button>
      </form>

    </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 