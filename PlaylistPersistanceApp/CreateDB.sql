CREATE DATABASE "Playlist"
CREATE TABLE track (
	id INT PRIMARY KEY,
	location VARCHAR(64),
	/*URI of resource to be rendered. Probably an audio resource, but MAY be any type of resource with a 
	 * well-known duration, such as video, a SMIL document, or an XSPF document. The duration of the resource 
	 * defined in this element defines the duration of rendering. xspf:track elements MAY contain zero or
	 *  more location elements, but a user-agent MUST NOT render more than one of the named resources.*/
	identifier VARCHAR(64),
	/* Canonical ID for this resource. Likely to be a hash or other location-independent name,
	 * such as a MusicBrainz identifier. MUST be a legal URI. xspf:track elements MAY contain zero
	 * or more identifier elements.
	 * 
	 * For example, the URI http://musicbrainz.org/track/7e1d6f5f-0ac3-4889-8b57-506a67b459fc.html
	 * is an identifier for a specific song,
	 * but dereferencing that identifier will not yield a copy of the song.*/
	title VARCHAR(64),
	/* Human-readable name of the track that authored the resource which defines the duration of track rendering.
	 * This value is primarily for fuzzy lookups, though a user-agent may display it. xspf:track elements MAY 
	 * contain exactly one.*/
	creator VARCHAR(64),
	/*Human-readable name of the entity (author, authors, group, company, etc) that authored the resource which
	 * defines the duration of track rendering. This value is primarily for fuzzy lookups,
	 * though a user-agent may display it. xspf:track elements MAY contain exactly one.*/
	annotation VARCHAR(64),
	/* A human-readable comment on the track. This is character data, not HTML,
	 * and it may not contain markup. xspf:track elements MAY contain exactly one.*/
	info VARCHAR(64),
	/*URI of a place where this resource can be bought or more info can be found.
	 * xspf:track elements MAY contain exactly one.*/
	image VARCHAR(64),
	/*URI of an image to display for the duration of the track. xspf:track elements MAY contain exactly one.*/
	album VARCHAR(64),
	/*Human-readable name of the collection from which the resource which defines the duration of track rendering
	 * comes. For a song originally published as a part of a CD or LP, this would be the title of the original 
	 * release. This value is primarily for fuzzy lookups, though a user-agent may display it. xspf:track 
	 * elements MAY contain exactly one.*/
	tracknum INT,
	/*Integer with value greater than zero giving the ordinal position of the media on the xspf:album.
	 * This value is primarily for fuzzy lookups, though a user-agent may display it. xspf:track elements
	 * MAY contain exactly one. It MUST be a valid XML Schema nonNegativeInteger.*/
	duration DATE,
	/*The time to render a resource, in milliseconds. It MUST be a valid XML Schema nonNegativeInteger.
	 * This value is only a hint — different XSPF generators will generate slightly different values.
	 * A user-agent MUST NOT use this value to determine the rendering duration, since the data will likely be
	 * low quality. xspf:track elements MAY contain exactly one duration element.*/
	link VARCHAR(64),
	/*The link element allows XSPF to be extended without the use of XML namespaces. xspf:track elements MAY 
	 * contain zero or more link elements.
	<link rel="http://foaf.org/namespace/version1">http://socialnetwork.org/foaf/mary.rdfs</link>*/
	meta VARCHAR(64),
	/*The meta element allows metadata fields to be added to xspf:track elements. xspf:track elements MAY
	 * contain zero or more meta elements.
	<meta rel="http://example.org/key">value</meta>*/
	extension VARCHAR(64)
	/*The extension element allows non-XSPF XML to be included in XSPF documents. The purpose is to allow nested
	 * XML, which the meta and link elements do not. xspf:track elements MAY contain zero or more extension
	 * elements.
	<playlist version="1" xmlns="http://xspf.org/ns/0/" xmlns:cl="http://example.com">
	  <trackList>
	    <track>
	      <extension application="http://example.com">
	        <cl:clip start="25000" end="34500"/>
	      </extension>
	    </track>
	  </trackList>
	</playlist>*/
);
CREATE TABLE playlist (
	id INT PRIMARY KEY,
	title VARCHAR(64),
	/* A human-readable title for the playlist. xspf:playlist elements MAY contain exactly one. */
	creator VARCHAR(64),
	/* Human-readable name of the entity (author, authors, group, company, etc) that authored the 
	 * playlist. xspf:playlist elements MAY contain exactly one. */
	annotation VARCHAR(64),
	/* A human-readable comment on the playlist. This is character data, not HTML, and it may not 
	 * contain markup. xspf:playlist elements MAY contain exactly one. */
	info VARCHAR(64),
	/* URI of a web page to find out more about this playlist. Likely to be homepage of the author,
	 * and would be used to find out more about the author and to find more playlists by the author.
	 * xspf:playlist elements MAY contain exactly one. */
	location VARCHAR(64),
	/* Source URI for this playlist. xspf:playlist elements MAY contain exactly one. */
	identifier VARCHAR(64),
	/* Canonical ID for this playlist. Likely to be a hash or other location-independent name.
	 * MUST be a legal URI. xspf:playlist elements MAY contain exactly one. */
	image VARCHAR(64),
	/* URI of an image to display in the absence of a //playlist/trackList/image element.
	 * xspf:playlist elements MAY contain exactly one. */
	date_creation DATE,
	/* Creation date (not last-modified date) of the playlist, formatted as a XML schema dateTime. xspf:playlist elements MAY contain exactly one.
	 * 
	 * A sample date is "2005-01-08T17:10:47-05:00". PHP to produce such a string from a unix timestamp is:
	 * 
	 * $main_date = date("Y-m-d\TH:i:s", $timestamp);
	 * $tz = date("O", $timestamp);  
	 * $tz = substr_replace ($tz, ':', 3, 0);
	 * 
	 * In the absence of a timezone, the element MAY be assumed to use Coordinated Universal Time
	 * (UTC, sometimes called "Greenwich Mean Time").
	 * 
	 * Note: in version 0 of XSPF, this was specifed as an ISO 8601 date. xsd:dateTime is the same thing
	 * (with better documentation) for almost every date in history, and there are no playlist creation dates
	 * that might be different. */
	licence VARCHAR(64),
	/* URI of a resource that describes the license under which this playlist was released.
	 * xspf:playlist elements may contain zero or one license element. */
	attribution VARCHAR(64),
	/* An ordered list of URIs. The purpose is to satisfy licenses allowing modification but requiring 
	 * attribution. If you modify such a playlist, move its //playlist/location or //playlist/identifier 
	 * element to the top of the items in the //playlist/attribution element. xspf:playlist elements MAY 
	 * contain exactly one xspf:attribution element.
	 * 
	 * Such a list can grow without limit, so as a practical matter we suggest deleting ancestors more 
	 * than ten generations back.
	 * 
	 * <attribution>
	 * 	<location>http://bar.com/modified_version_of_original_playlist.xspf</location>
	 * 	<identifier>somescheme:original_playlist.xspf</identifier>
	 * </attribution> */
	link VARCHAR(64),
	/* The link element allows XSPF to be extended without the use of XML namespaces. xspf:playlist elements 
	 * MAY contain zero or more link elements.
	 * 
	 * <link rel="http://foaf.example.org/namespace/version1">http://socialnetwork.example.org/foaf/mary.rdfs</link> */
	meta VARCHAR(64),
	/* The meta element allows metadata fields to be added to XSPF. xspf:playlist elements MAY contain zero or
	 * more meta elements.
	 * 
	 * <meta rel="http://example.org/key">value</meta> */
	extension VARCHAR(64),
	/* The extension element allows non-XSPF XML to be included in XSPF documents. The purpose is to allow nested XML, which the meta and link elements do not. xspf:playlist elements MAY contain zero or more extension elements.
	 * 
	 * <playlist version="1" xmlns="http://xspf.org/ns/0/" xmlns:cl="http://example.com">
	 * 	<extension application="http://example.com">
	 * 		<cl:clip start="25000" end="34500"/>
	 * 	</extension>
	 * <trackList />
	 * </playlist> */
	tracklist VARCHAR(64),
	/* Ordered list of xspf:track elements to be rendered. The sequence is a hint, not a requirement; renderers are advised to play tracks from top to bottom unless there is an indication otherwise.
	 * 
	 * If an xspf:track element cannot be rendered, a user-agent MUST skip to the next xspf:track element and MUST NOT interrupt the sequence.
	 * 
	 * xspf:playlist elements MUST contain one and only one trackList element. The trackList element my be empty. */
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
	username VARCHAR(64),
	password VARCHAR(64),
	image VARCHAR(64),
);
