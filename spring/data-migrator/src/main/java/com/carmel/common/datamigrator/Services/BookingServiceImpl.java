package com.carmel.common.datamigrator.Services;

import com.carmel.common.datamigrator.Response.GuestResponse;
import com.carmel.common.datamigrator.config.CarmelConfig;
import com.carmel.common.datamigrator.model.Booking;
import com.carmel.common.datamigrator.model.Guest;
import com.carmel.common.datamigrator.repository.BookingRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    @LoadBalanced
    RestTemplate clientAuthenticated;

    @Autowired
    CarmelConfig carmelConfig;

    @Override
    public void doCheckIn(Booking booking, Date actualCheckInDate) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> postData = new HashMap<String, String>();
            postData.put("bookingId", booking.getId());
            postData.put("actual_checkin", actualCheckInDate.toString());
            HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(postData), headers);
            ResponseEntity<GuestResponse> result = clientAuthenticated.exchange(
                    carmelConfig.getGuestJiniServiceURL() + "/booking/checkin",
                    HttpMethod.POST,
                    entity,
                    GuestResponse.class
            );
        } catch (Exception ex) {
            throw ex;
        }
    }
}
