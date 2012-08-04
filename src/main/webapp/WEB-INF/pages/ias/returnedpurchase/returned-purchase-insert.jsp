<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>退货</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
				<div class="search">
					<div style="margin-left: 10px;margin-bottom: 10px;font: bold;">
					     <b>退货信息</b>
					</div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/returned-purchase">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">供应商：</th>
								<td class="ltd"><b>${currentAccount.company.parent.companyName}</b></td>
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd">
									<textarea name="memo" rows="3" style="width:80%"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="th">
								<th class="rth" colspan="5">退货明细：</th>
							</tr>
							
							<tr>
								<td>输入箱码:</td>
								<td colspan="3">
									<textarea class="required" id="boxCodeValues" name="boxCodeValues" rows="3" style="width: 90%;"></textarea>
									<label id="boxCodeValuesVerify" for="boxCodeValues" class="error" style="display: none;">请输入输入箱码</label>
								</td>
								<td>
									<button type="button" class="btn btn-primary" onclick="boxCode.group();">显示退货明细</button>
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
						           	<button type="submit" class="btn btn-primary">提交退货信息</button>
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