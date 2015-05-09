var currentApp = angular.module("addPlaylist", []);
	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.playlists = [];
		$scope.addRow = function(){		
			$scope.playlists.push({ 'name':$scope.name, 'creator': $scope.creator, 'tracks':$scope.tracks });
			$scope.name='';
			$scope.creator='';
			$scope.tracks='';
		};
		$scope.selectedPlaylist = function() {
			console.log("selectedPlaylist");
			$scope.selectedPlaylist = true ? false : true;
		};

		$scope.tracklist = [];
		$scope.addRowtl = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'album': $scope.album, 'artist':$scope.artist });
			$scope.name='';
			$scope.album='';
			$scope.artist='';
		};
		
	});
	
	
function selectPlaylist(){
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
