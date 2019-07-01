<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete job post successful page</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"	crossorigin="anonymous">
<link	href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface"	rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="	crossorigin="anonymous"></script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
]	<div class="container-fluid">
        <header class="header">
            <div class="row">
                <div class="menu_main col-xs-4" style="background-color:#05ffb0;">
                    <h4 >JOBFAIR WORLD<br/><p>Welcome, ${name.fname}</p></h4>
                </div>              
            </div>
            <div class="menu_main_content col-xs-8">
                <div class = "dropdown">
                    <div class="col-xs-3 droplink" style="background-color:#05ffb0;" ><a href="#" style="color: black; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Accounts <span class="caret"></span></a>
                        <div class="dropdown-content">
                            <a href="${contextPath}/logout.htm">Logout</a>
                        </div></div></div>
                <div class="col-xs-3" style="background-color:#05ffb0;"><a href="${contextPath}/employer/postajob.htm?name=${name.fname}" style="color: black; text-decoration:none">Post a Job</a></div>
                <div class="col-xs-3" style="background-color:#05ffb0;"><a href="${contextPath}/employer/allmyjobposts.htm" style="color: black; text-decoration:none">View my Posts</a></div>
            </div>
        </header>	
         <input type="hidden" value= "${name}" name = "userText"></input>
        </div>
	<h1>Job has been deleted successfully!!!!</h1>	
 </body>
</html>