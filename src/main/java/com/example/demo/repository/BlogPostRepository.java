package com.example.demo.repository;

import java.util.List;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
	
	Slice<BlogPost> findAll(Pageable pr);

}
