<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="addPlaylist">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="CSS/myCSS.css" rel="stylesheet">
<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.17/angular.min.js"></script>

</head>
<body ng-controller="ctrlPlaylist">


<nav class="myNavbar navbar navbar-default">
  <div class="myNavbar container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">#</a>
    </div>

   
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Welcome <% out.println(request.getSession().getAttribute("user"));  %></a></li>
      </ul>
  </div><!-- /.container-fluid -->
</nav>


<table style="width:100%"><tbody><tr><td style="width:45%;padding-left:40px;vertical-align: top">
<form id="tabPlaylist" class="form-horizontal" role="form" ng-submit="addRow()" >
<div>Add a playlist</div><br>
	<div class="form-group">
		<label class="col-md-2 control-label">Name</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="name"
				ng-model="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">Creator</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="creator"
				ng-model="creator" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">Tracks</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="tracks"
				ng-model="tracks" />
		</div>
	</div>
	<div class="form-group">								
		<div style="padding-left:110px">
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</div>
	</div>
</form>	
<form hidden="true" id="tabTrack" class="form-horizontal" role="form" ng-submit="addTrack()" >
<div>Add a track</div>	<br/>
	<div class="form-group">
		<label class="col-md-2 control-label">Name</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="Name"
				ng-model="Name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">Artist</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="artist"
				ng-model="artist" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">Album</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="album"
				ng-model="album" />
		</div>
	</div>
	<div class="form-group">								
		<div style="padding-left:110px">
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</div>
	</div>
	</form>
</td><td style="vertical-align: top">
<table class="table" ng-model="clicked">
	<tr>
		<th>Name</th>
		<th>Creator</th>
		<th>Tracks</th>
		<th></th>
	</tr>
	<tr ng-repeat="playlist in playlists" class="ng-scope" onClick="selectPlaylist();">
		<td>{{playlist.name}}</td>
		<td>{{playlist.creator}}</td>
		<td>{{playlist.tracks}}</td>
		<td class="ng-binding">Show tracks</td>
	</tr>
</table>
</td></tr>
<tr id="tracktab" hidden="true"><td></td><td>Tracklist <br/><br/>
<table class="table" ng-model="clicked">
	<tr>
		<th>Name</th>
		<th>Album</th>
		<th>Artist</th>
		
	</tr>
	<tr ng-repeat="track in tracklist" class="ng-scope" onClick="selectTrack();">
		<td>{{track.name}}</td>
		<td>{{track.album}}</td>
		<td>{{track.artist}}</td>
		<td class="ng-binding">Show tracks</td>
	</tr>
</table>
</td></tr></tbody></table>
	

<script src="js/bootstrap.min.js"></script>
<script src="js/myJS.js"></script>
</body>
</html>