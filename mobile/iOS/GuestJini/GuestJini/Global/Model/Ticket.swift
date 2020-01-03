//
//  Ticket.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

class Ticket: Decodable, Encodable {
    public var id:String? = ""
    public var clientId:String? = ""
    public var orgId:String? = ""
    public var ticketCategoryId:String? = ""
    public var parentId:String? = ""
    public var predecessorId:String? = ""
    public var ticketNo:String? = ""
    public var ticketTitle:String? = ""
    public var ticketNarration:String = ""
    public var estimatedDuration:Double? = 0
    public var estimatedUnit:Int? = 0
    public var plannedStartDate:String? = ""
    public var plannedEndDate:String? = ""
    public var actualStartDate:String? = ""
    public var actualEndDate:String? = ""
    public var ticketStatus:Int? = 0
    public var requesterType:Int? = 0
    public var requesterId:String? = ""
    public var creationTime:String? = ""
}
