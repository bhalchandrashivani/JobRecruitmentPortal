<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applied Jobs Posted</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"	crossorigin="anonymous">
<link	href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface"	rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="	crossorigin="anonymous"></script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
		<div class="container-fluid">
        <header class="header">
            <div class="row">
                <div class="menu_main col-xs-4" style="background-color:#05ffb0;">
                    <h4 >JOBFAIR WORLD<br/><p>Welcome, ${name.fname}</p></h4>
                </div>              
            </div>
                <div class="menu_main_content col-xs-10">
                    <div class = "dropdown">
                        <div class="col-xs-3 droplink" style="background-color:#05ffb0;" ><a href="#" style="color: black; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Account<span class="caret"></span></a>
                            <div class="dropdown-content">
                                <a href="${contextPath}/logout.htm">Logout</a>
                            </div></div></div>
                     <div class="col-xs-4" style="background-color:#05ffb0;"><a href="${contextPath}/student/alljobs.htm?name=${name}" style="color: black; text-decoration:none">View all jobs</a></div>
                    <div class="col-xs-5" style="background-color:#05ffb0;"><a href="${contextPath}/student/viewMyJobs.htm?name=${name}" style="color: black; text-decoration:none">View my applied jobs</a></div>
                </div>
        </header>
		<div>
			<center><h2>Your applications</h2></center>
			<form action = "${contextPath}/student/withdrawMyApplication.htm" method="get">
			<table class = "table"  border="1">						
				<c:forEach var="entry" items="${map}">
				<c:forEach var="j" items="${entry.value}">
				<input type="hidden" name = "jobID" value = "${j.id}" />
					<tr>
						<td><b>ID</b></td>
						<td>${j.id}</td>
					</tr>
					
					<tr>
						<td><b>Job ID</b></td>
						<td>${j.jobID}</td>
					</tr>
					<tr>
						<td><b>Title</b></td>
						<td>${j.jobTitle}</td>
					</tr>
					<tr>
						<td><b>Company Name</b></td>
						<td>${j.companyName}</td>
					</tr>
					<tr>
						<td><b>Job Type</b></td>
						<td>${j.jobType}</td>
					</tr>
					<tr>
						<td><b>Location</b></td>
						<td>${j.state}, ${j.country}</td>
					</tr>
					<tr>
						<td><b>Industry</b></td>
						<td>${j.industry}</td>
					</tr>
					<tr>
						<td><b>Job For</b></td>
						<td>${j.major}</td>
					</tr>
					<tr>
						<td><b>Job-URL</b></td>
						<c:if test= "${empty j.jobUrl}"> 
						<td>Please check the company's website</td></c:if>
						<c:if test= "${not empty j.jobUrl}"> 
						<td>${j.jobUrl}</td></c:if>
					</tr>
					<tr>
						<td><b>Description</b></td>
						<c:if test= "${empty j.description}"> 
						<td>No description provided</td></c:if>
						<c:if test= "${not empty j.description}"> 
						<td>${j.description}</td></c:if>
					</tr>
					<tr>
						<td><b>Posted On</b></td>
						<td>${j.jobpostedOn}</td>
					</tr>
					<tr><td></td><td>
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Withdraw application</button>
					</td>								
				</c:forEach>
				</c:forEach>

			<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red"><i class="fas fa-exclamation-triangle"></i> Warning: Withdraw application?</h4>
        </div>
        <div class="modal-body">
          <p>Are you sure you want withdraw this application? You would have to re-apply.</p>
        </div>
        <div class="modal-footer">
          
          <button type="submit" class="btn btn-danger btn-lg">Yes</button>
        
          <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">No</button>
                  </div>
    </div></div></div>
           </table></form>
</div>
  </div>
        <div><br><br></div>

</body>
</html>