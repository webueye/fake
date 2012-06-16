<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${dictCategory.categoryName}数据</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/data-dict/list">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">名称 </span>
					            <input class="input-large" name="filter_LIKES_name" style="width: 100px;" placeholder="名称"/>
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
										<a href="${pageContext.request.contextPath}/data-dict/edit-new/${dictCategory.id}">
											${dictCategory.categoryName}数据添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>编码</th>
								<th>名称</th>
								<th>状态</th>
								<th>描述</th>
								<th>添加日期</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="dataDict" items="${page.datas}">
								<tr>
									<td>${dataDict.id}</td>
									<td>${dataDict.code}</td>
									<td>${dataDict.name}</td>
									<td>${dataDict.status == false? '禁用': '启用'}</td>
									<td>${dataDict.memo}</td>
									<td>
										<u:dateFormat value="${dataDict.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/data-dict/edit/${dataDict.id}">修改</a>
										<a href="${pageContext.request.contextPath}/data-dict/destroy/${dictCategory.id}/${dataDict.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="data-dict/list/${dictCategory.id}"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>