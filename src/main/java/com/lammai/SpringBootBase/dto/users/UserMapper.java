package com.lammai.SpringBootBase.dto.users;

import com.lammai.SpringBootBase.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "username", target = "username")
//    @Mapping(source = "password", target = "password")
    User createUser(CreateUserDto createUserDto);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    User updateUser(UpdateUserDto updateUserDto, @MappingTarget User user);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "username", target = "username")
    @Mapping(ignore = true, target = "password")
    UserResponseDto userResponse(User user);
}
