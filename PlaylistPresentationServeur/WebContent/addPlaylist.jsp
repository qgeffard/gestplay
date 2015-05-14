<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.kgj.pds.playlist.presentation.messagingProtocol.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="addPlaylist">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="CSS/myCSS.css" rel="stylesheet">
<link href="CSS/bootstrap.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.17/angular.min.js"></script>
<script src="js/myPrimeJS.js"></script>

</head>
<body ng-controller="ctrlPlaylist">
<% int Id; %>

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
      <a class="myNavbar navbar-brand" href="#">Actions</a>
      <a class="navbarSelected myNavbar navbar-brand" href="#">Manage playlists</a>
    </div>

   
      <ul class="nav navbar-nav navbar-right">
        <li><a class="myNavbar navbar-brand" href="#">Welcome <% out.println(request.getSession().getAttribute("id"));  %></a></li>
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
			<input id="submitNewPlaylist" type="submit" value="Submit" class="btn btn-primary"/>
		</div>
	</div>
</form>	
<form hidden="true" id="tabTrack" class="form-horizontal" role="form" ng-submit="addRowtl()" >
<div>Add a track</div>	<br/>
	<div class="form-group">
		<label class="col-md-2 control-label">Name</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="name"
				ng-model="name" />
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
		<label class="col-md-2 control-label">Artist</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="artist"
				ng-model="artist" />
		</div>
	</div>
	<div class="form-group">								
		<div style="padding-left:110px">
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</div>
	</div>
	</form>
</td><td style="vertical-align: top">


   		
   			
<form id="submitPlaylist" action="connectedServlet?action=savePlaylists" method="post">
<table class="table" ng-model="clicked">
	<tr>
		<th>Name</th>
		<th>Creator</th>
		<th>Tracks</th>
		<th></th>
	</tr>
	<tr ng-repeat="playlist in playlists" class="ng-scope" ng-click="editPlaylist($index)">
		<td>{{playlist.name}}</td>
		<td>{{playlist.creator}}</td>
		<td>{{playlist.tracks}}</td>
		<td class="ng-binding"><a ng-click="editPlaylist($index)"><i class="icon-edit icon-2x"></i></a> <a ng-click="delPlaylist($index)"><i class="icon-remove-sign icon-2x"></i></a></td>
	</tr>
</table>
<input type="submit" value="Save playlists" class="alignright btn btn-primary"/>
</form>

</td></tr>

<tr id="tracktab" hidden="true"><td></td><td>Tracklist <br/><br/>

<form id="submitTrack" action="connectedServlet?action=saveTrack" method="post">
<table class="table" ng-model="clicked">
	<tr>
		<th>Name</th>
		<th>Album</th>
		<th>Artist</th>
		<th></th>
	</tr>
	<tr ng-repeat="track in tracklist" class="ng-scope">
		<td>{{track.name}}</td>
		<td>{{track.album}}</td>
		<td>{{track.artist}}</td>
		<td><a ng-click="del($index)"><i class="icon-remove-sign icon-2x"></i></a></td>
	</tr>
</table>
<input type="submit" value="Save playlist" class="alignright btn btn-primary"/>
</form>

</td></tr></tbody></table>
	

<script src="js/bootstrap.min.js"></script>
<script src="js/myJS.js"></script>


   		<% 	
   			List<PlaylistType> pT;
    		if(null != request.getSession().getAttribute("playlist")) {
    			pT = (List<PlaylistType>) request.getSession().getAttribute("playlist");
    			TrackListType tL; 
    			
   			   	for(int i = 0; i < pT.size() ; i++) { 
   			   		if(0 != pT.size()) {
   						tL = pT.get(i).getTrackList();
   						pT.get(i).getTitle();
   						String str = "<script>window.onload = loadPlaylist("+'"';
   						str = str + pT.get(i).getIdentifier().toString()+'"'+','+'"'+pT.get(i).getTitle().toString()+'"'+','+'"'+pT.get(i).getCreator().toString()+'"'+','+'"'+pT.get(i).getTrackList().getTrack().size();
   						str = str + '"';
   						str = str + ");</script>";
   						 
   					for(int j = 0; j < tL.getTrack().size(); j++) {
   						String toTracklist = "<script>window.onload = addTrackToPlaylist("+'"';
   						toTracklist = toTracklist + tL.getTrack().get(j).getTitle().toString()+'"'+','+'"'+tL.getTrack().get(j).getAlbum().toString()+'"'+','+'"'+pT.get(i).getTrackList().getTrack().get(j).getCreator()+ '"'+ ");</script>";
   						out.println(toTracklist);
   					}
   					
   					out.println(str); 
   						%>
<script>   					
<%   			   		}
   			   	}
    		}
   			%>
   			</script>
</body>
</html>