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
                    self.viewRouter.selectedBottomBarItem = BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_SUPPORT
                    self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                }) {
                    if(self.viewRouter.selectedBottomBarItem == BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_SUPPORT){
                        GuestJiniBottomBarItem(systemImage: "headphones", menuText: "Support",isSelected: 1)
                    }else{
                        GuestJiniBottomBarItem(systemImage: "headphones", menuText: "Support",isSelected: 0)
                        
                    }
                    
                }.padding(.horizontal)
                
                Button(action: {
                    self.viewRouter.selectedBottomBarItem = BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_COMMUNITY
                    self.viewRouter.currentPage = ViewRoutes.COMMUNIT_LANDING_PAGE
                }) {
                    if(self.viewRouter.selectedBottomBarItem == BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_COMMUNITY){
                        GuestJiniBottomBarItem(systemImage: "person.2", menuText: "Community",isSelected: 1)
                    }else{
                        GuestJiniBottomBarItem(systemImage: "person.2", menuText: "Community",isSelected: 0)
                        
                    }
                }.padding(.horizontal)
                Button(action: {
                    self.viewRouter.selectedBottomBarItem = BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_SETTINGS
                    self.viewRouter.currentPage = ViewRoutes.SETTINGS_VIEW
                }) {
                    if(self.viewRouter.selectedBottomBarItem == BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_SETTINGS){
                        GuestJiniBottomBarItem(systemImage: "gear", menuText: "Settings", isSelected: 1)
                    }else{
                        GuestJiniBottomBarItem(systemImage: "gear", menuText: "Settings", isSelected: 0)
                        
                    }
                    
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
