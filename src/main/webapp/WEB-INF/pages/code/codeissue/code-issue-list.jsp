<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码/防伪码生成记录</title>
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
					            <input id="categoryCode" class="input-large" name="filter_LIKES_issueName" value="${filter_LIKES_issueName}" style="width: 100px;" placeholder="类型名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="th" colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/code-issue/edit-new">
											箱码/防伪码生成
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="8">码生成记录</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>标题</th>
								<th>包装箱规格</th>
								<th>数量</th>
								<th>码长度</th>
								<th>码类型</th>
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
										<u:valueFormat value="${codeIssue.codeLength}"/>
									</td>
									<td>${codeIssue.codeType? '箱码': '防伪码'}</td>
									<td>
										<u:dateFormat value="${codeIssue.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/code-issue/export/${codeIssue.id}">导出</a>&nbsp;
										<a href="${pageContext.request.contextPath}/code-issue/code/${codeIssue.id}">${codeIssue.codeType? '箱码': '防伪码'}列表</a>
									</td>
								</tr>
							</c:forEach>
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