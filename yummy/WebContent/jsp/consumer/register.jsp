<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>consumer/register</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/consumer/index">返回上一级</a>
<form method="post">
<div>name:<input name="name" value="xyp"></div>
<div>password:<input name="password" value="xyp2016"></div>
<div>phoneNumber:<input name="phoneNumber" value="123456"></div>
<div>site:<div id="siteDiv"></div></div>
<div>
<select id="siteSelect">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
</select>
<button id="siteButton" type="button">addItem</button>
</div>
<div>aid:<input name="aid" value="1000000"></div>
<div>email:<input id="emailInput" name="email" value="593955515@qq.com"></div>
<div><button id="codeButton"  type="button" >send the code to my mail</button></div>
<div>code:<input name="code"></div>
<div><input type="submit"></div>
</form>
<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script>
var emailInput=document.getElementById("emailInput");
var codeButton=document.getElementById("codeButton");
codeButton.onclick=function(){
	var email=emailInput.value;
	$.post("<%=request.getContextPath() %>/utils/email",{email:email},function(){});
}
</script>
<script>
var siteDiv=document.getElementById("siteDiv");
var siteSelect=document.getElementById("siteSelect");
var siteButton=document.getElementById("siteButton");
siteButton.onclick=function(){
	var site=siteSelect.value;
	var input=document.createElement("input");
	input.name="site";
	input.value=site;
	input.readOnly="true";
	siteDiv.appendChild(input);
}
</script>
</body>
</html>
