<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>历史数据预览 </title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
				
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-code-history/upload">	
				
				<div id="resultDiv" style="display: none;">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="9" onclick="closeDiv();" style="cursor: pointer;">关闭</th>
							</tr>
							<tr>
								<th>类型</th>
								<th>码</th>
								<th>原因</th>
							</tr>
						</thead>
						<tbody id="reulstTR">
						</tbody>
					</table>
				</div>
				
				<div class="tabbable"> 
				  <ul class="nav nav-tabs">
				    <li class="active"><a href="#tab1" data-toggle="tab">可导入数据</a></li>
				    <li><a href="#tab2" data-toggle="tab">有问题的数据</a></li>
				    <li><a href="#tab3" data-toggle="tab">其它问题</a></li>
				  </ul>
				  <div class="tab-content">
				    <div class="tab-pane active" id="tab1">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="gray" colspan="5">文件列表</th>
								</tr>	
								<tr>
									<th>箱码</th>
									<th>盒码</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${results}">
								<c:forEach var="box" items="${result.boxs}">
								<c:forEach var="fake" items="${box.fakes}">
									<tr>
										<td>${box.boxCode}</td>
										<td>${fake.plainCode}</td>
										<td>${fake.fakeResult.reason}</td>
									</tr>
								</c:forEach>
								</c:forEach>
								</c:forEach>
								<tr>
									<td colspan="5">
										<div align="center">
											<button type="button" class="btn btn-primary" onclick="batchImp();">批量导入</button>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
				      
				    </div>
				    <div class="tab-pane" id="tab2">
				      <p>Howdy, I'm in Section 2.</p>
				    </div>
				    <div class="tab-pane" id="tab3">
				      	<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>类型</th>
									<th>码</th>
									<th>原因</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${results}">
								<c:forEach var="illegal" items="${result.illegals}">
									<tr>
										<td>${illegal.type}</td>
										<td>${illegal.code}</td>
										<td>${illegal.reason}</td>
									</tr>
								</c:forEach>
								</c:forEach>
							</tbody>
						</table>
				    </div>
				  </div>
				</div>
				
			</form>	
						
		</div>
		
		<div class="modal" id="impMessage" style="display: none;">
			<div class="modal-header">
		    	<h3>数据导入</h3>
		  	</div>
			<div class="modal-body">
		    	<div align="center">数据导入中……</div>
		  	</div>
		</div>
		
		<div id="backgroupDiv" class="modal-backdrop" style="display: none;"></div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function checkHandle(elem){
				if($(elem).attr("checked")){
					$(".suffix").each(function(i){
						$(this).attr("checked", "true");
					});
				}else{
					$(".suffix").each(function(i){
						$(this).removeAttr("checked");
					});
				}
			}
		
			function closeDiv(){
				$("#resultDiv").hide();
			}	
		
			function batchImp(){
				var arr = new Array();
				$(".suffix").each(function(i){
					if($(this).attr("checked")){
						arr.push($(this).val());
					}
				});
				if(arr.length > 0){
					impData(arr.join(","));
				}else{
					alert("请选择要导入的文件");
				}
			}
			
			function impData(value){
				closeDiv();
				$("#impMessage").show();
				$("#backgroupDiv").show();
				var suffix = {suffix: value};
				common.post(suffix, "/box-code-history/imp", function(data){
					if(data && data.length > 0){
						var arr = new Array();
						for(var i = 0; i<data.length; i++){
							arr.push("<tr>");
							arr.push("<td>");
							arr.push(data[i].type);
							arr.push("</td>");
							arr.push("<td>");
							arr.push(data[i].code);
							arr.push("</td>");
							arr.push("<td>");
							arr.push(data[i].reason);
							arr.push("</td>");
							arr.push("</tr>");
						}
						$("#reulstTR").html(arr.join(""));
						$("#resultDiv").show();
					}else{
						alert("导入成功");
					}
					$("#impMessage").hide();
					$("#backgroupDiv").hide();
				});
			}
		
		</script>
		
	</body>

</html>