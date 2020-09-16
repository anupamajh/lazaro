package com.carmel.common.dbservice.Base.AppFeature.Controller;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.Base.AppFeature.Service.AppFeaturesService;
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
@RequestMapping(value = "/app-features")
public class AppFeaturesController {

    Logger logger = LoggerFactory.getLogger(AppFeaturesController.class);

    @Autowired
    AppFeaturesService appFeaturesService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppFeaturesResponse save(@Valid @RequestBody AppFeatures appFeatures) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .saveAppFeature(appFeatures);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AppFeaturesResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AppFeaturesResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .get(formData);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public AppFeaturesResponse getAll() {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .getAll();
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public AppFeaturesResponse getDeleted() {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .getDeleted();
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .getPaginated(formData);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/search-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .searchPaginated(formData);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-deep-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getDeepAppFeatures(Map<String, String> formData) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .getDeepAppFeatures(formData);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-user-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getUserAppFeatures() {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .getUserAppFeatures();
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AppFeaturesResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse = appFeaturesService
                    .search(searchRequest);
        } catch (Exception ex) {
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

}
