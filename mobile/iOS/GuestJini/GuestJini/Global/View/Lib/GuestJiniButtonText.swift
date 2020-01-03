//
//  GuestJiniButtonText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniButtonText: View {
    var buttonText: String
    var body: some View {
        VStack{
            Text(buttonText)
                .font(Fonts.RobotButtonFont)
                .padding(.top, 10)
                .padding(.bottom, 10)
                .padding(.leading, 20)
                .padding(.trailing, 20)
                .foregroundColor(Color.white)
            .background(Color("aquaMarine"))
            .cornerRadius(18)
        }.shadow(radius: 5)
        
        
    }
}

struct GuestJiniButton_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniButtonText(buttonText: "RESET PASSWORD")
    }
}
