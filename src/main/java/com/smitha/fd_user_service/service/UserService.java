package com.smitha.fd_user_service.service;

import com.smitha.fd_user_service.dto.UserDTO;
import com.smitha.fd_user_service.entity.User;
import com.smitha.fd_user_service.mapper.UserMapper;
import com.smitha.fd_user_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDTO addUser(UserDTO userDTO) {
       User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
       return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
         Optional<User> fetchedUser =  userRepo.findById(userId);
         if(fetchedUser.isPresent())
             return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
         return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}
