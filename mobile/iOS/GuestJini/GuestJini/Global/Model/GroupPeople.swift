//
//  GroupPeople.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GroupPeople: Codable, Identifiable, ObservableObject {
    public var id:String? = ""
    public var clientId:String?  = ""
    public var orgId:String? = ""
    public var groupId:String? = ""
    public var hasAcceptedInvitation:Int? = 0
    public var name:String? = ""
    public var description:String? = ""
    public var creationTime:String? = ""
}
