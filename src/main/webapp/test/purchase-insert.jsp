<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-spec">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">经销商：</th>
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
								<th class="rth">发货日期：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="specNo" type="text" style="width: 50%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="specName" type="text" style="width: 50%;"/>
								</td>
							</tr>
							
													
						</tbody>
					</table>
					
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="5">发货明细：</th>
							</tr>
							
							<tr>
								<td colspan="5">
									输入箱码:
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
							
							<tr>
								<td colspan="5" align="center">
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
		
	</body>

</html>