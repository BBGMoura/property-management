package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserConverter {
    public UserEntity convertDTOToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        return userDTO;
    }

    public List<UserDTO> convertEntityListToDTOList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::convertEntityToDTO).toList(); //toList is unmodifiable. collectors to list is modifiable.
    }

    public List<UserDTO> convertDTOListToEntityList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::convertEntityToDTO).toList();
    }
}
