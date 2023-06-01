package com.quantion.ejercicio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.quantion.ejercicio.entity.UserEntity;
import com.quantion.ejercicio.model.User;

@Mapper
public interface UserMapper {
	
	public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target = "password", ignore = true)
	UserEntity userToUserEntity (User user);
	
	User userEntityToUser (UserEntity userEntity);
	
	List<User> userEntityListToUserList (List<UserEntity> userEntityList);
	
	

}
