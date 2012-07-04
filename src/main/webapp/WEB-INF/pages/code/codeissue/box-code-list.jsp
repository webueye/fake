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
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/code-issue/code-search/${codeIssue.id}">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码 </span>
					            <input class="input-small" name="filter_LIKES_boxCode" value="${filter_LIKES_boxCode}" placeholder="箱码"/>
					           	
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-small" name="filter_LIKES_boxSpec.product.name" value="${filter_LIKES_boxSpec_product_name}" placeholder="产品名称"/>
							  	
							  	<span style="margin-left: 10px;">状态 </span>
					            <select name="filter_EQI_statusCode" class="input-small">
					           		<option value="">请选择</option>
					           		<option value="1" ${filter_EQI_statusCode == '1'? 'selected': ''}>生成</option>
					           		<option value="2" ${filter_EQI_statusCode == '2'? 'selected': ''}>入库</option>
					           		<option value="3" ${filter_EQI_statusCode == '3'? 'selected': ''}>在途</option>
					           		<option value="-2" ${filter_EQI_statusCode == '-2'? 'selected': ''}>废除</option>
					           		<option value="-1" ${filter_EQI_statusCode == '-1'? 'selected': ''}>售完</option>
					           		<option value="0" ${filter_EQI_statusCode == '0'? 'selected': ''}>冻结</option>
					           	</select>
							  	
							  	<span style="margin-left: 10px;">生成日期： </span>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime}" onFocus="WdatePicker()" placeholder="开始日期"/>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime_}" onFocus="WdatePicker()" placeholder="结束日期"/>
					            
							  	<button type="submit" class="btn btn-primary">查询</button>
					           
					            <input name="filter_EQL_codeIssue.id" value="${codeIssue.id}" type="hidden"/>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="9">箱码列表</th>
							</tr>							
							<tr>
								<th class="${page.orderBy == 'id'? page.className: 'cursor'}" onclick="javascript: common.sort('id', 'desc');" title="点击按编号排序">编号</th>
								<th class="${page.orderBy == 'boxCode'? page.className: 'cursor'}" onclick="javascript: common.sort('boxCode', 'asc');" title="点击按箱码号排序">箱码号</th>
								<th>包装箱规格</th>
								<th>产品名称</th>
								<th>箱容量</th>
								<th>箱码状态</th>
								<th>所在公司[位置]</th>
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
										<u:valueFormat value="${boxCode.boxSpec.product.name}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.boxSpec.capacity}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.status.value}"/>
									</td>
									<td>
										<u:valueFormat value="${boxCode.storageCompany.companyName}"/>
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