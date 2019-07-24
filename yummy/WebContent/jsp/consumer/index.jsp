<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>consumer/index</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/index">返回上一级</a>
<a href="<%=request.getContextPath() %>/consumer/login">登陆</a>
<a href="<%=request.getContextPath() %>/consumer/logout">登出</a>
<a href="<%=request.getContextPath() %>/consumer/logoff">注销</a>
<a href="<%=request.getContextPath() %>/consumer/register">注册</a>
<a href="<%=request.getContextPath() %>/consumer/updateinfo">信息更新</a>
<a href="<%=request.getContextPath() %>/consumer/order/index">订单管理</a>
<a href="<%=request.getContextPath() %>/consumer/account/index">银行账户管理</a>
</body>
</html>
