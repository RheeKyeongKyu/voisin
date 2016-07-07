<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import='web.tool.*'%>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
 
<link href="../css/profile.css" rel="Stylesheet" type="text/css">
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="./jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
window.onload = function(){ // 무명 함수
  var file1 = document.getElementById("file1");
  if (file1 != null){
    if (file1.width > 270){
      file1.width = 270;
    }else if(file1.width < 270){
      file1.width = 270;
    }
  }
} 
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
 
 <div class="card">
  <FORM name='frm' method='GET' action=''>
  <input type='hidden' name='mno' value='${memberVO.mno }'> 
  <input type="hidden" name="col" value="${searchDTO.col}">
  <input type="hidden" name="word" value="${searchDTO.word}">        
  <input type="hidden" name="nowPage" value="${searchDTO.nowPage}"> 
  
  <div class="card-header">
    <c:set var='file1' value="${fn:toLowerCase(memberVO.file1)}" />
       <c:choose>
         <c:when test="${fn:endsWith(file1, '.jpg')}">
           <div style="text-align: center; margin: 10px 10px 0px 0px;">
            <IMG id='file1' src='./storage/${memberVO.file1}'>
           </div> 
         </c:when>
         <c:when test="${fn:endsWith(file1, '.gif')}">
           <div style="text-align: center; margin: 10px 10px 0px 0px;">
            <IMG id='file1' src='./storage/${memberVO.file1}'>
           </div> 
          </c:when>
          <c:when test="${fn:endsWith(file1, '.png')}">
           <div style="text-align: center; margin: 10px 10px 0px 0px;">
             <IMG id='file1' src='./storage/${memberVO.file1}'>
           </div> 
          </c:when>
          <c:otherwise>  
           <div style="text-align: center; margin: 10px 10px 0px 0px;">
              <IMG id='file1' src='./images/profile.png'>
           </div>
          </c:otherwise>
          </c:choose>          
  </div>
  
  <div class="card-content">
    <label class='title_ah' for='email'>Email: </label>
    <span class='content_ah' id='email'>${memberVO.email }</span><br>
     
    <label class='title_ah' for='name'>성명: </label>
    <span class='content_ah' id='name'>${memberVO.name }</span><br>
  
    <label class='title_ah' for='tel'>전화번호: </label>
    <span class='content_ah' id='tel'>${memberVO.tel }</span><br> 
   
    <label class='title_ah' for='hobby'>취미: </label>
    <span class='content_ah' id='hobby'>${memberVO.hobby }</span><br> 
 
    <label class='title_ah' for='birth'>생년월일: </label>
    <span class='content_ah' id='birth'>${memberVO.birth }</span><br> 
   
    <label class='title_ah' for='gen'>성별: </label>
    <span class='content_ah' id='gen'>${memberVO.gen }</span><br>  
    
    <label class='title_ah' for='push'>Push알람 설정: </label>
    <span class='content_ah' id='push'>${memberVO.push }</span><br>
      
    <!-- 관리자만 보는 곳 -->
    <c:if test="${master == 'M' }">
       
    <label class='title_ah' for='master'>관리자 여부: </label>
    <span class='content_ah' id='master'>${memberVO.master }</span><br>
    
    <label class='title_ah' for='local_code'>지역 코드: </label>
    <span class='content_ah' id='local_code'>${memberVO.local_code }</span><br>  
    </c:if>
    <!-- 관리자만 보는 곳 끝-->  
 
    <label class='title_ah' for='confirm'>인증 여부: </label>
    <span class='content_ah' id='confirm'>${memberVO.confirm }</span><br>
  
    <label class='title_ah' for='sound'>나의 소음: </label>
    <span class='content_ah' id='sound'>${memberVO.sound }</span><br>
 
    <label class='title_ah' for='mi'>마일리지: </label>
    <span class='content_ah' id='mi'>${memberVO.mi }</span><br>
  
    <label class='title_ah' for='zipcode'>우편번호: </label>
    <span class='content_ah' id='zipcode'>${memberVO.zipcode }</span><br> 
    
    <label class='title_ah' for='address'>주소: </label>
    <span class='content_ah' id='address'>${memberVO.mAddress } ${memberVO.bAddress }</span><br><br>   
     
    <div class="card-footer" style="text-align: center;">
        <button class="btn btn-primary" type="button" onclick="location.href='./update.do?mno=${memberVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'">수정</button> 
         <button class="btn btn-primary" type='button' onclick="location.href='./delete.do?mno=${memberVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'">삭제</button>
         <button class="btn btn-primary" type="button" onclick="location.href='./passwd.do?mno=${memberVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'">비번변경</button>
        <c:choose>
            <c:when test="${master == 'M' }"><!-- 관리자일 경우 -->
              <button class="btn btn-primary" type="button" onclick = "location.href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'">뒤로</button>
            </c:when>
            <c:otherwise><!-- 일반회원일 경우 -->
              <button class="btn btn-primary" type="button" onclick = "location.href='./home.do'">뒤로</button>
            </c:otherwise>          
        </c:choose>         
    </div>
        
  </div>

  </FORM> 
</div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 