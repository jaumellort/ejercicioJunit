package com.quantion.ejercicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantion.ejercicio.model.User;
import com.quantion.ejercicio.service.UserService;

@WebMvcTest(value = UserRestController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	private UserRestController userController = new UserRestController();

	@Test
	void saveUserTest() throws Exception {

		User user = User.builder().name("Name").surname("Test").email("a@a.com").password("pass").age(1).active(false)
				.build();

		User userSaved = User.builder().name("Name").surname("Test").email("a@a.com").password("pass").age(1).active(false)
				.id(1L).build();
		
		doReturn(userSaved).when(this.userService).save(Mockito.any(User.class));

		ResultActions result = null;

		result = mockMvc
				.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)));

		result.andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(userSaved.getName())));
	}
	
	@Test
	void saveUserError500Test() throws Exception {

		User user = User.builder().name("Name").surname("Test").email("a@a.com").password("pass").age(1).active(false)
				.build();
		
		doReturn(null).when(this.userService).save(Mockito.any(User.class));

		ResultActions result = null;

		result = mockMvc
				.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)));

		result.andExpect(status().isInternalServerError());
	}

}
