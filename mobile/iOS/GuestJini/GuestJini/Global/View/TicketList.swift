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
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct TicketList_Previews: PreviewProvider {
    static var previews: some View {
        TicketList(viewRouter: ViewRouter())
    }
}
