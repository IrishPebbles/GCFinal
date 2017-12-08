<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preferences</title>
</head>
<body>

<form action="voting" method="post">
		${noAccountMessage}

		<fieldset>
			Log in or provide email address: <input type="email" name="organizerEmail" placeholder="email@domain.com" required><br>
			Choose a date for your Outing: <input type="date" name="date" required><br><br> 
			Enter an address at the center of the search area: <br>
			Street: <input type="text" name="street" placeholder="123 Main St" required><br>
			City: <input type="text" name="city" placeholder="Detroit" required><br>
			State: <select name="state">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
				<option value="CT">Connecticut</option>
				<option value="DE">Delaware</option>
				<option value="DC">District Of Columbia</option>
				<option value="FL">Florida</option>
				<option value="GA">Georgia</option>
				<option value="HI">Hawaii</option>
				<option value="ID">Idaho</option>
				<option value="IL">Illinois</option>
				<option value="IN">Indiana</option>
				<option value="IA">Iowa</option>
				<option value="KS">Kansas</option>
				<option value="KY">Kentucky</option>
				<option value="LA">Louisiana</option>
				<option value="ME">Maine</option>
				<option value="MD">Maryland</option>
				<option value="MA">Massachusetts</option>
				<option value="MI">Michigan</option>
				<option value="MN">Minnesota</option>
				<option value="MS">Mississippi</option>
				<option value="MO">Missouri</option>
				<option value="MT">Montana</option>
				<option value="NE">Nebraska</option>
				<option value="NV">Nevada</option>
				<option value="NH">New Hampshire</option>
				<option value="NJ">New Jersey</option>
				<option value="NM">New Mexico</option>
				<option value="NY">New York</option>
				<option value="NC">North Carolina</option>
				<option value="ND">North Dakota</option>
				<option value="OH">Ohio</option>
				<option value="OK">Oklahoma</option>
				<option value="OR">Oregon</option>
				<option value="PA">Pennsylvania</option>
				<option value="RI">Rhode Island</option>
				<option value="SC">South Carolina</option>
				<option value="SD">South Dakota</option>
				<option value="TN">Tennessee</option>
				<option value="TX">Texas</option>
				<option value="UT">Utah</option>
				<option value="VT">Vermont</option>
				<option value="VA">Virginia</option>
				<option value="WA">Washington</option>
				<option value="WV">West Virginia</option>
				<option value="WI">Wisconsin</option>
				<option value="WY">Wyoming</option>
			</select>
			<br>
		<!-- 
		</fieldset>

		<fieldset>
			How long would you like to set the voting window for?<br> <select
				name="votingWindow">
				<option value="2h">2 hours</option>
				<option value="4h">4 hours</option>
				<option value="6h">6 hours</option>
				<option value="12h">12 hours</option>
				<option value="24h">24 hours</option>
				<option value="48h">48 hours</option>
-->
			</select><br> How many additional participants would you like to enter?<br>
			<select id="selection" name="numAttendees"
				onchange="createEmailFields(this)">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
			</select><br><br>
	<!-- 	</fieldset>
		<fieldset> -->
			<div id="email"></div>
		</fieldset>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>
	<script>
		function createEmailFields(val) {
			document.getElementById("email").innerHTML = "<div></div>";
			for (i = 0; i < val.value; i++) {

				var node = document.createElement("INPUT");
				var nameAttribute = "emailAddress";
				node.setAttribute('name', nameAttribute);
				var textnode = document.createTextNode("Email Address "
						+ (i + 1) + ": ");
				var brk = document.createElement("BR");

				document.getElementById("email").appendChild(textnode);
				document.getElementById("email").appendChild(node);
				document.getElementById("email").appendChild(brk);

			}
		}
	</script>
</body>
</html>