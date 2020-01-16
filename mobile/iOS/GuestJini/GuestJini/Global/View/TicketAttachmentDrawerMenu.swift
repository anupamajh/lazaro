//
//  TicketAttachmentDrawerMenu.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketAttachmentDrawerMenu: View {
    let width: CGFloat
    let isOpen: Bool
    let menuClose: () -> Void
    @ObservedObject var ticketAttachmentDrawerAction: TicketAttachmentDrawerAction
     var body: some View {
        ZStack {
            GeometryReader { _ in
                EmptyView()
            }
            .background(Color.gray.opacity(0.9))
            .opacity(self.isOpen ? 1.0 : 0.0)
            .animation(Animation.easeIn.delay(0.25))
            .onTapGesture {
                self.menuClose()
            }
            
            HStack {
                TicketAttachmentDrawerContent(ticketAttachmentDrawerAction: ticketAttachmentDrawerAction)
                    .frame(width: self.width)
                    .background(Color.white)
                    .offset(x: self.isOpen ? 0 : -self.width)
                    .animation(.default)
                
                Spacer()
            }.onTapGesture {
                if(self.ticketAttachmentDrawerAction.currentAction != 0){
                    self.menuClose()
                }
            }
        }
    }
}

