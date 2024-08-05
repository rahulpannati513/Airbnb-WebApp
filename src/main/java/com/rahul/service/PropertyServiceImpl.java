package com.rahul.service;

import com.rahul.Exception.PropertyNotFoundException;
import com.rahul.model.Property;
import com.rahul.repo.PropertyRepository;
import com.rahul.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }
    @Override
    public List<Property> getAllProperties()  throws PropertyNotFoundException{
        return propertyRepository.findAll();
    }
    @Override
    public Property getPropertyById(Long id) throws PropertyNotFoundException {
        return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property not found"));
    }
    @Override
    public Property updateProperty(Long id, Property propertyDetails) throws PropertyNotFoundException {
        propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property not found"));
        Property property = getPropertyById(id);
        property.setName(propertyDetails.getName());
        property.setDescription(propertyDetails.getDescription());
        property.setAddress(propertyDetails.getAddress());
        property.setPricePerNight(propertyDetails.getPricePerNight());
        property.setNumberOfBedrooms(propertyDetails.getNumberOfBedrooms());
        property.setNumberOfBathrooms(propertyDetails.getNumberOfBathrooms());
        property.setAvailable(propertyDetails.isAvailable());
        property.setDrinkAllowed(propertyDetails.isDrinkAllowed());
        property.setPetAllowed(propertyDetails.isPetAllowed());
        property.setMaxCheckoutTimeInNights(propertyDetails.getMaxCheckoutTimeInNights());
        property.setExtraCharges(propertyDetails.getExtraCharges());
        return propertyRepository.save(property);
    }
    @Override
    public ResponseEntity<?> deleteProperty(Long id) throws PropertyNotFoundException {
        propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property not found"));
      propertyRepository.deleteById(id);
      return new ResponseEntity<>(new ApiResponse("successfully deleted property",true), HttpStatus.OK);
    }
    @Override
    public List<Property> getPropertiesByUserId(Long userId)  throws PropertyNotFoundException {
        return propertyRepository.findByOwnerId(userId);
    }
}