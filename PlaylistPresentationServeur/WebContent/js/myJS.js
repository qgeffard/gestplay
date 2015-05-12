var currentApp = angular.module("addPlaylist", []);
var playlists = [];
var tracklist = [];
var idPlaylist = 0;


	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.playlists = playlists;
		$scope.tracklist = tracklist;
		$scope.count = idPlaylist;
		$scope.addRow = function(){		
			$scope.playlists.push({ 'name':$scope.name, 'creator': $scope.creator, 'tracks':$scope.tracks, 'count':$scope.count });
			$scope.name='';
			$scope.creator='';
			$scope.tracks='';
			document.getElementById("tabPlaylist").getElementsByClassName("showTracks")[$scope.count].setAttribute("onClick","selectPlaylist("+$scope.count+");");
			$scope.count++;
		};
		
		$scope.addRowtl = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'album': $scope.album, 'artist':$scope.artist });
			$scope.name='';
			$scope.album='';
			$scope.artist='';
		};
		
	});
	
	
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

function loadPlaylist(name,creator,tracks) {
	playlists.push({ 'name':name, 'creator': creator, 'tracks':tracks, 'count':idPlaylist });  
	idPlaylist++;
}


