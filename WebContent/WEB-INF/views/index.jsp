<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://use.fontawesome.com/84c291ee55.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Outings </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<style>
/* #####################################################################
   #
   #   Project       : Modal Login with jQuery Effects
   #   Author        : Rodrigo Amarante (rodrigockamarante)
   #   Version       : 1.0
   #   Created       : 07/28/2015
   #   Last Change   : 08/02/2015
   #
   ##################################################################### */



#login-modal .modal-dialog {
    width: 350px;
}

#login-modal input[type=text], input[type=password], #preferences-modal input[type=text], input[type=password]{
	margin-top: 10px;
}

#preferences-modal {
  transition: all ease-in-out 500ms;
}
#div-login-msg,
#div-lost-msg,
#div-register-msg {
    border: 1px solid #dadfe1;
    height: 30px;
    line-height: 28px;
    transition: all ease-in-out 500ms;
}

#div-login-msg.success,
#div-lost-msg.success,
#div-register-msg.success {
    border: 1px solid #68c3a3;
    background-color: #c8f7c5;
}

#div-login-msg.error,
#div-lost-msg.error,
#div-register-msg.error {
    border: 1px solid #eb575b;
    background-color: #ffcad1;
}

#icon-login-msg,
#icon-lost-msg,
#icon-register-msg {
    width: 30px;
    float: left;
    line-height: 28px;
    text-align: center;
    background-color: #dadfe1;
    margin-right: 5px;
    transition: all ease-in-out 500ms;
}

#icon-login-msg.success,
#icon-lost-msg.success,
#icon-register-msg.success {
    background-color: #68c3a3 !important;
}

#icon-login-msg.error,
#icon-lost-msg.error,
#icon-register-msg.error {
    background-color: #eb575b !important;
}

#img_logo {
    max-height: 100px;
    max-width: 100px;
}

/* #########################################
   #    override the bootstrap configs     #
   ######################################### */

.modal-backdrop.in {
    filter: alpha(opacity=50);
    opacity: .8;
}

.modal-content {
    background-color: #ececec;
    border: 1px solid #bdc3c7;
    border-radius: 0px;
    outline: 0;
}

.modal-header {
    min-height: 16.43px;
    padding: 15px 15px 15px 15px;
    border-bottom: 0px;
}

.modal-body {
    position: relative;
    padding: 5px 15px 5px 15px;
}

.modal-footer {
    padding: 15px 15px 15px 15px;
    text-align: left;
    border-top: 0px;
}

.checkbox {
    margin-bottom: 0px;
}

.btn {
    border-radius: 0px;
}

.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
    outline: none;
}

.btn-lg, .btn-group-lg>.btn {
    border-radius: 0px;
}

.btn-link {
    padding: 5px 10px 0px 0px;
    color: #95a5a6;
}

.btn-link:hover, .btn-link:focus {
    color: #2c3e50;
    text-decoration: none;
}

.glyphicon {
    top: 0px;
}

.form-control {
  border-radius: 0px;
}


