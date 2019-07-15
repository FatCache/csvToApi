package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.BlogPost;

/**
 * Repository layer which has the interface to interact with the Cassandra database
 * @author aahmed
 *
 */
public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
	
	Slice<BlogPost> findAll(Pageable pageable);

}
