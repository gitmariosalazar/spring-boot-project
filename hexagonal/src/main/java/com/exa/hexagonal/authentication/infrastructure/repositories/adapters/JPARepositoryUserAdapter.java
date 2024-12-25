package com.exa.hexagonal.authentication.infrastructure.repositories.adapters;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;
import com.exa.hexagonal.authentication.infrastructure.mappers.UserMapper;
import com.exa.hexagonal.authentication.infrastructure.model.entities.UserEntity;
import com.exa.hexagonal.authentication.infrastructure.repositories.repository.JPARepositoryUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPARepositoryUserAdapter implements UserRepositoryOutputPort {
    private final PasswordEncoder passwordEncoder;
    private final JPARepositoryUser jpaRepository;
    private final UserMapper userMapper;

    public JPARepositoryUserAdapter(JPARepositoryUser jpaRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.jpaRepository=jpaRepository;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public User save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        UserEntity userEntity=userMapper.toUserEntity(user);
        UserEntity userEntitySaved = jpaRepository.save(userEntity);
        return userMapper.fromUserEntityToUser(userEntitySaved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserEntity::toDomainModel);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(userMapper::fromUserEntityToUser).collect(Collectors.toList());
    }

    @Override
    public Optional<User> update(Long id, User user) {
        Optional<UserEntity> existingUserEntity = jpaRepository.findById(id);
        if (existingUserEntity.isPresent()) {
            UserEntity userEntityToUpdate = existingUserEntity.get();
            String password = passwordEncoder.encode(user.getPassword());
            userEntityToUpdate.setFirstname(user.getFirstname());
            userEntityToUpdate.setLastname(user.getLastname());
            userEntityToUpdate.setEmail(user.getEmail());
            userEntityToUpdate.setAddress(user.getAddress());
            userEntityToUpdate.setPhone(user.getPhone());
            userEntityToUpdate.setPassword(password);
            userEntityToUpdate.setToken(user.getToken());
            userEntityToUpdate.setIdentification(user.getIdentification());
            UserEntity updatedEntity = jpaRepository.save(userEntityToUpdate);
            return Optional.of(userMapper.fromUserEntityToUser(updatedEntity));
        }
        return Optional.empty();
    }


    @Override
    public boolean deleteById(Long id) {
        if(jpaRepository.existsById(id)){
            jpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserEntity::toDomainModel);
    }
}
