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
    static var AUTHORISATION_URL = END_POINT_URL + "/auth/oauth/token"
    static var CHECK_TOKEN_URL = END_POINT_URL + "/auth/oauth/check_token"
    static var FORGOT_PASSWORD = END_POINT_URL + "/api/user/reset-password"
    static var APP_ACCESS_REQUEST_URL = END_POINT_URL + "/api/user/app-access-request"
    static var MY_PROFILE_URL = END_POINT_URL + "/common/user/me"
    static var SAVE_USER_PREFERENCE_URL = END_POINT_URL + "/common/user-preference/save"
    static var GET_USER_PREFERENCES_URL = END_POINT_URL + "/common/user-preference/get-all"
    static var SAVE_PROFILE_PIC_URL = END_POINT_URL + "/common/address-book/save-profile-pic"
    static var GET_PROFILE_PIC_URL = END_POINT_URL + "/common/user/me/pic"
    static var GET_MY_ADDRESS_BOOK_URL = END_POINT_URL + "/common/address-book/get-my-address-book"
    
    
    //TICKET
    static var TICKET_SAVE_URL = END_POINT_URL + "/helpdesk/task-ticket/save"
    static var TICKET_GET_URL = END_POINT_URL + "/helpdesk/task-ticket/get"
    static var TICKET_LIST_URL = END_POINT_URL + "/helpdesk/task-ticket/get-all"
    
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
