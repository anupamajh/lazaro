//
//  TicketAttachmentDrawerAction.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//


import Foundation
import Combine
import SwiftUI


class TicketAttachmentDrawerAction: ObservableObject{
    let objectWillChange = PassthroughSubject<TicketAttachmentDrawerAction,Never>()
    var currentAction: Int = 0{
        didSet {
            objectWillChange.send(self)
        }
    }
}
