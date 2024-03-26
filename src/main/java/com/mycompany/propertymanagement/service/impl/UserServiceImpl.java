package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        handleInvalidRegister(userEntity);
        UserEntity savedUser = userRepository.save(userEntity);
        return userConverter.convertEntityToDTO(savedUser);
    }

    @Override
    public UserDTO login(String email, String password){
        Optional<UserEntity> userEntity = userRepository.findByEmailAndPassword(email, password);
        if(userEntity.isEmpty()){
            handleInvalidLogin();
        }
        return userConverter.convertEntityToDTO(userEntity.get());
    }

    private void handleInvalidRegister(UserEntity user){
        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            List<ErrorModel> errorModelList = createError("EMAIL_ALREADY_EXISTS", "The email used to register is already used.");
            throw new BusinessException(errorModelList);
        }
    }

    private void handleInvalidLogin() {
        List<ErrorModel> errorModelList = createError("INVALID_LOGIN","Incorrect username email or password.");
        throw new BusinessException(errorModelList);
    }

    private List<ErrorModel> createError(String code, String message){
        List<ErrorModel> errorModelList = new ArrayList<>();
        errorModelList.add( new ErrorModel(code, message));
        return errorModelList;
    }
}
