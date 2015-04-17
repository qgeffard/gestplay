package org.kgj.pds.playlist.persistance.entity;

import java.math.BigInteger;
import java.net.URI;


public class TrackEntity {
	private int id;
	private URI location;
	private URI identifier;
	private String title;
	private String creator;
	private String annotation;
	private URI info;
	private URI image;
	private String album; 
	private BigInteger trackNum; // > 0
	private BigInteger duration; // > 0
	private String link; //linktype
	private String meta; //metatype
	private String extension; //ExtensionType
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public URI getImage() {
		return image;
	}
	public void setImage(URI image) {
		this.image = image;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}

	public BigInteger getTrackNum() {
		return trackNum;
	}
	public void setTrackNum(BigInteger trackNum) {
		this.trackNum = trackNum;
	}
	public BigInteger getDuration() {
		return duration;
	}
	public void setDuration(BigInteger duration) {
		this.duration = duration;
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
}
