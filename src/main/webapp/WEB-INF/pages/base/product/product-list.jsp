<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>产品管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/product/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-large" name="filter_LIKES_name" value="${filter_LIKES_name}" style="width: 100px;" placeholder="产品名称"/>
					           	
					           	<span style="margin-left: 10px;">分类 </span>
					           	<select name="filter_EQL_productCategory.id" style="width: 100px;">
					           		<option value="">请选择</option>
					           		<c:forEach var="productCategory" items="${productCategorys}">
					           			<option value="${productCategory.id}" ${productCategory.id == filter_EQL_productCategory_id? 'selected': ''}>${productCategory.name}</option>
					           		</c:forEach>
					           	</select>
					           	
					           	<span style="margin-left: 10px;">品牌 </span>
					            <select name="filter_EQL_brand.id" style="width: 100px;">
					           		<option value="">请选择</option>
					           		<c:forEach var="brand" items="${brands}">
					           			<option value="${brand.id}" ${brand.id == filter_EQL_brand_id? 'selected': ''}>${brand.brandName}</option>
					           		</c:forEach>
					           	</select>
					           	
					           	<span style="margin-left: 10px;">状态 </span>
					            <select name="filter_EQB_status" style="width: 100px;">
					           		<option value="">请选择</option>
					           		<option value="1" ${filter_EQB_status == '1'? 'selected': ''}>上架</option>
					           		<option value="0"  ${filter_EQB_status == '0'? 'selected': ''}>下架</option>
					           	</select>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
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
								<th  class="gray" colspan="8">产品列表</th>
							</tr>
							<tr>
								<th>产品货号/名称</th>
								<th>分类</th>
								<th>品牌</th>
								<th>条形码</th>
								<th>价格</th>
								<th>保持期[天]</th>
								<th>状态</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${page.datas}">
								<tr>
									<td>
										<u:valueFormat value="${product.productNo}"/>/<u:valueFormat value="${product.name}"/>
									</td>
									<td>
										<u:valueFormat value="${product.productCategory.name}"/>
									</td>
									<td>
										<u:valueFormat value="${product.brand.brandName}"/>
									</td>
									<td>
										<u:valueFormat value="${product.barCode}"/>
									</td>
									<td>
										<u:valueFormat value="${product.marketPrice}"/>
									</td>
									<td>
										<u:valueFormat value="${product.retentionPeriod}"/>
									</td>
									<td>
										${product.status? '上架': '下架'}
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/product/edit/${product.id}">修改</a>
										<a href="${pageContext.request.contextPath}/product/destroy/${product.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="8" name="colspan"/>
							</jsp:include>
							
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