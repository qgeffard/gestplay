var currentApp = angular.module("addPlaylist", []);
var playlists = [];
var tracklist = [];
var user = "";
var name = "";
var currentTracks;

	currentApp.controller("ctrlPlaylist", function($scope) {
		$scope.language_EN = 
		{
			"welcome": "Welcome",
			"addPL": "Add a playlist",
			"addTL": "Add a track",
			"submit": "Submit",
			"savePL": "Save playlist",
			"trackL": "Tracklist",
			"name":"Name",
			"creator":"Creator",
			"tracks":"Tracks",
			"album":"Album",
			"artist":"Artist",
			"h1":"Your music without limit..",
			"h2":"Create and edit your playlists to infinity.  ",
			"h3":"Connect now and enjoy.",
			"connection":"Sign-in",
			"login":"Log-in",
			"id":"Username",
			"pass":"Password"
		};

		$scope.language_FR = 
		{
		    "welcome": "Bienvenue",
		    "addPL": "Ajouter une playlist",
		    "addTL": "Ajouter une musique",
		    "submit": "Envoyer",
		    "savePL": "Sauvegarder",
		    "trackL": "Liste des musiques",
		    "name":"Titre",
		    "creator":"Créateur",
		    "tracks":"Taille",
		    "album":"Album",
		    "artist":"Artiste",
		    "h1":"Votre musique en illimité..",
		    "h2":"Créer et modifier vos playlists à l'infini.  ",
		    "h3":"Connectez-vous dès maintenant pour en profiter.",
		    "connection":"Se connecter",
		    "login":"Log-in",
		    "id":"Identifiant",
		    "pass":"Mot de passe"
		};
		$scope.language_Current = $scope.language_EN;
		
		$scope.changeLanguage = function(lang) {
			switch(lang) {
			case "FR":
				console.log("FR");
				$scope.language_Current = $scope.language_FR;
				break;
			case "EN":
				console.log("EN");
				$scope.language_Current = $scope.language_EN;
				break;
			}
			
		};
		
		$scope.user = user;
		$scope.ident = 0;
		$scope.playlists = playlists;
		$scope.tracklist = tracklist;
		$scope.idCurrentPlaylist = 0;  // Mis à jour dès qu'on affiche les tracks.
		$scope.action = "";
		$scope.addRow = function(){		
			name = $scope.name;
			if($scope.action == "update") {
					// Ici on modifie
				$.ajax({
					method : "POST",	
		            url : 'connectedServlet',
		            data : {
		            	action : $scope.action,
		            	identifier : $scope.playlists[$scope.idCurrentPlaylist]['ident'],
		                name : $scope.name,
		                creator : $scope.playlists[$scope.idCurrentPlaylist]['creator'],
		                tracks : $scope.playlists[$scope.idCurrentPlaylist]['tracks'],
		                tracklist : $scope.playlists[$scope.idCurrentPlaylist]['tracklist']
		            },
		            success : function(ident) {
		            	if(ident != "Error") {
		            	$scope.playlists[$scope.idCurrentPlaylist]['name'] = $scope.name;
		            	$scope.playlists[$scope.idCurrentPlaylist]['tracklist'] = $scope.tracklist; // On utilise la variable intermediaire pour éviter les traitements douloureux.
		            //	$scope.playlists[$scope.idCurrentPlaylist]['tracks'] = $scope.playlists[$scope.idCurrentPlaylist]['tracklist'].length;
		            	
		            	$scope.playlists[$scope.idCurrentPlaylist]['name'] = name;
		            	$scope.action = "";
		            	$scope.$apply(); // Allez, on se motive, le scope a changé
		            	} else {
		            		// On envoie la notification de non création 
		            	}
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		                console.log(xhr.status);
		                console.log(thrownError);
		              }	        });
				
			$scope.action = "";
				
			} else {  // Et ici on ajoute
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
            	if(ident != "Error") {
            	$scope.playlists.push({ 'ident':ident, 'name':name, 'creator': user, 'tracks':0, 'trackList':[] });		
            	$scope.action = "";
            	
            	$scope.$apply(); // Allez, on se motive, le scope a changé
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
		};
		$scope.undo = function() {
			$scope.action = "undo";
			$.ajax({
				method : "POST",	
	            url : 'connectedServlet',
	            data : {
	            	action : $scope.action
	            },
	            success : function(ident) {
	            	if(ident != "Error") {
	            		location.reload();
	            	} else {
	            		// Si on reçoit un message d'erreur
	            		// Envoie la notification de non suppresion.
	            	}
	            },
	            error: function (xhr, ajaxOptions, thrownError) {
	                console.log(xhr.status);
	                console.log(thrownError);
	              }	        });
		}
		$scope.redo = function() {
			$scope.action = "redo";
			$.ajax({
				method : "POST",	
	            url : 'connectedServlet',
	            data : {
	            	action : $scope.action
	            },
	            success : function(ident) {
	            	if(ident != "Error") {
	            		location.reload();
	            	} else {
	            		// Si on reçoit un message d'erreur
	            		// Envoie la notification de non suppresion.
	            	}
	            },
	            error: function (xhr, ajaxOptions, thrownError) {
	                console.log(xhr.status);
	                console.log(thrownError);
	              }	        });
		}
		$scope.del = function ( idx ) {
			  var trackToDelete = $scope.tracklist[idx];		
			  $scope.tracklist.splice(idx, 1);
			  $scope.playlists[$scope.idCurrentPlaylist].trackList = $scope.tracklist;
			};
			
		$scope.delPlaylist = function ( idx ) {
			$scope.action = "delete";
			$scope.idCurrentPlaylist = idx;
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
            	if(ident != "Error") {
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
   			$scope.$apply(); // Allez, on se motive, le scope a changé
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
				$scope.editPlaylistInfos($scope.idCurrentPlaylist);
			} else {
				$scope.action = "update";
				$scope.playlists[idx]['name'] = $scope.name;
            	$scope.tracklist = $scope.playlists[idx]['tracklist']; 
            	$scope.idCurrentPlaylist = idx;
			}
		},
		
		$scope.editPlaylistInfos = function (idx) {
			/* Affichage de la tracklist */
			var tab = document.getElementById("tabPlaylist");
			var track = document.getElementById("tabTrack");
			var tracktab = document.getElementById("tracktab");
			
			if(tracktab.hidden == true) {  // Ici on affiche la tracklist 
				$scope.idCurrentPlaylist = idx;
				tracklist = $scope.playlists[idx]['trackList'];
				$scope.tracklist = tracklist;
				tab.style.postion = "relative";
				tab.hidden = true;
				track.hidden = false;
				tracktab.hidden = false;
				$scope.action = "update";
			}
			else {   // Ici on cache la tracklist
				tracklist = [];
				$scope.tracklist = [];
				tab.hidden = false;
				track.style.postion = "relative";
				track.hidden = true;
				tracktab.hidden = true;
				$scope.action = "";
			}
		},		

		$scope.updatePlaylist = function() {
			$scope.playlists[$scope.idCurrentPlaylist]['trackList'] = $scope.tracklist;
			$scope.modifierPlaylist($scope.idCurrentPlaylist);
		},
		$scope.modifierPlaylist = function(idx) {
			console.log(JSON.stringify($scope.playlists[idx].trackList));
			$.ajax({
				method : "POST",	
	            url : 'connectedServlet',
	            data : {
	            	action : $scope.action,
	            	identifier : $scope.playlists[idx].ident,
	                name : $scope.playlists[idx].name,
	                creator : $scope.playlists[idx].creator,
	                tracks : $scope.playlists[idx].tracks,
	                tracklist : JSON.stringify($scope.playlists[idx].trackList)
	            },
	            success : function(ident) {
	            	if(ident != "Error") {
	            	$scope.playlists[idx]['tracklist'] = $scope.tracklist; // On utilise la variable intermediaire pour éviter les traitements douloureux.
	            	$scope.playlists[idx]['tracks'] = $scope.playlists[idx]['tracklist'].length;
	            	$scope.action = "";
	            	$scope.$apply(); // Allez, on se motive, le scope a changé
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
	tracklist = [];
}

function addTrackToPlaylist(name, album, artist) {
	tracklist.push({ 'name':name, 'album': album, 'artist':artist });
} 