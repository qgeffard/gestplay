var currentApp = angular.module("addPlaylist", []);
	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.playlists = [];
		$scope.addRow = function(){		
			$scope.playlists.push({ 'name':$scope.name, 'employees': $scope.employees, 'headoffice':$scope.headoffice });
			$scope.name='';
			$scope.employees='';
			$scope.headoffice='';
		};
		$scope.selectedPlaylist = function() {
			console.log("selectedPlaylist");
			$scope.selectedPlaylist = true ? false : true;
		};
	});
	
var currentApp = angular.module("addTrack", []);
	currentApp.controller("ctrlTrack", function($scope) {
		$scope.tracklist = [];
		$scope.addRow = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'employees': $scope.employees, 'headoffice':$scope.headoffice });
			$scope.name='';
			$scope.employees='';
			$scope.headoffice='';
		};
		
	});
	
	
function selectPlaylist(){
	var tab = document.getElementById("tabPlaylist");
	var track = document.getElementById("tabTrack");
	if(tab.hidden == true) {
	tab.hidden = false;
	track.style.postion = "relative";
	track.hidden = true;
	}
	else {
	tab.style.postion = "relative";
	tab.hidden = true;
	track.hidden = false;
	}
}
