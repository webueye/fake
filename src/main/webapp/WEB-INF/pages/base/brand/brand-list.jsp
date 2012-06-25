<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>品牌列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/brand/search">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
					           	<span style="margin-left: 10px;">品牌编码 </span>
					            <input class="input-large" name="filter_LIKES_brandSpell" value="${filter_LIKES_brandSpell}" style="width: 100px;" placeholder="品牌编码"/>
					           	<span style="margin-left: 10px;">品牌名称 </span>
					            <input class="input-large" name="filter_LIKES_brandName" value="${filter_LIKES_brandName}" style="width: 100px;" placeholder="品牌名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="th" colspan="7" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/brand/edit-new">
											品牌添加
										</a>
									</div>
								</th>
							</tr>
							<tr>
								<th class="gray" colspan="7">品牌列表</th>
							</tr>
							<tr>
								<th>编号</th>
								<th>品牌编码</th>
								<th>品牌名称</th>
								<th>品牌链接</th>
								<th>Logo链接</th>
								<th>品牌状态</th>
								<th>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="brand" items="${page.datas}">
								<tr>
									<td>${brand.id}</td>
									<td>
										<u:valueFormat value="${brand.brandSpell}"/>
									</td>
									<td>
										<u:valueFormat value="${brand.brandName}"/>
									</td>
									<td>
										<a href="${brand.link}">${brand.link}</a>
									</td>
									<td>
										<a href="${brand.logoLink}">${brand.logoLink}</a>
									</td>
									<td>${brand.status? '启用': '禁用'}</td>
									<td>
										<a href="${pageContext.request.contextPath}/brand/edit/${brand.id}">修改</a>
										<a href="${pageContext.request.contextPath}/brand/destroy/${brand.id}" class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="7" name="colspan"/>
							</jsp:include>							
							
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="brand"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>