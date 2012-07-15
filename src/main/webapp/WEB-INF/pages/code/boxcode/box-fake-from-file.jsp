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
				
				<div align="center" style="margin: 10px;">
					<font color="red">
						<b>
							${msg == 'error'? '绑定失败': ''}
							${msg == 'success'? '绑定成功': ''}
						</b>
					</font>
				</div>
				
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/box-code/upload-to-bind" enctype="multipart/form-data">	
					
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="5">箱码与明码绑定：</th>
							</tr>
							
							<tr>
								<th>明码/箱码</th>
								<td>
									<input class="{required:true,accept:'txt'}" id="codeFile" name="codeFile" type="file"/>
									<label style="display: none;" for="codeFile" class="error">请选择一个文本文件(*.txt)</label>
								</td>
								<td>
									<button type="submit" class="btn btn-primary">上传并绑定</button>
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