<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="model.presentobject.OrderForPresentVO" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/order/checknow/index</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/provider/order/index">返回上一级</a>
<ol>
<%
	List<OrderForPresentVO> orderList=(List<OrderForPresentVO>)request.getAttribute("orderList");
for(OrderForPresentVO order:orderList){
%>
<li>
<ul>
<li>oid:<%=order.getOid() %></li>
<li>cid:<%=order.getCid() %></li>
<li>pid:<%=order.getPid() %></li>
<li>state:<%=order.getState() %></li>
<li>date:<%=order.getDate() %></li>
<li>address:<%=order.getAddress() %></li>
<li>discount:<%=order.getDiscount() %></li>
<li>money:<%=order.getMoney() %></li>
<ol>
<%
for(String orderDishSummary:order.getOrderDishSummaryList()){
%>
<li><%=orderDishSummary %></li>
<%
}
%>
</ol>
</ul>
</li>
<%
}
%>
</ol>
</body>
</html>
