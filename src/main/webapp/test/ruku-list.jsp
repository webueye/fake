<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>入库</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-spec">	
					
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="5">列表</th>
							</tr>
							
							<tr>
								<td colspan="5">
									查询条件--------------------根据查询条件导出
								</td>
							</tr>
							
							<tr>
								<td>货号</td>
								<td>货品名称</td>
								<td>箱数</td>
								<td>总数</td>
								<td>箱码/包装类型/容量</td>
							</tr>
							<tr class="th">
								<th class="rth" colspan="5">显示添加的列表</th>
							</tr>
							
						</tbody>
							
					</table>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>