<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-spec">	
					
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="8">供货列表</th>
							</tr>
							
							<tr>
								<td>单据编号</td>
								<td>经销商</td>
								<td>发货日期<td>
								<td>状态</td>
								<td>操作员</td>
								<td>备注</td>
								<td>供货明细</td>
							</tr>
							<tr>
								<td>8676868</td>
								<td>HMH</td>
								<td>2012/6/9<td>
								<td>新建</td>
								<td>admin</td>
								<td>紧急</td>
								<td><a>明细</a></td>
							</tr>
							
						</tbody>
							
					</table>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>