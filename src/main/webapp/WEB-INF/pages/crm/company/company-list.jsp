<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商档案管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/company/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">企业名称 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_specName" value="${filter_LIKES_specName}" style="width: 100px;" placeholder="企业名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="9" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/company/edit-new">
											档案添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>企业名称</th>
								<th>销售区域</th>
								<th>销售形式</th>
								<th>员工人数</th>
								<th>业务人数</th>
								<th>状态</th>
								<th>帐号管理</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="company" items="${page.datas}">
								<tr>
									<td>${company.id}</td>
									<td>${company.companyName}</td>
									<td>${company.saleRegionId}</td>
									<td>${company.saleFormId}</td>
									<td>${company.employeeCount}</td>
									<td>${company.businessCount}</td>
									<td>
										<a href="${pageContext.request.contextPath}/company/state/${company.id}">${company.status == false? '禁用': '启用'}</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/account?companyId=${company.id}">帐号管理</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/company/edit/${company.id}">修改</a>
										<a href="${pageContext.request.contextPath}/company/destroy/${company.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="company"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>