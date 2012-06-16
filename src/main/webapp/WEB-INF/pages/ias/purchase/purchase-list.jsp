<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商采购列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/purchase/search">	
					<div class="search">
						<div class="row">
							<div class="span10">
								<div class="control-group">
						           	<span style="margin-left: 10px;">供应商： </span>
						            <input class="input-large" name="filter_LIKES_supplierCompany.companyName" value="${filter_LIKES_supplierCompany_companyName}" style="width: 100px;" placeholder="供应商名称"/>
								  	<button type="submit" class="btn btn-primary">查询</button>
						            <input name="filter_EQL_purchaseCompany.id" value="${currentAccount.companyId}" type="hidden"/>
						        </div>
							</div>
						  </div>
					</div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="8">采购列表</th>
							</tr>
							
							<tr>
								<th>编号</th>
								<th>供应商</th>
								<th>发货人</th>
								<th>收货人</th>
								<th>发货日期</th>
								<th>收货日期</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							
							<c:forEach var="purchase" items="${page.datas}">
								<tr>
									<td>${purchase.id}</td>
									<td>${purchase.supplierCompany.companyName}</td>
									<td>${purchase.deliveryName}</td>
									<td>${purchase.arrivalName}</td>
									<td>
										<ueye:dateFormat value="${purchase.deliveryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<ueye:dateFormat value="${purchase.arrivalDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>${purchase.status.value}</td>
									<td>
										<c:if test="${purchase.status.code == 2}">
											<a href="${pageContext.request.contextPath}/delivery/detail/${purchase.id}" onclick="return showDeliveryModal('${purchase.id}');">收货</a>
										</c:if>
										<a href="${pageContext.request.contextPath}/delivery/show/${purchase.id}">明细</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
							
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="purchase"/>
					</jsp:include>
					
				</form>	
						
			</div>
		</div>
		
		<div id="deliveryModal" class="modal hide fade in">
            <div class="modal-header">
              	<button data-dismiss="modal" class="close" type="button">X</button>
              	<h3>确认收货</h3>
            </div>
            <div class="modal-body">
            	<h4 style="margin-bottom: 10px;">备注</h4>
              	<span>
              		<input id="id" name="id" value="" type="hidden">
              		<textarea id="arrivalMemo" name="arrivalMemo" rows="3" style="width:100%;"></textarea>
              	</span>
            </div>
            <div class="modal-footer">
              	<a data-dismiss="modal" class="btn" href="#">关闭</a>
              	<a class="btn btn-primary" href="#" onclick="deliverySubmit();">收货</a>
            </div>
          </div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function showDeliveryModal(id){
				$("#deliveryModal").modal({
					backdrop:true
				});
				$("#id").val(id);
				return false;
			}
			
			function deliverySubmit(){
				var id = $("#id").val();
				var url = "/delivery/state/" + id;
				var data = {id: id, state: 4, arrivalMemo: $("#arrivalMemo").val()};
				common.post(data, url, function(data){
					alert("已收货");
					$("#deliveryModal").hide();
					$("#validateForm").submit();
				});
			}
		</script>
		
	</body>

</html>