<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>

	<head>
		<meta charset="utf-8">
		<link rel="shortcut icon" href="<%=basePath%>resources/image/favicon2.ico" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>script/jquery-easyui-1.4.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>script/jquery-easyui-1.4.3/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>resources/css/login.css">
		<script type="text/javascript"
			src="<%=basePath%>script/jquery-easyui-1.4.3/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>script/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>script/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>script/md5/md5-min.js"></script>

	</head>
	<body onload='document.loginForm.j_username.focus();'>
		<div id='container'>
			<div id='title'> </div>
			<div id='mid'>
				<div id='left-kuang'>
				</div>
				<div id='right-kuang'>
					<div>
						<c:if test="${not empty param.login_error}">
							<font color="red">登录失败，请重试.<br />
								<br />原因:<c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</font>

						</c:if>
					</div>
					<div id="login-box" >

						<form name='loginForm'
							action="<c:url value='j_spring_security_check' />" method='POST'>
							<table>
								<tr>
									<td>
										<FONT color='#fff'>&nbsp;用&nbsp;户:</FONT>
									</td>
									<td>
										<input class="easyui-textbox"
											style="width: 150px; height: 30px" type='text'
											name='j_username' value=''>
									</td>
								</tr>
								<tr>
									<td>
										<FONT color='#fff'>&nbsp;密&nbsp;码:</FONT>
									</td>
									<td>
										<input class="easyui-textbox"
											style="width: 150px; height: 30px" type='password'
											name='j_password' />
									</td>
								</tr>
								<tr height="20"></tr>
								<tr>
									<td>
									</td>
									<td>
										<input name="submit" type="submit" class="easyui-linkbutton"
											style="width: 150px; height: 30px" value="登&nbsp;&nbsp;陆" />
									</td>
								</tr>
							</table>

						</form>
					</div>
				</div>


			</div>


		</div>
	</body>
</html>
