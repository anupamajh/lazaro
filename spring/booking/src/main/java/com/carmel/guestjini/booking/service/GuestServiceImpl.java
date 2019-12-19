package com.carmel.guestjini.booking.service;

import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService{
    @Autowired
    GuestRepository guestRepository;


    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }
}
