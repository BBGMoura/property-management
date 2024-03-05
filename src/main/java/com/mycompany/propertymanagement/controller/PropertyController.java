package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/save")
    public PropertyDTO saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyService.saveProperty(propertyDTO);
        return propertyDTO;
    }

}
