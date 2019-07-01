<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view-jobs</title>

<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

<link href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

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
                <div class="menu_main col-xs-4" style="background-color:#05ffb0;">
                    <h4 >JOBFAIR WORLD<br/><p>Welcome, ${name.fname}</p></h4>
                </div>              
            </div>
            <div class="menu_main_content col-xs-10">
                    <div class = "dropdown">
                        <div class="col-xs-3 droplink" style="background-color:#05ffb0;" ><a href="#" style="color: black; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Account <span class="caret"></span></a>
                            <div class="dropdown-content">
                                <a href="${contextPath}/logout.htm">Logout</a>
                            </div></div></div>
                    <div class="col-xs-4" style="background-color:#05ffb0;"><a href="${contextPath}/student/alljobs.htm?name=${name}" style="color: black; text-decoration:none">View all jobs</a></div>
                    <div class="col-xs-5" style="background-color:#05ffb0;"><a href="${contextPath}/student/viewMyJobs.htm?name=${name}" style="color: black; text-decoration:none">View my applied jobs</a></div>
             </div>
        </header>	
        <div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong>You have successfully applied to the organization.</strong> You will be contacted on the email provided by you
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"> X </button>
		</div>		
		<center><h2>Open Jobs Positions </h2></center>
		<br><br>
		<table id = "myTable" class = "table">
				<br><br>
				<tr class="header">
					<th style="width: 5%;">Job ID</th>
					<th style="width: 5%;">Title</th>
					<th style="width: 7%;">Category</th>
					<th style="width: 10%;">Company</th>					
					<th style="width: 5%;">Job-Type</th>
					<th style="width: 10%;">Location</th>
					<th style="width: 10%;">Industry</th>
					<th style="width: 20%;">Job-URL</th>
					<th style="width: 15%;">Know More</th>
					<th style="width: 10%;">Posted On</th>
					<th style="width: 5%;">Task</th>
				</tr>
				<c:forEach var="k" items="${allJobs}">
				<tr>
						<td>${k.jobID}</td>
						<td>${k.jobTitle}</td>
						<td>${k.jobType}</td>
						<td>${k.companyName}</td>
						<td>${k.major}</td>						
						<td>${k.state}, ${k.country}</td>												
						<td>${k.industry}</td>
						<c:if test= "${empty k.jobUrl}"> 
						<td>Please check the company's website</td></c:if>
						<c:if test= "${not empty k.jobUrl}"> 
						<td>${k.jobUrl}</td></c:if>					
						<c:if test= "${empty k.description}"> 
						<td>No description</td></c:if>
						<c:if test= "${not empty k.description}"> 
						<td>${k.description}</td></c:if>					
						<td>${k.jobpostedOn}</td>
						<td><a href="${contextPath}/student/UploadPage.htm?jobID=${k.id}&name=${name.fname}" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Apply</a></td>
				</tr>
			</c:forEach>
			</table>		
        </div>
 </body>
</html>