<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${dictCategory.categoryName}数据添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/data-dict">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">数据编码：</th>
								<td class="ltd">
									<input name="dictCategory.id" value="${dictCategory.id}" type="hidden"/>
									<input class="input-xlarge required" name="code" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">数据名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="name" type="text"/>
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
								<th class="rth">数据描述：</th>
								<td class="ltd">
									<input class="input-xlarge" id="memo" name="memo" type="text"/>
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