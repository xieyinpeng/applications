<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>consumer/updateinfo</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/consumer/index">返回上一级</a>
<form method="post">
<div>name:<input name="name" value="${requestScope.consumer.name}"></div>
<div>cid:<input name="cid" value="${requestScope.cid}" readonly="readonly"></div>
<div>password:<input name="password" value="xyp2016"></div>
<div>phoneNumber:<input name="phoneNumber" value="${requestScope.consumer.phoneNumber}"></div>
<div>aid:<input name="aid" value="${requestScope.consumer.aid}"></div>
<div>email:<input id="emailInput" name="email" value="${requestScope.consumer.email}"></div>
<div>site:<div id="siteDiv"></div></div>
<div>
<select id="siteSelect">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
</select>
<button id="siteButton" type="button" >addItem</button>
</div>
<div><input type="submit"></div>
</form>
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
