<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>包装箱种类管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-spec/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">种类名称 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_specName" value="${filter_LIKES_specName}" style="width: 100px;" placeholder="类型名称"/>
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
										<a href="${pageContext.request.contextPath}/box-spec/edit-new">
											包装箱种类添加
										</a>
									</div>
								</th>
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
							<c:forEach var="boxSpec" items="${page.datas}">
								<tr>
									<td>${boxSpec.id}</td>
									<td>${boxSpec.specName}</td>
									<td>${boxSpec.product.name}</td>
									<td>${boxSpec.capacity}</td>
									<td>${boxSpec.status == false? '禁用': '启用'}</td>
									<td>${boxSpec.createDateTime}</td>
									<td>${boxSpec.memo}</td>
									<td>
										<a href="${pageContext.request.contextPath}/box-spec/edit/${boxSpec.id}">修改</a>
										<a href="${pageContext.request.contextPath}/box-spec/destroy/${boxSpec.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
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