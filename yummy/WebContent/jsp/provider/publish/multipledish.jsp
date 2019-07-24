<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/publish/multipledish</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/publish/index">返回上一级</a>
<form method="post">
<div>summary:<input name="summary" value="DishSummary"></div>
<div>price:<input name="price" value="5"></div>
<div>limit:<input name="limit"value="1000"></div>
<div>dateBegin:<input name="dateBegin"  type="date"  value="2018-01-01"></div>
<div>dateEnd:<input name="dateEnd" type="date" value="2020-01-01"></div>
<div id="sidDiv">(sidList)</div>
<div><input type="submit" value="submit"></div>
</form>
<div>sid:<input id="sidInput" value="1000000"><Button type="button"  id="sidButton">addSid</Button></div>
<script>
var sidButton=document.getElementById("sidButton");
var sidDiv=document.getElementById("sidDiv");
var sidInput=document.getElementById("sidInput");
sidButton.onclick=function(){
	var input=document.createElement("input");
	input.name="sid";
	input.value=sidInput.value;
	input.readOnly="true";
	sidDiv.appendChild(input);
}
</script>
</body>
</html>
