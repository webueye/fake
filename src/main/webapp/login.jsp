<%@ page language="java" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>

	<head>
		<title>防伪防窜子系统</title>
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap-responsive.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/twitter/docs.css" rel="stylesheet" />
	</head>

	<body>

		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
				</div>
			</div>
		</div>


    	<div class="container">

			<header class="masthead">
				<div class="inner">
					<h1>防伪防窜子系统</h1>
				</div>
			</header>

			<div class="row" align="center">
	
				<form class="form-horizontal" method="post"
					action="${pageContext.request.contextPath }/login" name="loginForm">
					<fieldset style="font-size: 20px;">
						<div style="margin-bottom: 25px;color:red;">
							${param.msg == 'accountNotExist'? '用户不存在': ''}
							${param.msg == 'passwordNotCorrect'? '密码不正确': ''}
							${param.msg == 'accountInactive'? '用户已被冻结,无法登陆': ''}
							${param.msg == 'captchaNotCorrect'? '验证码不正确': ''}
						</div>
						
						<div class="control-group">
							<div>
								<span>用户名:</span> 
								<input class="input-xlarge focused" name="username" type="text" placeholder="username" style="width: 300px; height: 25px;"/>
							</div>
						</div>
	
						<div class="control-group">
							<div>
								<span>密&nbsp;&nbsp;&nbsp;码:</span> 
								<input class="input-xlarge focused" name="password" type="password" placeholder="password" style="width: 300px; height: 25px;"/>
							</div>
						</div>
	
						<div class="control-group">
							<div>
								<span>验证码:</span> 
								<input class="input-xlarge focused" name="captcha" type="text" placeholder="captcha" style="width: 130px; height: 25px;"/>
								<img style="width: 100px; height: 35px; cursor: pointer;" id="captchaImg" onclick="changeCaptcha();" src="${pageContext.request.contextPath}/servlet/captcha.jpg"/>
								<span style="cursor: pointer;" onclick="changeCaptcha();">换一张</span>
							</div>
						</div>
						
						<div class="">
							<button type="submit" class="btn btn-primary btn-large">登&nbsp;&nbsp;&nbsp;陆</button>
						</div>
					</fieldset>
				</form>
	
			</div>

			<hr class="soften"/>
		</div>

		<script type="text/javascript">
		<!--
			if (window.self != window.top) {
				window.open(".", "_top");
			}
			document.forms["loginForm"].elements["username"].focus();
			
			function changeCaptcha(){
				document.getElementById("captchaImg").src = "${pageContext.request.contextPath}/servlet/captcha.jpg?" + (new Date()).valueOf(); ;
			}
			
		// -->
		</script>

	</body>

</html>
