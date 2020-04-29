package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, String> {
    Optional<Package> findByPackageTitleAndIsDeleted(String packageName, int isDeleted);
}
