package com.carmel.guesture.inventory.services;

import com.carmel.guesture.inventory.model.InventoryType;
import com.carmel.guesture.inventory.repository.InventoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryTypeServiceImpl implements InventoryTypeService {

    @Autowired
    InventoryTypeRepository inventoryTypeRepository;

    @Override
    public List<InventoryType> findAllByClientIdAndTitle(String clientId, String title) {
        return inventoryTypeRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public List<InventoryType> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return inventoryTypeRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title,id, 0);
    }

    @Override
    public InventoryType save(InventoryType inventoryType) {
        return inventoryTypeRepository.save(inventoryType);
    }

    @Override
    public Optional<InventoryType> findById(String id) {
        return inventoryTypeRepository.findById(id);
    }

    @Override
    public List<InventoryType> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return inventoryTypeRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<InventoryType> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return inventoryTypeRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<InventoryType> findAll(Specification<InventoryType> textInAllColumns, Pageable pageable) {
        return inventoryTypeRepository.findAll(textInAllColumns, pageable);
    }
}
