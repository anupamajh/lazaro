//
//  GuestJiniBottomBarItem.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniBottomBarItem: View {
    var systemImage: String
    var menuText:String
    var body: some View {
        VStack{
            Image(systemName: systemImage)
            Text(menuText)
                .font(Fonts.RobotBottomBarText)
            .lineLimit(1)
            
            
        }.padding()
    }
}

struct GuestJiniBottomBarItem_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniBottomBarItem(systemImage: "headphones", menuText: "Support")
    }
}
