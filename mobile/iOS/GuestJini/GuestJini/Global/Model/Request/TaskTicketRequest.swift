//
//  TaskTicketRequest.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class TaskTicketRequest: Codable {
    public var taskTicket:Ticket? = nil
    public var taskAttachmentList:[TaskAttachment]?  = nil
}
