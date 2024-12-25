package com.exa.hexagonal.returns.infrastructure.controller;

import com.exa.hexagonal.errors.exception.BadRequestException;
import com.exa.hexagonal.errors.exception.ResourceNotFoundException;
import com.exa.hexagonal.errors.payload.MessageResponse;
import com.exa.hexagonal.returns.application.services.ReturnsService;
import com.exa.hexagonal.returns.domain.dto.request.ReturnRequest;
import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.infrastructure.mappers.ReturnsMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/returns")
public class ReturnsController {
    private final ReturnsService returnsService;
    private final ReturnsMapper returnsMapper;
    private final MessageResponse messageResponse=new MessageResponse();
    public ReturnsController(ReturnsService returnsService, ReturnsMapper returnsMapper){
        this.returnsService=returnsService;
        this.returnsMapper=returnsMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> createReturn(@RequestBody ReturnRequest returnRequest){
        try {
            Returns returnsCreated = returnsService.createReturn(returnsMapper.fromReturnRequestToReturns(returnRequest));
            messageResponse.setObject(returnsCreated);
            messageResponse.setMessage("Return created successfully!");
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        }catch (DataAccessException exception){
            throw new BadRequestException("Error while creating return: "+exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> annulReturn(@PathVariable(name = "id") Long id){
        Optional<Returns> returns = returnsService.annulReturn(id, "annulled");
        if(returns.isEmpty()){
            throw new ResourceNotFoundException("return", "id", id);
        }
        messageResponse.setMessage("Return annulled successfully!");
        messageResponse.setObject(returns.get());
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findReturnById(@PathVariable(name = "id") Long id){
        Optional<Returns> returns = returnsService.findReturnById(id);
        if(returns.isEmpty()){
            throw new ResourceNotFoundException("return","id",id);
        }
        messageResponse.setObject(returns.get());
        messageResponse.setMessage("Return found successfully!");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> findAllReturns(){
        List<Returns> returnsList = returnsService.findAllReturns();
        if(returnsList==null || returnsList.isEmpty()){
            throw new ResourceNotFoundException("returns");
        }
        messageResponse.setMessage("Returns not found!");
        messageResponse.setObject(returnsList);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_USER')")
    public ResponseEntity<?> deleteReturn(@PathVariable(name = "id") Long id){
        if(returnsService.deleteReturn(id)){
            messageResponse.setMessage("Returns deleted successfully!");
            messageResponse.setObject(null);
            return new ResponseEntity<>(messageResponse,HttpStatus.NO_CONTENT);
        }
        else {
            throw new ResourceNotFoundException("return", "id", id);
        }
    }

}
