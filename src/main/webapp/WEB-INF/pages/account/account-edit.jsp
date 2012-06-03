<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>用户编辑</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/account/update/${account.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th>所属公司：</th>
								<th colspan="3">${company.companyName}
									<input name="companyId" value="${company.id}" type="hidden"/>
									<input name="id" value="${account.id}" type="hidden"/>
								</th>
							</tr>
							<tr>
								<td>用户编号：</td>
								<td>
									<input class="input-xlarge" name="userNo" value="${account.userNo}" type="text" style="width: 80%"/>
								</td>
								<td>用户昵称：</td>
								<td>
									<input class="input-xlarge" name="nickname" value="${account.nickname}" type="text" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>登陆名称：</td>
								<td>
									<input class="input-xlarge required" name="username" value="${account.username}" type="text" style="width: 80%"/>
								</td>
								<td>登陆密码：</td>
								<td>
									<input class="input-xlarge" name="password" type="password" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>
									<input class="input-xlarge required email" name="email" value="${account.email}" type="text" style="width: 80%"/>
								</td>
								<td>性别：</td>
								<td>
									男<input class="input-xlarge" name="sex" style="margin-right: 20px;" value="true" type="radio" ${account.sex? 'checked': ''}/>
									女<input class="input-xlarge" name="sex" value="false" type="radio" ${account.sex? '': 'checked'}/>
								</td>
							</tr>
							<tr>
								<td>电话：</td>
								<td>
									<input class="input-xlarge" name="phone" value="${account.phone}" type="text" style="width: 80%"/>
								</td>
								<td>手机：</td>
								<td>
									<input class="input-xlarge" name="mobile" value="${account.mobile}" type="text" style="width: 80%"/>
								</td>
							</tr>
							<tr>
								<td>用户类型：</td>
								<td>
									管理员<input class="input-xlarge required" name="admin" style="margin-right: 20px;" value="true" type="radio" ${account.admin? 'checked': ''}/>
									普通用户<input class="input-xlarge required" name="admin" value="false" type="radio" ${account.admin? '': 'checked'}/>
								</td>
								<td>用户状态：</td>
								<td>
									启用<input class="input-xlarge required" name="status" style="margin-right: 20px;" value="true" type="radio" ${account.status? 'checked': ''}/>
									禁用<input class="input-xlarge required" name="status" value="false" type="radio" ${account.status? '': 'checked'}/>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
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