//
//  AddressBook.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AddressBook: Decodable {
    public var id:String? = nil
    public var displayName:String?  = nil
    public var address:String? = nil
    public var dateOfBirth:String? = nil
    public var place:String? = nil
    public var pinCode:String? = nil
    public var phone1:String? = nil
    public var phone2:String? = nil
    public var email1:String? = nil
    public var email2:String? = nil
    public var logoPath:String? = nil
    public var logoBlob:String? = nil
    public var geoLat:String? = nil
    public var geoLon:String? = nil
}
