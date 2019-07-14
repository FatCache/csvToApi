package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CSVToDatabase;
import com.example.demo.model.BlogPost;
import com.example.demo.repository.BlogPostRepository;

@RestController
@RequestMapping("blogpost")
public class BlogPostController {
	
	@Autowired
	BlogPostRepository blogspotrepo;
	
	@GetMapping("/all")
	public List<BlogPost> getBlogPosts() {
		Iterable<BlogPost> result = blogspotrepo.findAll();
		List<BlogPost> blogpostList = new ArrayList<BlogPost>();
		result.forEach(blogpostList::add);
		return blogpostList;
	}
	
	/**
	 * Get a set of all tags in the post
	 * @return
	 */
	@GetMapping("/tags")
	public Set<String> getAllTags() {
		Iterable<BlogPost> result = blogspotrepo.findAll();
		Set<String> set = new HashSet<>();
		
		result.forEach(bp -> {
			String[] sp = bp.getTags().replaceAll("\\s+","").split(",");
			for (String s : sp) {
			set.add(s);
			}
		});
		return set;
	}
	
	/**
	 * On each return, the paginated result has @size of 2
	 * @param page
	 * @return
	 */
	@GetMapping("/all/{page}")
	public List<BlogPost> getBlogPostsPaginated(@PathVariable Integer page) {
		final int size = 2;
		int currpage = 0;
		
		Slice<BlogPost> slice = blogspotrepo.findAll(CassandraPageRequest.first(size));
		while(slice.hasNext() && currpage < page) {
			slice = blogspotrepo.findAll(slice.nextPageable());
			currpage++;
		}
		
		return slice.getContent();
	}

	@GetMapping("/{id}")
	public Optional<BlogPost> getBlogPost(@PathVariable Integer id) {
		Optional<BlogPost> bp = blogspotrepo.findById(id);
		return bp;
	}

	@PutMapping("/{id}")
	public Optional<BlogPost> updateBlogPost(@RequestBody BlogPost nbp, @PathVariable Integer id) {
		Optional<BlogPost> optionalBp = blogspotrepo.findById(id);
		if (optionalBp.isPresent()) {
			BlogPost bp = optionalBp.get();
			bp.setAuthor(nbp.getAuthor());
			bp.setCreate_at(nbp.getCreate_at());
			bp.setDescription(nbp.getDescription());
			bp.setId(nbp.getId());
			bp.setTags(nbp.getTags());
			bp.setTitle(nbp.getTitle());
			bp.setUpdated_at(nbp.getUpdated_at());
			blogspotrepo.save(bp);
		}
		return optionalBp;
	}

	@DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
	public String deleteBlogSpot(@PathVariable Integer id) {
		Boolean result = blogspotrepo.existsById(id);
		blogspotrepo.deleteById(id);
		return "{ \"success\" : " + (result ? "true" : "false") + " }";
	}

	@PostMapping("/")
	public BlogPost addBlogSpot(@RequestBody BlogPost bp) {
		Integer id = new Random().nextInt();
		bp.setId(id);
		blogspotrepo.save(bp);
		return bp;
	}

}
