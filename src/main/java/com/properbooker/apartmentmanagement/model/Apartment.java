package com.properbooker.apartmentmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

import java.util.List;


@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum apartment name length: 4 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String details;

    @Column(unique = false, nullable = false)
    private Integer numberOfRooms;

    @Column(unique = false, nullable = false)
    private Integer numberOfBeds;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

}


