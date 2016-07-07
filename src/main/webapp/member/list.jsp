<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
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

<!-- 검색 부분 시작 -->
<form name="frmSearch" method="get" action="./list.do"> 
  <div class='content_menu' style='width: 90%;'>
    <A href='#'>회원 목록(관리자용)(${totalRecord })</A> ｜
    
    <select name="col"> 
      <option value="">선택</option> 
      <option value="email" ${searchDTO.col == "email" ? "selected=selected" : "" }>Email</option> 
      <option value="name" ${searchDTO.col == "name" ? "selected=selected" : "" }>이름</option>  
      <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체</option>
    </select>
    
    
    <c:choose>
      <c:when test="${searchDTO.col != 'total' }">
        <input type="text" name="word" size="15" value="${searchDTO.word }">
      </c:when>
      <c:when test="${searchDTO.col == 'total' }">
        <input type="text" name="word" size="15" value="">
      </c:when>
      
    </c:choose> 
     
    <input type="submit" value="검색">｜  
    <A href='./create.do'>등록</A>｜
    <A href="javascript:location.reload();">새로고침</A>
  </div>
  </form> 
<!-- 검색 부분 끝 -->
 
 <div class="container">
  <table class="table table-hover">
    <colgroup>
      <col style='width: 5%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    
     <col style='width: 10%;'/>
     <col style='width: 17%;'/>
    
     <col style='width: 6%;'/>
     <col style='width: 5%;'/>
     <col style='width: 7%;'/>
    
      <col style='width: 5%;'/>
      <col style='width: 10%;'/>
      <col style='width: 5%;'/>
    </colgroup>
    <thead>
      <tr>
        <th>No.</th>
        <th>Email</th>
        <th>성명</th>
    
        <th>전화번호</th>
        <th>주소</th>
    
        <th>Push</th>
        <th>인증</th>
        <th>관리자</th>
    
        <th>소음</th>
        <th>가입일</th>
        <th>설정</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach var="vo" items="${list }">
      <tr class="active">
        <TD>${vo.mno}</TD>
        <TD><A href="./read.do?mno=${vo.mno }&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">${vo.email }</A></TD>
        <TD><A href="./read.do?mno=${vo.mno }&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">${vo.name }</A></TD>
    
        <TD>${vo.tel }</TD>
        <TD>${vo.mAddress }</TD>
    
        <TD>${vo.push }</TD>
        <TD>${vo.confirm }</TD>
        <TD>${vo.master }</TD>
    
        <TD>${vo.sound }</TD>
        <TD>${vo.mdate.substring(0, 10) }</TD>    

        <TD>
          <A href="./passwd.do?mno=${vo.mno }&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
          <A href="./update.do?mno=${vo.mno }&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"><IMG src='./images/update.png' title='수정'></A>
          <A href="./delete.do?mno=${vo.mno }&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"><IMG src='./images/delete.png' title='삭제'></A>
        </TD>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>
 
 
<DIV class='bottom'>${paging}</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</body>
<!-- -------------------------------------------- -->
</html> 