<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manager/index</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/index">返回上一级</a>
<a href="<%=request.getContextPath() %>/manager/login">登陆</a>
<a href="<%=request.getContextPath() %>/manager/logout">登出</a>
<a href="<%=request.getContextPath() %>/manager/account/index">管理银行账户</a>
<a href="<%=request.getContextPath() %>/manager/consumer/index">管理顾客</a>
<a href="<%=request.getContextPath() %>/manager/provider/index">管理餐厅</a>
</body>
</html>
