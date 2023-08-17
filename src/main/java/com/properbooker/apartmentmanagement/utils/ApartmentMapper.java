package com.properbooker.apartmentmanagement.utils;

import com.properbooker.apartmentmanagement.model.AddApartmentDTO;
import com.properbooker.apartmentmanagement.model.Apartment;

public class ApartmentMapper {

    public static Apartment mapToApartment(AddApartmentDTO dto, String username) {
        Apartment apartment = new Apartment();
        apartment.setName(dto.getName());
        apartment.setDetails(dto.getDetails());
        apartment.setNumberOfRooms(dto.getNumberOfRooms());
        apartment.setNumberOfBeds(dto.getNumberOfBeds());
        apartment.setUsername(username);
        return apartment;
    }

    public static AddApartmentDTO mapToAddApartmentDTO(Apartment apartment) {
        AddApartmentDTO dto = new AddApartmentDTO();
        dto.setName(apartment.getName());
        dto.setDetails(apartment.getDetails());
        dto.setNumberOfRooms(apartment.getNumberOfRooms());
        dto.setNumberOfBeds(apartment.getNumberOfBeds());
        return dto;
    }
}
