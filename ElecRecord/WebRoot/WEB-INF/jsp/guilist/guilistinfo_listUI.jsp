<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header_js.jsp"%>
<%@ taglib prefix="a" uri="http://openhome.cc/jstl/fake"%>

<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${basePath}css/base.css" />
<link rel="stylesheet" href="${basePath}css/info-mgt.css" />
<link rel="stylesheet" href="${basePath}css/alter.css" />
<link rel="stylesheet" href="${basePath}css/jquery.dialog.css" />
<link rel="stylesheet" href="${basePath}css/list.css" />

<style type="text/css">
/*单元格样式*/
table tbody tr td{
	 text-align: center;
}
table thead tr th{
	 text-align: center;
}

/*行显示效果*/
  .even{ background:#FFF;color:#000;}  /* 偶数行样式*/
        .odd{ background:#eff6fa;color:#000;}  /* 奇数行样式*/
        .selected{background:#dddddd;color:#003333} /*选中行样式*/

</style>

<script type="text/javascript" src="${basePath}js/core.js"></script>
<script type="text/javascript" src="${basePath}js/jquery.dialog.js"></script>
<script type="text/javascript" src="${basePath}js/jquery.pagination.js"></script>
<script type="text/javascript" src="${basePath}js/WdatePicker.js"></script>

<script type="text/javascript" >

//选择框效果的JS
function doSelectAll(){
	$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));
} 


//单击的JS
$(function(){
    
   $("tr:odd").addClass("odd");  /* 奇数行添加样式*/
   $("tr:even").addClass("even"); /* 偶数行添加样式*/
   
    
    //点击改变选中样式
    $('tbody>tr').click(function() {
        $(this)
                .addClass('selected')
                .siblings().removeClass('selected')
                .end();
    });
})

//以下两条路径用于访问Action
var queryAction="${basePath}guilist/guilist_listUI.action";
var deleteAction="${basePath}guilist/guilist_delete.action";
var importUrl="${basePath}guilist/guilist_importExcel.action";

//向guilist_editorUI.action提交信息
function editor(id){
		var url="${basePath}guilist/guilist_editorUI.action?guiList.id="+id;
		$("#queryForm").attr("action",url);
 		$("#queryForm").submit();  
} 

//向guilist_addUI.action提交信息
function add(){
		var url="${basePath}guilist/guilist_addUI.action";
		$("#queryForm").attr("action",url);
 	$("#queryForm").submit();  
} 

//向guilist_listUI.action提交信息
function query(){
	  	$("#pageNo").val(1);
	  	$("#queryForm").attr("action",queryAction);
	 	$("#queryForm").submit(); 
	}
	
function detail(id){
	var url="${basePath}guilist/guilist_detailUI.action?guilist.id="+id;
	
	window.open(url) ; //打开窗口
}

 </script>
<title>导学名单</title>
</head>

<body>
<div class="title"><h2>导学名单</h2></div>
<form id="queryForm" action="${basePath}guilist/guilist_listUI.action" method="post">
<div class="query">

		<div class="main">                 
	     <p class="short-input ue-clear"> 
            <label >学号：</label>
            <input   type="text" name="guiList.studentNo" value="${queryCon.studentNo}"  />
            
            <label >学生姓名：</label>
            <input  type="text"  name="guiList.stuName" value="${queryCon.stuName}" />
            
            <label >导师姓名：</label>
            <input  type="text"  name="guiList.teacherName" value="${queryCon.teacherName}" />
            </p>
        </div>
        
        
        
       
        
    
    
    <div class="query-btn ue-clear">
    	<a href="javascript:query()" class="confirm" style="margin-left:5px">查询</a>
    </div>
</div>

<div class="table-operate ue-clear">
  <a:if url="/guilist/guilist_add.action"><a href="javascript:add()" class="add">添加</a></a:if>
    <a:if url="/guilist/guilist_delete.action"><a href="javascript:del()" class="del confirm save">删除</a></a:if>
    <a:if url="/guilist/guilist_importExcel.action"><a href="javascript:" class="import clear clear">导入</a></a:if>
</div>

<div class="table-box">
	<table>
    	<thead>
        	<tr>
			<a:if url="/guilist/guilist_delete.action"><th  width="5%"><input type="checkbox" id="selAll" class="checkall" onclick="doSelectAll()" /></th></a:if>
            	<th width="25%" class="num">学号</th>
                <th width="25%" >专业班级</th>
				<th width="20%" align="center">姓名</th>
				<th width="20%" >导师姓名</th>
		<a:if url="/guilist/guilist_editor.action"><th width="10%">编辑</th></a:if>				
            </tr>
        </thead>
        <tbody>
           <s:iterator value="pageUtils.items" var="guilist">
        	<tr>
        	
			<a:if url="/guilist/guilist_delete.action"><td class="num" ><input  type="checkbox" name="selectedRow" value='<s:property value='#guilist.id'/>' /></td></a:if>
			
            	<td><s:property value="#guilist.studentNo"/></td>
            	<td><s:property value="#guilist.className"/></td>
				<td ><s:property value="#guilist.stuName"/></td>		
				<td><s:property value="#guilist.teacherName"/></td>
			<a:if url="/guilist/guilist_editor.action"><td><a href="javascript:editor('<s:property value='#guilist.id'/>')"><img src="../images/edtico.png"/></a></td></a:if>
            </tr> 
            </s:iterator>          
        </tbody>
    </table>
    
</div>


<jsp:include page="/common/pagination.jsp"></jsp:include>
</form>

<script type="text/javascript" src="${basePath}js/core.js"></script>
<script type="text/javascript" src="${basePath}js/jquery.dialog.js"></script>
<script type="text/javascript" src="${basePath}js/jquery.pagination.js"></script>
<script type="text/javascript" src="${basePath}js/jquery-form.js"></script>
<jsp:include page="/common/inputdialog.jsp"></jsp:include>

<script type="text/javascript">

function del(){
	var selectedRows=document.getElementsByName("selectedRow");
	
	for(i=0;i<selectedRows.length;i++){
		if(selectedRows[i].checked){
			$('.delDialog').Dialog('open');
		}
	}
	
}

</script>
</html>
