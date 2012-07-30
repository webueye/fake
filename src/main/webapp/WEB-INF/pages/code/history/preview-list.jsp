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
									<th class="gray" colspan="5">可导入码列表</th>
								</tr>	
								<tr>
									<th>箱码</th>
									<th>盒码</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="box" items="${notExisted}">
									<c:set var="state" value="false"/>
									<c:forEach var="fake" items="${box.fakes}">
										<tr >
											<c:if test="${state == 'false'}">
												<td rowspan="${box.size}">
													${box.boxCode}[${box.size}]
												</td>
												<c:set var="state" value="true"/>
											</c:if>
											<td>${fake.plainCode}</td>
										</tr>
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
				      <table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="gray" colspan="5">已存在数据列表</th>
								</tr>	
								<tr>
									<th>箱码</th>
									<th>盒码</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="box" items="${existed}">
									<c:set var="state" value="false"/>
									<c:forEach var="fake" items="${box.fakes}">
										<tr >
											<c:if test="${state == 'false'}">
												<td rowspan="${box.size}">
													${box.boxCode}[${box.size}]
												</td>
												<c:set var="state" value="true"/>
											</c:if>
											<td>${fake.plainCode}</td>
											<td>${fake.fakeResult.reason}</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
				    </div>
				    <div class="tab-pane" id="tab3">
				      	<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="gray" colspan="5">其它问题数据列表</th>
								</tr>
								<tr>
									<th>类型</th>
									<th>码</th>
									<th>原因</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="illegal" items="${illegals}">
									<tr>
										<td>${illegal.type}</td>
										<td>${illegal.code}</td>
										<td>${illegal.reason}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				    </div>
				  </div>
				</div>
				
				<c:forEach var="suffix" items="${suffixes}">
					<input type="hidden" class="suffix" name="suffix" value="${suffix}"/>
				</c:forEach>
				
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
			function closeDiv(){
				$("#resultDiv").hide();
			}	
		
			function batchImp(){
				var arr = new Array();
				$(".suffix").each(function(i){
					arr.push($(this).val());
				});
				impData(arr.join(","));
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