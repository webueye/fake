<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/purchase/search">	
					<div class="search">
						<div class="row">
							<div class="span10">
								<div class="control-group">
						           	<span style="margin-left: 10px;">经销商： </span>
						            <input class="input-large" name="filter_LIKES_purchaseCompany.companyName" value="${filter_LIKES_purchaseCompany_companyName}" style="width: 100px;" placeholder="经销商名称"/>
								  	<button type="submit" class="btn btn-primary">查询</button>
						            <input name="filter_EQL_supplierId" value="${currentAccount.companyId}" type="hidden"/>
						        </div>
							</div>
						  </div>
					</div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="7">供货列表</th>
							</tr>
							
							<tr>
								<th>编号</th>
								<th>经销商</th>
								<th>发货日期</th>
								<th>状态</th>
								<th>操作员</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							
							<c:forEach var="purchase" items="${page.datas}">
								<tr>
									<td>${purchase.id}</td>
									<td>${purchase.purchaseCompany.companyName}</td>
									<td>
										<ueye:dateFormat value="${purchase.deliveryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>${purchase.status.value}</td>
									<td>${purchase.creater}</td>
									<td>${purchase.memo}</td>
									<td>
										<a href="${pageContext.request.contextPath}/purchase/detail/${purchase.id}">发送</a>
										<a href="${pageContext.request.contextPath}/purchase/show/${purchase.id}">明细</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
							
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="purchase"/>
					</jsp:include>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>