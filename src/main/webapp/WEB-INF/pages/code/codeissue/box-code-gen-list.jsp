<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码生成记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/code-issue/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">标题 </span>
					            <input class="input-small" name="filter_LIKES_issueName" value="${filter_LIKES_issueName}" placeholder="标题"/>
					           
					           	<span style="margin-left: 10px;">包装箱种类 </span>
					            <input class="input-small" name="filter_LIKES_boxSpec.specName" value="${filter_LIKES_boxSpec_specName}" placeholder="种类名称"/>
					            
					            <span style="margin-left: 10px;">生成日期： </span>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime}" onFocus="WdatePicker()" placeholder="开始日期"/>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime_}" onFocus="WdatePicker()" placeholder="结束日期"/>
					            
					            <input name="filter_EQB_codeType" value="true" type="hidden"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="th" colspan="6" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/code-issue/box-code-gen">
											箱码生成
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="6">箱码生成记录</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>标题</th>
								<th>包装箱种类</th>
								<th>数量</th>
								<th>生成日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="codeIssue" items="${page.datas}">
								<tr>
									<td>${codeIssue.id}</td>
									<td>
										<u:valueFormat value="${codeIssue.issueName}"/>
									</td>
									<td>
										<u:valueFormat value="${codeIssue.boxSpec.specName}"/>
									</td>
									<td>
										<u:valueFormat value="${codeIssue.issueCount}"/>
									</td>
									<td>
										<u:dateFormat value="${codeIssue.createDateTime}"/>
									</td>
									<td>
										<a href="#" onclick="printHandle('${codeIssue.id}');">打印</a>
										<a href="${pageContext.request.contextPath}/code-issue/export/${codeIssue.id}">导出</a>&nbsp;
										<a href="${pageContext.request.contextPath}/code-issue/code/${codeIssue.id}">${codeIssue.codeType? '箱码': '防伪码'}列表</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="6" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="code-issue"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<div id="printDiv" style="display: none;">
            <input class="input-small" id="minCode"/>
            <input class="input-small" id="maxCode"/>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			var codeIssueId;
			function printHandle(id){
				codeIssueId = id;
				common.post({codeIssueId: codeIssueId}, "/box-code/range", function(data){
					if(data){
						$("#minCode").val(data[0]);
						$("#maxCode").val(data[1]);
						
						var content = new Array();
						content.push('<div class="control-group">');
						content.push('	<span>打印码范围：</span>');
						content.push('	<input class="input-medium " id="startCode" value="'+data[0]+'"/>');
						content.push('	<input class="input-medium " id="endCode" value="'+data[1]+'"/>');
						content.push('</div>');
						
						$("#messageAlert").html(content.join(''));
						$("#modalDiv").show();
						$("#modalDiv").modal({
							backdrop:true
						});
					}
				});
				return false;
			}
			
			function confirmMessage(elem){
				var startCode = $("#startCode").val();
				var endCode = $("#endCode").val();

				var minCode = $("#minCode").val();
				var maxCode = $("#maxCode").val();
				
				$(elem).attr("data-dismiss", "");
				if(isNaN(startCode) || isNaN(endCode) || parseInt(startCode) < parseInt(minCode) || parseInt(endCode) < parseInt(maxCode)){
					alert("请输入在["+ minCode +"]和["+ maxCode +"]之间的值");
				}else{
					if(parseInt(startCode) > parseInt(endCode)){
						alert("起始码不能大于结束码");
					}else{
						$(elem).attr("data-dismiss", "modal");
						window.open("${pageContext.request.contextPath}/print.jsp?codeIssueId="+codeIssueId+"&startCode="+startCode+"&endCode="+endCode);
					}
				}
			}
			
		</script>
		
	</body>

</html>