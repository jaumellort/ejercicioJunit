package com.quantion.ejercicio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quantion.ejercicio.entity.UserEntity;
import com.quantion.ejercicio.model.User;

@Mapper
public interface UserMapper {
	
	@Mapping(target = "password", ignore = true)
	UserEntity userToUserEntity (User user);
	
	User userEntityToUser (UserEntity userEntity);
	
	List<User> userEntityListToUserList (List<UserEntity> userEntityList);
	
	

}
