var currentApp = angular.module("addPlaylist", []);
	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.tracklist = [];
		$scope.addRow = function(){		
			$scope.tracklist.push({ 'name':$scope.name, 'employees': $scope.employees, 'headoffice':$scope.headoffice });
			$scope.name='';
			$scope.employees='';
			$scope.headoffice='';
		};
	});