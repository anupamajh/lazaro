//
//  AccountTicket.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AccountTicket: Decodable, Encodable, Identifiable {
    public var id:String? = ""
    public var clientId:String? = ""
    public var orgId:String? = ""
    public var bookingId:String? = ""
    public var guestId:String? = ""
    public var accountHeadId:String? = ""
    public var ticketIdentifier:Int? = 0
    public var ticketNumber:String? = ""
    public var templateId:String? = ""
    public var ticketDate:String? = ""
    public var periodFrom:String? = ""
    public var periodUpto:String? = ""
    public var ticketNarration:String? = ""
    public var itemTotal:Double? = 0.0
    public var taxTotal:Double? = 0.0
    public var discount:Double? = 0.0
    public var netTotal:Double? = 0.0
    public var ticketStatus:Int? = 0
    public var creationTime:String? = ""
    public var accountTicketItems:[AccountTicketItem]? = []
}
