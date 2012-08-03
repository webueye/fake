<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>用户详情</title>
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
								<th colspan="3">${currentAccount.company.companyName}
									<input name="id" value="${account.id}" type="hidden"/>
								</th>
							</tr>
							<tr>
								<td>用户编号：</td>
								<td>${account.userNo}
								</td>
								<td>用户昵称：</td>
								<td>${account.nickname}
								</td>
							</tr>
							<tr>
								<td>登陆名称：</td>
								<td colspan="3">${account.username}
								</td>
								
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>${account.email}
								</td>
								<td>性别：</td>
								<td>${account.sex? '男': '女'}
								</td>
							</tr>
							<tr>
								<td>电话：</td>
								<td>${account.phone}
								</td>
								<td>手机：</td>
								<td>${account.mobile}
								</td>
							</tr>
							<tr>
								<td>用户类型：</td>
								<td>${account.admin? '管理员': '普通用户'}
								</td>
								<td>用户状态：</td>
								<td>${account.status? '启用': '禁用'}
								</td>
							</tr>
							<tr>
								<td>所属部门：</td>
								<td colspan="1">${account.dept.name}
								</td>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
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
			getDepts("${account.dept.id}");
			
			function getDepts(defaultSelect) {
				$.ajax({
					type : 'post',
					url : '${pageContext.request.contextPath}/dept/nodes',
					dataType : 'json',
					success : function(data) {
						if (data) {
							for ( var i = 0; i < data.length; i++) {
								var op = new Option(data[i].name, data[i].id);
								op.innerHTML = data[i].name;
								if(defaultSelect == data[i].id){
									op.selected = true;
								}
								$("#deptId").append(op);
							}
						}
					}
				});
			}
		</script>
		
	</body>

</html>