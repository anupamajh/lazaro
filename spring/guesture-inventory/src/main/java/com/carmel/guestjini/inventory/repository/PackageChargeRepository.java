package com.carmel.guestjini.inventory.repository;

import com.carmel.guestjini.inventory.model.PackageCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageChargeRepository extends JpaRepository<PackageCharge, String> {
    List<PackageCharge> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<PackageCharge> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<PackageCharge> findAll(Specification<PackageCharge> textInAllColumns, Pageable pageable);

    List<PackageCharge> findAllByClientIdAndTitle(String clientId, String title);

    List<PackageCharge> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
