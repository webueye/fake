<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码生成</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>生成箱码</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/code-issue">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">标题：</th>
								<td class="ltd">
									<input name="codeType" value="boxCode" type="hidden"/>
									<input class="input-xlarge required" name="issueName" type="text" style="width:50%"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">包装箱种类：</th>
								<td class="ltd">
									<select name="boxSpec.id" class="required" style="width:50%">
										<option value="">--请选择--</option>
										<c:forEach var="boxSpec" items="${boxSpecs}">
											<option value="${boxSpec.id}">${boxSpec.specName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">生成数量：</th>
								<td class="ltd">
									<input class="input-xlarge required number" name="issueCount" type="text" style="width:50%"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd">
									<input class="input-xlarge" name="memo" type="text" style="width:50%"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">生成箱码</button>
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