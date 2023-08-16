package com.properbooker.apartmentmanagement.repository;

import com.properbooker.apartmentmanagement.model.Apartment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    boolean existsByApartmentName(String name);

    Apartment findByApartmentName(String name);

    @Transactional
    void deleteById(Integer id);

}

