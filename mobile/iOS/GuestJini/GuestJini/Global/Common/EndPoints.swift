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
    
    
    static var END_POINT_URL = "http://139.59.32.238:8000"
    static var LOCAL_END_POINT_URL = "http://192.168.0.8:8006"
    static var AUTHORISATION_URL = END_POINT_URL + "/auth/oauth/token"
    static var CHECK_TOKEN_URL = END_POINT_URL + "/auth/oauth/check_token"
    static var FORGOT_PASSWORD = END_POINT_URL + "/public/api/reset-password"
    static var APP_ACCESS_REQUEST_URL = END_POINT_URL + "/public/api/app-access-request"
    static var MY_PROFILE_URL = END_POINT_URL + "/common/user/me"
    static var SAVE_USER_PREFERENCE_URL = END_POINT_URL + "/common/user-preference/save"
    static var GET_USER_PREFERENCES_URL = END_POINT_URL + "/common/user-preference/get-all"
    static var SAVE_PROFILE_PIC_URL = END_POINT_URL + "/common/address-book/save-profile-pic"
    static var GET_PROFILE_PIC_URL = END_POINT_URL + "/common/user/me/pic"
    static var GET_MY_ADDRESS_BOOK_URL = END_POINT_URL + "/common/address-book/get-my-address-book"
    
    static var GET_LIST_OF_PEOPLE =  END_POINT_URL + "/common/people/get-people"
    static var GET_PERSON_DETAIL =  END_POINT_URL + "/common/people/get-person"
    static var AD_PERSON_TO_FAVOURITE =  END_POINT_URL + "/common/people/add-remove-favourite"
    
    static var GET_INTEREST_CATEGORY_LIST_URL = END_POINT_URL + "/common/interest-category/get-all"
    static var GET_INTEREST_LIST_URL = END_POINT_URL + "/common/interest/get-all"
    static var GET_MY_INTEREST_LIST_URL = END_POINT_URL + "/common/user-interests/get-user-interests"
    static var SAVE_MY_INTEREST_URL = END_POINT_URL + "/common/user-interests/save"
    
    
    //TICKET
    static var TICKET_SAVE_URL = END_POINT_URL + "/helpdesk/task-ticket/save"
    static var TICKET_GET_URL = END_POINT_URL + "/helpdesk/task-ticket/get"
    static var TICKET_LIST_URL = END_POINT_URL + "/helpdesk/task-ticket/get-all"
    static var TICKET_ATTACHMENT_URL = END_POINT_URL + "/helpdesk/task-ticket/upload"
    
    static var KB_LIST_URL = END_POINT_URL + "/helpdesk/kb/get-all"
    static var KB_GET_URL = END_POINT_URL + "/helpdesk/kb/get"
    static var KB_GET_AUTHOR_PIC = END_POINT_URL + "/helpdesk/kb/kb-author/pic"
    
    static var KB_SAVE_REVIEW = END_POINT_URL + "/helpdesk/kb-review/save"
    static var KB_GET_ALL_REVIEWS = END_POINT_URL + "/helpdesk/kb-review/get-all"
    
    static var KB_SAVE_RATING = END_POINT_URL + "/helpdesk/kb-rating/save"
    static var KB_GET_RATING = END_POINT_URL + "/helpdesk/kb-rating/get-my-rating"
    static var KB_GET_RATINGS_PERCENTAGE = END_POINT_URL + "/helpdesk/kb-rating/get-rating-percent"
    
    static var TASK_NOTES_SAVE_URL = END_POINT_URL + "/helpdesk/task-ticket-notes/save"
    static var TASK_NOTES_GET_BY_TICKET_URL = END_POINT_URL + "/helpdesk/task-ticket-notes/get-ticket-notes"
    
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
