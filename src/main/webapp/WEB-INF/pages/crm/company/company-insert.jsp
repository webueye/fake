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
				<div class="grayDiv">
					<div class="divFont"><b>经销商档案添加</b></div>
				</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/company">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">企业名称：</th>
								<td class="ltd">
									<input name="parent.id" value="${currentAccount.company.id}" type="hidden"/>
									<input class="input-xlarge required" name="companyName" type="text" style="width: 80%"/>
								</td>
								<th class="rth">状态：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="status" value="true" type="radio" checked="checked"/>启用
									<input class="input-xlarge required" name="status" value="false" type="radio" style="margin-left: 20px;"/>禁用
								</td>
							</tr>
							<tr class="th">
								<th class="rth">企业编号：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="compnayNo" type="text" style="width: 80%"/>
								</td>
								<th class="rth">企业类型：</th>
								<td class="ltd">
									<select name="companyType.id" style="width:80%;">
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
									<select name="saleRegion.id" style="width:80%;">
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
									<select name="companyNature.id" style="width:80%;">
										<option value="">--请选择--</option>
										<c:forEach var="companyNature" items="${companyNatures}">
											<option value="${companyNature.id}">${companyNature.name}</option>
										</c:forEach>
									</select>
								</td>
								<th class="rth">销售形式：</th>
								<td class="ltd">
									<select name="saleForm.id" style="width:80%;">
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
							
							<!-- 法人信息 -->
							<tr>
								<th  class="th" colspan="4" align="right">
									<div align="left" style="margin:6px;">
										<b>法人信息</b>
									</div>
								</th>
							</tr>
							<tr>
								<th>姓名</th>
								<td><input class="input-xlarge required" name="corporation.name" type="text" style="width: 80%"/></td>
								<th>出生年月</th>
								<td><input class="input-xlarge" name="corporation.birthday" onFocus="WdatePicker({isShowClear:false, dateFmt:'yyyy-MM-dd'})" type="text" style="width: 80%"/></td>
							</tr>
							<tr>
								<th>身份证号</th>
								<td><input class="input-xlarge" name="corporation.idCard" type="text" style="width: 80%"/></td>
								<th>性别</th>
								<td>
									<input class="input-xlarge" name="corporation.sex" value="1" type="radio"/>男
									<input class="input-xlarge" name="corporation.sex" value="2" style="margin-lefet: 10px;" type="radio"/>女
									<input class="input-xlarge" name="corporation.sex" value="3" style="margin-left: 10px;" type="radio"/>未知
								</td>
							</tr>
							<tr>
								<th>籍贯</th>
								<td><input class="input-xlarge" name="corporation.nativePlace" type="text" style="width: 80%"/></td>
								<th>手机</th>
								<td><input class="input-xlarge" name="corporation.mobile" type="text" style="width: 80%"/></td>
							</tr>
							<tr>
								<th>办公电话</th>
								<td><input class="input-xlarge" name="corporation.officePhone" type="text" style="width: 80%"/></td>
								<th>邮箱</th>
								<td><input class="input-xlarge email" name="corporation.email" type="text" style="width: 80%"/></td>
							</tr>
							
							<!-- 业务主管信息 -->
							<tr>
								<th  class="th" colspan="4" align="right">
									<div align="left" style="margin:6px;">
										<b>业务主管信息</b>
									</div>
								</th>
							</tr>
							<tr>
								<th>姓名</th>
								<td><input class="input-xlarge required" name="business.name" type="text" style="width: 80%"/></td>
								<th>出生年月</th>
								<td><input class="input-xlarge" name="business.birthday" onFocus="WdatePicker({isShowClear:false, dateFmt:'yyyy-MM-dd'})" type="text" style="width: 80%"/></td>
							</tr>
							<tr>
								<th>身份证号</th>
								<td><input class="input-xlarge" name="business.idCard" type="text" style="width: 80%"/></td>
								<th>性别</th>
								<td>
									<input class="input-xlarge" name="business.sex" value="1" type="radio"/>男
									<input class="input-xlarge" name="business.sex" value="2" style="margin-left: 10px;" type="radio"/>女
									<input class="input-xlarge" name="business.sex" value="3" style="margin-left: 10px;" type="radio"/>未知
								</td>
							</tr>
							<tr>
								<th>籍贯</th>
								<td><input class="input-xlarge" name="business.nativePlace" type="text" style="width: 80%"/></td>
								<th>手机</th>
								<td><input class="input-xlarge" name="business.mobile" type="text" style="width: 80%"/></td>
							</tr>
							<tr>
								<th>办公电话</th>
								<td><input class="input-xlarge" name="business.officePhone" type="text" style="width: 80%"/></td>
								<th>邮箱</th>
								<td><input class="input-xlarge email" name="business.email" type="text" style="width: 80%"/></td>
							</tr>
							
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">添&nbsp;&nbsp;&nbsp;加</button>
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