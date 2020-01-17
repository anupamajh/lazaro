//
//  TaskAttachmentResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class TaskAttachmentResponse: Decodable {
    public var taskAttachment:TaskAttachment? = nil
    public var taskAttachmentList:[TaskAttachment]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
