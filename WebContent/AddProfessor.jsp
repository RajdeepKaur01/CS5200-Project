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
<title>Add New Professor</title>
</head>
<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
	<span style="float: right;color: #f7f7f7; padding: 3px;"> Logged in as:<c:out
				value="${user.getUserName() }" />| <a style="color: #f7f7f7;" href="logout">Logout</a>
		</span>
		<form action="addprofessor" method="post">
			<div class="jumbotron" style="background-color: #3b5998;">
				<h1 style="color: #f7f7f7;text-align: center;float: left">Add Professor</h1>
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
			<div class="row">
			<div class="col-lg-6">
				<div>
					<label for="firstname">First Name</label>
				</div>
				<div>
					<input class="form-control" id="firstname" name="firstname"
						value="${fn:escapeXml(param.firstname)}">
				</div>
			</div>
			
			<div class="col-lg-6">
				<div>
					<label for="lastname">Last Name</label>
				</div>
				<div>
					<input class="form-control" id="lastname" name="lastname"
						value="${fn:escapeXml(param.lastname)}">
				</div>
			</div>
			</div>
			<br>
			<div class="row">
			<div class="col-lg-4">
				<div>
					<label for="bachelors">UnderGrad University</label>
				</div>
				<div>
					 <select class="form-control" id="bachelors" name="bachelors">
					 <option value="0">Select</option>
					 	<c:forEach items="${university }" var="univ">
					 	 <option value="${univ.getUniversityId()}"><c:out value="${univ.getName()}"/></option>
					 	</c:forEach>
	 				 </select>
				</div>
			</div>
			<div class="col-lg-4">
				<div>
					<label for="masters">Graduate University</label>
				</div>
				<div>
					 <select class="form-control" id="masters" name="masters">
					 <option value="0">Select</option>
					 	<c:forEach items="${university }" var="univ">
					 	 <option value="${univ.getUniversityId()}"><c:out value="${univ.getName()}"/></option>
					 	</c:forEach>
	 				 </select>
				</div>
			</div>
			
			<div class="col-lg-4">
				<div>
					<label for="phd">PHD University</label>
				</div>
				<div>
					 <select class="form-control" id="phd" name="phd">
					 <option value="0">Select</option>
					 	<c:forEach items="${university }" var="univ">
					 	 <option value="${univ.getUniversityId()}"><c:out value="${univ.getName()}"/></option>
					 	</c:forEach>
	 				 </select>
				</div>
			</div>
			</div>
			<br>
			
			<div>
				<label for="teachinguniversity">Current Teaching University</label>
			</div>
			<div>
				 <select class="form-control" id="teachinguniversity" name="teachinguniversity">
				 	<option value="0">Select</option>
				 	<c:forEach items="${university }" var="univ">
				 	 <option value="${univ.getUniversityId()}"><c:out value="${univ.getName()}"/></option>
				 	</c:forEach>
 				 </select>
			</div>
			<br>
			
			<div>
				<label for="joiningyear">Joining Year</label>
			</div>
			<div>
				<input class="form-control" type="text" id="joiningyear" name="joiningyear"/>
			</div>
			<br>
			
			<div>
				<label for="rank">Rank</label>
			</div>
			<div>
				 <select class="form-control" id="rank" name="rank">
				 	<option>Associate</option>
				 	<option>Full Time</option>
				 	<option>Assistant</option>
				 	<option>Research</option>
				 	<option>Adjunct</option>
				 	<option>Lecturer</option>
				 	<option>Adjunct Associate</option>
				 	<option>Research Assistant</option>
				 	<option> Senior Lecturer</option>
 				 </select>
			</div>
			<br>
			
			<div>
				<label for="url">Professor Site/URL</label>
			</div>
			<div>
				<input class="form-control" id="url" name="url"
					value="${fn:escapeXml(param.url)}">
			</div>
			<br>
			
			<div>
				<label for="photourl">Professor Photo URL</label>
			</div>
			<div>
				<input class="form-control" id="photourl" name="photourl"
					value="${fn:escapeXml(param.photourl)}">
			</div>
			<br>
			
			<div>
				<input type="submit" class="btn btn-lg" class="btn btn-primary" style="background-color: #3b5998; color: #f7f7f7"> <a
					id="cancel" href="search" style="font-size: 20px;">Cancel</a>
			</div>
			
		</form>
	</div>
</body>
</html>