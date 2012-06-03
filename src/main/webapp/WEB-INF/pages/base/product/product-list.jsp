<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>产品管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-large" name="filter_LIKES_name" value="${filter_LIKES_name}" style="width: 100px;" placeholder="产品名称"/>
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
										<a href="${pageContext.request.contextPath}/product/edit-new">
											产品添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>产品名称</th>
								<th>货号</th>
								<th>条形码</th>
								<th>价格</th>
								<th>规格</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="product" items="${page.datas}">
								<tr>
									<td>${product.id}</td>
									<td>${product.name}</td>
									<td>${product.productNo}</td>
									<td>${product.barCode}</td>
									<td>${product.marketPrice}</td>
									<td>${product.spec}</td>
									<td>
										<a href="${pageContext.request.contextPath}/product/edit/${product.id}">修改</a>
										<a href="${pageContext.request.contextPath}/product/destroy/${product.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="product"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>