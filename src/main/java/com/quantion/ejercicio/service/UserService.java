package com.quantion.ejercicio.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantion.ejercicio.entity.UserEntity;
import com.quantion.ejercicio.model.User;
import com.quantion.ejercicio.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {

		List<UserEntity> userEntityList = userRepository.findAll();

		List<User> response = new ArrayList<>();

		for (UserEntity userEntity : userEntityList) {
			User user = new User();
			BeanUtils.copyProperties(userEntity, user);
			response.add(user);
		}

		return response;
	}

	public User save(User user) {

		UserEntity entity = new UserEntity();

		BeanUtils.copyProperties(user, entity, "password");

		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = f.generateSecret(spec).getEncoded();
			Base64.Encoder enc = Base64.getEncoder();
			entity.setPassword(enc.encodeToString(hash));
		} catch (Exception e) {
			return null;
		}

		userRepository.save(entity);

		BeanUtils.copyProperties(entity, user);

		return user;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User updateUser(Long id, User user) {

		Optional<UserEntity> userEntityOpt = userRepository.findById(id);

		if (userEntityOpt.isPresent()) {

			UserEntity userEntity = userEntityOpt.get();
			BeanUtils.copyProperties(user, userEntity, "id");
			userRepository.save(userEntity);
			BeanUtils.copyProperties(userEntity, user);

			return user;
		}

		return null;
	}

}
