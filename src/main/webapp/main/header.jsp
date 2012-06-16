<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Header</title>
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap-responsive.css" rel="stylesheet" />
	</head>

	<body>
	
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner" style="min-height: 75px;">
				<div class="container-fluid">
					<a class="brand" href="#" onclick="return false;" style="font-size: 25px; color: white; font: bold;">防伪防窜子系统</a>
					
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i> 
							${currentAccount.username} 
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/login/logout">退出</a></li>
						</ul>
					</div>
	
				</div>
			</div>
		</div>
	
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/twitter/bootstrap-dropdown.js"></script>
	
	</body>
	
</html>