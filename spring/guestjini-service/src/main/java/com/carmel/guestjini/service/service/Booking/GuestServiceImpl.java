package com.carmel.guestjini.service.service.Booking;

import com.carmel.guestjini.service.common.Booking.GuestStatus;
import com.carmel.guestjini.service.common.Booking.RentUnit;
import com.carmel.guestjini.service.common.Search.SearchBuilder;
import com.carmel.guestjini.service.common.Search.SearchCriteria;
import com.carmel.guestjini.service.common.Search.SearchRequest;
import com.carmel.guestjini.service.common.Search.SearchUnit;
import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.repository.Booking.GuestRepository;
import com.carmel.guestjini.service.response.Booking.GuestResponse;
import com.carmel.guestjini.service.service.Accounts.AccountTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestRepository guestRepository;


    @Autowired
    AccountTicketService accountTicketService;

    @Autowired
    EntityManager entityManager;

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Optional<Guest> findByBooking(Booking booking) {
        return guestRepository.findByBooking(booking);
    }

    @Override
    public void delete(Guest guestToDelete) {
        guestRepository.delete(guestToDelete);
    }

    @Override
    public Optional<Guest> findById(String guestId) {
        return guestRepository.findById(guestId);
    }

    @Override
    public Guest doCheckout(Guest guest, Date actualCheckout) throws Exception {
        this._isGuestValidForCheckout(guest);
        this._reconcileAccounts(guest);
        return this._updateGuestRecord(guest, actualCheckout);
    }


    private void _isGuestValidForCheckout(Guest guest) throws Exception {
        try {
            if (guest == null) {
                throw new Exception("Guest record not found.");
            }
            if (guest.getGuestStatus() != GuestStatus.RESIDING) {
                throw new Exception("This guest record is not valid for check out operation.");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void _reconcileAccounts(Guest guest) throws Exception {
        try {
            if (guest.getRentUnit() == RentUnit.RENT_UNIT_DAY) {
                accountTicketService.generateDayRentInvoice(guest);
            } else if (guest.getRentUnit() == RentUnit.RENT_UNIT_MONTH) {
                accountTicketService.generateMonthRentInvoice(guest);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private Guest _updateGuestRecord(Guest guest, Date actualCheckout) throws Exception {
        try {
            guest.setActualCheckout(actualCheckout);
            return guestRepository.save(guest);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        return guestRepository.findByEmail(email);
    }

    @Override
    public Optional<Guest> findByPhone(String phone) {
        return guestRepository.findByPhone(phone);
    }

    @Override
    public GuestResponse getGuestById(String guestId) throws Exception {
        GuestResponse guestResponse = new GuestResponse();
        try {
            Optional<Guest> optionalGuest = this.findById(guestId);
            if(optionalGuest.isPresent()){
                guestResponse.setGuest(optionalGuest.get());
            }else{
                throw new Exception("Guest not found");
            }
            guestResponse.setSuccess(true);
        } catch (Exception ex) {
            throw ex;
        }
        return guestResponse;
//        return null;
    }

    @Override
    public GuestResponse getGuestByEmail(String email) throws Exception {
        try {
            GuestResponse guestResponse = new GuestResponse();
            Optional<Guest> optionalGuest = this.findByEmail(email);
            if(optionalGuest.isPresent()){
                guestResponse.setGuest(optionalGuest.get());
            }else{
                optionalGuest = this.findByPhone(email);
                if(optionalGuest.isPresent()){
                    guestResponse.setGuest(optionalGuest.get());
                }else {
                    throw new Exception("Guest not found");
                }
            }
            guestResponse.setSuccess(true);
            return guestResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public GuestResponse getGuestWithinPeriod(int month, int year) throws Exception {
        try {
            GuestResponse guestResponse = new GuestResponse();
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
            return guestResponse;
        } catch (Exception ex) {
            throw ex;
        }
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
