// tool.js version 2.0

/**
 * 태그를 검색합니다.
 * @param id
 * @returns 검색된 태그
 */
function find(id){
  return document.getElementById(id);
}

/**
 * 천단위 컴마를 설정합니다.
 * @param val
 * @returns
 */
function comma(val){
  return Number(val).toLocaleString('en')
}

/**
문자열에서 숫자만 추출합니다. 예) 100,000 -> 100000
정규 표현식
/ ... /  : 정규 표현식 선언 영역
[^0-9]: 0 ~ 9를 제외한 모든 문자 추출
g       : 추출된 문자열 리턴
*/
function getNumber(val){
  var regexp = /[^0-9]/g; // 숫자가 제외된 문자만 나타냄 
  var returnval = new String(val); // 전달 받은 값으로 새로운 문자열 생성
  // 숫자가 제외된 문자열만 추출하여 ''으로 변경함으로 문자는 삭제됨
  returnval = returnval.replace(regexp, ''); 
  
  return returnval;
}

/**
 * input 태그와 관련하여 값 추출
 */
function val(id){
  return document.getElementById(id).value;  
}

/**
 * <SPAN>, <DIV>태그의 값 추출
 * @param id
 * @returns
 */
function html(id){
  return document.getElementById(id).innerHTML;
}

/**
 * 파일 크기의 단위를 계산합니다.
 * @param length 파일 크기
 */
function unit(length){
  var str = "";
  
  if (length < 1024){  // Byte: 0 ~ 1023
    str = parseInt(length) + " Byte";   
  }else if (length < (1024 * 1024)){ // Byte: 1024 ~ 1048575
    str = parseInt(length / 1024) + " KB";
  }else if(length < (1024 * 1024 * 1024)){ // Byte: 1048576 ~ 1073741823
    str = parseInt(length / 1024 / 1024) + " MB";
  }else if(length < (1024 * 1024 * 1024 * 1024)){ // Byte: 1073741824 ~
    str = parseInt(length / 1024 / 1024 / 1024) + " GB";
  }
  
  return str;
}

//jQuery 사용자정의 함수
jQuery.fn.center = function () {
  this.css("color","#FFFFFF");
  this.css("background-color","#00AA00");
  this.css("opacity",0.7);
  this.css("position","absolute");
  this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2)) + "px");
  this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2)) + "px");
  return this;
}
 
var show_layer = function() {
  $("#layer").show();
  $("#layer").center();
}

//나 혼자 만든 jQuery 사용자정의 함수 2016.5.3
jQuery.fn.center2 = function () {
  //this.css("color","#FFFFFF");
  //this.css("background-color","#00AA00");
  //this.css("opacity",0.7);
  this.css("position","absolute");
  //Math.max(1,2):큰수 비교, 출력은 2
  //alert('$(window).height(): '+$(window).height());
  //alert('$(this).outerHeight(): '+$(this).outerHeight());
  
  this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2)) + "px");
  this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2)) + "px");
  return this;
}
//나 혼자 만든 2016.5.3
var show_layer2 = function() {
  $("#layer").show();
  $("#layer").center2();
}




