//
//  UserDeviceDataResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 08/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class UserDeviceDataResponse: Decodable {
    public var userDeviceData:UserDeviceData? = UserDeviceData()
    public var userDeviceDataList:[UserDeviceData]?  = []
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
