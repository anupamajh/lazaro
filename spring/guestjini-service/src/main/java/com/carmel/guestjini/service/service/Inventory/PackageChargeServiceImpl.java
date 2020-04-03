package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.PackageCharge;
import com.carmel.guestjini.service.repository.Inventory.PackageChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageChargeServiceImpl implements  PackageChargeService{
    @Autowired
    PackageChargeRepository packageChargeRepository;

    @Override
    public PackageCharge save(PackageCharge packageCharge) {
        return packageChargeRepository.save(packageCharge);
    }

    @Override
    public Optional<PackageCharge> findById(String id) {
        return packageChargeRepository.findById(id);
    }

    @Override
    public List<PackageCharge> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return packageChargeRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<PackageCharge> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return packageChargeRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public List<PackageCharge> findAllByClientIdAndTitle(String clientId, String title) {
        return packageChargeRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public List<PackageCharge> findAllByClientIdAndPTitleAndIdIsNot(String clientId, String title, String id) {
        return packageChargeRepository.findAllByClientIdAndTitleAndIdIsNot(clientId, title, id);
    }

    @Override
    public Page<PackageCharge> findAll(Specification<PackageCharge> textInAllColumns, Pageable pageable) {
        return packageChargeRepository.findAll(textInAllColumns, pageable);
    }
}
