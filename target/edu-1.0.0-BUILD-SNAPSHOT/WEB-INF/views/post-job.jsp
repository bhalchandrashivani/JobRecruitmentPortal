<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Posting</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	
<link  rel="stylesheet" href="resources/css/header.css" type="text/css" />
<link  rel="stylesheet" href="resources/css/footer.css" type="text/css" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface" rel="stylesheet">
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
    <script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/homecss.css" >
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/headerandfooter.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/postAJob.css">
    
 <script>
        /*Jquery for Job Description*/
        $(document).ready(function()  {
            $(".jobform").animate({left: '250px'});
            $(".alert").hide();
            $("div.show-if-active").hide();
            $("div.markItUpContainer").hide();
            //$("div.show-if-active-dropdown").hide();

            /*$("input[name$='job[loc]']").click(function () {
                $("div.show-if-active-dropdown").slideDown();
            })*/

            $("input[name$='job_desc_url']").click(function () {
                $("input[name$='job_desc']").prop('checked', false);
                $("div.show-if-active").slideDown();
                $("div.markItUpContainer").slideUp();

           })
            $("input[name$='job_desc']").click(function () {
                $("input[name$='job_desc_url']").prop('checked', false);
                $("div.markItUpContainer").slideDown();
                $("div.show-if-active").slideUp();
            })

            $('button').click(function(){
                $('.alert').show();
            })

           /* $("#job_type_country").click(function () {
                $("div.locationDropdown").slideDown();

            })*/
        });

    </script>

</head>
<body>
	<div class="container-fluid">
        <header class="header">
            <div class="row">
                <div class="menu_main col-xs-4" style="background-color:#35404f;">
                    <h4 class="menu_main_logo"><i class="fa fa-university" aria-hidden="true"> NU CAREERS<br/><p>Hello , ${name.fname}</p></i></h4>

                </div>
                <div class="menu_main_content col-xs-8">
                    <div class = "dropdown">
                        <div class="col-xs-3 droplink" style="background-color:#35404f;" ><a href="#" style="color: lightblue; text-decoration:none" id="dropdownMenu1" data-toggle="dropdown">Accounts <span class="caret"></span></a>
                            <div class="dropdown-content">
                                <!-- <a href="#">Edit Profile </a> -->
                                <a href="${contextPath}/logout.htm">Logout</a>
                            </div></div></div>
                    <div class="col-xs-3" style="background-color:#35404f;"><a href="${contextPath}/employeer/postjob.htm?name=${name}" style="color: lightblue; text-decoration:none">Post a Job</a></div>
                    <%-- <div class="col-xs-3" style="background-color:#35404f;"><a href="${contextPath}/employer/myjobposts.htm" style="color: lightblue; text-decoration:none">View Applications</a></div> --%>
                </div>
            </div>
        </header>	
		  <div class="alert alert-danger alert-dismissible fade show" role="alert">
		  <strong>${errorMessage}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    X
		  </button>
		</div>
		
    <div class="jumbotron">
        <h1 id="myHeader">POST A JOB</h1>
        <h2>Find the right person for your company</h2>
    </div>

    <div class="disclaimer">
        <p>Fields marked by an Asterisk <span>&#42;</span> are required</p>
    </div>
    <div class="grid-2-6">
        <form class ="jobform" method="post" action = "${contextPath}/employer/postjobsuccess.htm?name=${name}">
        <div id="div-jobId" class="error-wrap title">
            <label for="job_title">Job ID <span class="asterisk">&#42;</span></label><br>
        <input placeholder="Job Title" maxlength="50" required="required" size="50" type="text" name="job_id" id="job_id">
        </div><br>
        
        <div id="div-jobTitle" class="error-wrap title">
            <label for="job_title">Job Title <span class="asterisk">&#42;</span></label><br>
        <input placeholder="Job Title" maxlength="85" required="required" size="50" type="text" name="jobtitle" id="job_title">
        </div><br>

        <div id="div-company" class="error-wrap company_name">
            <label for="job_company-name">Company <span class="asterisk">&#42;</span></label><br>
            <input placeholder="Company" maxlength="85" required="required" size="50" type="text" name="job_company_name" id="job_company-name">
        </div><br>

        <div id="div-type" class="error-wrap type">
            <div class="row-of-radio">
                <h3>Job Type <span class="asterisk">&#42;</span></h3>
                <div>
                    <input required="required" type="radio" value="Full Time" name="job_type" id="job_type_full">
                    <label for="job_type_full">Full Time</label>
                </div>

                <div>
                    <input required="required" type="radio" value="Co-op" name="job_type" id="job_type_coop">
                    <label for="job_type_coop">Co-op</label>
                </div>

                <div>
                    <input required="required" type="radio" value="Contract" name="job_type" id="job_type_contract">
                    <label for="job_type_contract">Contract</label>
                </div>

                <div>
                    <input required="required" type="radio" value="On Campus" name="job_type" id="job_type_oncampus">
                    <label for="job_type_oncampus">On Campus</label>
                </div>
            </div>
        </div><br>


        <div id="div-location" class="error-wrap location">
            <h3>Location <span class="asterisk">&#42;</span></h3>
            <div>
                <!--<input required="required" type="radio" value="remote" name="job[loc]" id="job_type_remote">-->
                <label for="job_type_country">Country</label>
                <div class="locationDropdown">

                    <script type= "text/javascript" src = "${contextPath}/resources/javascripts/countries.js"></script>
                    <select id="job_type_country" name ="country"></select> </br><br>

                    <label for="job_type_state">State</label><br>
                    <select name ="state" id ="job_type_state"></select>

                </div>
            </div>
        </div><br><br>

            <script language="javascript">
                populateCountries("job_type_country", "job_type_state"); // first parameter is id of country drop-down and second parameter is id of state drop-down
            </script>

            <div>
                <label for="majorCat">Select industry in which the job belongs <span class="asterisk">&#42;</span></label>
                <div>
                    <script src = "${contextPath}/resources/javascripts/majorsList.js"></script>
                    <select required="required" id="majorCat" name ="majCategory"></select> <br><br>
                    <label for="majorList">Select the major applicable for this job  <span class="asterisk">&#42;</span></label><br>
                    <select required="required" name ="major" id ="majorList"></select>
                </div>
            </div>

            <script language="javascript">
                populateCategories("majorCat", "majorList"); // first parameter is id of country drop-down and second parameter is id of state drop-down
            </script>

        <div id="div-description" class="error-wrap ">
            <h3>Job Description</h3>
            <div class="row-of-radio">
                <div>
                    <input type="radio" value="full" name="job_desc_url" id="job_desc_url">
                    <label for="job_desc_url">Link Job Posting to a URL</label>
                    <div class="show-if-active">
                        <input placeholder="http://your-company.com/jobs/job.html" type="url" max="200" size="50" name="joburl" id="job_url">
                    </div>
                </div>
                <div>
                    <input type="radio" value="full" name="job_desc" id="job_desc_onhere">
                    <label for="job_desc_onhere">Post Job Description Here</label>
                    <div class="markItUpContainer">
                        <textarea rows="10" cols="40" placeholder="Describe the job briefly" class="textarea-markItUpHeader" name= "job_description"
                        id="job_description" spellcheck="true" style="z-index: auto; position: relative"; font-size: 19px;></textarea>

                    </div>
                </div>

            </div>
    </div>
        <div style="text-align:center;">
        	<input type = "submit" class="btn btn-success btn-lg" value = "Post" />
        </div>
        </form>
        <div><br></div>
    </div>
</div>
     <jsp:include page="footer.jsp" />  
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script> 
</body></html>