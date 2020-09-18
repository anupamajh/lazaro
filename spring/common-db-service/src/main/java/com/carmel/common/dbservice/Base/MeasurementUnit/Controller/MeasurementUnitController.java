package com.carmel.common.dbservice.Base.MeasurementUnit.Controller;

import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;
import com.carmel.common.dbservice.Base.MeasurementUnit.Response.MeasurementUnitResponse;
import com.carmel.common.dbservice.Base.MeasurementUnit.Service.MeasurementUnitService;
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
@RequestMapping(value = "/measurement-unit")
public class MeasurementUnitController {

    Logger logger = LoggerFactory.getLogger(MeasurementUnitController.class);

    @Autowired
    MeasurementUnitService measurementUnitService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MeasurementUnitResponse save(@Valid @RequestBody MeasurementUnit measurementUnit) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.saveMeasurementUnit(measurementUnit);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public MeasurementUnitResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.moveToTrash(formData);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public MeasurementUnitResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.get(formData);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public MeasurementUnitResponse getDeleted() {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.getDeleted();
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public MeasurementUnitResponse getAll() {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.getAll();
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-measurement-units", method = RequestMethod.POST)
    public MeasurementUnitResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.getPaginated(formData);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/search-measurement-units", method = RequestMethod.POST)
    public MeasurementUnitResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.searchPaginated(formData);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }



    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public MeasurementUnitResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try{
            measurementUnitResponse = measurementUnitService.search(searchRequest);
        }catch (Exception ex){
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

}
