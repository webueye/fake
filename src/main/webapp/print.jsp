﻿<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
	<head>
		<title>打印</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css" media="screen">
		html, body { height:100%; background-color: #ffffcc;}
		body { margin:0; padding:0; overflow:hidden; }
		#flashContent { width:100%; height:100%; }
		</style>
	</head>
	<body>
		<div id="flashContent" style="margin:auto; text-align:center;">
			<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="900" height="600" id="print" align="middle">
				<param name="movie" value="${pageContext.request.contextPath}/images/swf/print.swf" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#ffffcc" />
				<param name="play" value="true" />
				<param name="loop" value="true" />
				<param name="wmode" value="direct" />
				<param name="FlashVars" value="urlGetData=${pageContext.request.contextPath}/box-code/gen-bar-code" />
				<param name="scale" value="showall" />
				<param name="menu" value="true" />
				<param name="devicefont" value="true" />
				<param name="salign" value="" />
				<param name="allowScriptAccess" value="sameDomain" />
				<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/images/swf/print.swf" width="900" height="600">
					<param name="movie" value="${pageContext.request.contextPath}/images/swf/print.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#ffffcc" />
					<param name="play" value="true" />
					<param name="loop" value="true" />
					<param name="FlashVars" value="urlGetData=${pageContext.request.contextPath}/box-code/gen-bar-code" />
					<param name="wmode" value="direct" />
					<param name="scale" value="showall" />
					<param name="menu" value="true" />
					<param name="devicefont" value="true" />
					<param name="salign" value="" />
					<param name="allowScriptAccess" value="sameDomain" />
				<!--<![endif]-->
					<a href="http://www.adobe.com/go/getflash">
						<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" />
					</a>
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
		</div>
	</body>
</html>
