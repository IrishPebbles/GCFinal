<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Testing Eventbright API</title>
</head>
<body>
<!-- <form action="indexlogin" method="POST">
User Name <input type="email" name="username">
<input type="submit" value="Submit"> -->


 <form id="login-form" action="indexlogin" method="post">
              <div class="modal-body">
    				    	<div id="div-login-msg">
                      <div id="icon-login-msg"><i class="fa fa-user-circle-o" aria-hidden="true"></i></div>
                      <span id="text-login-msg">Type your username and password.</span>
                  </div>
    			    		<input id="login_username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" name ="userEmail" required>
    			    		<input id="login_password" class="form-control" type="password" placeholder="Password" name="passwordInformation"required >
                      <div class="checkbox">
                        <label>
                          <input type="checkbox"> Remember me
                        </label>
                      </div>
            	</div>
${noAccountMessage}

</form> 

<%-- ${data } --%>
</body>
</html>