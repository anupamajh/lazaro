package com.carmel.common.dbservice.services.Instagram;

import com.carmel.common.dbservice.response.Instagram.InstagramResponse;

public interface InstagramService {
    InstagramResponse importData(String filepath);
}
