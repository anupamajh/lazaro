package com.carmel.common.dbservice.controller.Google;

import com.carmel.common.dbservice.config.CompositionController;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.AutocompleteStructuredFormatting;
import com.google.maps.model.PlaceAutocompleteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/google")
public class GoogleController {

    @Autowired
    YAMLConfig yamlConfig;

    @RequestMapping(value = "/search-addresses",  method = RequestMethod.POST)
    public List<AutocompletePrediction>  searchAddress(@RequestBody Map<String, String> formData) throws InterruptedException, ApiException, IOException {
        PlaceAutocompleteRequest.SessionToken session = new PlaceAutocompleteRequest.SessionToken();
        AutocompletePrediction[] autoCompletePredictions = PlacesApi.placeAutocomplete(
                CompositionController.getGeoApiContext(yamlConfig),
                formData.get("search_text"),
                session
        )
                .await();

        return Arrays.asList(autoCompletePredictions);
    }
}
