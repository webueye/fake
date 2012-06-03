<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<tbody>
							<tr>
								<th  class="th" colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/code-issue/edit-new">
											箱码/防伪码生成
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>标题</th>
								<th>包装箱规格</th>
								<th>数量</th>
								<th>码长度</th>
								<th>码类型</th>
								<th>生成日期</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="codeIssue" items="${page.datas}">
								<tr>
									<td>${codeIssue.id}</td>
									<td>${codeIssue.issueName}</td>
									<td>${codeIssue.boxSpec.specName}</td>
									<td>${codeIssue.issueCount}</td>
									<td>${codeIssue.codeLength}</td>
									<td>${codeIssue.codeType? '箱码': '防伪码'}</td>
									<td>${codeIssue.createDateTime}</td>
									<td>
										<a href="${pageContext.request.contextPath}/code-issue/edit/${codeIssue.id}">${codeIssue.codeType? '箱码': '防伪码'}记录</a>
										<a href="${pageContext.request.contextPath}/code-issue/show/${codeIssue.id}">详情</a>
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