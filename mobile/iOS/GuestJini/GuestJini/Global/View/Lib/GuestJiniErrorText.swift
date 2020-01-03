//
//  GuestJiniErrorText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniErrorText: View {
    var message: String
    var body: some View {
        VStack{
            Text(message)
                .font(Fonts.RobotRegular)
        }.padding(.all, 10)
        .background(Color("veryLightPinkTwo"))
    }
}

struct GuestJiniErrorText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniErrorText(message: "This email is not registered with GuestJini")
    }
}
