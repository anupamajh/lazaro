//
//  GroupConversation.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GroupConversation: Codable, Identifiable, ObservableObject {
    public var id:String? = ""
    public var clientId:String?  = ""
    public var orgId:String? = ""
    public var groupId:String? = ""
    public var displayName:String? = ""
    public var message:String? = ""
    public var creationTime:String? = ""
    public var isItMe:Int? = 0
}
