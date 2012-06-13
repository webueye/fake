<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商采购列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/delivery/search">	
					<div class="search">
						<div class="row">
							<div class="span10">
								<div class="control-group">
						           	<span style="margin-left: 10px;">供应商： </span>
						            <input class="input-large" name="filter_LIKES_supplierCompany.companyName" value="${filter_LIKES_supplierCompany_companyName}" style="width: 100px;" placeholder="供应商名称"/>
								  	<button type="submit" class="btn btn-primary">查询</button>
						            <input name="filter_EQL_purchaseCompany.id" value="${currentAccount.companyId}" type="hidden"/>
						        </div>
							</div>
						  </div>
					</div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="8">采购列表</th>
							</tr>
							
							<tr>
								<th>编号</th>
								<th>供应商</th>
								<th>发货日期</th>
								<th>收货日期</th>
								<th>状态</th>
								<th>操作员</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							
							<c:forEach var="purchase" items="${page.datas}">
								<tr>
									<td>${purchase.id}</td>
									<td>${purchase.supplierCompany.companyName}</td>
									<td>
										<ueye:dateFormat value="${purchase.deliveryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<ueye:dateFormat value="${purchase.arrivalDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>${purchase.status.value}</td>
									<td>${purchase.creater}</td>
									<td>${purchase.memo}</td>
									<td>
										<c:if test="${purchase.status.code == 2}">
											<a href="${pageContext.request.contextPath}/delivery/detail/${purchase.id}">收货</a>
										</c:if>
										<a href="${pageContext.request.contextPath}/delivery/show/${purchase.id}">明细</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
							
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="delivery"/>
					</jsp:include>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>