//
//  TaskAttachment.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class TaskAttachment: Codable, Identifiable, ObservableObject {
    public var id:String? = nil
    public var clientId:String?  = nil
    public var orgId:String? = nil
    public var ticketId:String? = nil
    public var type:String? = nil
    public var docTitle:String? = nil
    public var path:String? = nil
    public var name:String? = nil
    public var error:Int = 0
    public var size:Int64 = 0
}
