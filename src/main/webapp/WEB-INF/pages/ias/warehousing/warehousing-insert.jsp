<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码贴箱入库</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
			
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>箱码贴箱入库</b>
					</div>
				</div>
				
				<div align="center" style="margin: 10px;"><font color="red">${param.msg == 'success'? '已成功入库': ''}</font></div>
				
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/warehousing">	
					
					<table class="table table-bordered table-striped">
						<thead>
							
							<tr>
								<td><b>输入箱码</b></td>
								<td colspan="3">
									<input name="company.id" value="${currentAccount.companyId}" type="hidden"/>
								
									<textarea id="boxCodeValues" name="boxCodeValues" rows="5" cols="80"></textarea>
									<label id="boxCodeValuesVerify" for="boxCodeValues" class="error" style="display: none;">请输入输入箱码</label>
								</td>
								<td>
									<button type="button" class="btn btn-primary" onclick="boxCode.group();">添加</button>
								</td>
							</tr>
							
							<tr>
								<th>货号</th>
								<th>货品名称</th>
								<th>箱数</th>
								<th>总数</th>
								<th>箱码/包装类型/容量</th>
							</tr>
							
						</thead>
						
						<tbody id="detailContent">
							
						</tbody>
							
					</table>
					
					<div class="search" align="center">
						<div class="row">
							<div class="span12">
								<div class="control-group">
						           	<button type="submit" class="btn btn-primary">提&nbsp;&nbsp;&nbsp;交</button>
									<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
						        </div>
							</div>
						  </div>
					</div>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>