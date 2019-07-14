package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.BlogPost;


public interface CSVToDatabase {
	public enum HEADERS {
		Id,title, description, author, tags, create_at, updated_at
	}

	public void initDatabase();
	public List<BlogPost> csvBlogToDatabase();
	
}
