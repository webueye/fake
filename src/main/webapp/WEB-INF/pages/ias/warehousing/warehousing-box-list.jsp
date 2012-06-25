<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>在库箱码列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/warehousing-box/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">箱码</span>
					            <input class="input-large" name="boxCode" value="${boxCode}" style="width: 100px;" placeholder="箱码"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th  class="th" colspan="8" align="right">
									<div align="left" style="margin-left:7px;">
											在库箱码列表
									</div>
								</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>箱码号</th>
								<th>包装箱规格</th>
								<th>箱容量</th>
								<th>箱码状态</th>
								<th>入库日期</th>
								<th>防伪码信息</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="warehousingBox" items="${page.datas}">
								<tr>
									<td>${warehousingBox.boxCode.id}</td>
									<td>
										<u:valueFormat value="${warehousingBox.boxCode.boxCode}"/>
									</td>
									<td>
										<u:valueFormat value="${warehousingBox.boxCode.boxSpec.specName}"/>
									</td>
									<td>
										<u:valueFormat value="${warehousingBox.boxCode.boxSpec.capacity}"/>
									</td>
									<td>
										<u:valueFormat value="${warehousingBox.boxCode.status.value}"/>
									</td>
									<td>
										<u:dateFormat value="${warehousingBox.createDateTime}"/>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/fake-code/boxcode?filter_LIKES_boxCode.boxCode=${warehousingBox.boxCode.boxCode}">防伪码列表</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="8" name="colspan"/>
							</jsp:include>
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="warehousing-box"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>