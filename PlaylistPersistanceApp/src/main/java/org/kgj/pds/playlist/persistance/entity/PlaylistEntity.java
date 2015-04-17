package org.kgj.pds.playlist.persistance.entity;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class PlaylistEntity {
	private int id;
	private String title;
	private String creator;
	private String annotation;
	private URI info; //AnyURI
	private URI location; //AnyURI
	private URI identifier; //AnyURI
	private URI image; //AnyURI
	private Date date; //Datetime
	private URI licence; //AnyURI
	private String attribution; //AttributionType
	private String link; //linktype
	private String meta; //metatype
	private String extension; //ExtensionType
	private List<TrackEntity> tracklist; //TrackListType
	private String version; //versiontype
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public URI getInfo() {
		return info;
	}
	public void setInfo(URI info) {
		this.info = info;
	}
	public URI getLocation() {
		return location;
	}
	public void setLocation(URI location) {
		this.location = location;
	}
	public URI getIdentifier() {
		return identifier;
	}
	public void setIdentifier(URI identifier) {
		this.identifier = identifier;
	}
	public URI getImage() {
		return image;
	}
	public void setImage(URI image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public URI getLicence() {
		return licence;
	}
	public void setLicence(URI licence) {
		this.licence = licence;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public List<TrackEntity> getTracklist() {
		return tracklist;
	}
	public void setTracklist(List<TrackEntity> tracklist) {
		this.tracklist = tracklist;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
