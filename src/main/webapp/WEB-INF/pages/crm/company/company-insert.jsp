<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商档案添加</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/company">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">企业名称：</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="companyName" type="text" style="width: 80%"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">企业编号：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="compnayNo" type="text" style="width: 80%"/>
								</td>
								<th class="rth">企业类型：</th>
								<td class="ltd">
									<select name="companyTypeId" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="companyType" items="${companyTypes}">
											<option value="${companyType.id}">${companyType.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">销售区域：</th>
								<td class="ltd">
									<select name="saleRegionId" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="saleRegion" items="${saleRegions}">
											<option value="${saleRegion.id}">${saleRegion.name}</option>
										</c:forEach>
									</select>
								</td>
								<th class="rth">所在城市：</th>
								<td class="ltd">
									<select name="zoneNo" style="width:80%;">
										<option value="">--请选择--</option>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">企业性质：</th>
								<td class="ltd">
									<select name="companyNatureId" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="companyNature" items="${companyNatures}">
											<option value="${companyNature.id}">${companyNature.name}</option>
										</c:forEach>
									</select>
								</td>
								<th class="rth">销售形式：</th>
								<td class="ltd">
									<select name="saleFormId" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="saleForm" items="${saleForms}">
											<option value="${saleForm.id}">${saleForm.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">企业规模：</th>
								<td class="ltd">
									<input class="input-xlarge number" name="scale" type="text" style="width: 80%"/>
								</td>
								<th class="rth">仓库面积：</th>
								<td class="ltd">
									<input class="input-xlarge number" name="depotArea" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">员工人数：</th>
								<td class="ltd">
									<input class="input-xlarge number" name="employeeCount" type="text" style="width: 80%"/>
								</td>
								<th class="rth">业务人员人数：</th>
								<td class="ltd">
									<input class="input-xlarge number" name="businessCount" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">公司地址：</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge" name="address" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">邮编：</th>
								<td class="ltd">
									<input class="input-xlarge number" name="companyZip" type="text" style="width: 80%"/>
								</td>
								<th class="rth">单位帐号：</th>
								<td class="ltd">
									<input class="input-xlarge" name="bankAccountNo" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">开户行：</th>
								<td class="ltd">
									<input class="input-xlarge" name="belongBankName" type="text" style="width: 80%"/>
								</td>
								<th class="rth">开户名：</th>
								<td class="ltd">
									<input class="input-xlarge" name="bankAccountName" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">收货地址：</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge" name="shippingAddress" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">收货人：</th>
								<td class="ltd">
									<input class="input-xlarge" name="shippingMan" type="text" style="width: 80%"/>
								</td>
								<th class="rth">收货电话：</th>
								<td class="ltd">
									<input class="input-xlarge" name="shippingPhone" type="text" style="width: 80%"/>
								</td>
							</tr>
							
							<tr class="th">
								<th class="rth">企业备注：</th>
								<td class="ltd" colspan="3">
									<textarea name="memo" class="input-xlarge" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 54px; margin-left: 0px; margin-right: 0px; width: 80%; ">
									</textarea>
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