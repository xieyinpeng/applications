<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="model.presentobject.ProviderSingleDishForPresentVO"%>
<%@page import="model.presentobject.ProviderMultipleDishForPresentVO"%>
<%@page import="model.presentobject.ProviderDiscountForPresentVO" %>
<%@page import="model.presentobject.ConsumerAddressForPresentVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>consumer/order/make</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/consumer/order/index">返回上一级</a>
<table>
<tr>
<th>sid</th>
<th>summary</th>
<th>price</th>
<th>pid</th>
<th>type</th>
<th>site</th>
<th>limit</th>
</tr>
<%
	List<ProviderSingleDishForPresentVO> singleList=(List<ProviderSingleDishForPresentVO>)request.getAttribute("singleDishList");
for(ProviderSingleDishForPresentVO singleDish:singleList){
%>
<tr>
<td><%=singleDish.getSid()%></td>
<td><%=singleDish.getSummary()%></td>
<td><%=singleDish.getPrice()%></td>
<td><%=singleDish.getPid()%></td>
<td><%=singleDish.getType()%></td>
<td><%=singleDish.getSite()%></td>
<td><%=singleDish.getLimit() %></td>
</tr>
<%
	}
%>
</table>
<table>
<tr>
<th>mid</th>
<th>summary</th>
<th>detail</th>
<th>price</th>
<th>pid</th>
<th>type</th>
<th>site</th>
<th>limit</th>
</tr>
<%
	List<ProviderMultipleDishForPresentVO> multipleList=(List<ProviderMultipleDishForPresentVO>)request.getAttribute("multipleDishList");
for(ProviderMultipleDishForPresentVO multipleDish:multipleList){
%>
<tr>
<td><%=multipleDish.getMid()%></td>
<td><%=multipleDish.getSummary()%></td>
<td><%=multipleDish.getDetail()%></td>
<td><%=multipleDish.getPrice()%></td>
<td><%=multipleDish.getPid()%></td>
<td><%=multipleDish.getType()%></td>
<td><%=multipleDish.getSite()%></td>
<td><%=multipleDish.getLimit() %></td>
</tr>
<%
	}
%>
</table>
<table>
<tr>
<th>did</th>
<th>pid</th>
<th>summary</th>
<th>moneyMin</th>
<th>moneyMax</th>
<th>dateBegin</th>
<th>dateEnd</th>
<th>levelMin</th>
<th>levelMax</th>
<th>type</th>
<th>scale</th>
</tr>
<%
	List<ProviderDiscountForPresentVO> discountList=(List<ProviderDiscountForPresentVO>)request.getAttribute("discountList");
for(ProviderDiscountForPresentVO discount:discountList){
%>
<tr>
<td><%=discount.getDid()%></td>
<td><%=discount.getPid()%></td>
<td><%=discount.getSummary()%></td>
<td><%=discount.getMoneyMin()%></td>
<td><%=discount.getMoneyMax()%></td>
<td><%=discount.getDateBegin()%></td>
<td><%=discount.getDateEnd()%></td>
<td><%=discount.getLevelMin()%></td>
<td><%=discount.getLevelMax()%></td>
<td><%=discount.getType()%></td>
<td><%=discount.getScale()%></td>
</tr>
<%
}
%>
</table>
<table>
<tr>
<th>caid</th>
<th>site</th>
</tr>
<%
List<ConsumerAddressForPresentVO> addressList=(List<ConsumerAddressForPresentVO>)request.getAttribute("addressList");
for(ConsumerAddressForPresentVO address:addressList){
%>
<tr>
<td><%=address.getCaid() %></td>
<td><%=address.getSite() %></td>
</tr>
<%
}
%>
</table>
<div>sid:<input id="sidInput"  value="1000000"><button  type="button" id="sidButton">addItem</button></div>
<div>mid:<input id="midInput" value="1000000"><button  type="button" id="midButton">addItem</button></div>
<form method="post">
<div>pid:<input name="pid" value="1000000"></div>
<div>sid:<div id="sidDiv"></div></div>
<div>mid:<div id="midDiv"></div></div>
<div>did:<input name="did" value="1000000">(如果没有符合的选择，请填1000000)</div>
<div>caid:<input name="caid" value="1000000"></div>
<div><input type="submit" value="submit"></div>
</form>
<script>
var sidInput=document.getElementById("sidInput");
var sidButton=document.getElementById("sidButton");
var sidDiv=document.getElementById("sidDiv");
var midInput=document.getElementById("midInput");
var midButton=document.getElementById("midButton");
var midDiv=document.getElementById("midDiv");
sidButton.onclick=function(){
	var sid=sidInput.value;
	var input=document.createElement("input");
	input.name="sid";
	input.value=sid;
	input.readOnly="true";
	sidDiv.appendChild(input);
}
midButton.onclick=function(){
	var mid=midInput.value;
	var input=document.createElement("input");
	input.name="mid";
	input.value=mid;
	input.readOnly="true";
	midDiv.appendChild(input);
}
</script>
</body>
</html>
