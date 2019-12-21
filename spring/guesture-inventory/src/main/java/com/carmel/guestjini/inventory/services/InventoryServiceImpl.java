package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.Inventory;
import com.carmel.guestjini.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAllByClientIdAndTitle(String clientId, String title) {
        return inventoryRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public List<Inventory> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return inventoryRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title, id, 0);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Optional<Inventory> findById(String id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return inventoryRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<Inventory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return inventoryRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<Inventory> findAll(Specification<Inventory> textInAllColumns, Pageable pageable) {
        return inventoryRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<Inventory> getAllParents(String id) {
        List<Inventory> inventories = new ArrayList<>();
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventories.add(inventory);
            if (inventory.getParent() != null) {
                inventories.addAll(getParentInventory(inventory.getParent().getId(), inventories));
            }
            inventories = removeDuplicates(inventories);
            if(inventory.getChildrens().size() >0){
                for(Inventory unit:inventory.getChildrens()){
                    inventories.add(unit);
                    inventories.addAll(getChildInventory(unit.getId(), inventories));
                }
            }
        }
        inventories = removeDuplicates(inventories);
        return inventories;
    }

    private List<Inventory> getParentInventory(String id, List<Inventory> inventories) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventories.add(inventory);
            if (inventory.getParent() != null) {
                inventories.addAll(getParentInventory(inventory.getParent().getId(), inventories));
            }
        }

        return inventories;
    }

    private List<Inventory> getChildInventory(String id, List<Inventory> inventories) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            if(optionalInventory.get().getChildrens().size() >0){
                for(Inventory unit:optionalInventory.get().getChildrens()){
                    inventories.add(unit);
                    inventories.addAll(getChildInventory(unit.getId(), inventories));
                }
            }
        }
        return inventories;
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        List<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
}
