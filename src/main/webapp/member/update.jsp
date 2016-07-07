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

function send(){ //라디오 박스 체크확인 
  
  if(!$(':input:radio[name=gen]:checked').val()) { //성별
    alert("성별을 선택해주세요.");
    return false; // 서브밋(전송) 중지
  }else if(!$(':input:radio[name=push]:checked').val()) { //push  
    alert("push를 선택해주세요.");
    return false; // 서브밋(전송) 중지
  }else if(!$(':input:radio[name=master]:checked').val() && $("M".equals(master))) {  //master  
    alert("관리자를 선택해주세요.");
    return false; // 서브밋(전송) 중지
  }else if(!$(':input:radio[name=confirm]:checked').val() && $("M".equals(master))) { // confirm 
    alert("인증여부를 선택해주세요.");
    return false; // 서브밋(전송) 중지
  }else{
    return true; // 서브밋(전송) 진행
  }
}

//주소 select설정
var mAddress0 = [{"text":"주소 2","value":"주소 2"}];

var mAddress1 = [{"text":"도봉구","value":"서울특별시 도봉구"},{"text":"노원구","value":"서울특별시 노원구"},{"text":"강북구","value":"서울특별시 강북구"},{"text":"중량구","value":"서울특별시 중량구"},
                      {"text":"성북구","value":"서울특별시 성북구"},{"text":"은평구","value":"서울특별시 은평구"},{"text":"종로구","value":"서울특별시 종로구"},{"text":"동대문구","value":"서울특별시 동대문구"},
                      {"text":"광진구","value":"서울특별시 광진구"},{"text":"성동구","value":"서울특별시 성동구"},{"text":"중구","value":"서울특별시 중구"}, {"text":"용산구","value":"서울특별시 용산구"},
                      {"text":"마포구","value":"서울특별시 마포구"},{"text":"서대문구","value":"서울특별시 서대문구"},{"text":"강서구","value":"서울특별시 강서구"},{"text":"영등포구","value":"서울특별시 영등포구"},
                      {"text":"구로구","value":"서울특별시 구로구"},{"text":"동작구","value":"서울특별시 동작구"},{"text":"금천구","value":"서울특별시 금천구"},{"text":"관악구","value":"서울특별시 관악구"},
                      {"text":"서초구","value":"서울특별시 서초구"},{"text":"강남구","value":"서울특별시 강남구"},{"text":"송파구","value":"서울특별시 송파구"},{"text":"강동구","value":"서울특별시 강동구"},
                      {"text":"양천구","value":"서울특별시 양천구"}];

var mAddress2 = [{"text":"가평군","value":"경기도 가평군"},{"text":"고양시","value":"경기도 고양시"},{"text":"과천시","value":"경기도 과천시"},{"text":"광명시","value":"경기도 광명시"},
                      {"text":"광주시","value":"경기도 광주시"},{"text":"구리시","value":"경기도 구리시"},{"text":"군포시","value":"경기도 군포시"},{"text":"김포시","value":"경기도 김포시"},
                      {"text":"남양주시","value":"경기도 남양주시"},{"text":"동두천시","value":"경기도 동두천시"},{"text":"부천시","value":"경기도 부천시"},{"text":"성남시","value":"경기도 성남시"},
                      {"text":"수원시","value":"경기도 수원시"},{"text":"시흥시","value":"경기도 시흥시"},{"text":"안산시","value":"경기도 안산시"},{"text":"안성시","value":"경기도 안성시"},
                      {"text":"안양시","value":"경기도 안양시"},{"text":"양주시","value":"경기도 양주시"},{"text":"양평군","value":"경기도 양평군"},{"text":"여주시","value":"경기도 여주시"},
                      {"text":"연천군","value":"경기도 연천군"},{"text":"오산시","value":"경기도 오산시"},{"text":"용인시","value":"경기도 용인시"},{"text":"의왕시","value":"경기도 의왕시"},
                      {"text":"의정부시","value":"경기도 의정부시"},{"text":"이천시","value":"경기도 이천시"},{"text":"파주시","value":"경기도 파주시"},{"text":"평택시","value":"경기도 평택시"},
                      {"text":"포천시","value":"경기도 포천시"},{"text":"하남시","value":"경기도 하남시"},{"text":"화성시","value":"경기도 화성시"}];

function ChangeMAddress(tAddress)
{
 var value = tAddress.value;
 var mAddressArray = eval("mAddress"+value);
//selectB 셀렉트 박스에 들어갈 변수를 가져올 수 있다.
//eval 은 변수이름으로 객체를 가져오는 용도로 사용.
 try
 {
  // DOM 방식을 사용하여 selectB 셀렉트 박스 객체를 얻어온다.
  // id를 사용해 가져오기 위해서 getElementById 메소드를 사용
  var oMAddress = document.getElementById("mAddress");
  
  //selectB 셀렉트 박스의 하위 엘리멘트를 삭제한다.
  while(0 < oMAddress.childNodes.length)
  {
   oMAddress.removeChild(oMAddress.firstChild);
  }

  var option = null;
  // 새로운 option을 selectB 셀렉트 박스에 추가해 준다.
  for (var index=0; index < mAddressArray.length ;index++ )
  {
   option = document.createElement("option");
   option.value = mAddressArray[index].value;
   // TextNode를 생성하여 option 객체의 하위 객체로 추가해 준다.
   option.appendChild(document.createTextNode(mAddressArray[index].text));
   //  selectB 셀렉트 박스에 option 객체를 추가한다.
   oMAddress.appendChild(option);
  }
 }
 catch (e)
 {
  alert(e.description);
 }
}
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
<%
String root=request.getContextPath();
%> 

