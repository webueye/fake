<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>包装箱种类添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>包装箱种类添加</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-spec">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">种类名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="specName" type="text" style="width: 50%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">编号：</th>
								<td class="ltd">
									<input class="input-xlarge required number {minlength:3, maxlength: 3}" id="specNo" name="specNo" type="text" style="width: 50%;" data-original-title="提示" data-content="编号格式：必须是3位的数字类型，可以以0开头，如：001，002，100， 222"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">产品名称：</th>
								<td class="ltd">
									<select name="product.id" class="required" style="width: 50%;">
										<option value="">--请选择--</option>
										<c:forEach var="product" items="${products}">
											<option value="${product.id}">${product.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">容量：</th>
								<td class="ltd">
									<input class="input-xlarge required number" name="capacity" type="text" style="width: 50%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">数据状态：</th>
								<td class="ltd"> 
									<input class="input-xlarge required" name="status" value="true" type="radio" checked="checked"/>启用
									<input class="input-xlarge required" name="status" value="false" type="radio" style="margin-left: 20px;"/>禁用
								</td>
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd">
									<input class="input-xlarge" name="memo" type="text" style="width: 50%;"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">添&nbsp;&nbsp;&nbsp;加</button>
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
			$('#specNo').popover();
		</script>
		
	</body>

</html>