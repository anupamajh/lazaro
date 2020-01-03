//
//  GuestJiniSettingMenuItem.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniSettingMenuItem: View {
    var imageName:String
    var menuText:String
    var body: some View {
        VStack{
            HStack{
                Image(systemName: imageName)
                    .padding()
                .foregroundColor(Color("greyishBrownTwo"))
                Text(menuText)
                    .font(Fonts.RobotRegularButton)
                .foregroundColor(Color("greyishBrownTwo"))
                Spacer()
            }
            Divider()
        }
    }
}

struct GuestJiniSettingMenuItem_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniSettingMenuItem(imageName: "person", menuText: "My Profile")
    }
}
