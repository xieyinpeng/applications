<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/publish/singledish</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/publish/index">返回上一级</a>
<form method="post">
<div>summary:<input name="summary" value="DishSummary"></div>
<div>price:<input name="price"  value="3"></div>
<div>limit:<input name="limit" value="1000"></div>
<div>dateBegin:<input name="dateBegin" value="2018-01-01"></div>
<div>dateEnd:<input name="dateEnd" value="2020-02-02"></div>
<div><input type="submit" value="submit"></div>
</form>
</body>
</html>
