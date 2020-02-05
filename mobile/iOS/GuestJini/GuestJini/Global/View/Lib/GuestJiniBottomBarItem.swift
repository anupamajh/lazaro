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
    var isSelected = 0
    var body: some View {
        VStack{
            if(isSelected == 0){
                Image(systemName: systemImage)
                    .foregroundColor(Color.black)
                Text(menuText)
                    .font(Fonts.RobotBottomBarText)
                    .foregroundColor(Color.black)
                    .lineLimit(1)
            }else{
                Image(systemName: systemImage)
                Text(menuText)
                    .font(Fonts.RobotBottomBarText)
                    .lineLimit(1)
            }
            
            
        }.padding()
    }
}

struct GuestJiniBottomBarItem_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniBottomBarItem(systemImage: "headphones", menuText: "Support")
    }
}
