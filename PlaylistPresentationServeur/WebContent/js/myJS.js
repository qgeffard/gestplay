var currentApp = angular.module("addPlaylist", []);
var playlists = [];
var tracklist = [];
var user = "";
var name = "";
var currentTracks;



	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.user = user;
		$scope.ident = 0;
		$scope.playlists = playlists;
		$scope.tracklist = tracklist;
		$scope.idCurrentPlaylist = 0;  // Mis à jour dès qu'on affiche les tracks.
		$scope.action = "";
		$scope.addRow = function(){		
			name = $scope.name;
			console.log($scope.action);
			if($scope.action == "update") {
				// Ici on modifie
				$.ajax({
					method : "POST",	
		            url : 'connectedServlet',
		            data : {
		            	action : $scope.action,
		            	identifier : $scope.playlists[$scope.idCurrentPlaylist]['ident'],
		                name : $scope.playlists[$scope.idCurrentPlaylist]['name'],
		                creator : $scope.playlists[$scope.idCurrentPlaylist]['creator'],
		                tracks : $scope.playlists[$scope.idCurrentPlaylist]['tracks'],
		                tracklist : $scope.playlists[$scope.idCurrentPlaylist]['tracklist']
		            },
		            success : function(ident) {
		            	console.log("Function Success");
		            	console.log("Ident : "+ident);
		            	if(!ident.equals("Error")) {
		            	$scope.playlists[$scope.idCurrentPlaylist]['name'] = $scope.name;
		            	$scope.playlists[$scope.idCurrentPlaylist]['tracks'] = $scope.playlists[idx]['tracklist'].length;
		            	$scope.playlists[$scope.idCurrentPlaylist]['tracklist'] = $scope.tracklist; // On utilise la variable intermediaire pour éviter les traitements douloureux.
		            	$scope.action = "";
		            	$scope.playlists[$scope.idCurrentPlaylist]['name'] = $scope.name;
		            	} else {
		            		// On envoie la notification de non création 
		            	}
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		                console.log(xhr.status);
		                console.log(thrownError);
		              }	        });
				
			$scope.action = "";
				
			} else {
			$scope.action = "create";
			$scope.tracks = 0;
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
            success : function(ident) {
            	console.log("Function Success");
            	console.log("Ident : "+ident);
            	if(ident != "Error") {
           		console.log("lol");
            	$scope.playlists.push({ 'ident':ident, 'name':name, 'creator': user, 'tracks':0, 'trackList':[] });		
            	$scope.action = "";
            	} else {
            		// On envoie la notification de non création 
            	}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
              }	        });
			}
			
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
			$scope.modifierPlaylist($scope.idCurrentPlaylist);
		};
		
		$scope.del = function ( idx ) {
			  var trackToDelete = $scope.tracklist[idx];		
			  $scope.tracklist.splice(idx, 1);
			  $scope.playlists[$scope.idCurrentPlaylist].trackList = $scope.tracklist;
			  $scope.modifierPlaylist($scope.idCurrentPlaylist);
			};
			
		$scope.delPlaylist = function ( idx ) {
			$scope.action = "delete";
			$.ajax({
			method : "POST",	
            url : 'connectedServlet',
            data : {
            	action : $scope.action,
            	identifier : $scope.playlists[idx]['ident'],
                name : $scope.playlists[idx]['name'],
                creator : $scope.playlists[idx]['creator'],
                tracks : $scope.playlists[idx]['tracks'],
                tracklist : $scope.playlists[idx]['tracklist']
            },
            success : function(ident) {
            	console.log("Function Success");
            	console.log("Ident : "+ident);
            	if(!ident == "Error") {
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
   			$scope.action = "";
            	} else {
            		// Si on reçoit un message d'erreur
            		// Envoie la notification de non suppresion.
            	}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
              }	        });
			};

		$scope.editPlaylist = function (idx) {  // Pour modifier la playlist
			/* On fill le formulaire et on modifie le currentPlaylist id pour changer l'action */
			$scope.name = $scope.playlists[idx]['name'];
			
			if($scope.action == "update") {  // Si on rappuie sur le bouton d'edit, on le cancel
				$scope.action = "";
				$scope.name = "";
				$scope.idCurrentPlaylist = "";
			} else {
				$scope.action = "update";
				$scope.playlists[idx]['name'] = $scope.name;
            	$scope.tracklist = $scope.playlists[idx]['tracklist']; 
            	$scope.idCurrentPlaylist = idx;
			}
		},
		$scope.editPlaylistInfos = function (idx) {
			console.log(idx);
			/* Affichage de la tracklist */
			var tab = document.getElementById("tabPlaylist");
			var track = document.getElementById("tabTrack");
			var tracktab = document.getElementById("tracktab");
			
			if(tracktab.hidden == true) {  // Ici on affiche la tracklist 
				$scope.idCurrentPlaylist = idx;
				tracklist = $scope.playlists[idx].trackList;
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
		},		

		
		$scope.modifierPlaylist = function(idx) {
			$.ajax({
				method : "POST",	
	            url : 'connectedServlet',
	            data : {
	            	action : $scope.action,
	            	identifier : $scope.playlists[idx]['ident'],
	                name : $scope.playlists[idx]['name'],
	                creator : $scope.playlists[idx]['creator'],
	                tracks : $scope.playlists[idx]['tracks'],
	                tracklist : $scope.playlists[idx]['tracklist']
	            },
	            success : function(ident) {
	            	console.log("Function Success");
	            	console.log("Ident : "+ident);
	            	if(!ident.equals("Error")) {
	            	$scope.playlists[idx]['name'] = $scope.name;
	            	$scope.playlists[idx]['tracks'] = $scope.playlists[idx]['tracklist'].length;
	            	$scope.playlists[idx]['tracklist'] = $scope.tracklist; // On utilise la variable intermediaire pour éviter les traitements douloureux.
	            	$scope.action = "";
	            	} else {
	            		// On envoie la notification de non création 
	            	}
	            },
	            error: function (xhr, ajaxOptions, thrownError) {
	                console.log(xhr.status);
	                console.log(thrownError);
	              }	        });
		}
			
	});	
		
		/* ancienne editPlaylist
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
	
	
	*/
	

			
function loadPlaylist(ident, name, creator, tracks) {
	playlists.push({ 'ident':ident, 'name':name, 'creator': creator, 'tracks':tracks, 'trackList':tracklist });  
}

function addTrackToPlaylist(name, album, artist) {
	tracklist.push({ 'name':name, 'album': album, 'artist':artist });
} 