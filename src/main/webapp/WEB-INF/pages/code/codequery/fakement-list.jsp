<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>假货警告记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/code-query/fakement">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">假货编码 </span>
					            <input class="input-small" name="filter_LIKES_codeNo" value="${filter_LIKES_codeNo}" placeholder=""/>
							  	
					            <input type="hidden"  name="filter_EQI_queryResult" value="-1"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="10">假货警告列表</th>
							</tr>
							<tr>
								<th class="${page.orderBy == 'id'? page.className: ''}" onclick="javascript: common.sort('id', 'desc');" title="点击按编号排序">编号</th>
								<th>假货编码</th>
								<th>查询方式</th>
								<th>查询结果</th>
								<th>用户编号</th>
								<th>用户手机</th>
								<th>查询时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="codeQuery" items="${page.datas}">
								<tr>
									<td>${codeQuery.id}</td>
									<td>
										<u:valueFormat value="${codeQuery.codeNo}"/>
									</td>
									<td>
										<u:valueFormat value="${codeQuery.queryWayStatus.value}"/>
									</td>
									<td>
										<u:valueFormat value="${codeQuery.codeQueryResult.value}"/>
									</td>
									<td>
										<u:valueFormat value="${codeQuery.userNo}"/>
									</td>
									<td>
										<u:valueFormat value="${codeQuery.userPhone}"/>
									</td>
									<td>
										<u:dateFormat value="${codeQuery.queryDateTime}"/>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="10" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="code-query"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>