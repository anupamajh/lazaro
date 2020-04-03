package com.carmel.guestjini.service.repository.Inventory;

import com.carmel.guestjini.service.model.Inventory.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, String> {

    List<Package> findAllByClientIdAndPackageTitleAndIsDeleted(String clientId, String packageTitle, int i);

    List<Package> findAllByClientIdAndPackageTitleAndIdIsNotAndIsDeleted(String clientId, String packageTitle, String id, int i);

    List<Package> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Package> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Package> findAll(Specification<Package> textInAllColumns, Pageable pageable);
}
