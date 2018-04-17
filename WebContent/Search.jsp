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
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>
<title>Search Professor</title>
</head>

<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
		<span style="float: right;color: #f7f7f7; padding: 3px;"> Logged in as:<c:out
				value="${user.getUserName() }" />| <a style="color: #f7f7f7;" href="logout">Logout</a>
		</span>
		<div class="jumbotron" style="background-color: #3b5998;">
			<h1 style="color: white;text-align: center;float: left">Search Professor</h1>
			<div>
				<a class="btn btn-lg" style="background-color: #3b5998; color: #f7f7f7; border-color: #8b9dc3 ;float: right"
					href="addprofessor">
					Add Professor </a>
			</div>
		</div>
		<c:if test="${deleted != null }" >
			<div class="alert alert-info" role="alert">
				<span id="info"><b>Professor Deleted successfully!!</b></span>
			</div>
		</c:if>
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
		
			<div class="col-lg-6" style="background-color: #8b9dc3;padding: 10px;">
			<h4>Search By Professor</h4>
				<form action="search" method="post">
					<div>
						<label for="firstname">First Name</label>
					</div>
					<div>
						<input class="form-control" id="firstname" name="firstname"
							value="${fn:escapeXml(param.firstname)}">
					</div><br>
					<div>
						<label for="lastname">Last Name</label>
					</div>
					<div>
						<input class="form-control" id="lastname" name="lastname"
							value="${fn:escapeXml(param.lastname)}">
					</div>
					<br>
					<div>
						<input type="submit" class="btn btn-lg" value="Search" style="background-color: #3b5998; color: #f7f7f7; float:right">
					</div>
				</form>
			</div>
			
			<div class="col-lg-6" style="background-color: #DADADA;padding: 10px;">
			<h4>Search By University</h4>
				<form action="search?univ=true" method="post">
					<div>
						<label for="universityname">University Name</label>
					</div>
					<div>
					<select class="form-control" id="universityname" name="universityname">
					<c:forEach items="${universities }" var="university">
						<option value="${university.getUniversityId()}">
						${university.getName()}
						</option>
					</c:forEach>
					
					</select>
					</div>
					
					<br><br><br><br><br>
					<div>
						<input type="submit" class="btn btn-lg" value="Search" style="background-color: #3b5998; color: #f7f7f7;float: right">
					</div>
				</form>
			</div>
		</div>

		<br> <br>
		<c:if test="${professors!=null && professors.size()!=0 }">
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Name</th>
						<th scope="col">University</th>
						<th scope="col">Rank</th>
						<th scope="col">Joining Year</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${professors}" var="professor">
						<tr>
							<th scope="row"><a
								href="professorreview?id=<c:out value="${professor.getProfessorId()}"/>">
									<c:out value="${professor.getFirstName()}" /> <c:out
										value="${professor.getLastName()}" />
							</a></th>
							<td><c:out
									value="${ professor.getTeachingUniversity().getName() }" /></td>
							<td><c:out value="${ professor.getRank()}" /></td>
							<td>
							<c:if test="${professor.getJoiningYear()==0 }"> <c:out value="Not Known" /></c:if>
							<c:if test="${professor.getJoiningYear()!=0 }"> <c:out value="${professor.getJoiningYear()}" /></c:if>
							</td>
							<td>
							<c:if test="${user.getRoles()=='ADMIN' }">
								<a href="deleteprofessor?id=<c:out value="${professor.getProfessorId() }"/>" >Delete</a></c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
<script type="text/javascript">

if(<%= request.getParameter("universityname") %> != null){
	val2 = '<%= request.getParameter("universityname") %>'
	$('#universityname').val(val2);}
	

</script>
</html>