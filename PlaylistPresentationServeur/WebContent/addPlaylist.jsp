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
<script	src="js/jquery.min.js"></script>
<script	src="js/bootstrap.js"></script>
<script	src="js/angular.min.js"></script>
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
      <a class="myNavbar navbar-brand" ng-click="undo()"><i class="icon-refresh"></i></a>
      <a class="myNavbar navbar-brand" ng-click="redo()"><i class="icon-repeat"></i></a>
    </div>

   
      <ul class="nav navbar-nav navbar-right">
        <li><a class="myNavbar navbar-brand" >{{language_Current.welcome}} <% out.println(request.getSession().getAttribute("id"));  %></a></li>
        <li><a ng-click="changeLanguage('EN')"><img src="img/USA-usa.png" ></img></a></li>
        <li><a ng-click="changeLanguage('FR')"><img src="img/FR-fr.png"  ></img></a></li>
      </ul>
  </div><!-- /.container-fluid -->
</nav>


<table style="width:100%"><tbody><tr><td style="width:45%;padding-left:40px;vertical-align: top">
<form id="tabPlaylist" class="form-horizontal" role="form" ng-submit="addRow()" >
<div>{{language_Current.addPL}}</div><br>
	<div class="form-group">
		<label class="col-md-2 control-label">{{language_Current.name}}</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name=""
				ng-model="name" />
		</div>
	</div>
	
	<div class="form-group">								
		<div style="padding-left:110px">
		<button id="submitNewPlaylist" type="submit" class="btn btn-primary">
		{{language_Current.submit}}  
		</button> 
		</div>
	</div>
</form>	
<form hidden="true" id="tabTrack" class="form-horizontal" role="form" ng-submit="addRowtl()" >
<div>{{language_Current.addTL}}</div>	<br/>
	<div class="form-group">
		<label class="col-md-2 control-label">{{language_Current.name}}</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="name"
				ng-model="name" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">{{language_Current.album}}</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="album"
				ng-model="album" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">{{language_Current.artist}}</label>
		<div class="col-md-4">
			<input type="text" class="form-control" name="artist"
				ng-model="artist" />
		</div>
	</div>
	<div class="form-group">								
		<div style="padding-left:110px">
			<button type="submit" class="myBtn blue btn pull-right">
					{{language_Current.submit}}  <i class="fa fa-sign-in"></i>
				</button> 

		</div>
	</div>
	</form>
</td><td style="vertical-align: top">


   		
   			

<table class="table" ng-model="clicked">
	<tr>
		<th>{{language_Current.name}}</th>
		<th>{{language_Current.creator}}</th>
		<th>{{language_Current.tracks}}</th>
		<th></th>
	</tr>
	<tr ng-repeat="playlist in playlists" class="ng-scope">
		<td ng-click="editPlaylistInfos($index)">{{playlist.name}}</td>
		<td ng-click="editPlaylistInfos($index)">{{playlist.creator}}</td>
		<td ng-click="editPlaylistInfos($index)">{{playlist.tracks}}</td>
		<td class="ng-binding"><a ng-click="editPlaylist($index)"><i class="icon-edit icon-2x"></i></a> <a ng-click="delPlaylist($index)"><i class="icon-remove-sign icon-2x"></i></a></td>
	</tr>
</table>


</td></tr>

<tr id="tracktab" hidden="true"><td></td><td>{{language_Current.trackL}} <br/><br/>

<form id="submitTrack" ng-submit="updatePlaylist()" role="form">
<table class="table" ng-model="clicked">
	<tr>
		<th>{{language_Current.name}}</th>
		<th>{{language_Current.album}}</th>
		<th>{{language_Current.artist}}</th>
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
   			   		System.out.println(pT.get(i));
   			   		System.out.println(pT.get(i).getIdentifier().toString());
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
				
<%   			   		}
   			   	}
    		}
    		String string = "<script>window.onload = function() { user = "+"'"+request.getSession().getAttribute("id")+"'"+"; }</script>";
    		out.println(string);
   			%>
 
</body>
</html>