<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preferences</title>
</head>
<body>
<h1>Select your preferences</h1>

<form action="votingJenna" method="POST" >
Name your event <input type="text" name="title" required><br>
Who do you want to invite <input type="email" name="person1" required><br>
Who do you want to invite <input type="email" name="person2" ><br>
Who do you want to invite <input type="email" name="person3" ><br>
Set your date <input type="date" name="date" required><br>
Set your time <input type="time" name="time" required><br>
Set location <input type="text" name="location" required><br>
<input type="radio" name="timelimit" value="24hours"> 24 hours<br>
<input type="radio" name="timelimit" value="48hours"> 48 hours<br>
<input type="submit" name="Submit">
</form>
</body>
</html>