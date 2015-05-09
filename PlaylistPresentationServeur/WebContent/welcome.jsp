<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ page import="org.kgj.pds.playlist.presentation.messagingProtocol.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome to our dream playlists...</title>
 
	 <link href="CSS/bootstrap.min.css" rel="stylesheet">
	 <link href="CSS/myCSS.css" rel="stylesheet">
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
        <li><a href="#">Welcome <% out.println(request.getSession().getAttribute("user"));  %></a></li>
      </ul>
  </div><!-- /.container-fluid -->
</nav>


<div class="container">
<h1>Your playlists !</h1>




   		<% 	
   			List<PlaylistType> pT;
    		if(null != request.getSession().getAttribute("playlist")) {
    		pT = (List<PlaylistType>) request.getSession().getAttribute("playlist");
    		TrackListType tL; 
   			   			for(int i = 0; i < pT.size() ; i++) { 
   			   				if(0 != pT.size()) {
   			   					out.println("<script>alert("+i+");</script>");
   							tL = pT.get(i).getTrackList();
   			%>
  <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          <% out.println(pT.get(i).getTitle()); %>
        </a>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
        <table data-toggle="table" class="table table-hover">
	<thead>
		<tr>
			<td>Track</td>
			<td>Album</td>
			<td>Durée</td>
			<td>Créateur</td>
			<td>Info</td>
			<td>Annotation</td>
			<td></td>
			<td></td>
		</tr>
	</thead><tbody>
   			<% for(int j = 0;j < tL.getTrack().size();j++) { %>
		<tr>
			<td><% out.println(tL.getTrack().get(j).getTitle()); %></td>
			<td><% out.println(tL.getTrack().get(j).getAlbum()); %></td>
			<td><% out.println(tL.getTrack().get(j).getDuration()); %></td>
			<td><% out.println(tL.getTrack().get(j).getCreator()); %></td>
			<td><% out.println(tL.getTrack().get(j).getInfo()); %></td>
			<td><% out.println(tL.getTrack().get(j).getAnnotation()); %></td>
			<td><a href="#display">M</a></td>
			<td><a href="#delete">S</a></td>
		</tr>
		<% } } }  %>
		</div>
	</tbody>
</table>
<% } %>

<br><br><br><p align="right">
Afficher, Créer, Modifier, Supprimer.
</p>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>