<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="fr" ng-app="addPlaylist">
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	 
	<title>Log-In</title>
	    
	<!-- CSS AND JS -->
	
	<link href="CSS/bootstrap.min.css" rel="stylesheet">
	<link href="CSS/style.css" rel="stylesheet">
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
	<link href="CSS/myCSS.css" rel="stylesheet">
	<script	src="js/jquery.min.js"></script>
	<script	src="js/bootstrap.js"></script>
	<script	src="js/angular.min.js"></script>
	<script	src="js/myPrimeJS.js"></script>
	<script	src="js/myJS.js"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
    
<body class="login" ng-controller="ctrlPlaylist">
<div class="logo"></div>

<div class="heading-1"><p>{{language_Current.h1}}</p></div>
<div class="heading-2"><p>{{language_Current.h2}}<br>{{language_Current.h3}}</p></div>
<br><br>
<div class="content">

      <form class="form-vertical login-form" action="myServlet" method="post">
        <h3 class="form-title">{{language_Current.connection}}</h3>
        
        <div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">{{language_Current.id}}</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" autocomplete="off" placeholder="{{language_Current.id}}" name="login" type="text">
					</div>
				</div>
			</div>
			
		<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">{{language_Current.pass}}</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input class="m-wrap placeholder-no-fix" autocomplete="off" placeholder="{{language_Current.pass}}" name="password" type="password">
					</div>
				</div>
			</div>
			
			<% if(null != session.getAttribute("erreur")) { %>
			<%
				if(request.getSession().getAttribute("connected").equals("-1")) { %>
		<diV class="badlogin">{{language_Current.errorLogin}}</diV>
		<% }
			} %>
		<div class="form-actions">
			<input id="setLang" hidden="true" name="language" value="EN">
				<button type="submit" class="myBtn blue btn pull-right">
				{{language_Current.login}}  <i class="fa fa-sign-in"></i>
				</button>            
			</div>
     </form>

    </div>
    
    <div class="pull-down pull-right">
	<a ng-click="changeLanguage('FR')"><img src="img/FR-fr.png"  ></img></a>
	<a ng-click="changeLanguage('EN')"><img src="img/USA-usa.png" ></img></a>
    </div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	<script src="js/myJS.js"></script>
  </body>
</html>