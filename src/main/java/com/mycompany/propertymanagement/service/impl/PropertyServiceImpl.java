package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private PropertyConverter propertyConverter;
    private UserRepository userRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
        this.userRepository = userRepository;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());
        if(optionalUserEntity.isEmpty()) {
            handleInvalidPropertyUser();
        }

        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
        propertyEntity.setUserEntity(optionalUserEntity.get());
        PropertyEntity savedEntity = propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(savedEntity);

    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProperties = (List<PropertyEntity>) propertyRepository.findAll();
        return propertyConverter.convertEntityListToDTOList(listOfProperties);
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        if(optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setAddress(propertyDTO.getAddress());
            propertyEntity.setPrice(propertyDTO.getPrice());
            propertyEntity.setDescription(propertyDTO.getDescription());

            PropertyEntity savedPropertyEntity = propertyRepository.save(propertyEntity);
            return propertyConverter.convertEntityToDTO(savedPropertyEntity);
        }
        return null;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        if (optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setDescription(propertyDTO.getDescription());
            PropertyEntity savedPropertyEntity = propertyRepository.save(propertyEntity);
            return propertyConverter.convertEntityToDTO(savedPropertyEntity);
        }
        return null;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    private void handleInvalidPropertyUser() {
        List<ErrorModel> errorModelList = createError("INVALID_USER_ID","User does not exist.");
        throw new BusinessException(errorModelList);
    }

    private List<ErrorModel> createError(String code, String message){
        List<ErrorModel> errorModelList = new ArrayList<>();
        errorModelList.add( new ErrorModel(code, message));
        return errorModelList;
    }
}