</style>
</head>
<body>
  <main role="main">
  <!-- Main jumbotron for a primary marketing message or call to action -->
  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">Welcome to Outings</h1>
      <p>Organize fun events with your friends and family and easily decide where to  go. Getting together eaiser and more enjoyable than ever before.</p>
      <p> <a class="btn btn-primary btn-lg" role="button" data-toggle="modal" data-target="#login-modal"> Create an Outing </a></p>
    </div>
  </div>
  <div class="container"><!--we can change this based on whether or not the person is logged in -->
    <!-- Example row of columns -->
    <div class="row justify-content-center" id="upcoming">
      <div class="col-md-6">
        <h2>Upcoming Outings</h2>
        <p>See outings you currently have planned. </p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div>
      <div class="col-md-6" id="previous">
        <h2>Past Outings</h2>
        <p> Enjoyed a past outing, and want a reminder of where you went and who you invited? You can create a simliar event. </p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div>
      <!--
      <div class="col-md-4">
        <h2>Heading</h2>
        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
      </div> -->
    </div>

    <hr>

  </div> <!-- /container -->
  <!-- BEGIN # MODAL LOGIN -->
  <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	<div class="modal-dialog">
  			<div class="modal-content">
  				<div class="modal-header" align="center">
  					<img class="img-circle" id="img_logo" src="resources/GrandCircusLogo.jpg">
  					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
  						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
  					</button>
  				</div>

          <!-- Begin # DIV Form -->
          <div id="div-forms">

            <!-- Begin # Login Form -->
            <form id="login-form" action="preferences" method="post">
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
    				  <div class="modal-footer">
                <div>
                  <input type="submit" class="btn btn-primary btn-lg btn-block" value="Login">
                </div>
    		        <div>
                  <button id="login_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                  <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
                </div>
    				  </div>
            </form>
            <!-- End # Login Form -->

           <!-- Begin | Lost Password Form -->
           <form id="lost-form" style="display:none;" action="lost" method="post"> <!--I dont have this being caught-->
      	      <div class="modal-body">
  		    				<div id="div-lost-msg">
                      <div id="icon-lost-msg"><i class="fa fa-user-circle-o" aria-hidden="true"></i></div>
                      <span id="text-lost-msg">Type your e-mail.</span>
                  </div>
  		    		    <input id="lost_email" class="form-control" type="text" placeholder="E-Mail (type ERROR for error effect)" required>
              </div>
  		    		<div class="modal-footer">
                  <div>
                      <input type="submit" class="btn btn-primary btn-lg btn-block" value="Send">
                  </div>
                  <div>
                    <button id="lost_login_btn" type="button" class="btn btn-link">Log In</button>
                    <button id="lost_register_btn" type="button" class="btn btn-link">Register</button>
                  </div>
  		    		 </div>
            </form>
            <!-- End | Lost Password Form -->

                      <!-- Begin | Register Form -->
                      <form id="register-form" style="display:none;" action="preferencesJ" method="post">
              		    <div class="modal-body">
  		    				<div id="div-register-msg">
                                  <div id="icon-register-msg" class="glyphicon glyphicon-chevron-right"></div>
                                  <span id="text-register-msg">Register an account.</span>
                              </div>
  		    				<input id="register_username" class="form-control" type="text" placeholder="Username (type ERROR for error effect)" required>
                              <input id="register_email" class="form-control" type="text" placeholder="E-Mail" required>
                              <input id="register_password" class="form-control" type="password" placeholder="Password" required>
              			</div>
  		    		    <div class="modal-footer">
                              <div>
                                  <input type="submit" class="btn btn-primary btn-lg btn-block" value="Register">
                              </div>
                              <div>
                                  <button id="register_login_btn" type="button" class="btn btn-link">Log In</button>
                                  <button id="register_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
                              </div>
  		    		    </div>
                      </form>
                      <!-- End | Register Form -->

              </div>
        </div>
     </div>
  </div>
 <div class="modal fade ${shown}" id="preferences-modal" tabindex="-1" role="dialog" aria-labelledby="preferences-modal" aria-hidden="true" style=${displayPreference}>
   <div class="modal-dialog">
     <div class="modal-content">
       <div class="modal-header" align="center">
         Welcome ${username} !
      </div>
      <div class="modal-body">
         <form action="voting" method="post">
   		${noAccountMessage}
      <fieldset>
   			Log in or provide email address: <input type="email" name="organizerEmail" placeholder="email@domain.com" required><br>

   			Outing name <input type="text" name="outingName"><br><br>

   			Choose a date for your Outing: <input type="date" name="date" required><br>

   			Enter an address at the center of the search area: <br>
   			Street: <input type="text" name="street" placeholder="123 Main St" ><br>
   			City: <input type="text" name="city" placeholder="Detroit" required><br>
   			State: <select name="state">
   				<option value="NA">Select State</option>
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

   		</fieldset>
      <!--
   		<fieldset>
   			How long would you like to set the voting window for?<br> <select
   				name="votingWindow">
   				<option value="2h">2 hours</option>
   				<option value="4h">4 hours</option>
   				<option value="6h">6 hours</option>
   				<option value="12h">12 hours</option>
   				<option value="24h">24 hours</option>
   				<option value="48h">48 hours</option>

   			</select> -->
        <br> How many additional participants would you like to enter?<br>
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
   	<!-- 	</fieldset>-->
   		<fieldset>
   			<div id="email"></div>
   		</fieldset>
   		<input type="submit" value="Submit"> <input type="reset"
   			value="Reset">
   	</form>
      </div>
    </div>
  </div>
