<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css" />

<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>

</head>
<body class="easyui-layout" >
<!-- 使用div元素描述每个区域 -->
<div  title= " XXX 管理系统" style="height:100px" data-options="region:'north'">北部区域</div>
<div title= "系统菜单" style="width:200px"data-options="region:'west'">
<div class = "easyui-accordion" data-options = "fit:true">
<div data-option ="" title ="面板1"></div>
<div title ="标题一">1223</div>
<div title ="标题二">2223</div>
</div>

</div>


<div data-options="region:'center'">
<!--  制作一个tab选项卡面板-->
<div class = "easyui-tabs" dataoptions="fit:true">
<!-- 使用子div表示每个面板 -->
<div data-options="iconCls:'icon-cut'" title= "面板一"></div>
<div data-options ="closable:true" title= "面板二"></div>
</div>
</div>
<div style="width:200px" data-options="region:'east'">东北区域</div>
<div style="height:50px" data-options="region:'south'">南部区域</div>
</body>
</html>