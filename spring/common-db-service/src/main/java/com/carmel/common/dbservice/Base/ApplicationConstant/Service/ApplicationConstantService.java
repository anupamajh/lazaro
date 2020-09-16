package com.carmel.common.dbservice.Base.ApplicationConstant.Service;

import com.carmel.common.dbservice.Base.ApplicationConstant.Model.ApplicationConstant;
import com.carmel.common.dbservice.Base.ApplicationConstant.Response.ApplicationConstantResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

public interface ApplicationConstantService {
    Optional<ApplicationConstant> findByKey(String key) throws Exception;

    ApplicationConstantResponse getValueByKey(Map<String, String> formData) throws Exception;
}


