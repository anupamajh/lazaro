package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.common.SearchBuilder;
import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.request.SearchCriteria;
import com.carmel.guestjini.booking.request.SearchRequest;
import com.carmel.guestjini.booking.request.SearchUnit;
import com.carmel.guestjini.booking.response.GuestResponse;
import com.carmel.guestjini.booking.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@RestController
@RequestMapping("/guest")
public class GuestController {

    Logger logger = LoggerFactory.getLogger(BookingSourceController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    GuestService guestService;

    @Autowired
    EntityManager entityManager;


    @RequestMapping(value = "/checkout")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public GuestResponse checkout(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {
            String guestId = formData.get("guestId") == null ? null : String.valueOf(formData.get("guestId"));
            Date actualCheckout = formData.get("actual_checkout") != null ? new Date(formData.get("actual_checkout")) : null;
            if (guestId == null) {
                throw new Exception("Guest ID not received");
            }
            Optional<Guest> optionalGuest = guestService.findById(guestId);
            optionalGuest.orElseThrow(() -> new Exception("Guest not found"));
            Guest guest = optionalGuest.get();
            guest = guestService.doCheckout(guest, actualCheckout);
            guestResponse.setGuest(guest);
            guestResponse.setSuccess(false);
            guestResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        return guestResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public GuestResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Guest> optionalGuest = guestService.findById(formData.get("id"));
            if (optionalGuest.isPresent()) {
                Guest guest = optionalGuest.get();
                guestResponse.setSuccess(true);
                guestResponse.setError("");
                guestResponse.setGuest(guest);
            } else {
                guestResponse.setSuccess(false);
                guestResponse.setError("Error occurred while Fetching Guest!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return guestResponse;
    }


    @RequestMapping(value = "/get-guest-in-period", method = RequestMethod.POST)
    public GuestResponse getGuestsInPeriod(@RequestBody  Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {
             int month = Integer.parseInt(formData.get("month"));
             int year = Integer.parseInt(formData.get("year"));
             if(month != 0){
                 month -= 1;
             }
            HashMap<String, Date> period = this._getRentPeriod(month, year);
            Date periodFrom = period.get("start");
            Date periodUpto = period.get("end");
            SearchUnit searchUnit = new SearchUnit();
            searchUnit.setOperator("greaterthanorequal");
            searchUnit.setField("scheduledCheckout");
            searchUnit.setValue(periodFrom.toString());
            searchUnit.setValue1(periodUpto.toString());
            List<SearchUnit> searchUnits = new ArrayList<>();
            searchUnits.add(searchUnit);
            SearchCriteria searchCriteria = new SearchCriteria();
            searchCriteria.setCondition("and");
            searchCriteria.setSearchUnitCondition("and");
            searchCriteria.setSearchUnits(searchUnits);
            List<SearchCriteria> searchCriteriaList = new ArrayList<>();
            searchCriteriaList.add(searchCriteria);
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.setSearchCriteria(searchCriteriaList);
            searchRequest.setCurrentPage(0);
            searchRequest.setPageSize(100000);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guest> criteriaQuery = criteriaBuilder.createQuery(Guest.class);
            Root<Guest> root = criteriaQuery.from(Guest.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Guest.class,
                    searchRequest
            );
            TypedQuery<Guest> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Guest> guestList = typedQuery.getResultList();
            guestResponse.setSuccess(true);
            guestResponse.setGuestList(guestList);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return guestResponse;
    }


    private HashMap<String, Date> _getRentPeriod(int month, int year) {
        int periodDay = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, periodDay, 0, 0);
        Date start = calendar.getTime();
        calendar.set(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0);
        Date end = calendar.getTime();
        HashMap<String, Date> retVal = new HashMap<>();
        retVal.put("start", start);
        retVal.put("end", end);
        return retVal;
    }

}
