package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.BlogPost;
import com.example.demo.repository.BlogPostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BlogPostRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

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

    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {

        mockMvc.perform(get("/blogpost/1000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1000)))
                .andExpect(jsonPath("title", is("title")))
                .andExpect(jsonPath("description", is("Testing")))
                .andExpect(jsonPath("author", is("abdusamed")));
    }

    @Test
    public void find_nologin_401() throws Exception {
        mockMvc.perform(get("/init/csvtodatabase"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}
