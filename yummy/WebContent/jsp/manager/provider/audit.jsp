<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="model.presentobject.ProviderForPresentVO" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manager/provider/audit</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/manager/provider/index">返回上一级</a>
<table>
<tr>
<th>pid</th>
<th>site</th>
<th>type</th>
<th>password</th>
<th>aid</th>
<th>state</th>
</tr>
<%
	List<ProviderForPresentVO> registeredList=(List<ProviderForPresentVO>)request.getAttribute("registeredList");
for(ProviderForPresentVO provider:registeredList){
%>
<tr>
<td><%=provider.getPid()%></td>
<td><%=provider.getSite()%></td>
<td><%=provider.getType() %></td>
<td><%=provider.getPassword() %></td>
<td><%=provider.getAid() %></td>
<td><%=provider.getState() %></td>
</tr>
<%
}
%>
</table>
<table>
<tr>
<th>pid</th>
<th>site</th>
<th>type</th>
<th>password</th>
<th>aid</th>
<th>state</th>
</tr>
<%
	List<ProviderForPresentVO> updatedList=(List<ProviderForPresentVO>)request.getAttribute("updatedList");
for(ProviderForPresentVO provider:updatedList){
%>
<tr>
<td><%=provider.getPid()%></td>
<td><%=provider.getSite()%></td>
<td><%=provider.getType() %></td>
<td><%=provider.getPassword() %></td>
<td><%=provider.getAid() %></td>
<td><%=provider.getState() %></td>
</tr>
<%
}
%>
</table>
<form method="post">
<div>pid:<input name="pid"></div>
<div>isApproved:
<select name="isApproved">
<option value="true">true</option>
<option value="false">false</option>
</select>
</div>
<div><input type="submit" value="submit"></div>
</form>
</body>
</html>