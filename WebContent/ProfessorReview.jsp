<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>

<title>Professor Details / Reviews</title>
</head>
<body style="background-color: #EFF1F3">
	<div class="container theme-showcase" role="main">
		<span style="float: right;color: #f7f7f7; padding: 3px;"> Logged in as:<c:out
				value="${user.getUserName() }" />| <a href="logout" style="color: #f7f7f7;">Logout</a>
		</span>
		<div class="jumbotron" style="background-color: #3b5998;">
			<h1 style="color: #f7f7f7;text-align: center; float: left">
				<c:out value="${professor.getFirstName() }" />
				<c:out value="${professor.getLastName() }" />
				<span class="badge badge-success"><c:out value="${rating }" /></span>
			</h1>
			<div style="float: right">
				<a class="btn btn-lg" style="background-color: #3b5998; color: #f7f7f7; border-color: #8b9dc3 ;"  
				href="postreview?id=<c:out value="${professor.getProfessorId()}"/>">
					Post Review 
				</a><span>   </span>
				<a href="search" class="btn btn-lg" style="background-color: #3b5998; color: #f7f7f7; border-color: #8b9dc3 ;"> Search </a>
			</div>
		</div>
		
		<c:if test="${deleted != null }" >
			<div class="alert alert-info" role="alert">
				<span id="info"><b>Review Deleted successfully!!</b></span>
			</div>
		</c:if>
		
		<div style="font-size: 18px;">
			<ul style="float: left;">
				<li style="padding: 5px;"><strong><span
						style="width: 200px; display: inline-block;">Current
							University:</span></strong> <span>${professor.getTeachingUniversity().getName() }</span>
				</li>
				
				<li style="padding: 5px;"><strong style="width: 200px; display: inline-block;">Bachelors:</strong>
				<c:if test="${professor.getBachelors().getName()==null }"><c:out value="Not Known"/></c:if>
					<span>${professor.getBachelors().getName() }</span>
				</li>
				
				<li style="padding: 5px;"><strong style="width: 200px; display: inline-block;">Masters:</strong>
				<c:if test="${professor.getMasters().getName()==null }"><c:out value="Not Known"/></c:if>
					<span>${professor.getMasters().getName() }</span></li>
				
				<li style="padding: 5px;"><strong style="width: 200px; display: inline-block;">PHD:</strong>
				<c:if test="${professor.getPhd().getName()==null }"><c:out value="Not Known"/></c:if>
					<span>${professor.getPhd().getName() }</span></li>
				
				<li style="padding: 5px;"><strong style="width: 200px; display: inline-block;">Rank:</strong>
					<span>${professor.getRank() }</span></li>
				
				<li style="padding: 5px;"><strong style="width: 200px; display: inline-block;">Website:</strong>
					<a href="${professor.getUrl() }">${professor.getUrl() }</a></li>
			</ul>
			<span style="float: right;"> <c:if
					test="${professor.getPhotoUrl()!=null}">
					<img src="${professor.getPhotoUrl()}" alt="Not Available"
						style="width: 170px; height: 200px; margin-right: 15px;" />
				</c:if>
			</span>
		</div>
		<br> <br>
		<div style="clear: both">
			<ul class="nav nav-tabs">
				<li style="width: 10%" class="nav-link active"><a
					data-toggle="tab" href="#home">Home</a></li>
				<c:forEach items="${courses}" var="course">
					<li style="width: 10%" class="nav-item"><a class="nav-link"
						data-toggle="tab"
						href="<c:out value="${hash }${course.getCourseId()}" />"> <c:out
								value="${course.getCourseCode()}" />
					</a></li>
				</c:forEach>
			</ul>

			<div class="tab-content">
				<div id="home" class="tab-pane active container">
					<br>
					<h3>Switch Courses to view Reviews</h3>
				</div>
				<c:forEach items="${reviews.keySet()}" var="courseid">
					<div id=<c:out value="${courseid }"/> class="tab-pane container">
						<br>
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th><span>${courseMap.get(courseid).getCourseName()}</span></th>

									<th><span>${courseMap.get(courseid).getUniversity().getName()}</span></th>
								</tr>
							</thead>
						</table>

						<table class="table table-striped table-bordered">
							<thead class="thead-dark">
								<tr>
									<th style="width: 10%;" scope="col" class="table-warning">Posted
										By</th>
									<th style="width: 75%;" scope="col">Reviews</th>
									<th style="width: 15%;" scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reviews.get(courseid) }" var="review">
									<tr>
										<td class="table-warning"><span> <c:if
													test="${review.isShowName()==true }">
													<c:out value="${review.getStudent().getFirstName() }" />
												</c:if> <c:if test="${review.isShowName()==false }">
	  										Anonymous
	  									</c:if>
										</span></td>

										<td><span> <c:out
													value="${review.getReviewDescription() }" /> <br> <c:if
													test="${review.getDifficulty()!=null }">
													<strong>Difficulty:</strong>
													<c:out value="${review.getDifficulty().name() }" />
												</c:if> <c:if test="${review.getCourseWork()!=null }">
													<strong> CourseWork:</strong>
													<c:out value="${review.getCourseWork().name() }" />
												</c:if> <br> <c:if test="${review.getYearAttended()!=null }">
													<strong> Year Attended:</strong>
													<c:out value="${review.getYearAttended() }" />
												</c:if>
										</span></td>

										<!-- Like Dislike Comments Delete -->
										<td><a href="#l${review.getReviewId()}" id="l${review.getReviewId()}" class="like-class"> Like </a><span
											class="badge badge-primary" id="bl${review.getReviewId()}">${review.getHelpful() }</span> <br>
											<a href="#d${review.getReviewId()}" id="d${review.getReviewId()}" class="dislike-class"> Dislike </a>
											<span class="badge badge-primary" id="bd${review.getReviewId()}">${review.getNotHelpful() }</span>
											<br> <a href="#mymodal" data-toggle="modal"
											class="mybtn" data-target="mymodal"
											id=${ review.getReviewId() }>Comments</a><br>
											<c:if test="${user.getRoles()=='ADMIN' }">
											<a href="deletereview?pid=<c:out value="${professor.getProfessorId() }"/>&rid=<c:out value="${review.getReviewId()}"/>" >
											Delete</a></c:if> <input
											type="hidden" id="reviewhidden" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="mymodal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header" style="background-color: #3b5998;">
					<h4 style="color: white;text-align: center;float: left" class="modal-title" id="myModalLabel">Comments</h4>
					<button type="button" class="close" data-dismiss="modal" id="modalclose" onclick="clearModalInput()">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>

				</div>

				<!-- Modal Body -->
				<div class="modal-body"></div>
				<div style="padding: 20px">
					<div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<input type="checkbox" aria-label="Anonymous Checkbox"
										name="showname" id="showname">
								</div>
							</div>
							<input type="text" class="form-control" name="addcomment"
								id="addcomment" placeholder="Check to post as anonymous"
								aria-label="Add Comment" aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button id="${reviewId }" class="submitbutton btn btn-md" style="background-color: #3b5998; color: #f7f7f7">
								Add</button>

							</div>
						</div>
						<!-- </form> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
