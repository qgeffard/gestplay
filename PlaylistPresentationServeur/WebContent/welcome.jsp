<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="org.kgj.pds.playlist.presentation.messagingProtocol.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to our dream playlists...</title>
 
 <link href="bootstrap.min.css" rel="stylesheet">
	<link href="myCSS.css" rel="stylesheet">
</head>
<body>


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
        <li><a href="#"><% out.println(request.getSession().getAttribute("user"));  %></a></li>
      </ul>
  </div><!-- /.container-fluid -->
</nav>


<div class="container">
<h1>Vos playlist..</h1>



<table data-toggle="table" class="table table-hover">
	<thead>
		<tr>
			<td>Nom</td>
			<td>Track</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</thead><tbody>
		<tr data-toggle="collapse" data-target="#playlist1" class="clickable">
			<td>Nom Playlist 1</td>
			<td>45</td>
			<td><a href="#display">M</a></td>
			<td><a href="#delete">S</a></td>
		</tr>
		
   		<% 	
   			List<PlaylistType> pT;
    		if(null != request.getSession().getAttribute("playlist")) {
    		pT = (List<PlaylistType>) request.getSession().getAttribute("playlist");
    		List<TrackListType> tL; 
   			int size = pT.size(); 
   			for(int i = 0; i < size ; i++) { 
   			tL = (List<TrackListType>) pT.get(i).getTrackList();
   			%>
		<tr>
			<td><% out.println(pT.get(i).getTitle()); %></td>
			<td><% out.println(tL.size()); %></td>
			<td><a href="#display">M</a></td>
			<td><a href="#delete">S</a></td>
		</tr>
		<% } } %>
	</tbody>
</table>

<br><br><br><p align="right">
Afficher, Créer, Modifier, Supprimer.
</p>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>