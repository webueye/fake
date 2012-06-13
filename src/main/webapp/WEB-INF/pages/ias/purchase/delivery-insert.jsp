<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/delivery">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">经销商：</th>
								<td class="ltd">
									<select name="purchaseCompany.id" class="required" style="width: 120px;">
										<option value="">--请选择--</option>
										<c:forEach var="company" items="${companies}">
											<option value="${company.id}">${company.companyName}</option>
										</c:forEach>
									</select>
								</td>
								<th class="rth">发货日期：</th>
								<td class="ltd">
									<input class="input-xlarge" name="deliveryDate"  onFocus="WdatePicker({isShowClear:false, dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" style="width: 120px;"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">备注：</th>
								<td class="ltd" colspan="3">
									<textarea name="memo" rows="3" style="width:80%"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="th">
								<th class="rth" colspan="5">发货明细：</th>
							</tr>
							
							<tr>
								<td>输入箱码:</td>
								<td colspan="3">
									<textarea id="boxCodeValues" name="boxCodeValues" rows="1" cols="80"></textarea>
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