<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import='web.tool.*'%>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>가입</title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- font awesome -->
<link rel="stylesheet" href="../css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
<!-- Custom style -->
<link rel="stylesheet" href="../css/style.css" media="screen" title="no title" charset="utf-8">

 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie-1.4.1.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
/* window.onload=function(){
  CKEDITOR.replace('file1MF');  // <TEXTAREA>태그 id 값
}; *//*ckeditor 부분*/

$(function(){
  $.removeCookie('checkEmail'); //기존의 쿠키 값을 삭제  
});

 function checkEmail(){
   var params = 'email='+$('#email').val();
   //요청주소, 전달 값, 응답 처리 함수, 전송 받는 형식
   $.post('./checkEmail.do', params, checkEmail_res, 'json');
 }
 
 function checkEmail_res(data){
   if(data.cnt == 0){
     $('#panel_email').css('color','#00AA00');
     $('#panel_email').html('Email 사용 가능');
     $.cookie('checkEmail', 'PASS'); //쿠키 생성 $.cookie(쿠키 변수명, 값)
   }else if(data.cnt == 1){
     $('#panel_email').css('color','#FF0000');
     $('#panel_email').html('Email이 중복됩니다.');
     $('#email').focus(); //포커스 이동!!!
   }
 }
 
 function send(){ //성별 체크확인
   
   if(!$(':input:radio[name=gen]:checked').val()) {
     alert("성별을 선택해주세요.");
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
// selectB 셀렉트 박스에 들어갈 변수를 가져올 수 있다.
// eval 은 변수이름으로 객체를 가져오는 용도로 사용.
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
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
<%
String root = request.getContextPath();
// out.println("root: " + root);
%>

<!-- ********************************부트********************************************************* -->

<div class="col-md-12">

        <div class="page-header">
          <h1>회원가입</h1>
        </div>
        <FORM class="form-horizontal" name='frm' method='POST' action='./create.do' onsubmit="return send();"
           enctype="multipart/form-data">
        <input type="hidden" name='auth' id='auth' value='${Tool.key()}'>
        <input type="hidden" name='confirm' id='confirm' value='N'>
        <input type="hidden" name='master' id='master' value='M'>
        
        <div class="form-group">
          <label class='col-sm-3 control-label' for='email'>이메일</label>
          <div class="col-sm-6">
            <input class="form-control" type='text' name='email' id='email' value='test1@mail.com' required="required">
            <button type='button' onclick="checkEmail()">중복확인</button>
            <SPAN id='panel_email'></SPAN>
          </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="passwd">패스워드</label>
          <div class="col-sm-6">
            <input class="form-control"  type='password' name='passwd' id='passwd' value='1234' required="required">
            <p class="help-block">숫자, 특수문자 포함 8자 이상</p>
          </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="name">성명</label>
          <div class="col-sm-6">
            <input class="form-control" type='text' name='name' id='name' value='홍길동' required="required">
          </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="tel">전화번호</label>
            <div class="col-sm-6">
              <input class="form-control"  type="tel" name='tel' id='tel' value='010-0000-0000'> 예) 010-0000-0000
            </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="zipcode">우편번호</label>
            <div class="col-sm-6">
              <input class="form-control"  type='text' name='zipcode' id='zipcode' value='' placeholder="우편번호">
              <input class="form-control"  type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
            </div>
        </div>        
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="address">주소</label>
            <div class="col-sm-6">
              <select name="tAddress" id="tAddress" onchange="ChangeMAddress(this);">
                <option value="0" selected = "selected">주소 1</option>
                <option value="1">서울특별시</option>
                <option value="2">경기도</option>
              </select>
        
              <select name="mAddress" id="mAddress">
                <option value="주소 2">주소 2</option>
              </select>
              <input class="form-control"  type='text' name='bAddress' id='bAddress' value='' size='50' placeholder="상세 주소(주소1과 주소2 필수 선택)" required="required">
              <p style="color: red; font-size: 15px; font-weight: bold;">(주소 1과 주소 2 선택 필수 둘 중 하나라도 선택 안하면 에러발생합니다.)</p>      
            </div>
        </div> 
       
        <div class="form-group">
          <label class="col-sm-3 control-label" for="hobby">취미</label>
            <div class="col-sm-6">
              <input class="form-control" type="text" name='hobby' id='hobby' value='' size='15' placeholder="취미" required="required">
            </div>
        </div>       

        <div class="form-group">
          <label class="col-sm-3 control-label" for="birth">생년월일</label>
            <div class="col-sm-6">
              <input class="form-control" type="text" name='birth' id='birth' value='' size='25' placeholder="1900-00-00" required="required">
            </div>
        </div> 

        <div class="form-group">
          <label class="col-sm-3 control-label" for="gen">성별</label>
            <div class="col-sm-6">
              <input type="radio" name="gen" id='gen'  value="남">남
              <input type="radio" name="gen" id='gen'  value="여">여
            </div>
        </div> 

        <div class="form-group">
          <label class="col-sm-3 control-label" for="push">Push알람 설정</label>
            <div class="col-sm-6">
              <input type="radio" name="push" id='push'  value="Y" checked="checked">수신 동의
              <input type="radio" name="push" id='push'  value="N">수신 거절
            </div>
        </div> 

        <div class="form-group">
          <label class="col-sm-3 control-label" for="file1">프로필 사진(10MB이하)</label>
            <div class="col-sm-6">
              <input class="form-control"  type="file" name='file1MF' id='file1MF' size='40' >
            </div>
        </div>  
         
        <div class="form-group">
          <label class="col-sm-3 control-label"></label>
          
          <!-- ----- DAUM 우편번호 API 시작 ----- -->
 
          <div class="col-sm-6" id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
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
          
        </div>                                
        
        <div class="form-group">
          <div class="col-sm-12 text-center">
            <button class="btn btn-primary" type="submit"><i class="fa fa-check spaceLeft"></i>가입</button>
            <button class="btn btn-danger"  type="button" onclick="location.href='<%=root%>/index.jsp'"><i class="fa fa-times spaceLeft"></i>취소</button>
          </div>
        </div>
        </form>
          <hr>
        </div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>

<!-- **********************************부트 종료************************************************** -->
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 