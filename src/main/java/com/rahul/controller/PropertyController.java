package com.rahul.controller;

import com.rahul.Exception.PropertyNotFoundException;
import com.rahul.model.Property;
import com.rahul.model.User;
import com.rahul.repo.UserRepository;
import com.rahul.response.ApiResponse;
import com.rahul.security.service.JwtService;
import com.rahul.security.service.UserService;
import com.rahul.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtUtil;
    @Autowired
    private UserRepository  userRepository;

    @PostMapping
    public ResponseEntity<?> createProperty(@RequestHeader("Authorization") String token, @RequestBody Property property) throws PropertyNotFoundException {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        String username = jwtUtil.extractUserName(jwt);
        User user = userRepository.findByUsername(username);
        property.setOwner(user);
         propertyService.createProperty(property);
        return new ResponseEntity<>(new ApiResponse("successfully created property",true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() throws PropertyNotFoundException {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) throws PropertyNotFoundException {
        Property property = propertyService.getPropertyById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) throws PropertyNotFoundException {
        propertyService.updateProperty(id, propertyDetails);
        return new ResponseEntity<>(new ApiResponse("Property updatedSucessfullly : "+id,true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) throws PropertyNotFoundException {
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(new ApiResponse("Deleted Sucessfully :"+id,true),HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/properties")
    public ResponseEntity<List<Property>> getPropertiesByUserId(@PathVariable Long userId) throws PropertyNotFoundException {
        List<Property> properties = propertyService.getPropertiesByUserId(userId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}