//
//  TicketUIModel.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import SwiftUI

class TicketUIModel: ObservableObject {
    @Published var title:String = ""
    @Published var narration:String = ""
    @Published var ticketAttachments:[TaskAttachment] = []
    
}
