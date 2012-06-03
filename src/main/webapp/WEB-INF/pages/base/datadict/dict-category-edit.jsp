<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>字典分类修改</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/dict-category/update/${dictCategory.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">字典类型代码：</th>
								<td class="ltd">
									<input name="id" value="${dictCategory.id}" type="hidden"/>
									<input class="input-xlarge required" id="categoryCode" name="categoryCode" value="${dictCategory.categoryCode}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">字典类型名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" id="categoryName" name="categoryName" value="${dictCategory.categoryName}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">字典类型说明：</th>
								<td class="ltd">
									<input class="input-xlarge" id="categoryDesc" name="categoryDesc" value="${dictCategory.categoryDesc}" type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">提&nbsp;&nbsp;&nbsp;交</button>
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
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("#validateForm").validate();
			});
		</script>
		
	</body>

</html>