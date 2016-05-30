<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header_js.jsp"%>

<html>
<head>
    


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="${basePath}css/alter.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}css/jquery.dialog.css" />
<link rel="stylesheet" href="${basePath}css/WdatePicker.css" />
<title>党团关系基本信息添加</title>
</head>
<body>

<div class="title"><h2>添加党团关系基本信息</h2></div>
<form id="myForm" method="post">
        
	    <input hidden="hidden" value="<s:property value="1"/>" name="politicalstatus.stuId">
	    <input hidden="hidden" value="<s:property value="'罗吉林'"/>" name="politicalstatus.creator">
	   
	  
<div class="main">
    <p class="short-input ue-clear">
    	<label>学号：</label>
        <input type="text" name="politicalstatus.studentNo" placeholder="请输入学号" /> 
        <label>姓名：</label>
        <input type="text" name="politicalstatus.stuName" placeholder="请输入姓名" /> 
       
    </p>
    
     <div class="short-input select ue-clear" >
        
        <label>政治面貌：</label>
        <input  hidden="hidden" value="" name="politicalstatus.politicalStatus">
        <div class="select-wrap">
        	<div class=" select-title ue-clear" ><span >共青团员</span><i class="icon"></i></div>
            <ul class="select-list" >
                <li id="共青团员">共青团员</li>
            	<li id="党员">党员</li>
                <li id="群众">群众</li>
             
            </ul>
        </div>
        
        
        
        <label>入党日期：</label>
        <div class="select-wrap" >
        	 
        	 <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="请选择日期" name="politicalstatus.joinDate"/>
        </div>
    </div>

    <p class="short-input ue-clear">
    	<label >备注：</label>
        <textarea name="politicalstatus.memo" placeholder="请输入备注"></textarea>
    </p>
    
</div>

<div class="btn ue-clear">
	
	<a href="javascript:editor('myForm','post','${basePath}polstatus/polstatus_add.action')"  class="confirm save">确定</a>
    <a href="javascript:back()" class="clear clear" >返回</a>
</div>
</form>
</body>

<script type="text/javascript" src="${basePath}js/WdatePicker.js"></script>
</html>