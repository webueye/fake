<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${dataDict.dictCategory.categoryName}数据修改</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/data-dict/update/${dataDict.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">数据编码：</th>
								<td class="ltd">
									<input name="id" value="${dataDict.id}" type="hidden"/>
									<input name="dictCategory.id" value="${dataDict.dictCategory.id}" type="hidden"/>
									<input class="input-xlarge required" name="code" value="${dataDict.code}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">数据名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="name" value="${dataDict.name}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">数据状态：</th>
								<td class="ltd"> 
									启用<input class="input-xlarge required" name="status" value="true" style="margin-right: 20px;" type="radio" ${dataDict.status != false? 'checked': ''}/>
									禁用<input class="input-xlarge required" name="status" value="false" type="radio" ${dataDict.status == false? 'checked': ''}/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">数据描述：</th>
								<td class="ltd">
									<input class="input-xlarge" name="memo" value="${dataDict.memo}" type="text"/>
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