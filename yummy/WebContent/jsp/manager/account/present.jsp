<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="model.statisticsobject.AccountBalanceByDateVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manager/account/present</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/manager/account/index">返回上一级</a>
<div id="presentDiv" style="width: 600px; height: 400px;">
</div>
<script src="<%=request.getContextPath() %>/js/echarts.js"></script>
<script>
var dataArray=[];
<%
List<AccountBalanceByDateVO> balanceList=(List<AccountBalanceByDateVO>)request.getAttribute("balanceList");
for(AccountBalanceByDateVO balance:balanceList){
	%>
	dataArray.push({value:['<%=balance.getDate()%>',<%=balance.getBalance()%>]});
	<%
}
%>
var option = {
	    xAxis: {
	        type: 'time'
	    },
	    yAxis: {
	        type: 'value'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    series: [{
	        data: dataArray,
	        type: 'line'
	    }]
	};
var presentDiv=document.getElementById("presentDiv");
var myChart=echarts.init(presentDiv);
myChart.setOption(option);
</script>
</body>
</html>