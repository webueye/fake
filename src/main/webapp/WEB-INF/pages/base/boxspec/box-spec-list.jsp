<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>包装箱种类管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-spec/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">种类名称 </span>
					            <input class="input-large" name="filter_LIKES_specName" value="${filter_LIKES_specName}" style="width: 100px;" placeholder="种类名称"/>
					           	
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-large" name="filter_LIKES_product.name" value="${filter_LIKES_product_name}" style="width: 100px;" placeholder="产品名称"/>
							  	
							  	<span style="margin-left: 10px;">状态 </span>
					            <select name="filter_EQB_status" style="width: 100px;">
					           		<option value="">状态</option>
					           		<option value="1" ${filter_EQB_status == '1'? 'selected': ''}>启用</option>
					           		<option value="0"  ${filter_EQB_status == '0'? 'selected': ''}>停用</option>
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
								<th colspan="8" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/box-spec/edit-new">
											包装箱种类添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="8">包装箱种类列表</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>种类名称</th>
								<th>产品名称</th>
								<th>容量</th>
								<th>状态</th>
								<th>添加日期</th>
								<th>备注</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="boxSpec" items="${page.datas}">
								<tr>
									<td>${boxSpec.id}</td>
									<td><u:valueFormat value="${boxSpec.specName}"/></td>
									<td><u:valueFormat value="${boxSpec.product.name}"/></td>
									<td><u:valueFormat value="${boxSpec.capacity}"/></td>
									<td>${boxSpec.status == false? '禁用': '启用'}</td>
									<td>
										<u:dateFormat value="${boxSpec.createDateTime}"/>
									</td>
									<td><u:valueFormat value="${boxSpec.memo}"/></td>
									<td>
										<a href="${pageContext.request.contextPath}/box-spec/edit/${boxSpec.id}">修改</a>
										<a href="${pageContext.request.contextPath}/box-spec/destroy/${boxSpec.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="8" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="box-spec"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>