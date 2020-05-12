package com.carmel.guestjini.Services.Group;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Group.GroupResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GroupService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/common/groups/get-all-by-type")
    Call<GroupResponse> getGroupByType(
           @Body Map<String, String > postData
    );
}
