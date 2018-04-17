<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<title>Register: New User</title>
</head>
<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
		<form action="register" method="post">
			<div class="jumbotron" style="background-color: #3b5998;">
				<h1 style="color: white;text-align: center; float: left">Register</h1>
			</div>
			
			<c:if test="${messages.error!=null}">
				<div class="alert alert-info" role="alert">
					<span id="errorMessage"><b>${messages.error}</b></span>
				</div>
			</c:if>
			
			<c:if test="${messages.success!=null}">
				<div class="alert alert-success" role="alert">
					<span id="successMessage"><b>${messages.success}</b></span>
				</div>
			</c:if>
			
			<div>
				<label for="username">Username</label>
			</div>
			<div>
				<input class="form-control" id="username" name="username"
					value="${fn:escapeXml(param.username)}">
			</div>
			<br>
			<div>
				<label for="password">Password</label>
			</div>
			<div>
				<input class="form-control" id="password" name="password" type="password"
					value="${fn:escapeXml(param.password)}">
			</div>
			<br>
			<div>
				<label for="firstname">First Name</label>
			</div>
			<div>
				<input class="form-control" id="firstname" name="firstname"
					value="${fn:escapeXml(param.firstname)}">
			</div>
			<br>
			<div>
				<label for="lastname">Last Name</label>
			</div>
			<div>
				<input class="form-control" id="lastname" name="lastname"
					value="${fn:escapeXml(param.lastname)}">
			</div>
			<br>
			
			<div>
				<label for="universityid">University</label>
			</div>
			<div>
				 <select class="form-control" id="universityid" name="universityid">
				 	<c:forEach items="${university }" var="univ">
				 	 <option value="${univ.getUniversityId()}"><c:out value="${univ.getName()}"/></option>
				 	</c:forEach>
 				 </select>
			</div>
			<br>
			
			<div>
				<label for="graduationyear">Graduation Year</label>
			</div>
			<div>
				<input class="form-control" type="text" id="graduationyear" name="graduationyear"/>
			</div>
			<br>
			<div>
				<input type="submit" class="btn btn-lg" style="background-color: #3b5998; color: #f7f7f7"> <a
					id="cancel" href="login" style="font-size: 20px;">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>