package com.quantion.ejercicio.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.quantion.ejercicio.entity.UserEntity;
import com.quantion.ejercicio.model.User;
import com.quantion.ejercicio.repository.UserRepository;

import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class UserServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserService();

	private AutoCloseable closeable;

	@BeforeEach
	void initService() {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void closeService() throws Exception {
		closeable.close();
	}

	@Test
	public void createUserTest() {

		User user = new User();
		user.setName("Name");
		user.setSurname("Test");
		user.setEmail("a@a.com");
		user.setPassword("password");
		user.setAge(1);
		user.setActive(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setName("Name");
		userEntity.setSurname("Test");
		userEntity.setEmail("a@a.com");
		userEntity.setPassword("password");
		userEntity.setAge(1);
		userEntity.setActive(true);

		doReturn(userEntity).when(this.userRepository).save(Mockito.any(UserEntity.class));

		User result = this.userService.save(user);

		assertNotNull(result);

	}

	@Test
	public void updateUserTest() {

		User user = new User();
		user.setId(1L);
		user.setName("Name");
		user.setSurname("Test");
		user.setEmail("a@a.com");
		user.setPassword("password");
		user.setAge(1);
		user.setActive(true);
		
		UserEntity userEntity = new UserEntity();
		user.setId(1L);
		userEntity.setName("Name");
		userEntity.setSurname("Test");
		userEntity.setEmail("a@a.com");
		userEntity.setPassword("password");
		userEntity.setAge(1);
		userEntity.setActive(true);

		Optional<UserEntity> userEntityOpt = Optional.ofNullable(userEntity);

		doReturn(userEntityOpt).when(this.userRepository).findById(Mockito.any(Long.class));

		doReturn(userEntity).when(this.userRepository).save(Mockito.any(UserEntity.class));

		User result = this.userService.updateUser(1L, user);

		assertNotNull(result);

	}

	@Test
	public void updateUserNotExistingTest() {

		User user = new User();
		user.setId(1L);
		user.setName("Name");
		user.setSurname("Test");
		user.setEmail("a@a.com");
		user.setAge(1);
		user.setActive(true);

		Optional<User> userEntityOpt = Optional.ofNullable(null);

		doReturn(userEntityOpt).when(this.userRepository).findById(Mockito.any(Long.class));

		User result = this.userService.updateUser(1L, user);

		assertNull(result);

	}

}
