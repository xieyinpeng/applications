<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="model.statisticsobject.ConsumerNumByLevelVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manager/consumer/present</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/manager/consumer/index">返回上一级</a>
<div id="presentDiv" style="width: 600px; height: 400px;">
</div>
<script src="<%=request.getContextPath() %>/js/echarts.js"></script>
<script>
var dataArray=[];
var categoryArray=[];
<%
List<ConsumerNumByLevelVO> numList=(List<ConsumerNumByLevelVO>)request.getAttribute("numList");
for(ConsumerNumByLevelVO num:numList){
	%>
	dataArray.push(<%=num.getNum()%>);
	categoryArray.push('<%=num.getLevel()%>');
	<%
}
%>

var option = {
	    xAxis: {
	        type: 'category',
	        data: categoryArray
	    },
	    yAxis: {
	        type: 'value'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    series: [{
	        data: dataArray,
	        type: 'bar'
	    }]
	};
var presentDiv=document.getElementById("presentDiv");
var myChart=echarts.init(presentDiv);
myChart.setOption(option);
</script>
</body>
</html>