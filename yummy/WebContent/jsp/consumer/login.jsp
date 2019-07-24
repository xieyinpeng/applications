<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>consumer/login</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/consumer/index">返回上一级</a>
<form method="post">
<div>cid:<input name="cid" value="1000000"></div>
<div>password:<input name="password" value="xyp2016"></div>
<div><img src="<%=request.getContextPath() %>${requestScope.verifyImageUrl}" ></div>
<div>验证码<input name="verifyCode"></div>
<div><input type="submit" value="submit"></div>
</form>
</body>
</html>
