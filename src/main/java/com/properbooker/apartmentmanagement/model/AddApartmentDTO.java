package com.properbooker.apartmentmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

import java.util.List;

@Data // Create getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class AddApartmentDTO {

    @Size(min = 4, max = 255, message = "Minimum apartment name length: 4 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String details;

    @Column(unique = false, nullable = false)
    private Integer numberOfRooms;

    @Column(unique = false, nullable = false)
    private Integer numberOfBeds;
}


