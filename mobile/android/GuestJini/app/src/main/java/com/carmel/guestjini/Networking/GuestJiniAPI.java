package com.carmel.guestjini.Networking;


import com.carmel.guestjini.Common.Search.SearchRequest;
import com.carmel.guestjini.Networking.Accounts.AccountTicketResponse;
import com.carmel.guestjini.Networking.Booking.BookingResponse;
import com.carmel.guestjini.Networking.Group.Group;
import com.carmel.guestjini.Networking.Group.GroupConversationResponse;
import com.carmel.guestjini.Networking.Group.GroupResponse;
import com.carmel.guestjini.Networking.Guest.GuestResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReviewResponse;
import com.carmel.guestjini.Networking.OTP.OTPResponse;
import com.carmel.guestjini.Networking.Tickets.InboxCount;
import com.carmel.guestjini.Networking.Tickets.TaskAssignee;
import com.carmel.guestjini.Networking.Tickets.TaskAssigneeResponse;
import com.carmel.guestjini.Networking.Tickets.TaskAttachmentResponse;
import com.carmel.guestjini.Networking.Tickets.TaskCountResponse;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.TaskNotesResponse;
import com.carmel.guestjini.Networking.Tickets.TaskRunnerResponse;
import com.carmel.guestjini.Networking.Tickets.TicketCategoryResponse;
import com.carmel.guestjini.Networking.Tickets.TicketCountDTO;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBack;
import com.carmel.guestjini.Networking.Tickets.TicketFeedBackResponse;
import com.carmel.guestjini.Networking.Tickets.TicketRequest;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;
import com.carmel.guestjini.Networking.Users.AccessToken;
import com.carmel.guestjini.Networking.Users.AppAccessRequestResponse;
import com.carmel.guestjini.Networking.Users.ForgotPasswordResponse;
import com.carmel.guestjini.Networking.Users.InterestCategoryResponse;
import com.carmel.guestjini.Networking.Users.InterestResponse;
import com.carmel.guestjini.Networking.Users.PeopleResponse;
import com.carmel.guestjini.Networking.Users.UserGrants;
import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Networking.Users.UserInterests;
import com.carmel.guestjini.Networking.Users.UserInterestsResponse;
import com.carmel.guestjini.Networking.Users.UserPreferenceResponse;
import com.carmel.guestjini.Networking.Users.UserResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    Call<AccessToken> attemptClientLogin(
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

    @GET("/auth/oauth/check_token")
    Call<UserGrants> getGrants(
            @Header("Authorization") String credentials,
            @Query("token") String token
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

    @POST("/guest-jini/task-ticket/get-account-tickets-by-status")
    Call<TicketResponse> getTicketList(@Body Map<String, String> requestData);

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

    @POST("/common/user-preference/save")
    Call<UserPreferenceResponse> saveUserPreference
            (
                    @Body Map<String, String> postData
            );

    @POST("/guest-jini/account-tickets/get-my-rent-invoice")
    Call<AccountTicketResponse> getMyRentInvoices();


    @POST("/guest-jini/account-tickets/get")
    Call<AccountTicketResponse> getMyRentInvoiceDetails(
            @Body Map<String, String> postData
    );

    @POST("/common/people/get-people")
    Call<PeopleResponse> getPeopleList();

    @POST("/common/people/get-person")
    Call<PeopleResponse> getPersonDetails(
            @Body Map<String, String> postData
    );

    @POST("/common/people/add-remove-favourite")
    Call<PeopleResponse> addPersonFavourite(
            @Body Map<String, String> postData
    );

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

    @POST("/common/groups/subscribe")
    Call<GroupResponse> subscribeToGroup(
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

    @POST("/guest-jini/task-ticket-categories/get-task-categories-by-parent-id")
    Call<TicketCategoryResponse> getTicketCategoriesByParent(
            @Body Map<String, String> postData
    );

    @POST("/guest-jini/task-ticket/get-count-by-status")
    Call<TicketCountDTO> getTicketCountByStatus();

    @POST("/guest-jini/task-ticket/trash")
    Call<TicketResponse> deleteTicket(@Body Map<String, String> postData);

    @POST("/guest-jini/booking/check-phone-number")
    Call<BookingResponse> checkPhoneNumber(@Body Map<String, String> postData);

    @POST("/common/otp/request-otp")
    Call<OTPResponse> sendOTP(@Body Map<String, String> postData);

    @POST("/common/otp/verify-otp")
    Call<OTPResponse> verifyOTP(@Body Map<String, String> postData);

    @POST("/common/user/phone-number-sign-up")
    Call<UserResponse> createUserAccount(@Body Map<String, String> postData);

    @POST("/common/user/phone-number-sign-up-task-force")
    Call<UserResponse> createSupportUserAccount(@Body Map<String, String> postData);

    @POST("/common/user/set-password")
    Call<GenericResponse> setPassword(@Body Map<String, String> postData);

    @POST("/guest-jini/ticket-feedback/save")
    Call<TicketFeedBackResponse> saveTicketFeedback(@Body TicketFeedBack ticketFeedBack);

    @POST("/guest-jini/ticket-feedback/get-by-ticket-id")
    Call<TicketFeedBackResponse> getTicketFeedbackByTicketId(@Body Map<String, String> postData);

    @Multipart
    @POST("/guest-jini/task-ticket/upload")
    Call<TaskAttachmentResponse> uploadTicketAttachment(
            @Part MultipartBody.Part file,
            @Part("fileName") RequestBody fileName
    );


    @POST("/guest-jini/task-ticket/get-task-count")
    Call<TaskCountResponse> getTaskCount();

    @POST("/guest-jini/task-ticket/search-shared-inbox")
    Call<TicketResponse> getSharedInboxList(@Body SearchRequest searchRequest);

    @POST("/guest-jini/task-ticket/get-assignee")
    Call<TaskAssigneeResponse> getTaskAssignee();

    @POST("/guest-jini/task-runner/assign-ticket")
    Call<TaskRunnerResponse> assignTaskTicket(@Body TaskAssignee taskAssignee);

    @POST("/guest-jini/task-ticket/get-assignee-by-group")
    Call<TaskAssigneeResponse> getTaskAssignee(@Body Map<String, String> postData);

    @POST("/guest-jini/task-runner/get-assignment-details")
    Call<TaskAssigneeResponse> fetchTaskAssignee(@Body Map<String, String> postData);

    @POST("/guest-jini/task-ticket/close")
    Call<TicketResponse> closeTicket(@Body Map<String, String> postData);

    @POST("/guest-jini/guest/get-by-phone")
    Call<GuestResponse> getGuestDetails(@Body Map<String, String> postData);

    @POST("/guest-jini/task-ticket/get-inbox-count")
    Call<InboxCount> getInboxCount();

    @POST("/guest-jini/task-runner/withdraw-ticket-from-group")
    Call<TaskRunnerResponse> withdrawTicketFromGroup(@Body TaskAssignee taskAssignee);

    @POST("/guest-jini/task-runner/withdraw-ticket-from-agent")
    Call<TaskRunnerResponse> withdrawTicketFromAgent(@Body TaskAssignee taskAssignee);

}
