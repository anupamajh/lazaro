package com.carmel.guesture.inventory.services;

import com.carmel.guesture.inventory.model.Package;
import com.carmel.guesture.inventory.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService{

    @Autowired
    PackageRepository packageRepository;

    @Override
    public Package save(Package aPackage) {
        return packageRepository.save(aPackage);
    }

    @Override
    public List<Package> findAllByClientIdAndPackageTitle(String clientId, String packageTitle) {
        return packageRepository.findAllByClientIdAndPackageTitleAndIsDeleted(clientId, packageTitle, 0);
    }

    @Override
    public List<Package> findAllByClientIdAndPackageTitleAndIdIsNot(String clientId, String packageTitle, String id) {
        return packageRepository.findAllByClientIdAndPackageTitleAndIdIsNotAndIsDeleted(clientId, packageTitle, id, 0);
    }

    @Override
    public Optional<Package> findById(String id) {
        return packageRepository.findById(id);
    }

    @Override
    public List<Package> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return packageRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<Package> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return packageRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted,pageable);
    }

    @Override
    public Page<Package> findAll(Specification<Package> textInAllColumns, Pageable pageable) {
        return packageRepository.findAll(textInAllColumns,pageable);
    }
}
