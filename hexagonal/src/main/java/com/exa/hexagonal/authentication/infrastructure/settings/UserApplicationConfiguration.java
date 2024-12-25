package com.exa.hexagonal.authentication.infrastructure.settings;

import com.exa.hexagonal.authentication.application.services.UserService;
import com.exa.hexagonal.authentication.application.usecases.*;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;
import com.exa.hexagonal.authentication.infrastructure.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class UserApplicationConfiguration {
    @Bean
    @Primary
    public UserService userService(UserRepositoryOutputPort userRepositoryOutputPort){
        return new UserService(
                new CreateUserUseCaseImplementation(userRepositoryOutputPort),
                new GetUsersUseCaseImplementation(userRepositoryOutputPort),
                new GetUserUseCaseImplementation(userRepositoryOutputPort),
                new UpdateUserUseCaseImplementation(userRepositoryOutputPort),
                new DeleteUserUseCaseImplementation(userRepositoryOutputPort),
                new FindUserByEmailUseCaseImplementation(userRepositoryOutputPort)
        );
    }

    @Bean
    UserMapper userMapper(){
        return new UserMapper();
    }
}
