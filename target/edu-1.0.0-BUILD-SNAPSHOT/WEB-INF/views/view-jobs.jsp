<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view-jobs</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface"
	rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

	<!-- Custom CSS -->
<link rel="stylesheet"href="resources/css/footer.css"type="text/css" />
<link rel="stylesheet"href="resources/css/home.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/homecss.css" >	
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" type="text/css" />
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css" type="text/css" />
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/viewjobs.css" type="text/css" />

<script>
	$(document).ready(function(){
		 $(".alert").hide();

		 if (window.location.href.indexOf("?jobID=") > -1) {
			    $(".alert").show();
			}
		
		});

	function myFunction() {
		  // Declare variables 
		  var input, filter, table, tr, td, i;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");

		  // Loop through all table rows, and hide those who don't match the search query
		  for (i = 0; i < tr.length; i++) {
		    /* td = tr[i].getElementsByTagName("td")[0]; */
		    td = tr[i].getElementsByTagName("td")[1];
		    td = tr[i].getElementsByTagName("td")[2];
		    td = tr[i].getElementsByTagName("td")[3];
		    td = tr[i].getElementsByTagName("td")[4];
		    td = tr[i].getElementsByTagName("td")[5];
		    td = tr[i].getElementsByTagName("td")[6];
		    
		    
		    if (td) {
		      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    } 
		  }
		}
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	<div class="container-fluid">
        <header class="header">
            <div class="row">
                <div class="menu_main col-xs-4" style="background-color:#35404f;">
                    <h4 class="menu_main_logo"><i class="fa fa-university" aria-hidden="true"> NU CAREERS<br/><p>Hello, ${name.fname}</p></i></h4>

                </div>
                <div class="menu_main_content col-xs-10">
                    <div class = "dropdown">
                        <div class="col-xs-3 droplink" style="background-color:#35404f;" ><a href="#" style="color: lightblue; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Accounts <span class="caret"></span></a>
                            <div class="dropdown-content">
                               <!--  <a href="#">Edit Profile </a> -->
                                <a href="${contextPath}/logout.htm">Logout</a>
                            </div></div></div>
                     <div class="col-xs-4" style="background-color:#35404f;"><a href="${contextPath}/student/viewalljobs.htm?name=${name}" style="color: lightblue; text-decoration:none">View all jobs</a></div>
                    <div class="col-xs-5" style="background-color:#35404f;"><a href="${contextPath}/student/viewMyJobs.htm?name=${name}" style="color: lightblue; text-decoration:none">View my applied jobs</a></div>
                     <!-- <div class="col-xs-5" style="background-color:#35404f;"><a href="#" style="color: lightblue; text-decoration:none">Github jobs</a></div> -->
                </div>
            </div>
        </header>	
         <%-- <input type="hidden" value= "${name}" name = "userText"></input> --%>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong>Congratulations!! Your application has been sent to the organization.</strong> If you are deemed as the right candidate by the company, you will be contacted on the email you have provided
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"> X </button>
		</div>
		
		<center><h2>Open Job Listings</h2></center>
		<br><br>
		<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search here..">
		<table id = "myTable" class = "table">
          		<%-- <input type="hidden" name = "jobID" value = "${j.id}" /> --%>
				<br><br>
				<tr class="header">
					<th style="width: 5%;">Job ID</th>
					<th style="width: 10%;">Title</th>
					<th style="width: 5%;">Company Name</th>
					<th style="width: 5%;">Job Type</th>
					<th style="width: 10%;">Location</th>
					<th style="width: 10%;">Industry</th>
					<th style="width: 10%;">Job For..</th>
					<th style="width: 20%;">Job-URL</th>
					<th style="width: 20%;">Description</th>
					<th style="width: 5%;">Posted On</th>
					<th style="width: 5%;">Action</th>
				</tr>
				<c:forEach var="j" items="${allJobs}">
				<tr>
						<td>${j.jobID}</td>
						<td>${j.jobTitle}</td>
						<td>${j.companyName}</td>
						<td>${j.jobType}</td>
						<td>${j.state}, ${j.country}</td>
						<td>${j.industry}</td>
						<td>${j.major}</td>
						
					<c:if test= "${empty j.jobUrl}"> 
					<td>No link available. Please check the company's website</td></c:if>
					<c:if test= "${not empty j.jobUrl}"> 
					<td>${j.jobUrl}</td></c:if>
					
					<c:if test= "${empty j.description}"> 
					<td>No description provided</td></c:if>
					<c:if test= "${not empty j.description}"> 
					<td>${j.description}</td></c:if>
					
					<td>${j.postedOn}</td>
					<td><a href="${contextPath}/student/showUploadPage.htm?jobID=${j.id}&name=${name.fname}" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Apply for this job</a></td>
					</tr>
				</tr>
			</c:forEach>
			</table>
		
        </div>
        <div><br><br></div>
       
     <jsp:include page="footer.jsp" /> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>       
</body>
</html>