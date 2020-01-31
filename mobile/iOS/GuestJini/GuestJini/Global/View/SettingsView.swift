//
//  SettingsView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    Button(action: {
                        self.viewRouter.returnPage = ""
                        self.viewRouter.currentPage = ViewRoutes.MY_PROFILE_VIEW
                    }) {
                        GuestJiniSettingMenuItem(imageName: "person", menuText: "My Profile")
                        
                    }.padding(.horizontal)
                    
                    Button(action: {
                        self.viewRouter.currentPage = ViewRoutes.NOTIFICATION_VIEW
                    }) {
                        GuestJiniSettingMenuItem(imageName: "bell", menuText: "Notifications")
                        
                    }.padding(.horizontal)
                    
                    /*
                     Button(action: {
                     self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                     }) {
                     GuestJiniSettingMenuItem(imageName: "gear", menuText: "Settings")
                     
                     }.padding(.horizontal)
                     */
                    
                    Button(action: {
                        self.viewRouter.currentPage = ViewRoutes.CHANGE_PASSWORD_PAGE
                    }) {
                        GuestJiniSettingMenuItem(imageName: "bag", menuText: "Change Password")
                        
                    }.padding(.horizontal)
                    
                    
                    Button(action: {
                        self.viewRouter.currentPage = ViewRoutes.HELP_VIEW
                    }) {
                        GuestJiniSettingMenuItem(imageName: "questionmark.circle", menuText: "Help")
                        
                    }.padding(.horizontal)
                    
                    
                    Button(action: {
                        UserDefaults.standard.set(false, forKey: "isLoggedIn")
                        UserDefaults.standard.set("", forKey: "access_token")
                        UserDefaults.standard.set("", forKey: "refresh_token")
                        UserDefaults.standard.set("", forKey: "token_type")
                        UserDefaults.standard.set("", forKey: "expires_in")
                        self.viewRouter.currentPage = ViewRoutes.LANDING_PAGE
                        
                    }) {
                        GuestJiniSettingMenuItem(imageName: "power", menuText: "Log out")
                        
                    }.padding(.horizontal)
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView(viewRouter: ViewRouter())
    }
}
