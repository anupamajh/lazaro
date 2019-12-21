package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.Amenity;
import com.carmel.guestjini.inventory.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    AmenityRepository amenityRepository;

    @Override
    public Amenity save(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @Override
    public List<Amenity> findAllByClientIdAndTitle(String clientId, String title) {
        return amenityRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public List<Amenity> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return amenityRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title, id, 0);
    }

    @Override
    public Optional<Amenity> findById(String id) {
        return amenityRepository.findById(id);
    }

    @Override
    public List<Amenity> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return amenityRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<Amenity> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return amenityRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted,pageable);
    }

    @Override
    public Page<Amenity> findAll(Specification<Amenity> textInAllColumns, Pageable pageable) {
        return amenityRepository.findAll(textInAllColumns,pageable);
    }
}
