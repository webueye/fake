<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>防伪码记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fake-code/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码 </span>
					            <input class="input-large" name="filter_LIKES_boxCode.boxCode" value="${filter_LIKES_boxCode_boxCode}" style="width: 120px;" placeholder="箱码"/>
					           	<span style="margin-left: 10px;">明 码</span>
					            <input class="input-large" name="filter_LIKES_plainCode" value="${filter_LIKES_plainCode}" style="width: 120px;" placeholder="明码"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th>编号</th>
								<th>箱码</th>
								<th>明码</th>
								<th>防伪码</th>
								<th>包装箱规格</th>
								<th>生成日期</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="fakeCode" items="${page.datas}">
								<tr>
									<td>${fakeCode.id}</td>
									<td>${fakeCode.boxCode.boxCode}</td>
									<td>${fakeCode.plainCode}</td>
									<td>${fakeCode.fakeCode}</td>
									<td>${fakeCode.boxSpec.specName}</td>
									<td>
										<ueye:dateFormat value="${fakeCode.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/fake-code/show/${fakeCode.id}">详情</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="fake-code"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>