package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {

    @PostMapping("/save")
    public PropertyDTO saveProperty(@RequestBody PropertyDTO propertyDTO){
        System.out.println(propertyDTO);
        return propertyDTO;
    }

}
