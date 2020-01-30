//
//  Connectivity.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire

class Connectivity {
    class func isConnectedToInternet() -> Bool {
        return NetworkReachabilityManager()?.isReachable ?? false
    }
    class func cancelAllRequests()->Void{
         Alamofire.Session.default.session.getAllTasks { (tasks) in
                       tasks.forEach({$0.cancel()})
        }
    }
}
