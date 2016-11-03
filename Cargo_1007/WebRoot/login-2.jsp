<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta charset="utf-8">

		<style type="text/css">
#container {
	margin: 0 auto;
	padding: 10px;
}

.hang {
	height: 40px;
}

.label {
	width: 60px;
	float: left;
}

.xiang {
	width: 180px;
	float: left;
}

.xiangb {
	width: 100px;
	float: left;
}
</style>
	</head>
	<body
		style="background-image:url(<%=basePath%>/resources/image/backgroud.jpg);
			background-position: center;
			background-repeat: no-repeat;
			background-attachment: fixed">
		<div id='container'>

			<form action="j_spring_security_check" method="POST">

				<div id='logaria'>

					<div class='hang'>
						<p>
							<span><H2>
									用户登陆
								</H2>
							</span>
						</p>
					</div>
					<div class='hang'>
						<div class='label'>
							用户名：
						</div>
						<div class='xiang'>
							<input type='text' name='j_username' style="height: 30px">
						</div>
					</div>
					<div class='hang'>
						<div class='label'>
							密码:
						</div>
						<div class='xiang'>
							<input type='password' name='j_password' style="height: 30px">
						</div>
					</div>
					<div class='hang'>
						<div class='xiangb'></div>
						<div class='xiangb'>
							<input type="submit" value=' 登陆 ' style="height: 40px">
						</div>
						<div class='xiangb'>
							<input type="reset" value=' 重填 ' style="height: 40px">
						</div>
					</div>
				</div>



			</form>
		</div>


	</body>
</html>
