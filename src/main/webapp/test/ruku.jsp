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
								<th class="rth" colspan="5">入库：</th>
							</tr>
							
							<tr>
								<td colspan="5">
									箱码+明码
									<textarea rows="1" cols="80"></textarea>
									添加
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