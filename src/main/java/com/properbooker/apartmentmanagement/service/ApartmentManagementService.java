package com.properbooker.apartmentmanagement.service;

import com.properbooker.apartmentmanagement.model.AddApartmentDTO;
import com.properbooker.apartmentmanagement.model.Apartment;
import com.properbooker.apartmentmanagement.repository.ApartmentRepository;
import com.properbooker.apartmentmanagement.utils.ApartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApartmentManagementService {

    private final ApartmentRepository apartmentRepository;

    public List<Apartment> getAllApartments() {
      return apartmentRepository.findAll();
    }

    public Apartment addApartment(AddApartmentDTO addApartmentDTO, String username) throws RuntimeException{
        return apartmentRepository.save(ApartmentMapper.mapToApartment(addApartmentDTO, username));
    }

    public void deleteApartment(Integer apartmentId){
         apartmentRepository.deleteById(apartmentId);
    }

    public Optional<Apartment> getApartmentById(Integer apartmentId){
        return apartmentRepository.findById(apartmentId);
    }

}
