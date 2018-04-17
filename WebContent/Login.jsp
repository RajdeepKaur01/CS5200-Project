<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
 <title>Review Instructors Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<style type="text/css">
.jumbotron{
background-image:url(img/main-bg.png);
background-repeat:no-repeat;
background-position:center;
background-size: cover;
margin-bottom:70px;
}
</style>
<title>Login</title>
</head>
<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
		<form action="login" method="post">
			<div class="jumbotron" style="background-color: #3b5998;">
				<h1 style="color: #f7f7f7;text-align: center;">Review Instructors</h1>
			</div>
			<c:if test="${messages.error!=null}">
				<div class="alert alert-danger" role="alert">
					<span id="errorMessage"><b>${messages.error}</b></span>
				</div>
			</c:if>
			<c:if test="${messages.success!=null}">
				<div class="alert alert-success" role="alert">
					<span id="successMessage"><b>${messages.success}</b></span>
				</div>
			</c:if>
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4">
					
					<div>
						<input class="form-control" id="username" name="username" placeholder="Enter Username here"
							value="${fn:escapeXml(param.username)}">
					</div>
					<br>
					
					<div>
						<input class="form-control" id="password" name="password" type="password" placeholder="Enter Password here"
							value="${fn:escapeXml(param.password)}">
					</div>
					<br>
					<div style="text-align: center;">
						<input type="submit" class="btn btn-lg" value="Login" style="background-color: #3b5998; color: #f7f7f7"> <a
							id="register" href="register" style="font-size: 20px;">Register</a>
					</div>
				</div>
				<div class="col-lg-4"></div>
			</div>

		</form>
		<br /> <br />
	</div>
</body>
</html>