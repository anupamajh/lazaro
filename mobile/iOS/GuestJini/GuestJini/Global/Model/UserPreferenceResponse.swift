//
//  UserPreferenceResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

class UserPreferenceResponse: Codable {
    public var userPreference:UserPreference? = nil
    public var userPreferenceList:[UserPreference]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
