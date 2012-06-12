<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货详情</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/delivery">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">经销商：</th>
								<td class="ltd">${purchase.purchaseCompany.companyName}</td>
								<th class="rth">发货人：</th>
								<td class="ltd">${purchase.deliveryName}</td>
							</tr>
							<tr>
								<th class="rth">状态：</th>
								<td class="ltd">${purchase.status.value}</td>
								<th class="rth">发货日期：</th>
								<td class="ltd">${purchase.deliveryDateTime}</td>
							
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd" colspan="3">${purchase.memo}</td>
							</tr>
						</tbody>
					</table>
					
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="th">
								<th class="rth" colspan="5">发货明细：</th>
							</tr>
							
							<tr>
								<th>货号</th>
								<th>货品名称</th>
								<th>箱数</th>
								<th>总数</th>
								<th>箱码/包装类型/容量</th>
							</tr>
							
						</thead>
						
						<tbody id="purchaseDetailContent">
							<c:forEach var="boxModel" items="${boxModels}">
								<tr>
									<td>${boxModel.product.productNo}</td>
									<td>${boxModel.product.name}</td>
									<td>${boxModel.boxCount}</td>
									<td>${boxModel.totalCount}</td>
									<td>
										<c:forEach var="boxCode" items="${boxModel.boxCodes}">
											<div>
												[${boxCode.boxCode}] [${boxCode.boxSpec.specName}] [${boxCode.boxSpec.capacity}]
											</div>
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</tbody>
							
					</table>
					<div class="search" align="center">
						<div class="row">
							<div class="span12">
								<div class="control-group">
									<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
						        </div>
							</div>
						  </div>
					</div>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>