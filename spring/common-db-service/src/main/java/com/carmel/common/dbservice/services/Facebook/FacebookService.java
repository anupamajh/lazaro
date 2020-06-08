package com.carmel.common.dbservice.services.Facebook;

import com.carmel.common.dbservice.response.Facebook.FacebookResponse;

public interface FacebookService {

    FacebookResponse importData(String filepath);
}
