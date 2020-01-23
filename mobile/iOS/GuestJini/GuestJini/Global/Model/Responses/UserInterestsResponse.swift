//
//  UserInterestsResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class UserInterestsResponse: Decodable {
    public var userInterests:UserInterests? = UserInterests()
    public var userInterestsList:[UserInterests]?  = []
    public var totalPages:Int? = 0
    public var totalRecords:Int? = 0
    public var currentRecords:Int? = 0
    public var success:Bool? = false
    public var error:String? = ""
}