<div class="card">
  <FORM name='frm' method='POST' action='./update.do' onsubmit="return send();"
           enctype="multipart/form-data">
  <input type='hidden' name='mno' value='${memberVO.mno }'>    
  <input type="hidden" name="col" value="${searchDTO.col}">
  <input type="hidden" name="word" value="${searchDTO.word}">        
  <input type="hidden" name="nowPage" value="${searchDTO.nowPage}"> 
  
  <div class="card-header">
    <div style="padding-top: 5px; padding-right: 18px; text-align: right;">
      <label  class="btn btn-primary" for='file1MF'>파일등록</label>
      <input class="sr-only" type="file" name='file1MF' id='file1MF' size='40' >
    </div>   
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
    <span class='content_ah' id='email'>${memberVO.email }(변경불가)</span><br>
    
    <label class='title_ah' for='name'>성명: </label>
    <input class='content_ah' type='text' name='name' id='name' value='${memberVO.name }' required="required"><br>
  
    <label class='title_ah' for='tel'>전화번호: </label>
    <input class='content_ah' type="tel" name='tel' id='tel' value='${memberVO.tel }'><br> 
   
    <label class='title_ah' for='hobby'>취미: </label>
    <input class='content_ah'  type="text" name='hobby' id='hobby' value='${memberVO.hobby }' size='15' placeholder="취미" required="required"><br> 
 
    <label class='title_ah' for='birth'>생년월일: </label>
    <input class='content_ah' type="text" name='birth' id='birth' value='${memberVO.birth }' size='20' placeholder="1900-00-00" required="required"><br> 
   
    <label class='title_ah' for='gen'>성별: </label>
    <input type="radio" name="gen" id='gen'  value="남"><span class='content_ah' >남</span>
    <input type="radio" name="gen" id='gen'  value="여"><span class='content_ah' >여</span>   
    <br>   
    <label class='title_ah' for='push'>Push알람 설정: </label>
    <input type="radio" name="push" id='push'  value="Y"><span class='content_ah' >동의</span>
    <input type="radio" name="push" id='push'  value="N"><span class='content_ah' >거절</span>
    <br>
    <!-- 관리자 여부 -->
    <c:choose>
      <c:when test="${master == 'M'}">
         <label class='title_ah' for='master'>관리자 여부: </label>
         <input type="radio" name="master" id='master'  value="N"><span class='content_ah' >일반 계정</span>
         <input type="radio" name="master" id='master'  value="M"><span class='content_ah' >관리자</span>
         <br>
         
         <label class='title_ah' for='confirm'>인증 여부: </label>
         <input type="radio" name="confirm" id='confirm'  value="Y"><span class='content_ah' >인증</span>
         <input type="radio" name="confirm" id='confirm'  value="N"><span class='content_ah' >불인증</span>
         <br>
         
         <label class='title_ah'  for='mi'>마일리지: </label>
         <input class='content_ah' type="number" name='mi' id='mi' value='${memberVO.mi }' required="required">
         <br>
          
      </c:when>
           
      <c:otherwise>  
        
         <input type="hidden" name="master" id='master'  value="N" >
         <input type="hidden" name="confirm" id='confirm'  value="Y" >
         <input type="hidden" name='mi' id='mi' value='${memberVO.mi }' >
            
      </c:otherwise>
    </c:choose>  
    <!-- 관리자 여부 끝 -->
    <div style="padding-top: 5px; padding-bottom: 5px;">         
    <label class='title_ah' for='zipcode'>우편번호: </label>
    <input class='content_ah' type='text' name='zipcode' id='zipcode' value='${memberVO.zipcode }' size='5' placeholder="우편번호">
    <input class="btn btn-primary" type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
    </div>
        <label class='label'></label>  
        <!-- ----- DAUM 우편번호 API 시작 ----- -->
 
          <div id="wrap" style="display:none;border:1px solid;width:300px;height:100px; margin:5px auto; position:relative">
            <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
          </div>
 
          <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
          <script>
            // 우편번호 찾기 찾기 화면을 넣을 element
            var element_wrap = document.getElementById('wrap');
 
            function foldDaumPostcode() {
              // iframe을 넣은 element를 안보이게 한다.
              element_wrap.style.display = 'none';
            }
 
            function DaumPostcode() {
              // 현재 scroll 위치를 저장해놓는다.
              var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
              new daum.Postcode({
                oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
 
                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                  }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                //document.getElementById('tAddress').value = fullAddr; //주소가 들어가는 부분
 
                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';
 
                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#mAddress').focus();
                },
                // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
                onresize : function(size) {
                  element_wrap.style.height = size.height+'px';
                },
                 width : '100%',
                 height : '100%'
              }).embed(element_wrap);
 
              // iframe을 넣은 element를 보이게 한다.
              element_wrap.style.display = 'block';
            }
      </script>
      <!-- ----- DAUM 우편번호 API 종료----- -->

    <label class='title_ah' for='tAddress'>주소: </label>
     <select name="tAddress" id="tAddress" onchange="ChangeMAddress(this);">
      <option value="0" selected = "selected">주소 1</option>
      <option value="1">서울특별시</option>
      <option value="2">경기도</option>
    </select>
    <select name="mAddress" id="mAddress">
      <option value="주소 2">주소 2</option>
    </select>
    
    <input class='content_ah' type='text' name='bAddress' id='bAddress' value='' size='30' placeholder="상세 주소(주소1과 주소2 필수 선택)" required="required">   
     
    <br>
    <div style="text-align: center; margin-top: 5px; padding-bottom: 5px;">
        <button class="btn btn-primary" type="submit">정보 수정</button>
        <button class="btn btn-primary" type='button' onclick="history.back();">뒤로가기</button>  
    </div>
   
        
  </div>

  </FORM> 
</div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 