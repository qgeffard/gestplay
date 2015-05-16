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
INSERT INTO query SET
	id=1,
	version=1,
	statut=true,
	creator="foo",
	query='<?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:query xmlns:ns2="http://xspf.org/ns/0/"><queryId>fra8jmi1a8o0s3n7h3vjag0jba</queryId><userManager><user><login>foo</login><password>bar</password></user></userManager><status><succed>succed</succed><progress>progress</progress></status><action><nameAction>login</nameAction></action><ns2:playlist><ns2:title>compil</ns2:title><ns2:creator>foo</ns2:creator><ns2:annotation>annotationfoo</ns2:annotation><ns2:info>infofoo</ns2:info><ns2:location>locationfoo</ns2:location><ns2:identifier>1</ns2:identifier><ns2:image>imagefoo</ns2:image><ns2:license>aze</ns2:license><ns2:trackList><ns2:track><ns2:title>titletrack1</ns2:title><ns2:creator>creatortrack1</ns2:creator><ns2:annotation>annotationtrack1</ns2:annotation><ns2:info>infotrack1</ns2:info><ns2:image>imagetrack1</ns2:image><ns2:album>albumtrack1</ns2:album><ns2:trackNum>1</ns2:trackNum><ns2:duration>140</ns2:duration></ns2:track><ns2:track><ns2:title>titletrack2</ns2:title><ns2:creator>creatortrack2</ns2:creator><ns2:annotation>annotationtrack2</ns2:annotation><ns2:info>infotrack2</ns2:info><ns2:image>imagetrack2</ns2:image><ns2:album>albumtrack2</ns2:album><ns2:trackNum>2</ns2:trackNum><ns2:duration>120</ns2:duration></ns2:track><ns2:track><ns2:title>titletrack3</ns2:title><ns2:creator>creatortrack3</ns2:creator><ns2:annotation>annotationtrack3</ns2:annotation><ns2:info>infotrack3</ns2:info><ns2:image>imagetrack3</ns2:image><ns2:album>albumtrack3</ns2:album><ns2:trackNum>3</ns2:trackNum><ns2:duration>200</ns2:duration></ns2:track></ns2:trackList></ns2:playlist></ns2:query>'
;