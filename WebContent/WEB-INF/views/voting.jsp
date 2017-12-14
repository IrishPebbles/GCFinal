<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<div class="container">
${userResult}
${result }
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


<script>
var myInput = document.getElementById ("psw");
		var letter = document.getElementById ("letter");
		var capital = document.getElementById ("capital");
		var number = document.getElementById ("number");
		var length = document.getElementById ("length"); // When the user clicks on the password field,
	show the message box
		myInput.onfocus = function () {document
	.getElementById("message").style.display = "block";
	
}

//
When the user clicks outside of the password field, hide the message box
		myInput.onblur = function () {document
	.getElementById("message").style.display = "none";
	
}

//
When the user starts to type something inside the password field
		myInput.onkeyup = function () { // Validate lowercase letters var
	lowerCaseLetters = /[a-z]/g;if (myInput.value.match(lowerCaseLetters))
	{ letter.classList.remove("invalid");letter .classList.add("valid");
	
}

else {letter .classList.remove("valid");letter .classList.add("invalid");
	
}

//
Validate capital letters
			var upperCaseLetters = /[A-Z] /g;
			if (myInput.value.match (upperCaseLetters)) {capital
	.classList.remove("invalid");capital .classList.add("valid");
	
}

else {capital .classList.remove("valid");capital
	.classList.add("invalid");
	
}

//
Validate numbers
			var numbers = /[ 0-9] /g;
			if (myInput.value.match (numbers)) {number
	.classList.remove("invalid");number .classList.add("valid");
	
}

else {number .classList.remove("valid");number .classList.add("invalid");
	
}

//
Validate
 
length

			
if
 
(
myInput
.value
.length
>
=
8)
{
length
.classList
.remove
("invalid");

				
length
.classList
.add
("valid");

			
}
else {length .classList.remove("valid");length .classList.add("invalid");
	
}
}
</
script
>


</body>
</html>