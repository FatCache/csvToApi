package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * JavaBean to represent each entry from CSV as BlogPost object.
 * Contains validation checks which invokes BlogPostExceptionHandler if not a valid bean
 * @author aahmed
 *
 */
@Validated
@Table("blogpost_table")
public class BlogPost implements Serializable {
	
	@PrimaryKey
	@NotNull
	private Integer id;
	
	@NotEmpty(message = "Please provide a title")
	@Size(min=5,max = 140,message="Title should between 0 & 140 characters long")
	private String title;
	
	@NotEmpty(message = "Please provide a description")
	private String description;
	
	@NotEmpty(message = "Please provide a author")
	private String author;
	
	@NotEmpty(message = "Please provide tags")
	@Size(min=3,max=20,message="Tag should be between 3 to 20 characters long")
	private String tags;
	
	@NotNull(message="Invalid: Either wrong format MM-dd-yyyy or null")
	@PastOrPresent
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate create_at;
	
	@NotNull(message="Invalid: Either wrong format MM-dd-yyyy or null")
	@JsonFormat(pattern = "MM-dd-yyyy")
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
