//
//  TicketAttachmentDrawerContent.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketAttachmentDrawerContent: View {
    @ObservedObject var ticketAttachmentDrawerAction: TicketAttachmentDrawerAction
    init(ticketAttachmentDrawerAction:TicketAttachmentDrawerAction){
        UITableView.appearance().separatorColor = .clear
        self.ticketAttachmentDrawerAction = ticketAttachmentDrawerAction
    }
    var body: some View {
        List {
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "camera")
                        .padding()
                    GuestJiniInformationText(information: "Take pictures to help us know the exact issue")
                    Spacer()
                }.onTapGesture {
                    self.ticketAttachmentDrawerAction.currentAction = 1
                }
                Divider()
            }.onTapGesture {
                self.ticketAttachmentDrawerAction.currentAction = 1
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "photo")
                        .padding()
                    GuestJiniInformationText(information: "Upload a picture relevant to the issue")
                    Spacer()
                }.onTapGesture {
                    self.ticketAttachmentDrawerAction.currentAction = 2
                }
                Divider()
            }.onTapGesture {
                self.ticketAttachmentDrawerAction.currentAction = 2
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "video")
                        .padding()
                    GuestJiniInformationText(information: "Upload a video relevant to the issue")
                    Spacer()
                }.onTapGesture {
                    self.ticketAttachmentDrawerAction.currentAction = 3
                }
                Divider()
            }.onTapGesture {
                self.ticketAttachmentDrawerAction.currentAction = 3
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "mic")
                        .padding()
                    GuestJiniInformationText(information: "You can send us an audio message too!")
                    Spacer()
                }.onTapGesture {
                   self.ticketAttachmentDrawerAction.currentAction = 4
                }
                Divider()
            }.onTapGesture {
               self.ticketAttachmentDrawerAction.currentAction = 4
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "folder")
                        .padding()
                    GuestJiniInformationText(information: "Upload documents like bills, warranties or any other files relevant to the issue")
                    Spacer()
                }.onTapGesture {
                    self.ticketAttachmentDrawerAction.currentAction = 5
                }
                Divider()
            }.onTapGesture {
                self.ticketAttachmentDrawerAction.currentAction = 5
            }
        }
    }
}

struct TicketAttachmentDrawerContent_Previews: PreviewProvider {
    static var previews: some View {
        TicketAttachmentDrawerContent(ticketAttachmentDrawerAction:TicketAttachmentDrawerAction())
    }
}
