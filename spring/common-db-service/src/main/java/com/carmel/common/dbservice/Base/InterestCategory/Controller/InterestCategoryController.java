package com.carmel.common.dbservice.Base.InterestCategory.Controller;

import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;
import com.carmel.common.dbservice.Base.InterestCategory.Responce.InterestCategoryResponse;
import com.carmel.common.dbservice.Base.InterestCategory.Service.InterestCategoryService;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/interest-category")
public class InterestCategoryController {
    Logger logger = LoggerFactory.getLogger(InterestCategoryController.class);

    @Autowired
    InterestCategoryService interestCategoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public InterestCategoryResponse save(@Valid @RequestBody InterestCategory interestCategory) {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.saveInterestcategory(interestCategory);
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InterestCategoryResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.moveToTrash(formData);
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InterestCategoryResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.get(formData);
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InterestCategoryResponse getDeleted() {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.getDeleted();
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InterestCategoryResponse getAll() {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            interestCategoryResponse = interestCategoryService.getAll();
        } catch (Exception ex) {
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-interest-categories", method = RequestMethod.POST)
    public InterestCategoryResponse getPaginated(@RequestBody Map<String, String> formData) {
            logger.trace("Entering");
            InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
            try{
                interestCategoryResponse = interestCategoryService.getPaginated(formData);
            }catch (Exception ex){
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError(ex.getMessage());
            }
            return interestCategoryResponse;
    }

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public InterestCategoryResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.getPaginated(formData);
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public InterestCategoryResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try{
            interestCategoryResponse = interestCategoryService.search(searchRequest);
        }catch (Exception ex){
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

}
