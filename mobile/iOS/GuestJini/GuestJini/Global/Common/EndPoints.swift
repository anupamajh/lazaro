//
//  EndPoints.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Network

class EndPoints{
    
    static var CLIENT_ID = "21e43c55-28ef-478a-ae65-dc896e5eaa34"
    static var CLIENT_SECRETE = "P@ssw0rd"
    
    
    static var AUTH_END_POINT_URL = "http://68.183.246.143:8000/auth"
    static var PUBLIC_END_POINT_URL = "http://68.183.246.143:8000/public"
    static var COMMON_END_POINT_URL = "http://68.183.246.143:8000/common"
    static var GUESTJINI_END_POINT_URL = "http://68.183.246.143:8000/guest-jini"
    
    
    static var LOCAL_END_POINT_URL = "http://192.168.1.7:8006"
    
    
    static var AUTHORISATION_URL = AUTH_END_POINT_URL + "/oauth/token"
    static var CHECK_TOKEN_URL = AUTH_END_POINT_URL + "/oauth/check_token"
    static var FORGOT_PASSWORD = PUBLIC_END_POINT_URL + "/api/reset-password"
    static var APP_ACCESS_REQUEST_URL = PUBLIC_END_POINT_URL + "/api/app-access-request"
    static var MY_PROFILE_URL = COMMON_END_POINT_URL + "/user/me"
    static var SAVE_USER_PREFERENCE_URL = COMMON_END_POINT_URL + "/user-preference/save"
    static var GET_USER_PREFERENCES_URL = COMMON_END_POINT_URL + "/user-preference/get-all"
    static var SAVE_PROFILE_PIC_URL = COMMON_END_POINT_URL + "/address-book/save-profile-pic"
    static var GET_PROFILE_PIC_URL = COMMON_END_POINT_URL + "/user/me/pic"
    static var GET_MY_ADDRESS_BOOK_URL = COMMON_END_POINT_URL + "/address-book/get-my-address-book"
    static var CHANGE_PASSWORD_URL = COMMON_END_POINT_URL + "/user/change-password"
    static var SAVE_USER_DEVICE_DATA = COMMON_END_POINT_URL + "/user-device-data/save"
    
    static var GET_LIST_OF_PEOPLE =  COMMON_END_POINT_URL + "/people/get-people"
    static var GET_PERSON_DETAIL =  COMMON_END_POINT_URL + "/people/get-person"
    static var AD_PERSON_TO_FAVOURITE =  COMMON_END_POINT_URL + "/people/add-remove-favourite"
    static var GET_PEOPLE_PIC = COMMON_END_POINT_URL + "/people/get-people-pic"
    
    static var GET_INTEREST_CATEGORY_LIST_URL = COMMON_END_POINT_URL + "/interest-category/get-all"
    static var GET_INTEREST_LIST_URL = COMMON_END_POINT_URL + "/interest/get-all"
    static var GET_MY_INTEREST_LIST_URL = COMMON_END_POINT_URL + "/user-interests/get-user-interests"
    static var SAVE_MY_INTEREST_URL = COMMON_END_POINT_URL + "/user-interests/save"
    
    static var GET_GROUP_LIST_BY_TYPE = COMMON_END_POINT_URL + "/groups/get-all-by-type"
    static var GET_GROUP_DETAILS = COMMON_END_POINT_URL + "/groups/get"
    static var INVITE_TO_GROUP = COMMON_END_POINT_URL + "/groups/invite"
    static var SAVE_GROUP = COMMON_END_POINT_URL + "/groups/save"
    static var SUBSCRIBE_TO_GROUP = COMMON_END_POINT_URL + "/groups/subscribe"
    static var SAVE_GROUP_CONVERSATION = COMMON_END_POINT_URL + "/group-conversation/save"
    static var GET_GROUP_CONVERSATION = COMMON_END_POINT_URL + "/group-conversation/get-by-group"
    
    
    //TICKET
    static var TICKET_SAVE_URL = GUESTJINI_END_POINT_URL + "/task-ticket/save"
    static var TICKET_GET_URL = GUESTJINI_END_POINT_URL + "/task-ticket/get"
    static var TICKET_LIST_URL = GUESTJINI_END_POINT_URL + "/task-ticket/get-all"
    static var TICKET_ATTACHMENT_URL = GUESTJINI_END_POINT_URL + "/task-ticket/upload"
    
    static var KB_LIST_URL = GUESTJINI_END_POINT_URL + "/kb/get-all"
    static var KB_GET_URL = GUESTJINI_END_POINT_URL + "/kb/get"
    static var KB_GET_AUTHOR_PIC = GUESTJINI_END_POINT_URL + "/kb/kb-author/pic"
    
    static var KB_SAVE_REVIEW = GUESTJINI_END_POINT_URL + "/kb-review/save"
    static var KB_GET_ALL_REVIEWS = GUESTJINI_END_POINT_URL + "/kb-review/get-all"
    
    static var KB_SAVE_RATING = GUESTJINI_END_POINT_URL + "/kb-rating/save"
    static var KB_GET_RATING = GUESTJINI_END_POINT_URL + "/kb-rating/get-my-rating"
    static var KB_GET_RATINGS_PERCENTAGE = GUESTJINI_END_POINT_URL + "/kb-rating/get-rating-percent"
    
    static var TASK_NOTES_SAVE_URL = GUESTJINI_END_POINT_URL + "/task-ticket-notes/save"
    static var TASK_NOTES_GET_BY_TICKET_URL = GUESTJINI_END_POINT_URL + "/task-ticket-notes/get-ticket-notes"
    
    
    static var ACCOUNT_GET_MY_RENT_INVOICES = GUESTJINI_END_POINT_URL + "/account-tickets/get-my-rent-invoice"
    static var ACCOUNT_GET_RENT_INVOICE = GUESTJINI_END_POINT_URL + "/account-tickets/get"
    
    
    static let monitor = NWPathMonitor()
    
    static func checkInterwebs() -> Bool {
        var status = false
        monitor.pathUpdateHandler = { path in
            if path.status == .satisfied {
                status = true  // online
            }
        }
        return status
    }
}
