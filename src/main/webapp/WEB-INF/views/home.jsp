<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Bungee|Abril+Fatface" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/07b0ce5d10.js"></script>

<script>
	$(document).ready(function() {
		$("div.register").hide();
		$("div.alert").hide();
		$("div.login").show();

		$("div.sign-up-toggle").click(function() {
			$("div.register").show(1000);
			$("div.login").hide(1000);
		});

		$("div.login-toggle").click(function() {
			$("div.register").hide(1000);
			$("div.login").show(1000);
		});

		if (window.location.href.indexOf("/register.htm") > -1) {
				$("div.alert").show();
			}

		if (window.location.href.indexOf("/login.htm") > -1) {
				$("div.alert").show();
			}
	});
</script>
<style>
body {
    
    background-repeat: no-repeat, repeat center center fixed;
    -webkit-background-size: cover;
  	-moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    background-color: #cccccc;
}
.homepage{
	
	background-color: #05ffb0;
}
</style>


</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="homepage">
		<div class="login">
			<br> <br>
			<h1>WELCOME</h1>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
		  <strong>${errorMessage}</strong>
		  <strong>${successMessage}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    X
		  </button>
		</div>
			<form action="${contextPath}/login.htm" method="POST">

				<div>
					Username: <input type="text" name="uname" placeholder="username" maxlength="26" required="required" size="21"><br /> <br />
					Password: <input type="password" name="pwd" placeholder="password" minlength="5" required="required" size="21"><br /> <br />
				</div>
				<div class="submit-login">
					<input type="submit" name="submit" value="Login" class="btn btn-primary">
				</div>
			</form>
		</div>

		<div class="register">
			<br> <br>
			<h1>Sign Up</h1>
		<div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong>${successMessage}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">Cancel</button>
		</div>
		<div class = "container">
			<form class="form-group" action="${contextPath}/register.htm" method="POST">
				<div>
					 <label for="email">First_Name:</label><input type="text" name="fname" placeholder="firstname" maxlength="20" required="required"
						size="20"><br> <br> 					
					<label>Last_Name:</label><input type="text" name="lname" placeholder="lastname" maxlength="20" required="required" size="20"><br><br> 						
					<label>Email Address:</label><input type="email" name="email" placeholder="email"maxlength="50" required="required" size="20"><br><br>						
					<label>Username:</label><input type="text" name="uname"placeholder="username" maxlength="20" required="required" size="20"><br><br> 						
					<label>Password:</label><input	type="password" name="pwd" placeholder="password" minlength="6"	required="required" size="20"><br><br> 
					<label>Select who are you:</label><br> 
						<input type="radio" name="role"
							value="student"> Student &nbsp&nbsp
						 <input	type="radio" name="role" value="employer">Employer <br> <br>
				</div>
				<div class="submit-registration">
					<input type="submit" name="submit" value="Register"
						class="btn btn-primary">
				</div>
			</form>
			</div>
		</div>
		<br /> <br />
		<div class="sign-up-toggle is-active">
			First time? <a href="#">Sign Up</a>
		</div>
		<div class="login-toggle">
			Have an account? <a href="#">Login</a>
		</div>
		<br> <br>
	</div>	
	</body>
</html>