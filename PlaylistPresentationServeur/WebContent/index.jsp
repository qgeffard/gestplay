<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>Log-In</title>
	    
	<!-- Bootstrap -->
	
	<link href="bootstrap.min.css" rel="stylesheet">
	<link href="style.css" rel="stylesheet">
	<link href="myCSS.css" rel="stylesheet">
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
    
<body class="login">
<div class="logo"></div>

<div class="heading-1"><p>Votre musique en illimité..</p></div>
<div class="heading-2"><p>Créer et modifier vos playlists à l'infini.<br>Connectez-vous dès maintenant pour en profiter.</p></div>
<br><br>
<div class="content">

      <form class="form-vertical login-form" action="myServlet" method="post">
        <h3 class="form-title">Se connecter</h3>
        
        <div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Identifiant</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-user"></i>
						<input class="m-wrap placeholder-no-fix" autocomplete="off" placeholder="Identifiant" name="login" type="text">
					</div>
				</div>
			</div>
			
		<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Mot de passe</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-lock"></i>
						<input class="m-wrap placeholder-no-fix" autocomplete="off" placeholder="Mot de passe" name="password" type="password">
					</div>
				</div>
			</div>
		
		<div class="form-actions">
				<button type="submit" class="myBtn blue btn pull-right">
				Login  <i class="fa fa-sign-in"></i>
				</button>            
			</div>
     </form>

    </div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>