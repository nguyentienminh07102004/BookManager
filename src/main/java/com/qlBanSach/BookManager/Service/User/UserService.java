package com.qlBanSach.BookManager.Service.User;

import com.qlBanSach.BookManager.Convertor.UserConvertor;
import com.qlBanSach.BookManager.Model.DTO.UserDTO;
import com.qlBanSach.BookManager.Model.Entity.UserEntity;
import com.qlBanSach.BookManager.Model.Response.UserResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserConvertor userConvertor;

    @Override
    public UserResponse save(UserDTO userDTO) {
        if(userDTO.getId() != null) {
            // update user, check xem user có tồn tại không
            userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new DataInvalidException("User not found!"));
        }
        else {
            // thêm user, check email đã tồn tại chưa
            Boolean isExists = userRepository.existsByEmail(userDTO.getEmail());
            if(isExists) {
                throw new DataInvalidException("Email already exists!");
            }
            if(userDTO.getRePassword() == null || !userDTO.getPassword().equals(userDTO.getRePassword())) {
                throw new DataInvalidException("Password do not match!");
            }
        }
        UserEntity userEntity = userConvertor.dtoToEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userConvertor.entityToResponse(savedUserEntity);
    }

    @Override
    public List<UserResponse> getAllUsers(Integer page, Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 3;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<UserEntity> list = userRepository.findAll(pageable);
        List<UserResponse> listUsers = list.getContent().stream()
                .map(userConvertor::entityToResponse)
                .toList();
        return listUsers;
    }
}
