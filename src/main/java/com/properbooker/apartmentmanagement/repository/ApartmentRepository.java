package com.properbooker.apartmentmanagement.repository;

import com.properbooker.apartmentmanagement.model.Apartment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    boolean existsByName(String name);

    Apartment findByName(String name);

    List<Apartment> findAllByUsername(String username);

    @Transactional
    void deleteById(Integer id);

}

