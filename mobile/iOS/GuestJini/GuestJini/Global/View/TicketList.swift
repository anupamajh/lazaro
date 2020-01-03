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
    @ObservedObject var ticketService:TicketService = TicketService()
    var tickets:[Ticket] = []
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
    
    func fetchTickets() -> Void {
        self.ticketService.getTicketList { (response) in
            if(response.success){
                self.tickets = response.taskTicketList!
            }
        }
    }
}

struct TicketList_Previews: PreviewProvider {
    static var previews: some View {
        TicketList(viewRouter: ViewRouter())
    }
}
