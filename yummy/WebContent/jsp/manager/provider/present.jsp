<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="model.statisticsobject.ProviderNumByTypeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>manager/provider/present</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/manager/provider/index">返回上一级</a>
<div id="presentDiv" style="width: 600px; height: 400px;">
</div>
<script src="<%=request.getContextPath() %>/js/echarts.js"></script>
<script>
var dataArray=[];
var categoryArray=[];
<%
List<ProviderNumByTypeVO> numList=(List<ProviderNumByTypeVO>)request.getAttribute("numList");
for(ProviderNumByTypeVO num:numList){
	%>
	dataArray.push(<%=num.getNum()%>);
	categoryArray.push('<%=num.getType()%>');
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