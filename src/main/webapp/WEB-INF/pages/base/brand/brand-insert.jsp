<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>品牌添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="grayDiv">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bolder;">
					     <b>品牌添加</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/brand">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">品牌拼音：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="brandSpell" type="text" style="width:80%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">品牌名称名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="brandName" type="text" style="width:80%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">品牌官网链接：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="link" type="text" style="width:80%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">品牌Logo链接：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="logoLink" type="text" style="width:80%;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">品牌状态：</th>
								<td class="ltd"> 
									<input class="input-xlarge required" name="status" value="true" type="radio" checked="checked"/>启用
									<input class="input-xlarge required" name="status" value="false" type="radio" style="margin-left: 20px;"/>禁用
								</td>
							</tr>
							<tr class="th">
								<th class="rth">品牌介绍：</th>
								<td class="ltd">
									<textarea name="brandDesc" rows="3" style="width:80%;"></textarea>
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