package com.carmel.guestjini.Services.Group;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Group.Group;
import com.carmel.guestjini.Models.Group.GroupConversation;
import com.carmel.guestjini.Models.Group.GroupConversationResponse;
import com.carmel.guestjini.Models.Group.GroupResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GroupService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/common/groups/get-all-by-type")
    Call<GroupResponse> getGroupByType(
            @Body Map<String, String> postData
    );

    @POST("/common/groups/save")
    Call<GroupResponse> saveGroup(
            @Body Group group
    );

    @POST("/common/groups/get")
    Call<GroupResponse> getGroupById(
            @Body Map<String, String> postData
    );

    @POST("/common/groups/invite")
    Call<GroupResponse> inviteToGroup(
            @Body Map<String, String> postData
    );

    @POST("/common/group-conversation/get-by-group")
    Call<GroupConversationResponse> getGroupConversationById(
            @Body Map<String, String> postData
    );

    @POST("/common/group-conversation/save")
    Call<GroupConversationResponse> saveGroupConversation(
            @Body Map<String, String> postData
    );
}
