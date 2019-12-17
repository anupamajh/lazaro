package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYC;
import com.carmel.guestjini.booking.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KYCServiceImpl implements KYCService{

    @Autowired
    KYCRepository kycRepository;

    @Override
    public KYC save(KYC kyc) {
        return kycRepository.save(kyc);
    }

    @Override
    public Optional<KYC> findById(String id) {
        return kycRepository.findById(id);
    }

    @Override
    public List<KYC> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking) {
        return kycRepository.findAllByIsDeletedAndClientIdAndBooking(isDeleted, clientId, booking);
    }

    @Override
    public Page<KYC> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable) {
        return kycRepository.findAllByClientIdAndIsDeletedAndBooking(clientId, isDeleted, booking, pageable);
    }

    @Override
    public Page<KYC> findAll(Specification<KYC> textInAllColumns, Pageable pageable) {
        return null;
    }
}
