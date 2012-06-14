<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ueye" uri="ueye" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

	<head>
		<title>经销商供货列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/delivery/search">	
					<div class="search">
						<div class="row">
							<div class="span10">
								<div class="control-group">
						           	<span style="margin-left: 10px;">经销商： </span>
						            <input class="input-large" name="filter_LIKES_purchaseCompany.companyName" value="${filter_LIKES_purchaseCompany_companyName}" style="width: 100px;" placeholder="经销商名称"/>
								  	<button type="submit" class="btn btn-primary">查询</button>
						            <input name="filter_EQL_supplierCompany.id" value="${currentAccount.companyId}" type="hidden"/>
						        </div>
							</div>
						  </div>
					</div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" colspan="8">供货列表</th>
							</tr>
							
							<tr>
								<th>编号</th>
								<th>经销商</th>
								<th>发货日期</th>
								<th>收货日期</th>
								<th>状态</th>
								<th>发货人</th>
								<th>收货人</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							
							<c:forEach var="purchase" items="${page.datas}">
								<tr>
									<td>${purchase.id}</td>
									<td>${purchase.purchaseCompany.companyName}</td>
									<td>
										<ueye:dateFormat value="${purchase.deliveryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<ueye:dateFormat value="${purchase.arrivalDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>${purchase.status.value}</td>
									<td>${purchase.deliveryName}</td>
									<td>${purchase.arrivalName}</td>
									<td>${purchase.memo}</td>
									<td>
										<c:if test="${purchase.status.code == 1}">
											<a href="#" onclick="return showDeliveryModal('${purchase.id}');">发送</a>
										</c:if>
										<c:if test="${purchase.status.code == 4}">
											<a href="${pageContext.request.contextPath}/delivery/complete/${purchase.id}">完成</a>
										</c:if>
										<a href="${pageContext.request.contextPath}/delivery/show/${purchase.id}">明细</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
							
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="delivery"/>
					</jsp:include>
					
				</form>	
						
			</div>
		</div>
		
		
		<div id="deliveryModal" class="modal hide fade in">
            <div class="modal-header">
              	<button data-dismiss="modal" class="close" type="button">X</button>
              	<h3>确认发货</h3>
            </div>
            <div class="modal-body">
            	<h4 style="margin-bottom: 10px;">备注</h4>
              	<span>
              		<input id="deliveryId" name="deliveryId" value="" type="hidden">
              		<textarea id="deliveryMemo" name="deliveryMemo" rows="3" style="width:100%;"></textarea>
              	</span>
            </div>
            <div class="modal-footer">
              	<a data-dismiss="modal" class="btn" href="#">关闭</a>
              	<a class="btn btn-primary" href="#" onclick="deliverySubmit();">发货</a>
            </div>
          </div>
		
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function showDeliveryModal(id){
				$("#deliveryModal").modal({
					backdrop:true
				});
				$("#deliveryId").val(id);
				return false;
			}
			
			function deliverySubmit(){
				var id = $("#deliveryId").val();
				var url = "/delivery/state/" + id;
				var data = {id: id, state: 2, deliveryMemo: $("#deliveryMemo").val()};
				common.post(data, url, function(data){
					alert("已发送");
					$("#deliveryModal").hide();
					$("#validateForm").submit();
				});
			}
		</script>
		
	</body>

</html>