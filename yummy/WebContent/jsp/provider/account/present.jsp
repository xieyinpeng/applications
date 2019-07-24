<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="model.presentobject.AccountTransferForPresentVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>provider/account/present</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/provider/account/index">返回上一级</a>
<table>
<tr>
<th>tid</th>
<th>date</th>
<th>aid_from</th>
<th>aid_to</th>
<th>money</th>
</tr>

<%
List<AccountTransferForPresentVO> transferList=(List<AccountTransferForPresentVO>)request.getAttribute("transferList");
for(AccountTransferForPresentVO transfer:transferList){
	%>
	<tr>
	<td><%=transfer.getTid() %></td>
		<td><%=transfer.getDate() %></td>
	<td><%=transfer.getAid_from() %></td>
	<td><%=transfer.getAid_to() %></td>
	<td><%=transfer.getMoney() %></td>
	</tr>
	<%
}
%>

</table>
</body>
</html>