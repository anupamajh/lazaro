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
                    GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                }
                Divider()
            }.onTapGesture {
                print("Camera Upload")
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "photo")
                        .padding()
                    GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                }
                Divider()
            }.onTapGesture {
                print("Image Upload")
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "video")
                        .padding()
                    GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                }
                Divider()
            }.onTapGesture {
                self.ticketAttachmentDrawerAction.currentAction = 3
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "mic")
                        .padding()
                    GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                }
                Divider()
            }.onTapGesture {
                print("Camera Upload")
            }
            VStack{
                HStack{
                    GuestJiniRoundButtonSystemImage(systemImage: "folder")
                        .padding()
                    GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                }
                Divider()
            }.onTapGesture {
                print("Camera Upload")
            }
        }
    }
}

struct TicketAttachmentDrawerContent_Previews: PreviewProvider {
    static var previews: some View {
        TicketAttachmentDrawerContent(ticketAttachmentDrawerAction:TicketAttachmentDrawerAction())
    }
}
