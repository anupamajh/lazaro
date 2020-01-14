//
//  UserPreference.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

class UserPreference: Codable{
    public var id:String? = ""
    public var userId:String? = ""
    public var preferenceType:Int? = 0
    public var isHidden:Int? = 0

}
