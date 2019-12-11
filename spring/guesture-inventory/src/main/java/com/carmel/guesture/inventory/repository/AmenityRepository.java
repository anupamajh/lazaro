package com.carmel.guesture.inventory.repository;

import com.carmel.guesture.inventory.model.Amenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, String> {
    List<Amenity> findAllByClientIdAndTitleAndIsDeleted(String clientId, String title, int isDeleted);

    List<Amenity> findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(String clientId, String title, String id, int isDeleted);

    List<Amenity> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Amenity> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Amenity> findAll(Specification<Amenity> textInAllColumns, Pageable pageable);
}
