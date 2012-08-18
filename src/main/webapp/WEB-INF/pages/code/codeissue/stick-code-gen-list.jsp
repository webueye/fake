<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>贴码生成记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/code-issue/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">标题 </span>
					            <input class="input-small" name="filter_LIKES_issueName" value="${filter_LIKES_issueName}" placeholder="标题"/>
							  	
							  	<span style="margin-left: 10px;">生成日期： </span>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime}" onFocus="WdatePicker()" placeholder="开始日期"/>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime_}" onFocus="WdatePicker()" placeholder="结束日期"/>
					            
					            <input name="filter_EQS_codeType" value="fakeCode" type="hidden"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="th" colspan="5" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/code-issue/stick-code-gen">
											贴码生成
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="5">码生成记录</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>标题</th>
								<th style="display: none;">包装箱规格</th>
								<th>数量</th>
								<th style="display: none;">码长度</th>
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
									<td style="display: none;">
										<u:valueFormat value="${codeIssue.boxSpec.specName}"/>
									</td>
									<td>
										<u:valueFormat value="${codeIssue.issueCount}"/>
									</td>
									<td style="display: none;">
										<u:valueFormat value="${codeIssue.codeLength}"/>
									</td>
									<td>
										<u:dateFormat value="${codeIssue.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/code-issue/export/${codeIssue.id}">导出</a>&nbsp;
										<a href="${pageContext.request.contextPath}/fang-wei-code/stick?filter_LIKES_codeIssue.issueName=${codeIssue.issueName}">贴码列表</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="5" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="code-issue"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>