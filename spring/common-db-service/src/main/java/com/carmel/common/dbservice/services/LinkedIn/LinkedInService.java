package com.carmel.common.dbservice.services.LinkedIn;

import com.carmel.common.dbservice.response.LinkedIn.LinkedInResponse;

public interface LinkedInService {
    LinkedInResponse importData(String path);
}
