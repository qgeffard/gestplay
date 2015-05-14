var currentApp = angular.module("addPlaylist", []);
var playlists = [];
var tracklist = [];
var form = $('#tabPlaylist');



	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.playlists = playlists;
		$scope.tracklist = tracklist;
		$scope.idCurrentPlaylist = 0;  // Mis à jour dès qu'on affiche les tracks.
		$scope.action;
		$scope.addRow = function(){		
				$scope.action = "create";
				$.ajax({
				method : "POST",	
	            url : 'connectedServlet',
	            data : {
	            	action :$scope.action,
	                name : $scope.name,
	                creator : $scope.creator,
	                tracks : $scope.tracks,
	                tracklist : $scope.tracklist
	            },
	            success : function(responseText) {
	            	console.log("success");
	            	$scope.playlists.push({ 'name':$scope.name, 'creator': $scope.creator, 'tracks':$scope.tracks, 'trackList':[] });		
	              //  $('#ajaxGetUserServletResponse').text(responseText);
	            }
	        });
			$scope.name='';
			$scope.creator='';
			$scope.tracks='';		
	
		};
		
		$scope.addRowtl = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'album': $scope.album, 'artist':$scope.artist });
			$scope.name='';
			$scope.album='';
			$scope.artist='';
			$scope.playlists[$scope.idCurrentPlaylist].trackList = $scope.tracklist;
		};
		
		$scope.del = function ( idx ) {
			  var trackToDelete = $scope.tracklist[idx];		
			  $scope.tracklist.splice(idx, 1);
			  $scope.playlists[$scope.idCurrentPlaylist].trackList = $scope.tracklist;
			};
			
		$scope.delPlaylist = function ( idx ) {
			  var trackToDelete = $scope.playlists[idx];		
			  $scope.playlists.splice(idx, 1);
			  
			  var tab = document.getElementById("tabPlaylist");
			  var track = document.getElementById("tabTrack");
			  var tracktab = document.getElementById("tracktab");
			  if(tab.hidden == true) {  // Ici on cache la tracklist
					tracklist = [];
					$scope.tracklist = []
					tab.hidden = false;
					track.style.postion = "relative";
					track.hidden = true;
					tracktab.hidden = true;
				}
			};

		$scope.editPlaylist = function (id) {
			
			var tab = document.getElementById("tabPlaylist");
			var track = document.getElementById("tabTrack");
			var tracktab = document.getElementById("tracktab");
			

			if(tracktab.hidden == true) {  // Ici on affiche la tracklist 
				$scope.idCurrentPlaylist = id;
				tracklist = $scope.playlists[id].trackList;
				$scope.tracklist = tracklist;
				tab.style.postion = "relative";
				tab.hidden = true;
				track.hidden = false;
				tracktab.hidden = false;
			}
			else {   // Ici on cache la tracklist
				tracklist = [];
				$scope.tracklist = []
				tab.hidden = false;
				track.style.postion = "relative";
				track.hidden = true;
				tracktab.hidden = true;
			}
		};
	});
	
	
	
	/*
function selectPlaylist(id){
	var tab = document.getElementById("tabPlaylist");
	var track = document.getElementById("tabTrack");
	var tracktab = document.getElementById("tracktab");
	if(tab.hidden == true) {
	tab.hidden = false;
	track.style.postion = "relative";
	track.hidden = true;
	tracktab.hidden = true;
	}
	else {
	tab.style.postion = "relative";
	tab.hidden = true;
	track.hidden = false;
	tracktab.hidden = false;
	}
}
	*/
	
function loadPlaylist(ident, name,creator,tracks) {
	playlists.push({ 'ident':ident, 'name':name, 'creator': creator, 'tracks':tracks, 'trackList':tracklist });  
}

function addTrackToPlaylist(name, album, artist) {
	tracklist.push({ 'name':name, 'album': album, 'artist':artist });

}


/*
var form = $('#tabPlaylist');
$( "#submitNewPlaylist" ).click(function(){
 $.ajax({
 type: form.attr('method'),
 url: form.attr('action'),
 data: value,
 success: function (data) {
 var result=data;
 $('#result').attr("value",result);
 }
 });
 return false;
 });
*/