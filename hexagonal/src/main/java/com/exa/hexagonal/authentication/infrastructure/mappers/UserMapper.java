package com.exa.hexagonal.authentication.infrastructure.mappers;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.infrastructure.model.dto.request.UserRequest;
import com.exa.hexagonal.authentication.infrastructure.model.dto.response.UserResponse;
import com.exa.hexagonal.authentication.infrastructure.model.entities.UserEntity;

public class UserMapper {
    public UserResponse toUserResponse(User user){
        return new UserResponse(user.getIdentification(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone(), user.getPassword());
    }

    public UserRequest toUserRequest(User user){
        return new UserRequest(user.getIdentification(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone(), user.getPassword());
    }

    public UserEntity toUserEntity(User user){
        return new UserEntity(user.getId(),user.getIdentification(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone(), user.getPassword(), user.getToken());
    }

    public User fromUserEntityToUser(UserEntity userEntity){
        return new User(userEntity.getId(),userEntity.getIdentification(), userEntity.getFirstname(), userEntity.getLastname(), userEntity.getEmail(), userEntity.getAddress(), userEntity.getPhone(), userEntity.getPassword(), userEntity.getToken());
    }

    public User toUser(UserRequest userRequest){
        return new User(null,userRequest.getIdentification(), userRequest.getFirstname(), userRequest.getLastname(), userRequest.getEmail(), userRequest.getAddress(), userRequest.getPhone(), userRequest.getPassword(), "");
    }
}
