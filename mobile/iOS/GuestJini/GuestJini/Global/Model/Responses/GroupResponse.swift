//
//  GroupResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GroupResponse: Decodable {
    public var group:GroupModel? = GroupModel()
    public var groupList:[GroupModel]?  = []
    public var groupPeople:[AddressBookDTO]?  = []
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}

