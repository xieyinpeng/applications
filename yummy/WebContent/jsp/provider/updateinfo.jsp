<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/updateinfo</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/index">返回上一级</a>
<form method="post">
<div>pid:<input name="pid" readonly value="${requestScope.pid}"></div>
<div>site:
<select name="site">
<option value="A" ${requestScope.site eq 'A' ? 'selected' : ''}>A</option>
<option value="B" ${requestScope.site eq 'B' ? 'selected' : ''}>B</option>
<option value="C" ${requestScope.site eq 'C' ? 'selected' : ''}>C</option>
<option value="D" ${requestScope.site eq 'D' ? 'selected' : ''}>D</option>
</select>
</div>
<div>type:
<select name="type">
<option value="A" ${requestScope.type eq 'A' ? 'selected' : ''}>A</option>
<option value="B" ${requestScope.type eq 'B' ? 'selected' : ''}>B</option>
<option value="C" ${requestScope.type eq 'C' ? 'selected' : ''}>C</option>
<option value="D" ${requestScope.type eq 'D' ? 'selected' : ''}>D</option>
</select>
</div>
<div>password:<input name="password"  value="xyp2016"></div>
<div>aid:<input name="aid" value="${requestScope.aid}"></div>
<div><input type="submit"></div>
</form>
</body>
</html>
