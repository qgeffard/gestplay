CREATE DATABASE "Playlist"
CREATE TABLE track (
	id INT PRIMARY KEY,
	location VARCHAR(64),
	identifier VARCHAR(64),
	title VARCHAR(64),
	creator VARCHAR(64), /* User qui le crée ?*/
	annotation VARCHAR(64),
	info VARCHAR(64),
	image VARCHAR(64),
	album VARCHAR(64),
	tracknum INT,
	duration DATE,
	link VARCHAR(64),
	meta VARCHAR(64),
	extension VARCHAR(64)
);
CREATE TABLE playlist (
	id INT PRIMARY KEY,
	title VARCHAR(64),
	creator VARCHAR(64), /* User qui le crée ?*/
	annotation VARCHAR(64),
	info VARCHAR(64),
	location VARCHAR(64),
	identifier VARCHAR(64),
	image VARCHAR(64),
	date_creation DATE,
	licence VARCHAR(64),
	attribution VARCHAR(64),
	link VARCHAR(64),
	meta VARCHAR(64),
	extension VARCHAR(64),
	tracklist VARCHAR(64),
	version VARCHAR(64)
);
CREATE TABLE playlist_track_bridge (
	track_id,
	playlist_id,
	FOREIGN KEY(track_id) REFERENCES track(id),
	FOREIGN KEY(playlist_id) REFERENCES playlist(id)
);
CREATE TABLE users (
	id INT PRIMARY KEY,
	name VARCHAR(64),
	password VARCHAR(64)
);
