<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>

	<head>
		<title>批次跟踪</title>
		<jsp:include page="/common/header.jsp"/>
		<style type="text/css">
			.ltd{
				font-size:10pt;
				color:#000000;
				text-align:left;
				LINE-HEIGHT: 22px;
				padding-left:3;
				padding-top:0;
				padding-right:0;
				padding-bottom:0;
				border-radius: 0 0 0 0;
				border-left: 0px;
			}
		</style>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/trace/batch">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码 </span>
					            <input class="input-small" name="filter_LIKES_boxCode" value="${filter_LIKES_boxCode}" placeholder="箱码"/>
					           	
					           	<span style="margin-left: 10px;">产品名称 </span>
					            <input class="input-small" name="filter_LIKES_boxSpec.product.name" value="${filter_LIKES_boxSpec_product_name}" placeholder="产品名称"/>
							  	
							  	<span style="margin-left: 10px;">状态 </span>
					            <select name="filter_EQI_statusCode" class="input-small">
					           		<option value="">请选择</option>
					           		<option value="1" ${filter_EQI_statusCode == '1'? 'selected': ''}>生成</option>
					           		<option value="2" ${filter_EQI_statusCode == '2'? 'selected': ''}>入库</option>
					           		<option value="3" ${filter_EQI_statusCode == '3'? 'selected': ''}>在途</option>
					           		<option value="-2" ${filter_EQI_statusCode == '-2'? 'selected': ''}>废除</option>
					           		<option value="-1" ${filter_EQI_statusCode == '-1'? 'selected': ''}>售完</option>
					           		<option value="0" ${filter_EQI_statusCode == '0'? 'selected': ''}>冻结</option>
					           	</select>
							  	
							  	<span style="margin-left: 10px;">生成日期： </span>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime}" onFocus="WdatePicker()" placeholder="开始日期"/>
					            <input class="input-small" name="filter_BAD_createDateTime" value="${filter_BAD_createDateTime_}" onFocus="WdatePicker()" placeholder="结束日期"/>
					            
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="gray" colspan="9">批次跟踪列表</th>
							</tr>							
							<tr>
								<th>编号</th>
								<th>产品名称</th>
								<th>批次</th>
								<th>箱数量</th>
								<th>位置</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="boxCodeGroup" items="${page.datas}">
								<c:set var="state" value="false"/>
								<c:set var="groupCompany" value="false"/>
								<c:forEach var="boxCode" items="${boxCodeGroup.boxCodes}">
									<tr>
										<td>
											${boxCode.id}
										</td>
										<td>
											<u:valueFormat value="${boxCode.boxSpec.product.name}"/>
										</td>
										<c:if test="${state == 'false'}">
											<td rowspan="${boxCodeGroup.size}">${boxCodeGroup.produceDate}</td>
											<td rowspan="${boxCodeGroup.size}">${boxCodeGroup.size}</td>
											<td rowspan="${boxCodeGroup.size}" style="background-color:#FFFFFF;padding:0px;">
												<table style="height:100%;">
												<c:if test="${groupCompany == 'false'}">
													<c:forEach var="groupCompany" items="${boxCodeGroup.groupCompany}">
														<tr>
															<td style="border-left: 0px;background-color:#FFFFFF;border-top:0px;">${groupCompany.storageCompany.companyName}</td>
															<td style="border-left: 0px;background-color:#FFFFFF;border-top:0px;">${groupCompany.size}</td>
														</tr>
													</c:forEach>
												</c:if>
												</table>
											</td>
											<c:set var="state" value="true"/>
										</c:if>
									</tr>
								</c:forEach>
								<c:set var="groupCompany" value="true"/>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="10" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="box-code"/>
					</jsp:include>
					
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>