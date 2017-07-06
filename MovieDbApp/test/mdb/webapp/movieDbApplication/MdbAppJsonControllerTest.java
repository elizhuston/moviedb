package mdb.webapp.movieDbApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MdbApp.class })
@WebAppConfiguration
public class MdbAppJsonControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@InjectMocks
	private MdbAppJsonController controller;

	// @Autowired
	// MockServletContext servletContext; // cached

	// @Autowired
	// MockHttpServletRequest request;
	//
	// @Autowired
	// MockHttpServletResponse response;
	//
	// @Autowired
	// ServletWebRequest webRequest;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getMovieTitle() throws Exception {
		this.mockMvc.perform(get("/movie/title/True"))
		.andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
//       .andExpect(jsonPath("*"), is("*") );
		
        		
	
//				// .andExpect(jsonPath("$[0].id", is(1)))
//				.andExpect(jsonPath("$..title", is("True Romance"))).andDo(print()).andReturn().getResponse()
//				.getContentAsString();
//		System.out.println("hey stop it");
	}

}
