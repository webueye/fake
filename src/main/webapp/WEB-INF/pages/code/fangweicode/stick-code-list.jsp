<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>码记录</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fang-wei-code/stick-search">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
								<span style="margin-left: 10px;">生成标题： </span>
					            <input class="input-small" name="filter_LIKES_codeIssue.issueName" value="${filter_LIKES_codeIssue_issueName}"/>
							  	
					           	<span style="margin-left: 10px;">码： </span>
					            <input class="input-small" name="filter_LIKES_codeNo" value="${filter_LIKES_codeNo}" placeholder="码"/>
							  	
							  	<span style="margin-left: 10px;">生成日期： </span>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime}" onFocus="WdatePicker()" placeholder="开始日期"/>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime_}" onFocus="WdatePicker()" placeholder="结束日期"/>
							  	
					            <input name="filter_EQS_codeType" value="stick" type="hidden"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="10">贴码列表</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>生成标题</th>
								<th>贴码</th>
								<th>生成日期</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fangWei" items="${page.datas}">
								<tr>
									<td>${fangWei.id}</td>
									<td>${fangWei.codeIssue.issueName}</td>
									<td>
										<u:valueFormat value="${fangWei.codeNo}"/>
									</td>
									<td>
										<u:dateFormat value="${fangWei.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/faing-wei-code/show/${fangWei.id}">详情</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="10" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="faing-wei-code"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>