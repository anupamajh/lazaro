package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.Amenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AmenityService {
    Amenity save(Amenity amenity);

    List<Amenity> findAllByClientIdAndTitle(String clientId, String title);

    List<Amenity> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    Optional<Amenity> findById(String id);

    List<Amenity> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Amenity> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Amenity> findAll(Specification<Amenity> textInAllColumns, Pageable pageable);
}
