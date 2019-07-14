package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Bean to represent a blog post
 * @author aahmed
 *
 */
@Table("blogpost_table")
public class BlogPost implements Serializable {
	
	@PrimaryKey
	private Integer id;
	private String title;
	private String description;
	private String author;
	private String tags;
	private LocalDate create_at;
	private LocalDate updated_at;
	
	
	public BlogPost() {
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	

	public LocalDate getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDate create_at) {
		this.create_at = create_at;
	}

	public LocalDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title).append(":").append(description.substring(0,10));
		
		return sb.toString();
	}


}
