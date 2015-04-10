CREATE DATABASE "Playlist"
CREATE TABLE track (
	track_id INT PRIMARY KEY,
	track_location VARCHAR(64),
	tracl_identifier VARCHAR(64),
	track_title VARCHAR(64),
	track_creator VARCHAR(64),
	track_annotation VARCHAR(64),
	track_info VARCHAR(64),
	track_image VARCHAR(64),
	track_album VARCHAR(64),
	track_tracknum INT,
	track_duration DATE,
	track_link VARCHAR(64),
	track_meta VARCHAR(64),
	track_extension VARCHAR(64),
);
CREATE TABLE playlist (
	playlist_id INT PRIMARY KEY,
	playlist_title VARCHAR(64),
	playlist_creator VARCHAR(64),
	playlist_annotation VARCHAR(64),
	playlist_info VARCHAR(64),
	playlist_location VARCHAR(64),
	playlist_identifier VARCHAR(64),
	playlist_image VARCHAR(64),
	playlist_date DATE,
	playlist_licence VARCHAR(64),
	playlist_attribution VARCHAR(64),
	playlist_link VARCHAR(64),
	playlist_meta VARCHAR(64),
	playlist_extension VARCHAR(64),
	playlist_tracklist VARCHAR(64),
	playlist_version VARCHAR(64),
);
CREATE TABLE playlist_track_bridge (
	playlist_track_bridge_track_id
	playlist_track_bridge_playlist_id
	FOREIGN KEY(playlist_track_bridge_track_id) REFERENCES track(track_id);
	FOREIGN KEY(playlist_track_bridge_playlist_id) REFERENCES playlist(playlist_id);
);
CREATE TABLE users (
	user_id INT PRIMARY KEY,
	user_name VARCHAR(64),
	user_password VARCHAR(64),
);
