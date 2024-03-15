package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private PropertyConverter propertyConverter;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
        PropertyEntity savedEntity = propertyRepository.save(propertyEntity);

        propertyDTO = propertyConverter.convertEntityToDTO(savedEntity);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProperties = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();

        for(PropertyEntity propertyEntity : listOfProperties) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(propertyEntity);
            propertyDTOList.add(dto);
        }
        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO propertyToUpdateDTO = null;
        if(optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setAddress(propertyDTO.getAddress());
            propertyEntity.setOwnerName(propertyDTO.getOwnerName());
            propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
            propertyEntity.setPrice(propertyDTO.getPrice());
            propertyEntity.setDescription(propertyDTO.getDescription());

            PropertyEntity savedPropertyEntity = propertyRepository.save(propertyEntity);
            propertyToUpdateDTO = propertyConverter.convertEntityToDTO(savedPropertyEntity);
            return propertyToUpdateDTO;
        }
        return propertyToUpdateDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO propertyToUpdateDTO = null;
        if(optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setDescription(propertyDTO.getDescription());
            PropertyEntity savedPropertyEntity = propertyRepository.save(propertyEntity);
            propertyToUpdateDTO = propertyConverter.convertEntityToDTO(savedPropertyEntity);
            return propertyToUpdateDTO;
        }
        return propertyToUpdateDTO;
    }

}
