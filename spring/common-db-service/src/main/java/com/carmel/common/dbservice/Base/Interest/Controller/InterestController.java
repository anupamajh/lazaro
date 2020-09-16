package com.carmel.common.dbservice.Base.Interest.Controller;

import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.Interest.Model.Interest;
import com.carmel.common.dbservice.Base.Interest.Response.InterestResponse;
import com.carmel.common.dbservice.Base.Interest.Service.InterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/interest")
public class InterestController {
    Logger logger = LoggerFactory.getLogger(InterestController.class);

    @Autowired
    InterestService interestService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public InterestResponse save(@Valid @RequestBody Interest interest) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .saveInterest(interest);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InterestResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InterestResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .get(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InterestResponse getDeleted() {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .getDeleted();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InterestResponse getAll() {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-interest-categories", method = RequestMethod.POST)
    public InterestResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .getPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public InterestResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .searchPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public InterestResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse = interestService
                    .search(searchRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

}
