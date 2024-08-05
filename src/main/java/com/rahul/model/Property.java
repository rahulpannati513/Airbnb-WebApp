package com.rahul.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;

    @NotNull(message = "Price per night is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per night must be greater than 0")
    private BigDecimal pricePerNight;

    @Min(value = 1, message = "Number of bedrooms must be at least 1")
    private int numberOfBedrooms;

    @Min(value = 1, message = "Number of bathrooms must be at least 1")
    private int numberOfBathrooms;

    private boolean isAvailable;
    private boolean drinkAllowed;
    private boolean petAllowed;

    @Min(value = 1, message = "Max checkout time in nights must be at least 1")
    private int maxCheckoutTimeInNights;

    @DecimalMin(value = "0.0", inclusive = true, message = "Extra charges must be at least 0")
    private BigDecimal extraCharges;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private User owner;
}