//
//  TicketList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketList: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketService:TicketService
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketService = TicketService(viewRouter: viewRouter)
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "MY TICKETS")
                        Spacer()
                    }.padding()
                    VStack{
                        List {
                            ForEach(self.ticketService.ticketList) { ticket in
                               TicketRow(ticket: ticket)
                            }
                        }
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct TicketList_Previews: PreviewProvider {
    static var previews: some View {
        TicketList(viewRouter: ViewRouter())
    }
}
