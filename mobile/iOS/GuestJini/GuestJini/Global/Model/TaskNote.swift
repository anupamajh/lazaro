//
//  TaskNote.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 20/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class TaskNote: Codable, Identifiable, ObservableObject {
    public var id:String? = nil
    public var clientId:String?  = nil
    public var orgId:String? = nil
    public var userId:String? = nil
    public var ticketId:String? = nil
    public var notes:String? = nil
    public var userName:String? = nil
    public var isRead:Int64 = 0
    public var creationTime:String? = ""
}
