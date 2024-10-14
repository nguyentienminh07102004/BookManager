package com.qlBanSach.BookManager.Convertor;

import com.qlBanSach.BookManager.Model.DTO.UserDTO;
import com.qlBanSach.BookManager.Model.Entity.RoleEntity;
import com.qlBanSach.BookManager.Model.Entity.UserEntity;
import com.qlBanSach.BookManager.Model.Response.UserResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConvertor {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity dtoToEntity(UserDTO dto) {
        try {
            UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
            userEntity.setStatus("ACTIVE");
            if(dto.getDateOfBirth() != null && !dto.getDateOfBirth().isEmpty()) {
                // Chuyển ngày sinh mặc về dạng date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = new Date(format.parse(dto.getDateOfBirth()).getTime());
                if(dateOfBirth.after(new Date(System.currentTimeMillis()))) {
                    throw new DataInvalidException("Ngày tháng năm sinh không hợp lệ!");
                }
                userEntity.setDateOfBirth(dateOfBirth);
            }
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
            if(dto.getRoles() == null) {
                RoleEntity role = roleRepository.findByCode("USER");
                userEntity.setRoles(List.of(role));
            } else {
                List<RoleEntity> roles = dto.getRoles().stream()
                        .map(roleRepository::findByCode)
                        .toList();
                userEntity.setRoles(roles);
            }
            return userEntity;
        } catch(ParseException e) {
            throw new DataInvalidException("Định dạng ngày tháng sai");
        }
    }

    public UserResponse entityToResponse(UserEntity savedUserEntity) {
        UserResponse response = modelMapper.map(savedUserEntity, UserResponse.class);
        List<String> roles = savedUserEntity.getRoles().stream()
                .map(RoleEntity::getName)
                .toList();
        response.setFullName(String.join(" ", savedUserEntity.getFirstname(), savedUserEntity.getLastname()));
        response.setRoles(roles);
        return response;
    }
}
