package com.qlBanSach.BookManager.BeanConfig;

import com.qlBanSach.BookManager.Model.DTO.UserDTO;
import com.qlBanSach.BookManager.Model.Entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(UserDTO.class, UserEntity.class)
                .addMappings(mapper -> mapper.skip(UserEntity::setDateOfBirth));
        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
