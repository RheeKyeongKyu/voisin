<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="./css/style.css" rel="Stylesheet" type="text/css">
<style type="text/css">
  *{
     padding: 0px; margin: 0px;
   }
   
   .img{
     display: block; /* 이미지의 여백 삭제, 이미지 출력후 라인 변경 */
     width: 100%;
     height: 60px;  
     
   }
   
   TD{
     width: 10%;
     padding: 1px;
   }
</style>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->


<br>
테스트
<br>


<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
