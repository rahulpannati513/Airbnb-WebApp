package com.rahul.service;

import com.rahul.Exception.PropertyNotFoundException;
import com.rahul.model.Property;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyService {
    Property createProperty(Property property) throws PropertyNotFoundException;
    List<Property> getAllProperties() throws PropertyNotFoundException;
    Property getPropertyById(Long id) throws PropertyNotFoundException;
    Property updateProperty(Long id, Property propertyDetails) throws PropertyNotFoundException;
    ResponseEntity<?> deleteProperty(Long id) throws PropertyNotFoundException;
    List<Property> getPropertiesByUserId(Long userId) throws PropertyNotFoundException;
}