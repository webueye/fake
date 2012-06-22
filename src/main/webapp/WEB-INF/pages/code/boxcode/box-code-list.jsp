<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>箱码记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-code/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码号 </span>
					            <input id="categoryCode" class="input-large" name="filter_LIKES_boxCode" value="${filter_LIKES_boxCode}" style="width: 120px;" placeholder="类型名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="7">箱码列表</th>
							</tr>							
							<tr>
								<th class="${page.orderBy == 'id'? page.className: ''}" onclick="javascript: common.sort('id', 'desc');" title="点击按编号排序">编号</th>
								<th class="${page.orderBy == 'boxCode'? page.className: ''}" onclick="javascript: common.sort('boxCode', 'asc');" title="点击按箱码号排序">箱码号</th>
								<th>包装箱规格</th>
								<th>箱容量</th>
								<th>箱码状态</th>
								<th>生成日期</th>
								<th>防伪码</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="boxCode" items="${page.datas}">
								<tr>
									<td>${boxCode.id}</td>
									<td>
										<u:valueFormat value="${boxCode.boxCode}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.boxSpec.specName}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.boxSpec.capacity}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.status.value}"/>
									</td>
									<td>
										<u:dateFormat value="${boxCode.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/fake-code/boxcode?filter_LIKES_boxCode.boxCode=${boxCode.boxCode}">防伪码</a>
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