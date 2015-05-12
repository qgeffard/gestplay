var currentApp = angular.module("addPlaylist", []);
var playlists = [];
var tracklist = [];



	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.playlists = playlists;
		$scope.tracklist = tracklist;
		$scope.addRow = function(){		
			$scope.playlists.push({ 'name':$scope.name, 'creator': $scope.creator, 'tracks':$scope.tracks, 'trackList':[] });
			$scope.name='';
			$scope.creator='';
			$scope.tracks='';
		};
		
		$scope.addRowtl = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'album': $scope.album, 'artist':$scope.artist });
			$scope.name='';
			$scope.album='';
			$scope.artist='';
		};
		
		$scope.del = function ( idx ) {
			  var trackToDelete = $scope.tracklist[idx];		
			  $scope.tracklist.splice(idx, 1);
			};
			
		$scope.delPlaylist = function ( idx ) {
			  var trackToDelete = $scope.playlists[idx];		
			  $scope.playlists.splice(idx, 1);
			};

		$scope.editPlaylist = function (id) {
				var tab = document.getElementById("tabPlaylist");
				var track = document.getElementById("tabTrack");
				var tracktab = document.getElementById("tracktab");
				if(tab.hidden == true) {
				tracklist = [];
				$scope.tracklist = []
				tab.hidden = false;
				track.style.postion = "relative";
				track.hidden = true;
				tracktab.hidden = true;
				}
				else {
				tracklist = $scope.playlists[id].trackList;
				$scope.tracklist = tracklist;
				tab.style.postion = "relative";
				tab.hidden = true;
				track.hidden = false;
				tracktab.hidden = false;
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
	
function loadPlaylist(name,creator,tracks) {
	playlists.push({ 'name':name, 'creator': creator, 'tracks':tracks, 'trackList':tracklist });  
}

function addTrackToPlaylist(name, album, artist) {
	tracklist.push({ 'name':name, 'album': album, 'artist':artist });
}


