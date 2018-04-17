<%@ page import="review.dao.CoursesDao" %>
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
<title>Post Review</title>
</head>
<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
		<span style="float: right;color: #f7f7f7; padding: 3px;"> Logged in as:<c:out
				value="${user.getUserName() }" />| <a style="color: #f7f7f7;" href="logout">Logout</a>
		</span>
		<form action="postreview?id=<c:out value="${id}"/>" method="post">
			<div class="jumbotron" style="background-color: #3b5998;">
				<h1 style="color: white;text-align: center; float: left">Post Review</h1>
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
				<label for="professor">Professor</label>
			</div>
			<div>
				<input class="form-control" type="text" disabled id="professor"
					name="professor"
					value="${professor.getFirstName() }${professor.getLastName()}">
				<input type="hidden" class="form-control" id="pid" name="pid"
					value="${professor.getProfessorId()}">
			</div>
			<br>
			<div>
				<label for="university">University</label>
			</div>
			<div>
				<select class="form-control" id="university" name="university"
					onchange="location.href='postreview?id=<c:out value="${id}"/>&univId='+ this.options[this.selectedIndex].value;">
					<option value=0>Select</option>
					<c:forEach items="${university }" var="univ">
						<c:if test="${univId==univ.getUniversityId() }">
							<option value="${univ.getUniversityId()}" selected><c:out
									value="${univ.getName()}" /></option>
						</c:if>
						<c:if test="${univId!=univ.getUniversityId() }">
							<option value="${univ.getUniversityId()}"><c:out
									value="${univ.getName()}" /></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<br>
			<div>
				<label for="coursename">CourseName</label>
			</div>
			<div class="row">
				<div class="col-lg-10">
					<select class="form-control" id="coursename" name="coursename">
						<option value=0>Select</option>
						<c:forEach items="${ courses}" var="course">
							<option value="${course.getCourseId()}"><c:out
									value="${course.getCourseName()}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<a class="btn btn-md" href="#mymodal" data-toggle="modal"
						id="mybtn" data-target="mymodal" style="background-color: #3b5998; color: #f7f7f7; border-color: #8b9dc3 ;">
						 Add Course</a>
				</div>
			</div>
			<br>
			<div>
				<label for="description">Description</label>
			</div>
			<div>
				<textarea class="form-control" rows="3" id="description"
					name="description">${fn:escapeXml(param.description)}</textarea>
			</div>
			<br>

			<div>
				<label for="difficulty">Difficulty Level</label>
			</div>
			<div>
				<select class="form-control" id="difficulty" name="difficulty">
					<option selected value="HARD">HARD</option>
					<option selected value="MEDIUM">MEDIUM</option>
					<option selected value="EASY">EASY</option>
				</select>
			</div>

			<br>
			<div>
				<label for="coursework">CourseWork Level</label>
			</div>
			<div>
				<select class="form-control" id="coursework" name="coursework">
					<option selected value="HEAVY">HEAVY</option>
					<option selected value="MEDIUM">MEDIUM</option>
					<option selected value="LIGHT">LIGHT</option>
				</select>
			</div>

			<br>
			<div>
				<label for="yearattended">Year Attended</label>
			</div>
			<div>
				<input class="form-control" type="text" id="yearattended"
					name="yearattended" value="${fn:escapeXml(param.yearattended)}" />
			</div>
			<br>
			<div class="form-check">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox" id="showname"
					name="showname"> Anonymous
				</label>
			</div><br>
			<div>
				<input type="submit" class="btn btn-lg" style="background-color: #3b5998; color: #f7f7f7"> 
				 <a style="font-size: 20px;"
					href="professorreview?id=<c:out value="${professor.getProfessorId()}"/>">Cancel</a>
			</div>
		</form>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="mymodal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header" style="background-color: #3b5998;">
					<h4 class="modal-title" id="myModalLabel" style="color: #f7f7f7;text-align: center; float: left">
					Add Course</h4>
					
					<button type="button" class="close" data-dismiss="modal" style="color: #f7f7f7;"
						id="modalclose" >
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>

				</div>

				<!-- Modal Body -->
				<div class="modal-body">
				<div style="padding: 2px 20px 2px 20px">
					<span>Please enter all details</span>
						<form action="addcourse" method="post">
							<input class="form-control" type="hidden" id="mprofessorid"
									name="mprofessorid" value="${id}">
							<div>
								<label for="mcoursecode">CourseCode</label>
							</div>
							<div>
								<input class="form-control" type="text" id="mcoursecode"
									name="mcoursecode" value="${fn:escapeXml(param.mcoursecode)}">
							</div>
							<br>
							<div>
								<label for="mcoursename">CourseName</label>
							</div>
							<div>
								<input class="form-control" type="text" id="mcoursename"
									name="mcoursename" value="${fn:escapeXml(param.mcoursename)}">
							</div>
							<br>
							<div>
								<label for="mdepartment">Department</label>
							</div>
							<div>
								<input class="form-control" type="text" id="mdepartment"
									name="mdepartment" value="${fn:escapeXml(param.mdepartment)}">
							</div>
							<br>
							<div>
								<label for="muniversity">University</label>
									</div>
									<div>
										<select class="form-control" id="muniversity" name="muniversity">
											<c:forEach items="${university }" var="univ">
												<option value="${univ.getUniversityId()}"><c:out
														value="${univ.getName()}" /></option>
											</c:forEach>
										</select>
									</div>
							<br>
							<button id="submitbutton" class="submitbutton btn btn-lg" style="background-color: #3b5998; color: #f7f7f7">
								Add</button>
						</form>

				</div>
			</div>
		</div>
		</div>
		</div>
</body>
<script type="text/javascript">

	$(document).ready(function() {
		
		$('#mybtn').click(function(e) {
			
				$('#mymodal').modal({show : true});
			
		});
	});
	
</script>
</html>