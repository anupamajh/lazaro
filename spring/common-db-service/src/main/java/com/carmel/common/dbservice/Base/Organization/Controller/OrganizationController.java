package com.carmel.common.dbservice.Base.Organization.Controller;

import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import com.carmel.common.dbservice.Base.Organization.Responce.OrganizationResponse;
import com.carmel.common.dbservice.Base.Organization.Service.OrganizationService;
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
@RequestMapping(value = "/organization")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationService organizationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public OrganizationResponse save(@Valid @RequestBody Organization organization) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.saveOrgnization(organization);
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public OrganizationResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.moveToTrash(formData);
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public OrganizationResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.get(formData);
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public OrganizationResponse getAll() {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.getAll();
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public OrganizationResponse getDeleted() {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.getDeleted();
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/get-organizations", method = RequestMethod.POST)
    public OrganizationResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.getPaginated(formData);
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }

    @RequestMapping(value = "/search-organizations", method = RequestMethod.POST)
    public OrganizationResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try{
            organizationResponse = organizationService.searchPaginated(formData);
        }catch (Exception ex){
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }



    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public OrganizationResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            organizationResponse = organizationService.search(searchRequest);
        } catch (Exception ex) {
            organizationResponse.setSuccess(true);
            organizationResponse.setError(ex.getMessage());
        }
        return organizationResponse;
    }


}
