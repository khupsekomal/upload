package com.itcrats.imageupload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String title;
	@Column
	private String text;
	@Column
	private String description;
	@Column
	private String imageDir;
	@Column
	private int status;
	@Column
	private int position;
	@Column
	private String createdAt;
	@Column
	private String breatedBy;
	@Column
	private String lastUpdatedAt;
	@Column
	private String lasUpdatedBy;

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	public int getId()
//	{
//	   return id;
//	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getBreatedBy() {
		return breatedBy;
	}

	public void setBreatedBy(String breatedBy) {
		this.breatedBy = breatedBy;
	}

	public String getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(String lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public String getLasUpdatedBy() {
		return lasUpdatedBy;
	}

	public void setLasUpdatedBy(String lasUpdatedBy) {
		this.lasUpdatedBy = lasUpdatedBy;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// other fields and getters, setters are not shown
}
