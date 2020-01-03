//
//  GuestJiniSubAction.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniSubAction: View {
    var actionText:String
    var systemImage: String
    
    var body: some View {
        HStack{
        Text(actionText)
            .font(Fonts.RobotRegularSmallText)
            .foregroundColor(Color("brownGrey"))
            Image(systemName: systemImage)
            .resizable()
                .frame(width: 8, height: 8, alignment: .center)
            .foregroundColor(Color("brownGrey"))
        }
        
    }
}

struct GuestJiniSubAction_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
    }
}
