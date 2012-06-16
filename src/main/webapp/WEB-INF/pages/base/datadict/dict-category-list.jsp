<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>字典分类</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="get" action="${pageContext.request.contextPath}/dict-category">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">类型编码 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_categoryCode" value="${filter_LIKES_categoryCode}" style="width: 100px;" placeholder="类型编码"/>
					           	<span style="margin-left: 10px;">类型名称 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_categoryName" value="${filter_LIKES_categoryName}" style="width: 100px;" placeholder="类型名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="7" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/dict-category/edit-new">
											分类添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>字典类型编码</th>
								<th>字典类型名称</th>
								<th>字典类型说明</th>
								<th>添加日期</th>
								<th>字典数据</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="dictCategory" items="${page.datas}">
								<tr>
									<td>${dictCategory.id}</td>
									<td>${dictCategory.categoryCode}</td>
									<td>${dictCategory.categoryName}</td>
									<td>${dictCategory.categoryDesc}</td>
									<td>
										<u:dateFormat value="${dictCategory.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/data-dict/list/${dictCategory.id}">字典数据</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/dict-category/edit/${dictCategory.id}">修改</a>
										<a href="${pageContext.request.contextPath}/dict-category/destroy/${dictCategory.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="dict-category"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>