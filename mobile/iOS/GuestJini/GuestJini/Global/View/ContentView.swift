//
//  ContentView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var viewRouter: ViewRouter
    
    var body: some View {
        VStack {
            if viewRouter.currentPage == ViewRoutes.LANDING_PAGE {
                LandingScreen(viewRouter: viewRouter)
            } else if viewRouter.currentPage ==  ViewRoutes.LOGIN_PAGE {
               LoginScreen(viewRouter: viewRouter, loginId: "", password: "")
            } else if viewRouter.currentPage ==  ViewRoutes.HOME_PAGE {
                          HomePage(viewRouter: viewRouter)
                       }
            else if viewRouter.currentPage ==  ViewRoutes.TICKET_UI {
               TicketUI(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.TICKET_LIST {
               TicketList(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.TICKET_VIEW {
               TicketView(viewRouter: viewRouter)
            }
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewRouter: ViewRouter())
    }
}
