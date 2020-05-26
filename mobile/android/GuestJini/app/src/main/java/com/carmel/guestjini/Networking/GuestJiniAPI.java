package com.carmel.guestjini.Networking;


import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReviewResponse;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.TaskNotesResponse;
import com.carmel.guestjini.Networking.Tickets.TicketRequest;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Networking.Users.AccessToken;
import com.carmel.guestjini.Networking.Users.AppAccessRequestResponse;
import com.carmel.guestjini.Networking.Users.ForgotPasswordResponse;
import com.carmel.guestjini.Networking.Users.InterestCategoryResponse;
import com.carmel.guestjini.Networking.Users.InterestResponse;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Networking.Users.UserInterestsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GuestJiniAPI {
    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AccessToken> attemptLogin(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("username") String userName,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/auth/oauth/token")
    Call<AccessToken> refreshToken(
            @Header("Authorization") String credentials,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken
    );

    @POST("/guest-jini/kb/get-all")
    Call<KBResponse> getKBList();

    @POST("/guest-jini/kb/get")
    Call<KBResponse> getKBById(@Body Map<String, String> postData);

    @POST("/guest-jini/kb-rating/save")
    Call<KBRatingResponse> saveKBRating(@Body KBRating kbRating);

    @POST("/guest-jini/kb-rating/get-my-rating")
    Call<KBRatingResponse> getKBRatings(@Body Map<String, String> postData);

    @POST("/guest-jini/kb-rating/get-rating-percent")
    Call<KBRatingPercentResponse> getKBRatingPercentage(@Body Map<String, String> postData);

    @POST("/guest-jini/kb-review/save")
    Call<KBResponse> saveKBReview(
            @Body KBReview kbReview
    );

    @POST("/guest-jini/kb-review/get-all")
    Call<KBReviewResponse> getAllKBReviews(
            @Body Map<String, String> postData
    );

    @POST("/guest-jini/task-ticket/save")
    Call<TicketResponse> saveTicket(
            @Body TicketRequest ticketRequest
    );

    @POST("/guest-jini/task-ticket/get-all")
    Call<TicketResponse> getTicketList();

    @POST("/guest-jini/task-ticket/get")
    Call<TicketResponse> getTicketById(
            @Body Map<String, String> requestData
    );

    @POST("/guest-jini/task-ticket-notes/get-ticket-notes")
    Call<TaskNotesResponse> getTicketNotes(
            @Body Map<String, String> requestData
    );

    @POST("/guest-jini/task-ticket-notes/save")
    Call<TaskNotesResponse> saveTicketNotes(
            @Body TaskNote taskNote
    );

    @GET("/common/user/me")
    Call<UserInfo> getMyProfile();

    @GET("/common/user/me/pic")
    Call<String> getMyProfilePic();

    @POST("/common/user/change-password")
    Call<GenericResponse> changePassword(
            @Body Map<String, String> requestData
    );

    @POST("/public/api/reset-password")
    Call<ForgotPasswordResponse> restPassword(
            @Body Map<String, String> postData);

    @POST("/public/api/app-access-request")
    Call<AppAccessRequestResponse> appAccessRequest(
            @Body Map<String, String> postData);

    @POST("/common/address-book/save-profile-pic")
    Call<UserInfo> saveProfilePic
            (
                    @Body Map<String, String> postData
            );

    @POST("/common/interest-category/get-all")
    Call<InterestCategoryResponse> getInterestCategoryList();

    @POST("/common/interest/get-all")
    Call<InterestResponse> getInterestList();

    @POST("/common/user-interests/get-user-interests")
    Call<UserInterestsResponse> getMyInterests();

    @POST("/common/user-interests/save")
    Call<UserInterestsResponse> saveMyInterest(
            @Body UserInterests userInterests
    );


}
