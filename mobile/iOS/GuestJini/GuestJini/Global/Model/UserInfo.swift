//
//  UserInfo.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 06/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//


import Foundation
class UserInfo: Decodable {
    public var id:String = ""
    public var fullName:String  = ""
    public var userName:String = ""
    public var phone:String = ""
    public var gender:Int = 0
    public var addressBook:AddressBook = AddressBook()
    public var userPreferences:[UserPreference]  = []
    
}
