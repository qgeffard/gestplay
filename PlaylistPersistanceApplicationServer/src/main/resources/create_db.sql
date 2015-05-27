CREATE TABLE IF NOT EXISTS command (
	id 				INT PRIMARY KEY AUTO_INCREMENT,
	creator 		VARCHAR(64),
	description 	VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS users (
	id 				INT PRIMARY KEY AUTO_INCREMENT,
	creator 		VARCHAR(64),
	password 		VARCHAR(64)
);
CREATE TABLE IF NOT EXISTS query (
	id				INT,
	version			INT,
	statut			boolean,
	creator			VARCHAR(64),
	query			LONGTEXT,
	PRIMARY KEY(id,version)
);