package com.exa.hexagonal.authentication.infrastructure.controllers;

import com.exa.hexagonal.authentication.application.services.UserService;
import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.infrastructure.mappers.UserMapper;
import com.exa.hexagonal.authentication.infrastructure.model.dto.request.UserRequest;
import com.exa.hexagonal.errors.exception.BadRequestException;
import com.exa.hexagonal.errors.exception.ResourceNotFoundException;
import com.exa.hexagonal.errors.payload.MessageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessageResponse messageResponse=new MessageResponse();

    public UserController(UserService userService, UserMapper userMapper){
        this.userService=userService;
        this.userMapper=userMapper;
    }

    @PostMapping
    //@PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        try {
            User createdUser = userService.createUser(userMapper.toUser(userRequest));
            messageResponse.setMessage("User created successfully!");
            messageResponse.setObject(createdUser);
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        } catch (DataAccessException exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> getUsers(){
        List<User> users=userService.getUsers();
        if(users==null || users.isEmpty()){
            throw new ResourceNotFoundException("users");
        }
        messageResponse.setMessage("Users found successfully!");
        messageResponse.setObject(users);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long id){
        User user = userService.getUserById(id).orElse(null);
        if(user==null){
            throw new ResourceNotFoundException("users", "id", id);
        }
        messageResponse.setMessage("User found successfully!");
        messageResponse.setObject(user);
        return new ResponseEntity<>(
                messageResponse,HttpStatus.OK
        );
    }

    @GetMapping("/by-email/{email}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email){
        User user = userService.findByEmail(email).orElse(null);
        if(user==null){
            throw new ResourceNotFoundException("users", "email", email);
        }
        messageResponse.setMessage("User found successfully!");
        messageResponse.setObject(user);
        return new ResponseEntity<>(
                messageResponse,HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserRequest userRequest){
       try {
           User user = userService.updateUser(id, userMapper.toUser(userRequest)).orElse(null);
           if(user==null){
               throw new ResourceNotFoundException("users", "id", id);
           }
           messageResponse.setMessage("User updated successfully!");
           messageResponse.setObject(user);
           return new ResponseEntity<>(
                   messageResponse,HttpStatus.OK
           );
       } catch (BadRequestException  exception){
           throw new BadRequestException(exception.getMessage());
       }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id){
        if(userService.deleteUser(id)){
            messageResponse.setObject(null);
            messageResponse.setMessage("User with id: "+id+" deleted successfully!");
            return new ResponseEntity<>(messageResponse,HttpStatus.NO_CONTENT);
        }
        else {
            throw new ResourceNotFoundException("users", "id", id);
        }
    }

}
