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
    static var FORGOT_PASSWORD = END_POINT_URL + "/api/user/reset-password"
    static var MY_PROFILE_URL = END_POINT_URL + "/common/user/me"
    
    
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
