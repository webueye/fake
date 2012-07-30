<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>箱码跟踪</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-trace/search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码 </span>
					            <input class="input-small" name="filter_LIKES_boxCode.boxCode" value="${filter_LIKES_boxCode_boxCode}" placeholder="箱码"/>
					           	
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-small" name="filter_LIKES_boxCode.boxSpec.product.name" value="${filter_LIKES_boxCode_boxSpec_product_name}" placeholder="产品名称"/>
							  	
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="9">箱码轨迹列表</th>
							</tr>							
							<tr>
								<th>编号</th>
								<th>箱码号</th>
								<th>包装箱规格</th>
								<th>产品名称</th>
								<th>所在公司[位置]</th>
								<th>记录类型</th>
								<th class="${page.orderBy == 'traceDateTime'? page.className: 'cursor'}" onclick="javascript: common.sort('traceDateTime', 'desc');" title="点击按记录日期排序">记录日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="boxTrace" items="${page.datas}">
								<tr>
									<td>${boxTrace.id}</td>
									<td>
										<u:valueFormat value="${boxTrace.boxCode.boxCode}"/>
									</td>
									<td>
										<u:valueFormat value="${boxTrace.boxCode.boxSpec.specName}"/>
									</td>
									<td>
										<u:valueFormat value="${boxTrace.boxCode.boxSpec.product.name}"/>
									</td>
									<td>
										<u:valueFormat value="${boxTrace.company.companyName}"/>
									</td>
									<td>
										<u:valueFormat value="${boxTrace.eventType.value}"/>
									</td>
									<td>
										<u:dateFormat value="${boxTrace.traceDateTime}"/>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="9" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="trace/retention"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>