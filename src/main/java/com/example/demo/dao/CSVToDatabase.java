package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.BlogPost;

/**
 * Defines the DAO API for implementation for CSV to Database
 * @author aahmed
 *
 */
public interface CSVToDatabase {
	public enum HEADERS {
		Id,title, description, author, tags, create_at, updated_at
	}

	public void initDatabase();
	public List<BlogPost> csvBlogToDatabase();
	
}
