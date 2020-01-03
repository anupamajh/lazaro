//
//  GuestJiniBottomBar.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniBottomBar: View {
    @ObservedObject var viewRouter: ViewRouter
    
    var body: some View {
        HStack{
            Spacer()
            Group{
                Button(action: {
                    self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                }) {
                    GuestJiniBottomBarItem(systemImage: "headphones", menuText: "Support")
                    
                }.padding(.horizontal)
                
                Button(action: {
                    self.viewRouter.currentPage = ViewRoutes.SETTINGS_VIEW
                }) {
                    GuestJiniBottomBarItem(systemImage: "gear", menuText: "Settings")
                    
                }.padding(.horizontal)
                
            }
            /* Spacer()
             GuestJiniBottomBarItem(systemImage: "person.2", menuText: "Community")
             Spacer()
             GuestJiniBottomBarItem(systemImage: "doc.text", menuText: "Accounts")
             Spacer()
             GuestJiniBottomBarItem(systemImage: "star", menuText: "Rewards")
             Spacer()*/
            
            
            
            Spacer()
            
        }
    }
}

struct GuestJiniBottomBar_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniBottomBar(viewRouter: ViewRouter())
    }
}
