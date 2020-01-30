//
//  GroupConversationResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GroupConversationResponse: Decodable {
    public var groupConversation:GroupConversation? = GroupConversation()
    public var groupConversationList:[GroupConversation]?  = []
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
