//
//  AccountTicketResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AccountTicketResponse: Decodable {
    public var accountTicket:AccountTicket? = nil
    public var accountTicketList:[AccountTicket]?  = nil
    public var totalPages:Int = 0
    public var totalRecords:Int = 0
    public var currentRecords:Int = 0
    public var success:Bool = false
    public var error:String = ""
}
