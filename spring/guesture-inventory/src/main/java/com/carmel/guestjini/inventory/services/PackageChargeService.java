package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.PackageCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PackageChargeService {
    PackageCharge save(PackageCharge packageCharge);

    Optional<PackageCharge> findById(String id);

    List<PackageCharge> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<PackageCharge> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<PackageCharge> findAll(Specification<PackageCharge> textInAllColumns, Pageable pageable);

    List<PackageCharge> findAllByClientIdAndTitle(String clientId, String title);

    List<PackageCharge> findAllByClientIdAndPTitleAndIdIsNot(String clientId, String title, String id);
}
