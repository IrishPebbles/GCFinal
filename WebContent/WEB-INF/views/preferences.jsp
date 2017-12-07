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
<form:form action="jennaVoting" method="post" modelAttribute="attendeeList">
		<fieldset>
			Log in or provide email address:
			<input type="email" name="organizerEmail" placeholder="email@domain.com"><br>
		
			Choose a date for your Outing: 
   			<input type="date" name="outing"><br><br>
		
			Enter an address at the center of the search area: <br>
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
	</form:form>
	<script>
    function createEmailFields(val){
        document.getElementById("email").innerHTML = "<div></div>";
        for(i = 0; i < val.value; i++){
            
            var node = document.createElement("INPUT");
            var nameAttribute ="attendeeList[${status.index}]";
            node.setAttribute('name', nameAttribute);
            var valueAttribute ="${person.email}";
            node.setAttribute('value', valueAttribute);
            var textnode = document.createTextNode("Email Address " + (i + 1) + ": ");
            var brk = document.createElement("BR"); 
            
            document.getElementById("email").appendChild(textnode);
            document.getElementById("email").appendChild(node);
            document.getElementById("email").appendChild(brk);
            
        }
        console.log(val.value);
    }
    </script>
</body>
</html>