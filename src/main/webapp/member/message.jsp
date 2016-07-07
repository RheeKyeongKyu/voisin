<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
 
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
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
<script type="text/javascript">
$(function(){ 
  
}
);
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' /> 
<!-- ----------------------------------------- -->
<%
ArrayList<String> msgs = (ArrayList<String>)request.getAttribute("msgs");
ArrayList<String> links = (ArrayList<String>)request.getAttribute("links");
%>

 <div class="container">

  <h2>알림 메시지</h2>
  <div class="alert alert-success" role="alert">
    <ul>
        <%
        for(int i=0; i<msgs.size(); i++){
        %>
         <li style="text-align: center;"><%=msgs.get(i) %></li>
        <%
        }
        %>
        <li style="text-align: center;">
        <%
        for(int i=0; i<links.size(); i++){
        %>
          <%=links.get(i) %>
        <%
        }
        %>
        </li>
     </ul>
  </div>
  
</div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</body>
<!-- -------------------------------------------- -->
</html> 