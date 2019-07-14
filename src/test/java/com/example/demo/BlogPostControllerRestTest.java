package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.CSVToDatabaseImp;
import com.example.demo.model.BlogPost;
import com.example.demo.repository.BlogPostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BlogPostControllerRestTest {

	 private static final ObjectMapper om = new ObjectMapper();

	    //@WithMockUser is not working with TestRestTemplate
	    @Autowired
	    private TestRestTemplate restTemplate;

	    @MockBean
	    private BlogPostRepository blogpostrepo;
	   

	    @Before
	    public void init() {
	        BlogPost bp = new BlogPost();
			LocalDate ldc = LocalDate.of(1991,Month.APRIL,28);
			LocalDate ldu = LocalDate.of(2019,Month.JULY,14);
			bp.setId(1000);
			bp.setAuthor("abdusamed");
	        bp.setCreate_at(ldc);
	        bp.setDescription("Testing");
	        bp.setTags("tag");
	        bp.setTitle("title");
	        bp.setUpdated_at(ldu);
	        when(blogpostrepo.findById(1000)).thenReturn(Optional.of(bp));
	    }

	    @Test
	    public void find_login_ok() throws Exception {

	        String expected = "{\"id\":1000,\"title\":\"title\",\"description\":\"Testing\",\"author\":\"abdusamed\",\"tags\":\"tag\",\"create_at\":\"1991-04-28\",\"updated_at\":\"2019-07-14\"}";

	        ResponseEntity<String> response = restTemplate
	                .withBasicAuth("user", "password")
	                .getForEntity("/blogpost/1000", String.class);

	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        assertEquals(HttpStatus.OK, response.getStatusCode());

	        JSONAssert.assertEquals(expected, response.getBody(), false);

	    }

	    @Test
	    public void find_nologin_401() throws Exception {

	        String expected = "status\":401,\"error\":\"Unauthorized\",\"message\":\"Unauthorized\",\"path\":\"/init/csvtodatabase\"}";
	        

	        ResponseEntity<String> response = restTemplate
	                .getForEntity("/init/csvtodatabase", String.class);

	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	        
	        assertEquals(expected, response.getBody().substring(45));

	    }



}
