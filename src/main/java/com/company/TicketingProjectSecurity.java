package com.company;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketingProjectSecurity {
    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectSecurity.class, args);
    }

    @Bean
    public ModelMapper mapper(){ // To be able to use ModelMapper, we need Spring to create a bean from it.
        return new ModelMapper();
    }



}
