<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>产品添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="grayDiv">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bolder;">
					     <b>产品修改</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/product/update/${product.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">产品名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="name" value="${product.name}" type="text"/>
								</td>
								<th class="rth">货号：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="productNo" value="${product.productNo}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">条形码：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="barCode" value="${product.barCode}" type="text"/>
								</td>
								<th class="rth">立即上架：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="status" value="true" type="radio" ${product.status? 'checked': ''}/>是
									<input class="input-xlarge required" name="status" value="false" type="radio" ${product.status? '': 'checked'} style="margin-left: 20px;"/>否
								</td>
							</tr>
							<tr class="th">
								<th class="rth">分类：</th>
								<td class="ltd">
									<select name="productCategory.id" style="width:80%;" class="required">
										<option value="">--请选择--</option>
										<c:forEach var="productCategory" items="${productCategories}">
											<option value="${productCategory.id}" ${product.productCategory.id == productCategory.id? 'selected': ''}>${productCategory.name}</option>
										</c:forEach>
									</select>
								</td>
								<th class="rth">品牌：</th>
								<td class="ltd"> 
									<select name="brand.id" style="width:80%;" class="required">
										<option value="">--请选择--</option>
										<c:forEach var="brand" items="${brands}">
											<option value="${brand.id}" ${brand.id == product.brand.id? 'selected': ''}>${brand.brandName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">建议零售价：</th>
								<td class="ltd">
									<input class="input-xlarge required number" name="marketPrice" value="${product.marketPrice}" type="text"/>
								</td>
								<th class="rth">保持期：</th>
								<td class="ltd">
									<input class="input-xlarge required number" name="retentionPeriod" value="${product.retentionPeriod}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">规格：</th>
								<td class="ltd">
									<input class="input-xlarge" name="spec" value="${product.spec}" type="text"/>
								</td>
								<th class="rth">重量：</th>
								<td class="ltd">
									<input class="input-xlarge" name="" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">描述：</th>
								<td class="ltd" colspan="3">
									<textarea name="productDesc" rows="3" style="width: 80%;">${product.productDesc}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">修&nbsp;&nbsp;&nbsp;改</button>
										<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
									</div>
								</td>
							</tr>							
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>