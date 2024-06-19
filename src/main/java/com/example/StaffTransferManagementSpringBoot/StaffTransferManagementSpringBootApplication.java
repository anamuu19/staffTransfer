package com.example.StaffTransferManagementSpringBoot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StaffTransferManagementSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffTransferManagementSpringBootApplication.class, args);
	}
  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }

}
