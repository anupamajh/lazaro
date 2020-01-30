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
                    GuestJiniRoundButtonSystemImage(systemImage: "camera").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 1
                    }
                        .padding()
                    GuestJiniInformationText(information: "Take pictures to help us know the exact issue").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 1
                    }
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
                    GuestJiniRoundButtonSystemImage(systemImage: "photo").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 2
                    }
                        .padding()
                    GuestJiniInformationText(information: "Upload a picture relevant to the issue").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 2
                    }
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
                    GuestJiniRoundButtonSystemImage(systemImage: "video").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 3
                    }
                        .padding()
                    GuestJiniInformationText(information: "Upload a video relevant to the issue").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 3
                    }
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
                    GuestJiniRoundButtonSystemImage(systemImage: "mic").onTapGesture {
                       self.ticketAttachmentDrawerAction.currentAction = 4
                    }
                        .padding()
                    GuestJiniInformationText(information: "You can send us an audio message too!").onTapGesture {
                       self.ticketAttachmentDrawerAction.currentAction = 4
                    }
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
                    GuestJiniRoundButtonSystemImage(systemImage: "folder").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 5
                    }
                        .padding()
                    GuestJiniInformationText(information: "Upload documents like bills, warranties or any other files relevant to the issue").onTapGesture {
                        self.ticketAttachmentDrawerAction.currentAction = 5
                    }
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
