<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>出库数据处理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
				
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/delivery-history/upload" enctype="multipart/form-data">	
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;"><b>出库数据上传：</b> </span>
					           	<input class="{required:true,accept:'zip'}" id="file" name="file" type="file"/>
								<button type="submit" class="btn btn-primary">上传</button>
								<label style="display: none;" for="file" class="error">请选择一个zip格式的压纹文件(*.zip)</label>
					        </div>
						</div>
					  </div>
				</div>
				
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
				
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th class="gray" colspan="5">文件列表</th>
						</tr>	
						<tr>
							<th><input type="checkbox" onclick="checkHandle(this);"/></th>
							<th>编号</th>
							<th>单据文件</th>
							<th>明细文件</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="historyFile" items="${files}" varStatus="status">
						<c:set var="state" value="false"/>
						<c:forEach var="outFile" items="${historyFile.outFileNames}">
							<tr>
								<c:if test="${state == 'false'}">
									<td style="padding-left: 18px;" rowspan="${historyFile.outFileSize}">
										<input type="checkbox" class="suffix" name="suffix" value="${historyFile.stockOutFileName}"/>
									</td>
									<td rowspan="${historyFile.outFileSize}">${status.index+1}</td>
									<td rowspan="${historyFile.outFileSize}">${historyFile.stockOutFileName}</td>
								</c:if>
								<td>${outFile}</td>
								
								<c:if test="${state == 'false'}">
									<td rowspan="${historyFile.outFileSize}">
										<a href="${pageContext.request.contextPath}/delivery-history/preview?stockOutFile=${historyFile.stockOutFileName}">导入预览</a>
										<a href="#" onclick="impData('${historyFile.stockOutFileName}');">导入</a>
										<a href="${pageContext.request.contextPath}/delivery-history/delete?stockOutFile=${historyFile.stockOutFileName}">删除</a>
									</td>
									<c:set var="state" value="true"/>
								</c:if>
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
				var suffix = {fileNames: value};
				common.post(suffix, "/delivery-history/imp", function(data){
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