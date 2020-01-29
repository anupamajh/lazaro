//
//  AddressBook.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import SwiftUI

class AddressBook: Codable, Identifiable {
    public var id:String? = ""
    public var userId:String? = ""
    public var displayName:String?  = ""
    public var address:String? = ""
    public var dateOfBirth:String? = ""
    public var place:String? = ""
    public var pinCode:String? = ""
    public var phone1:String? = ""
    public var phone2:String? = ""
    public var email1:String? = ""
    public var email2:String? = ""
    public var logoPath:String? = ""
    public var logoBlob:String? = ""
    public var geoLat:String? = ""
    public var geoLon:String? = ""
}
