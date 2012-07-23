<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>批次跟踪</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/trace/batch">
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="9">批次跟踪列表</th>
							</tr>							
							<tr>
								<th>编号</th>
								<th>产品名称</th>
								<th>批次</th>
								<th>箱数量</th>
								<th>盒数量</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="productModel" items="${page.datas}">
								<c:set var="state" value="false"/>
								<c:forEach var="batchModel" items="${productModel.batchs}">
									<tr>
										<c:if test="${state == 'false'}">
											<td rowspan="${productModel.size}">
												${productModel.product.id}
											</td>
											<td rowspan="${productModel.size}">
												<u:valueFormat value="${productModel.product.name}"/>
											</td>
											<c:set var="state" value="true"/>
										</c:if>
										<td>${batchModel.batch}</td>
										<td>${batchModel.boxNum}</td>
										<td>${batchModel.fakeNum}</td>
									</tr>
								</c:forEach>
							</c:forEach>
							
						</tbody>
					</table>
					
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>