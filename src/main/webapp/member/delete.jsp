<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script type="text/javascript" src="../js/tool.js"></script>

<script type="text/javascript">

</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
  <h2>삭제 메시지</h2>
 <div class="alert alert-danger" role="alert">
  <FORM name='frm' method='POST' action='./delete.do'>
    <input type='hidden' name='mno' value='${mno}'> 
    <input type="hidden" name="col" value="${searchDTO.col}">
    <input type="hidden" name="word" value="${searchDTO.word}">        
    <input type="hidden" name="nowPage" value="${searchDTO.nowPage}"> 
      <ul style="text-align: center;">
        <li>
          <strong>계정을 삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.</strong>
        </li>
        <li class = 'right'>
          <button class="btn btn-primary" type = "submit">삭제</button>
          <button class="btn btn-danger" type = "button" onclick = "history.back()">취소</button>
         </li>
      </ul>
  </FORM>
 </div>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 

