<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/publish/discount</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/publish/index">返回上一级</a>
<form method="post">
<div>summary:<input name="summary"  value="20% off"></div>
<div>moneyMin:<input name="moneyMin" value="1"></div>
<div>moneyMax:<input name="moneyMax"  value="100000"></div>
<div>dateBegin:<input name="dateBegin" type="date" value="2018-01-01"></div>
<div>dateEnd:<input name="dateEnd" type="date" value="2020-01-01"></div>
<div>levelMin:<input name="levelMin"  value="1"></div>
<div>levelMax:<input name="levelMax"  value="10"></div>
<div>type:<select name="type">
<option value="Total">Total</option>
</select></div>
<div>scale:<input name="scale" value="0.8"></div>
<div><input type="submit" value="submit"></div>
</form>
</body>
</html>
