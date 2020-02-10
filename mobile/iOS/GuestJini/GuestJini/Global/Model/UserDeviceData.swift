//
//  UserDeviceData.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 08/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import SwiftUI

class UserDeviceData: Codable, Identifiable {
    public var id:String? = ""
    public var userId:String? = ""
    public var fullName:String?  = ""
    public var userName:String? = ""
    public var deviceType:Int? = 0
    public var deviceIdentifier:String? = ""
    public var deviceToken:String? = ""
    public var creationTime:String? = ""
}
