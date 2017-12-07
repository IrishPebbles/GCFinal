<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preferences</title>
</head>
<body>
<form>
		<fieldset>
			Please enter an address at the center of the search area: <br>
			Street: <input type="text" name="street" placeholder="123 Main St"><br>
			City: <input type="text" name="city" placeholder="Detroit"><br>
			State: <input type="text" name="state" placeholder="MI"><br>
		</fieldset>
		
		<fieldset>
		How long would you like to set the voting window for?<br>
			<select name="votingWindow">
				<option value="2h">2 hours</option>
				<option value="4h">4 hours</option>
				<option value="6h">6 hours</option>
				<option value="12h">12 hours</option>
				<option value="24h">24 hours</option>
				<option value="48h">48 hours</option>
			</select><br> 
		
		How many additional participants would you like to enter?<br>
		<select id= "selection" name="numAttendees" onchange="createEmailFields(this)" >
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
			</select><br> 
			</fieldset>
			
<fieldset>
		<div id="email">
		
		</div>
		</fieldset>
			<input type="submit" value="Submit"> 
			<input type="reset" value="Reset">
	</form>
	<script>
	function createEmailFields(val){
		document.getElementById("email").innerHTML = "<div></div>";
		for(i = 0; i < val.value; i++){
			var node = document.createElement("INPUT");
			var textnode = document.createTextNode("Email Address " + (i + 1) + ": ");
			var brk = document.createElement("BR"); 
			
			document.getElementById("email").appendChild(textnode);
			document.getElementById("email").appendChild(node);
			document.getElementById("email").appendChild(brk);
			
		}
		var disable = document.createAttribute("DISABLED")
		document.getElementById("selection").appendChild(disable);
		console.log(val.value);
	}
	</script>
</body>
</html>