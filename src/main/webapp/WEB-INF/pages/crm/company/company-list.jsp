<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商档案管理</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/company/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">企业名称 </span>
					            <input class="input-small" name="filter_LIKES_companyName" value="${filter_LIKES_companyName}" placeholder="企业名称"/>
							  	
							  	<span style="margin-left: 10px;">客户类型 </span>
					           	<select name="filter_EQL_companyType.id" class="input-small">
					           		<option value="">请选择</option>
					           		<c:forEach var="companyType" items="${companyTypes}">
					           			<option value="${companyType.id}" ${companyType.id == filter_EQL_companyType_id? 'selected': ''}>${companyType.name}</option>
					           		</c:forEach>
					           	</select>
							  	
							  	<span style="margin-left: 10px;">销售区域 </span>
					           	<select name="filter_EQL_saleRegion.id" class="input-small">
					           		<option value="">请选择</option>
					           		<c:forEach var="saleRegion" items="${saleRegions}">
					           			<option value="${saleRegion.id}" ${saleRegion.id == filter_EQL_saleRegion_id? 'selected': ''}>${saleRegion.name}</option>
					           		</c:forEach>
					           	</select>
							  	
							  	<span style="margin-left: 10px;">状态 </span>
					            <select name="filter_EQB_status" class="input-small">
					           		<option value="">请选择</option>
					           		<option value="1" ${filter_EQB_status == '1'? 'selected': ''}>启用</option>
					           		<option value="0"  ${filter_EQB_status == '0'? 'selected': ''}>禁用</option>
					           	</select>
							  	<button type="submit" class="btn btn-primary">查询</button>
					            <input class="input-large" name="filter_EQL_parent.id" value="${currentAccount.company.id}" type="hidden"/>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th  class="th" colspan="10" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/company/edit-new">
											经销商添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th  class="gray" colspan="10" align="right">
									经销商管理
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>企业名称</th>
								<th>客户类型</th>
								<th>销售区域</th>
								<th>销售形式</th>
								<th>员工人数</th>
								<th>业务人数</th>
								<th>状态</th>
								<th>帐号管理</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="company" items="${page.datas}">
								<tr>
									<td>${company.id}</td>
									<td>
										<u:valueFormat value="${company.companyName}"/>
									</td>
									<td>
										<u:valueFormat value="${company.companyType.name}"/>
									</td>
									<td>
										<u:valueFormat value="${company.saleRegion.name}"/>
									</td>
									<td>
										<u:valueFormat value="${company.saleForm.name}"/>
									</td>
									<td>
										<u:valueFormat value="${company.employeeCount}"/>
									</td>
									<td>
										<u:valueFormat value="${company.businessCount}"/>
									</td>
									<td>
										<a onclick="return handleState('${pageContext.request.contextPath}/company/state/${company.id}');" href="#">${company.status == false? '禁用': '启用'}</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/company/account-list/${company.id}">帐号管理</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/company/edit/${company.id}">修改</a>
										<a href="${pageContext.request.contextPath}/company/destroy/${company.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="10" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="company"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			var stateUrl;
			function handleState(url){
				$("#messageAlert").html("是否更改状态?");
				$("#modalDiv").show();
				$("#modalDiv").modal({
					backdrop:true
				});
				stateUrl = url;
				return false;
			}
			
			function confirmMessage(){
				window.location.href = stateUrl;
			}
			
		</script>
		
	</body>

</html>