//
//  GuestJiniTitleText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniTitleText: View {
    var title: String
    var body: some View {
        Group{
            Text(title).font(Fonts.RobotTitle).fontWeight(.heavy)
            
        }.padding(.all, 10)
            
        
    }
}

struct GuestJiniTitleText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniTitleText(title: "Forgot password?")
    }
}
