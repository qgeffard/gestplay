CREATE DATABASE IF NOT EXISTS gestplay;

USE gestplay;

CREATE TABLE IF NOT EXISTS track (
	identifier INT  PRIMARY KEY AUTO_INCREMENT,
	location 		VARCHAR(64),
	title 			VARCHAR(64),
	creator 		VARCHAR(64),
	annotation 		VARCHAR(64),
	info 			VARCHAR(64),
	image 			VARCHAR(64),
	album 			VARCHAR(64),
	tracknum 		INT,
	duration 		INT,
	link 			VARCHAR(64),
	meta 			VARCHAR(64),
	extension 		VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS playlist (
	identifier 	INT PRIMARY KEY AUTO_INCREMENT,
	title			VARCHAR(64),
	creator			VARCHAR(64),
	/* FOREIGN KEY Pour le user*/
	annotation		VARCHAR(64),
	info			VARCHAR(64),
	location		VARCHAR(64),
	image			VARCHAR(64),
	date_creation	DATE,
	licence 		VARCHAR(64),
	attribution 	VARCHAR(64),
	link			VARCHAR(64),
	meta			VARCHAR(64),
	extension		VARCHAR(64),
	tracklist		VARCHAR(64),
	version			VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS playlist_track_bridge (
	track_id		INT,
	playlist_id 	INT,
	PRIMARY KEY (track_id, playlist_id),
	FOREIGN KEY(track_id) REFERENCES track(id) ON DELETE CASCADE,
	FOREIGN KEY(playlist_id) REFERENCES playlist(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS command (
	id 				INT PRIMARY KEY AUTO_INCREMENT,
	username 		VARCHAR(64),
	description 	VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS users (
	id 				INT PRIMARY KEY AUTO_INCREMENT,
	username 		VARCHAR(64),
	password 		VARCHAR(64),
	image 			VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS playlistxml (
	id INT  PRIMARY KEY AUTO_INCREMENT,
    version 		VARCHAR(64),
    statut 			BOOLEAN,
	playlist 		LONGTEXT,
    creator			VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS query (
	id INT  		PRIMARY KEY ,
    version 		PRIMARY KEY VARCHAR(64),
    statut 			BOOLEAN,
	query 			LONGTEXT,
    creator			VARCHAR(64)
);