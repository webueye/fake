<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>防伪码详细信息</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/box-spec/update/${boxSpec.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="8" align="right">
									<div align="left" style="margin-left:10px;">
											防伪码详细信息
									</div>
								</th>
							</tr>
							<tr class="th">
								<th class="rth">明码：</th>
								<td class="ltd">${fakeCode.plainCode}</td>
								<th class="rth">防伪码</th>
								<td class="ltd">${fakeCode.fakeCode}</td>
							</tr>
							<tr class="th">
								<th class="rth">包装箱规格</th>
								<td class="ltd">${fakeCode.boxSpec.specName}</td>
								<th class="rth">查询次数</th>
								<td class="ltd">${fakeCode.queryCount}</td>
							</tr>
							<tr>
								<th class="rth">查询方式</th>
								<td class="ltd">${fakeCode.queryWayStatus.value}</td>
								<th class="rth">生成日期</th>
								<td class="ltd"><u:dateFormat value="${fakeCode.createDateTime}"/></td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
									</div>
								</td>
							</tr>							
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>