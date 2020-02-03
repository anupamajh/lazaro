//
//  AddressBookDTO.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import SwiftUI

class AddressBookDTO: Codable, Identifiable {
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
    public var creationTime:String? = ""
    public var isInvited:Int? = 0
    public var isMe:Int? = 0
    public var hasAcceptedInvitation:Int? = 0
}
