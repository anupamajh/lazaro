package com.carmel.common.dbservice.services.Twitter;

import com.carmel.common.dbservice.response.Twitter.TwitterResponse;

public interface TwitterService {
    TwitterResponse importData(String filepath);
}