</div>

</main>

<footer class="container">
  <p>&copy; Outings Planner 2017 </p>
  <p>  Made possible by <a href="http://grandcircus.co">  Grand Circus </a>
   with special thanks to Antonella and Merc for all their guidance.</p>
  <p>
     Code for this project can be found on Github <a href="https://github.com/jennaprice/GCFinal"> here.</a>
  </p>
</footer>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
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
<script>
/* #####################################################################
 #
 #   Project       : Modal Login with jQuery Effects
 #   Author        : Rodrigo Amarante (rodrigockamarante)
 #   Version       : 1.0
 #   Created       : 07/29/2015
 #   Last Change   : 08/04/2015
 #
 ##################################################################### */

$(function() {

  var $formLogin = $('#login-form');
  var $formLost = $('#lost-form');
  var $formRegister = $('#register-form');
  var $divForms = $('#div-forms');
  var $prefForms = $('#div-forms');
  var $modalAnimateTime = 300;
  var $msgAnimateTime = 150;
  var $msgShowTime = 2000;

  $("form").submit(function () {
      switch(this.id) {
          case "login-form":
              var $lg_username=$('#login_username').val();
              var $lg_password=$('#login_password').val();
              if ($lg_username == "ERROR") {
                  msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Login error");
              } else {
                  msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Login OK");
              }
              return false;
              break;
          case "lost-form":
              var $ls_email=$('#lost_email').val();
              if ($ls_email == "ERROR") {
                  msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "error", "glyphicon-remove", "Send error");
              } else {
                  msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "success", "glyphicon-ok", "Send OK");
              }
              return false;
              break;
          case "register-form":
              var $rg_username=$('#register_username').val();
              var $rg_email=$('#register_email').val();
              var $rg_password=$('#register_password').val();
              if ($rg_username == "ERROR") {
                  msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "error", "glyphicon-remove", "Register error");
              } else {
                  msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "success", "glyphicon-ok", "Register OK");
              }
              return false;
              break;
          default:
              return false;
      }
      return false;
  });

  $('#login_register_btn').click( function () { modalAnimate($formLogin, $formRegister) });
  $('#register_login_btn').click( function () { modalAnimate($formRegister, $formLogin); });
  $('#login_lost_btn').click( function () { modalAnimate($formLogin, $formLost); });
  $('#lost_login_btn').click( function () { modalAnimate($formLost, $formLogin); });
  $('#lost_register_btn').click( function () { modalAnimate($formLost, $formRegister); });
  $('#register_lost_btn').click( function () { modalAnimate($formRegister, $formLost); });

  function modalAnimate ($oldForm, $newForm) {
      var $oldH = $oldForm.height();
      var $newH = $newForm.height();
      $divForms.css("height",$oldH);
      $oldForm.fadeToggle($modalAnimateTime, function(){
          $divForms.animate({height: $newH}, $modalAnimateTime, function(){
              $newForm.fadeToggle($modalAnimateTime);
          });
      });
  }

  function msgFade ($msgId, $msgText) {
      $msgId.fadeOut($msgAnimateTime, function() {
          $(this).text($msgText).fadeIn($msgAnimateTime);
      });
  }

  function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
      var $msgOld = $divTag.text();
      msgFade($textTag, $msgText);
      $divTag.addClass($divClass);
      $iconTag.removeClass("glyphicon-chevron-right");
      $iconTag.addClass($iconClass + " " + $divClass);
      setTimeout(function() {
          msgFade($textTag, $msgOld);
          $divTag.removeClass($divClass);
          $iconTag.addClass("glyphicon-chevron-right");
          $iconTag.removeClass($iconClass + " " + $divClass);
    }, $msgShowTime);
  }
});
</script>

</body>
</html>
