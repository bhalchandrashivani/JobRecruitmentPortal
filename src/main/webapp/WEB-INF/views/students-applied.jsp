<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
	$(document).ready(function(){
		 $(".alert").hide();
		
		});
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>students-applied</title>

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
                <div class="menu_main_content col-xs-8">
                    <div class = "dropdown">
                        <div class="col-xs-3 droplink" style="background-color:#05ffb0;" ><a href="#" style="color: black; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Account<span class="caret"></span></a>
                            <div class="dropdown-content">
                                <a href="${contextPath}/logout.htm">Logout</a>
                            </div></div></div>
                    <div class="col-xs-3" style="background-color:#05ffb0;"><a href="${contextPath}/employer/postajob.htm?name=${name}" style="color: black; text-decoration:none">Post a Job</a></div>
                    <div class="col-xs-3" style="background-color:#05ffb0;"><a href="${contextPath}/employer/allmyjobposts.htm" style="color: black; text-decoration:none">View my Posts</a></div>
                </div>
        </header>
        <input type="hidden" value= "${id}" name = "id"></input>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
		   <strong>${errorMessage}</strong>
		  <strong>${successMessage}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    X
		  </button>
		</div>
		<div>
			<center><h2>Applications received for this positon are </h2></center>
			
			<table class="table"  border="1">
				<c:forEach var="entry" items="${listuser}">
				<c:forEach var="val" items="${entry.value}">
				
					<tr>
						<td style="color: Blue"><b>First Name</b></td>
						<td>${val.fname}</td>
					</tr>
					<tr>
						<td style="color: Blue"><b>Last Name</b></td>
						<td>${val.lname}</td>
					</tr>
					<tr>
						<td style="color: Blue"><b>Email Address</b></td>
						<td>${val.email}</td>
					</tr>
					<tr>
					<td><a href="${contextPath}/job/emailstudent.htm?jobID=${id}&name=${val.fname}&email=${val.email}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Send email</a></td>
					</tr>										
				</c:forEach>				
			</c:forEach>			
		</table>				
	</div>
  </div><div><br><br></div>   
</body>
</html>