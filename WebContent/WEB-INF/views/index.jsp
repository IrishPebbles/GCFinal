<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://use.fontawesome.com/84c291ee55.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Outings </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<link rel="stylesheet" href="resources/modallogin.css" type="text/css">
</head>
<body>

  <main role="main">
  <form action="voting" id="login-form" method="POST" style = "text-align: center" >
  <!-- Main jumbotron for a primary marketing message or call to action -->
 
	  <div class="jumbotron">
	    <div class="container">
	      <h1 class="display-3" style = "text-align: center">Welcome to Outings</h1>
	      
	      <p style = "text-align: center">Organize fun events with your friends and family and easily decide where to go. Getting together easier and more enjoyable than ever before.</p>
	     
	     <h6>Please enter email to start an Outing:</h6>
	     <input type="email" name="organizerEmail"><br><br>   
	      <p id="outingbutton"> <a class="btn btn-primary btn-lg" role="button" data-toggle="modal" data-target="#login-modal"> Create an Outing </a></p>
	      
	    </div>
	  </div>
	  
	  <div class="container"><!--we can change this based on whether or not the person is logged in -->
	    <!-- Example row of columns -->
	    <div class="row justify-content-center" id="upcoming">
	      <div class="col-md-6" id="upcoming">
	      </div>
	      <div class="col-md-6" id="previous">
	      </div>
	    </div>
	    <hr>
	
	  </div> <!-- /container -->
	  <!-- BEGIN # MODAL LOGIN -->
	  <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
	    	<div class="modal-dialog">
	  			<div class="modal-content">
	  				<div class="modal-header" align="center">
	  					<img class="img-circle" id="img_logo" align="middle" src="resources/GrandCircusLogo.jpg" >
	  					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	  						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	  					</button>
	  				</div>
	
	          <!-- Begin # DIV Form -->
	          <div id="div-forms">
				
	            <!-- Begin # Login Form -->
	              <div class="modal-body">
	               
	    			<fieldset>
	    			
					<input type="hidden" name="userEmail1" value="">
	   				Outing name <input type="text" name="outingName"><br><br>
	
	   				Choose a date for your Outing: <input type="date" name="date" required><br>
	
	   				Enter an address for area event: <br>
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
		  
		        <br> How many participants would you like to enter?<br>
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

			   		<fieldset>
			   			<div id="email"></div>
			   		</fieldset>
			   		
	
	            	</div>
	    			<div class="modal-footer">
		                <div>
		                  <input type="submit" value="Submit"> 
		                  <input type="reset" value="Reset">
				   			
				   		</div>
	             	 </div>
	    
	       	  	 </div>
	    		</div><!-- End of Modal Content -->
	 		 </div> <!-- End of Modal Dialog -->
		</div><!-- End of Modal Fade -->
		</form>

</main>

<footer class="container">

  <p style="text-align:center" >&copy; Outings Planner 2017 </p>
  <p style="text-align:center">  Made possible by <a href="http://grandcircus.co">  Grand Circus </a>
   with special thanks to Antonella and Merc for all their guidance.</p>
  <p style="text-align:center">
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