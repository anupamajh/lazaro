package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PackageService {
    Package save(Package aPackage);

    List<Package> findAllByClientIdAndPackageTitle(String clientId, String packageTitle);

    List<Package> findAllByClientIdAndPackageTitleAndIdIsNot(String clientId, String packageTitle, String id);

    Optional<Package> findById(String id);

    List<Package> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Package> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Package> findAll(Specification<Package> textInAllColumns, Pageable pageable);
}
