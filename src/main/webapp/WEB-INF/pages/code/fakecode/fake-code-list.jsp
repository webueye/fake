<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>防伪码记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-code/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">防伪码号 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_fakeCode" value="${filter_LIKES_fakeCode}" style="width: 120px;" placeholder="类型名称"/>
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
								<th>明码</th>
								<th>防伪码</th>
								<th>包装箱规格</th>
								<th>查询次数</th>
								<th>查询方式</th>
								<th>生成日期</th>
								<th>操 作</th>
							</tr>
							<c:forEach var="fakeCode" items="${page.datas}">
								<tr>
									<td>${fakeCode.id}</td>
									<td>${fakeCode.plainCode}</td>
									<td>${fakeCode.fakeCode}</td>
									<td>${fakeCode.boxSpec.specName}</td>
									<td>${fakeCode.queryCount}</td>
									<td>${fakeCode.queryWayStatus.value}</td>
									<td>${fakeCode.createDateTime}</td>
									<td>
										<a href="${pageContext.request.contextPath}/box-code/show/${fakeCode.id}">详情</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="box-code"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>