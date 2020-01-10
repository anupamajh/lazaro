package com.carmel.android.guestjini.datalayer.Common;

public class EndPoints {
    public static String END_POINT_URL = "http://139.59.32.238:8000";
    public static String CLIENT_ID = "21e43c55-28ef-478a-ae65-dc896e5eaa34";
    public static String CLIENT_SECRETE = "P@ssw0rd";


    public  static String AUTHORISATION_URL = END_POINT_URL + "/auth/oauth/token";
    public static String FORGOT_PASSWORD = END_POINT_URL + "/api/user/reset-password";
    public  static String MY_PROFILE_URL = END_POINT_URL + "/common/user/me";


    //TICKET
    public  static String TICKET_SAVE_URL = END_POINT_URL + "/helpdesk/task-ticket/save";
    public static String TICKET_GET_URL = END_POINT_URL + "/helpdesk/task-ticket/get";
    public static String TICKET_LIST_URL = END_POINT_URL + "/helpdesk/task-ticket/get-all";
}
