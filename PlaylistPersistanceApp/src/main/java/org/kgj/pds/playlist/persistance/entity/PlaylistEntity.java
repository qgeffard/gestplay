package org.kgj.pds.playlist.persistance.entity;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class PlaylistEntity {
	private int id;
	private String title;
	private String creator;
	private String annotation;
	private String info; //AnyURI
	private String location; //AnyURI
	private String identifier; //AnyURI
	private String image; //AnyURI
	private Date date; //Datetime
	private String licence; //AnyURI
	private String attribution; //ListAttributionType
	private String link; //list linktype
	private String meta; //list metatype
	private String extension; //list ExtensionType
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
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
