<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/register</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/index">返回上一级</a>
<form method="post">
<div>site:
<select name="site">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
</select>
</div>
<div>type:
<select name="type">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<option value="D">D</option>
</select>
</div>
<div>password:<input name="password"  value="xyp2016"></div>
<div>aid:<input name="aid"  value="1000001"></div>
<div><input type="submit"></div>
</form>
</body>
</html>
