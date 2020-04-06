package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, String> {
    List<InventoryGroup> findAllByParentId(String parentId);
}
