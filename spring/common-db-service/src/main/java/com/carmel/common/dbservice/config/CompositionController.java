package com.carmel.common.dbservice.config;

import com.google.maps.GeoApiContext;

public class CompositionController {

    private static GeoApiContext geoApiContext;

    public static GeoApiContext getGeoApiContext(YAMLConfig yamlConfig) {
        if (geoApiContext == null) {
            geoApiContext = new GeoApiContext.Builder()
                    .apiKey(yamlConfig.getGoogleKey())
                    .build();
        }
        return geoApiContext;
    }
}
