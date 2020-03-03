//
//  AccountTicketItem.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AccountTicketItem: Decodable, Encodable, Identifiable {
    public var id:String? = ""
    public var clientId:String? = ""
    public var orgId:String? = ""
    public var bookingId:String? = ""
    public var guestId:String? = ""
    public var ticketId:String? = ""
    public var lineNo:Int? = 0
    public var itemNarration:String? = ""
    public var rate:Double? = 0.0
    public var qty:Double? = 0.0
    public var qtyUnit:String? = ""
    public var subTotal:Double? = 0.0
    public var taxValue:Double? = 0.0
    public var taxValueIdentifier:Int? = 0
    public var itemTotal:Double? = 0.0
    public var hasVoucher:Int? = 0
    public var voucherId:String? = ""
    public var amountUsed:Double? = 0.0
    public var creationTime:String? = ""

}

