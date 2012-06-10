<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码与明码绑定</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				
				<div align="center"><font color="red">
					${boxCodeNotExist != null? '箱码不存在': ''} ${boxCodeNotExist}
					${codeList != null? '列表中显示的明码不存在': ''}
					${success == 'success'? '绑定成功': ''}
				</font></div>
				
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-code/bind">	
					
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="5">箱码与明码绑定：</th>
							</tr>
							
							<tr>
								<th>明码/箱码</th>
								<td>
									<textarea id="codeList" class="required" name="codeList" rows="21" cols="80" data-original-title="提示" 
									data-content="明码与箱码绑定，规则如下：<br/>明码：001,002,003,003；<br/>箱码：01则输入格式为：<br/>001<br/>002<br/>003<br/>01<br/>即箱码则为列表中的最后一位">${codeList}</textarea>
								</td>
								<td>
									<button type="submit" class="btn btn-primary">绑&nbsp;&nbsp;&nbsp;定</button>
								</td>
							</tr>
							
						</tbody>
							
					</table>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			$('#codeList').popover();
		</script>
		
	</body>

</html>