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
									启用<input class="input-xlarge required" name="status" style="margin-right: 20px;" value="true" type="radio" checked="checked"/>
									禁用<input class="input-xlarge required" name="status" value="false" type="radio"/>
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