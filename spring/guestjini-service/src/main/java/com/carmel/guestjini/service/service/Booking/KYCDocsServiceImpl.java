package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.KYCDocs;
import com.carmel.guestjini.service.repository.Booking.KYCDocsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KYCDocsServiceImpl implements KYCDocsService{

    @Autowired
    KYCDocsRepository kycDocsRepository;

    @Override
    public KYCDocs save(KYCDocs kycDocs) {
        return kycDocsRepository.save(kycDocs);
    }

    @Override
    public Optional<KYCDocs> findById(String id) {
        return kycDocsRepository.findById(id);
    }

    @Override
    public List<KYCDocs> findAllByIsDeletedAndClientIdAndBooking(int isDeleted, String clientId, Booking booking) {
        return kycDocsRepository.findAllByIsDeletedAndClientIdAndBooking(isDeleted, clientId, booking);
    }

    @Override
    public Page<KYCDocs> findAllByClientIdAndIsDeletedAndBooking(String clientId, int isDeleted, Booking booking, Pageable pageable) {
        return kycDocsRepository.findAllByClientIdAndIsDeletedAndBooking(clientId, isDeleted, booking, pageable);
    }

    @Override
    public Page<KYCDocs> findAll(Specification<KYCDocs> textInAllColumns, Pageable pageable) {
        return kycDocsRepository.findAll(textInAllColumns, pageable);
    }
}