function clearModalInput(){
	{$("#addcomment").val="";}
}

	$(document).ready(function() {
		var url = "http://localhost:8080/ReviewInstructors/addcomment?reviewid=";
		$('.mybtn').click(function(e) {
			$('#reviewhidden').val(e.currentTarget.id);
			console.log("HERE", e.currentTarget.id);
			$('.modal-body').load(url + e.currentTarget.id,function(result) {
				$('#mymodal').modal({show : true});
			});
		});

		$('.submitbutton').click(function(e) {
			var url = "http://localhost:8080/ReviewInstructors/addcomment?reviewid=";
			e.preventDefault();
			$.ajax({
				async : false,
				url : url+ $('#reviewhidden').val(),
				type : "post",
				data : {showname : $('#showname').prop('checked'),
						addcomment : $('#addcomment').val()
						},
				success : function(msg) {}
				});
											
			$('.modal-body').load(url + $('#reviewhidden').val(),
				function(result) {
					$('#mymodal').modal({show : true});
			});
		});
		
		$('.like-class').click(function(e) {
			var url = "http://localhost:8080/ReviewInstructors/likereview?ReviewId=";
			e.preventDefault();
			
			$.ajax({
				async : false,
				url : url+ (""+(e.currentTarget.id)).substring(1),
				type : "post",
				data : { },
				success : function(msg) {}
				});
				
			var text1 = $('#b'+e.currentTarget.id).text();
			$('#b'+e.currentTarget.id).text(Number(text1)+1);
		});
		
		$('.dislike-class').click(function(e) {
			var url = "http://localhost:8080/ReviewInstructors/dislikereview?ReviewId=";
			e.preventDefault();
			
			$.ajax({
				async : false,
				url : url+ (""+(e.currentTarget.id)).substring(1),
				type : "post",
				data : { },
				success : function(msg) {}
				});
				
			var text1 = $('#b'+e.currentTarget.id).text();
			$('#b'+e.currentTarget.id).text(Number(text1)+1);
		});
		
		
	});
</script>
</html>