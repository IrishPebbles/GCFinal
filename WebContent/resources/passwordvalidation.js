

function checkForm(form)
{
  if(form.username.value == "") {
    alert("Error: Username cannot be blank!");
    form.username.focus();
    return false;
  }
  re = /^\w+$/;
  if(!re.test(form.username.value)) {
    alert("Error: Username must contain only letters, numbers and underscores!");
    form.username.focus();
    return false;
  }

  if(form.pwd1.value != "" && form.pwd1.value == form.pwd2.value) {
    if(form.pwd1.value.length < 6) {
      alert("Error: Password must contain at least six characters!");
      form.pwd1.focus();
      return false;
    }
    if(form.pwd1.value == form.username.value) {
      alert("Error: Password must be different from Username!");
      form.pwd1.focus();
      return false;
    }
    re = /[0-9]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one number (0-9)!");
      form.pwd1.focus();
      return false;
    }
    re = /[a-z]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one lowercase letter (a-z)!");
      form.pwd1.focus();
      return false;
    }
    re = /[A-Z]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one uppercase letter (A-Z)!");
      form.pwd1.focus();
      return false;
    }
  } else {
    alert("Error: Please check that you've entered and confirmed your password!");
    form.pwd1.focus();
    return false;
  }

  alert("You entered a valid password: " + form.pwd1.value);
  return true;
}







/*<!DOCTYPE html>
<html>
<body>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<link href="resources/style.css" rel="stylesheet" />
<style>
</style>
<title>GC Coffee Shop: Register</title>

</head>
<body>
	<section>
		<div class="container form-group">
			<img src="resources/GrandCircusLogo.jpg" />
			<h2>Welcome to the Grand Circus Coffee Shop</h2>
			<p>
					Already have an account? <a href="index.html">Click Here</a> to
					login.
			</p>
			
			<form action="submitform" method="post">
			<input type="text" class="form-control" name="customername" placeholder="Full Name">
			<input type="email" class="form-control" name="emailaddress" placeholder="Email Address">
			<input type="password" class="form-control" name="password"  placeholder="Password" id="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
				required>
			<input type="password" class="form-control" name="re-enterpassword" placeholder="Confirm Password" id="confirm_password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
					required/>
		
			 <input type="submit" class="form-control" value="Create Account" id="buttonactivate" disabled="true"/>
 			</form>
 			<div id="message">
					<h6>Password must contain the following:</h6>
					<p id="letter" class="invalid">
						A <b>lowercase</b> letter
					</p>
					<p id="capital" class="invalid">
						A <b>capital (uppercase)</b> letter
					</p>
					<p id="number" class="invalid">
						A <b>number</b>
					</p>
					<p id="length" class="invalid">
						Minimum <b>8 characters</b>
					</p>
					<p id="match" class="invalid">
						Passwords must match
					</p>
				</div>	
		</div>

	</section>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<script>
		var myInput = document.getElementById ("password");
		var letter = document.getElementById ("letter");
		var capital = document.getElementById ("capital");
		var number = document.getElementById ("number");
		var length = document.getElementById ("length"); // When the user
															// clicks on the
															// password field,
	/how the message box*
		myInput.onfocus = function () {document
	.getElementById("message").style.display = "block";
	
}


/hen the user clicks outside of the password field, hide the message box*
		myInput.onblur = function () {document
	.getElementById("message").style.display = "none";
	
}


/hen the user starts to type something inside the password field*
		myInput.onkeyup = function () { // Validate lowercase letters var
	lowerCaseLetters = /[a-z]/g;if (myInput.value.match(lowerCaseLetters))
	{ letter.classList.remove("invalid");letter .classList.add("valid");
	
}

else {letter .classList.remove("valid");letter .classList.add("invalid");
	
}


Validate capital letters var upperCaseLetters = /[A-Z] /g;
			if (myInput.value.match (upperCaseLetters)) {capital
	.classList.remove("invalid");capital .classList.add("valid");
	
}else {capital .classList.remove("valid");capital
	.classList.add("invalid");
	
}Validate numbers var numbers = /[ 0-9] /g;
			if (myInput.value.match (numbers)) {number
	.classList.remove("invalid");number .classList.add("valid");
	
}else {number .classList.remove("valid");number .classList.add("invalid");
	
}Validate length if (myInput.value.length>=8){
length.classList.remove("invalid");

				
length.classList.add("valid");
			
}
else {length .classList.remove("valid");length .classList.add("invalid");
	
}
}
</script>
</body>
</html>*/