INSERT INTO playlist SET 
	id=1,
	title="compil",
	creator="foo",
	annotation="annotationfoo",
	info="infofoo",
	location="locationfoo",
	identifier="identifierfoo",
	image="imagefoo",
	date_creation=
	licence="licencefoo",
	attribution="attributionfoo",
	link="linkfoo",
	meta="metafoo",
	extension="extfoo",
	version="0.1"
;
INSERT INTO track SET
	id=1,
    location="locationtrack1",
    identifier="identifiertrack1",
    title="titletrack1",
    creator="creatortrack1",
    annotation="annotationtrack1",
    info="infotrack1",
    image="imagetrack1",
    album="albumtrack1",
    tracknum=1,
    duration=140,
    link="linktrack1",
    meta="metatrack1",
    extension="extensiontrack1"
;
INSERT INTO track SET
	id=2,
    location="locationtrack2",
    identifier="identifiertrack2",
    title="titletrack2",
    creator="creatortrack2",
    annotation="annotationtrack2",
    info="infotrack2",
    image="imagetrack2",
    album="albumtrack2",
    tracknum=2,
    duration=120,
    link="linktrack2",
    meta="metatrack2",
    extension="extensiontrack2"
;
INSERT INTO track SET
	id=3,
    location="locationtrack3",
    identifier="identifiertrack3",
    title="titletrack3",
    creator="creatortrack3",
    annotation="annotationtrack3",
    info="infotrack3",
    image="imagetrack3",
    album="albumtrack3",
    tracknum=3,
    duration=200,
    link="linktrack3",
    meta="metatrack3",
    extension="extensiontrack3"
;
INSERT INTO playlist_track_bridge SET
	track_id=1,
    playlist_id=1
;
INSERT INTO playlist_track_bridge SET
	track_id=2,
    playlist_id=1
;
INSERT INTO playlist_track_bridge SET
	track_id=3,
    playlist_id=1
;