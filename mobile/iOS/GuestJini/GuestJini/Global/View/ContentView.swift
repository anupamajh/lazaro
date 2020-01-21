//
//  ContentView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var ticketUIData: TicketUIModel
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
               TicketUI(viewRouter: viewRouter).environmentObject(self.ticketUIData)
            }else if viewRouter.currentPage ==  ViewRoutes.TICKET_LIST {
               TicketList(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.TICKET_VIEW {
               TicketView(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.FORGOT_PASSWORD_PAGE {
               ForgotPassword(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.SETTINGS_VIEW {
               SettingsView(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.MY_PROFILE_VIEW {
               MyProfileView(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.NOTIFICATION_VIEW {
               NotificationsView(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.HELP_VIEW {
               HelpView(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.APP_ACCESS_REQUEST_PAGE {
               AppAccessRequest(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.FIND_HELP_PAGE {
               FindHelpPage(viewRouter: viewRouter)
            }else if viewRouter.currentPage ==  ViewRoutes.TICKET_ATTACHMENT_LIST {
               TicketAttachmentList(
                viewRouter: viewRouter,
                ticketAttachmentDrawerAction: TicketAttachmentDrawerAction()
               ).environmentObject(self.ticketUIData)
            }else if viewRouter.currentPage ==  ViewRoutes.HELP_ARTICLE_PAGE {
                HelpArticleView(viewRouter: viewRouter)
            }
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewRouter: ViewRouter())
    }
}
