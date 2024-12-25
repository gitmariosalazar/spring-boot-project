package com.exa.hexagonal.returns.infrastructure.settings;

import com.exa.hexagonal.returns.application.services.ReturnsService;
import com.exa.hexagonal.returns.application.usecases.*;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;
import com.exa.hexagonal.returns.infrastructure.mappers.ReturnsMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReturnsApplicationConfiguration {
    @Bean
    public ReturnsService returnsService(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        return new ReturnsService(
                new CreateReturnUseCaseImplementation(returnsRepositoryOutputPort),
                new AnnulReturnUseCaseImplementation(returnsRepositoryOutputPort),
                new FindReturnByIdUseCaseImplementation(returnsRepositoryOutputPort),
                new FindAllReturnsUseCaseImplementation(returnsRepositoryOutputPort),
                new DeleteReturnUseCaseImplementation(returnsRepositoryOutputPort)
        );
    }

    @Bean
    ReturnsMapper returnsMapper(){
        return new ReturnsMapper();
    }
}
